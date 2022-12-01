package com.dongnaoedu.chapter08;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dongnaoedu.chapter08.adapter.CartAdapter;
import com.dongnaoedu.chapter08.database.ShoppingDBHelper;
import com.dongnaoedu.chapter08.entity.CartInfo;
import com.dongnaoedu.chapter08.entity.GoodsInfo;
import com.dongnaoedu.chapter08.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private TextView tv_count;
    private ListView lv_cart;
    private ShoppingDBHelper mDBHelper;

    // 声明一个购物车中的商品信息列表
    private List<CartInfo> mCartList;
    // 声明一个根据商品编号查找商品信息的映射，把商品信息缓存起来，这样不用每一次都去查询数据库
    private Map<Integer, GoodsInfo> mGoodsMap = new HashMap<>();
    private TextView tv_total_price;
    private LinearLayout ll_empty;
    private LinearLayout ll_content;
    private CartAdapter mCartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("购物车");
        lv_cart = findViewById(R.id.lv_cart);
        tv_total_price = findViewById(R.id.tv_total_price);

        tv_count = findViewById(R.id.tv_count);
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));

        mDBHelper = ShoppingDBHelper.getInstance(this);

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.btn_shopping_channel).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_settle).setOnClickListener(this);
        ll_empty = findViewById(R.id.ll_empty);
        ll_content = findViewById(R.id.ll_content);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCart();
    }

    // 展示购物车中的商品列表
    private void showCart() {
        // 查询购物车数据库中所有的商品记录
        mCartList = mDBHelper.queryAllCartInfo();
        if (mCartList.size() == 0) {
            return;
        }
        for (CartInfo info : mCartList) {
            // 根据商品编号查询商品数据库中的商品记录
            GoodsInfo goods = mDBHelper.queryGoodsInfoById(info.goodsId);
            mGoodsMap.put(info.goodsId, goods);
            info.goods = goods;
        }
        mCartAdapter = new CartAdapter(this, mCartList);
        lv_cart.setAdapter(mCartAdapter);
        // 给商品行添加点击事件。点击商品行跳到商品的详情页
        lv_cart.setOnItemClickListener(this);
        // 给商品行添加长按事件。长按商品行就删除该商品
        lv_cart.setOnItemLongClickListener(this);

        // 重新计算购物车中的商品总金额
        refreshTotalPrice();
    }

    private void deleteGoods(CartInfo info) {
        MyApplication.getInstance().goodsCount -= info.count;
        // 从购物车的数据库中删除商品
        mDBHelper.deleteCartInfoByGoodsId(info.goodsId);
        // 从购物车的列表中删除商品
        CartInfo removed = null;
        for (CartInfo cartInfo : mCartList) {
            if (cartInfo.goodsId == info.goodsId) {
                removed = cartInfo;
                break;
            }
        }
        mCartList.remove(removed);
        // 显示最新的商品数量
        showCount();
        ToastUtil.show(this, "已从购物车删除" + mGoodsMap.get(info.goodsId).name);
        mGoodsMap.remove(info.goodsId);
        // 刷新购物车中所有商品的总金额
        refreshTotalPrice();
    }

    // 显示购物车图标中的商品数量
    private void showCount() {
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));
        // 购物车中没有商品，显示“空空如也”
        if (MyApplication.getInstance().goodsCount == 0) {
            ll_empty.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
            // 通知适配器发生了数据变化
            mCartAdapter.notifyDataSetChanged();
        } else {
            ll_content.setVisibility(View.VISIBLE);
            ll_empty.setVisibility(View.GONE);
        }
    }

    // 重新计算购物车中的商品总金额
    private void refreshTotalPrice() {
        int totalPrice = 0;
        for (CartInfo info : mCartList) {
            GoodsInfo goods = mGoodsMap.get(info.goodsId);
            totalPrice += goods.price * info.count;
        }
        tv_total_price.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                // 点击了返回图标
                // 关闭当前页面
                finish();
                break;

            case R.id.btn_shopping_channel:
                // 从购物车页面跳到商场页面
                Intent intent = new Intent(this, ShoppingChannelActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case R.id.btn_clear:
                // 清空购物车数据库
                mDBHelper.deleteAllCartInfo();
                MyApplication.getInstance().goodsCount = 0;
                // 显示最新的商品数量
                showCount();
                ToastUtil.show(this, "购物车已清空");
                break;

            case R.id.btn_settle:
                // 点击了“结算”按钮
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("结算商品");
                builder.setMessage("客官抱歉，支付功能尚未开通，请下次再来");
                builder.setPositiveButton("我知道了", null);
                builder.create().show();
                break;
        }
    }

    // 给商品行添加点击事件。点击商品行跳到商品的详情页
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ShoppingCartActivity.this, ShoppingDetailActivity.class);
        intent.putExtra("goods_id", mCartList.get(position).goodsId);
        startActivity(intent);
    }

    // 给商品行添加长按事件。长按商品行就删除该商品
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        CartInfo info = mCartList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
        builder.setMessage("是否从购物车删除" + info.goods.name + "？");
        builder.setPositiveButton("是", (dialog, which) -> {
            // 从集合中移除数据
            mCartList.remove(position);
            // 通知适配器发生了数据变化
            mCartAdapter.notifyDataSetChanged();
            // 删除该商品
            deleteGoods(info);
        });
        builder.setNegativeButton("否", null);
        builder.create().show();
        return true;
    }
}