package com.dongnaoedu.chapter08.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongnaoedu.chapter08.R;
import com.dongnaoedu.chapter08.ShoppingChannelActivity;
import com.dongnaoedu.chapter08.ShoppingDetailActivity;
import com.dongnaoedu.chapter08.entity.GoodsInfo;

import java.util.List;

public class GoodsAdapter extends BaseAdapter {

    private Context mContext;
    private List<GoodsInfo> mGoodsInfo;

    public GoodsAdapter(Context mContext, List<GoodsInfo> mGoodsInfo, AddCartListener mAddCartListener) {
        this.mContext = mContext;
        this.mGoodsInfo = mGoodsInfo;
        this.mAddCartListener = mAddCartListener;
    }

    @Override
    public int getCount() {
        return mGoodsInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodsInfo info = mGoodsInfo.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods, null);
            holder = new ViewHolder();
            holder.iv_thumb = convertView.findViewById(R.id.iv_thumb);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_price = convertView.findViewById(R.id.tv_price);
            holder.btn_add = convertView.findViewById(R.id.btn_add);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        // 给控件设置值
        holder.iv_thumb.setImageURI(Uri.parse(info.picPath));
        holder.tv_name.setText(info.name);
        holder.tv_price.setText(String.valueOf((int) info.price));

        // 添加到购物车
        holder.btn_add.setOnClickListener(v -> {
            //ShoppingChannelActivity activity = (ShoppingChannelActivity) mContext;
            //activity.addToCart(info.id, info.name);
            mAddCartListener.addToCart(info.id, info.name);
        });

        // 点击商品图片，跳转到商品详情页面
        holder.iv_thumb.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ShoppingDetailActivity.class);
            intent.putExtra("goods_id", info.id);
            mContext.startActivity(intent);
        });

        return convertView;
    }

    public final class ViewHolder {
        public ImageView iv_thumb;
        public TextView tv_name;
        public TextView tv_price;
        public Button btn_add;
    }

    // 声明一个加入购物车的监听器对象
    private AddCartListener mAddCartListener;

    // 定义一个加入购物车的监听器接口
    public interface AddCartListener {
        void addToCart(int goodsId, String goodsName);
    }
}
