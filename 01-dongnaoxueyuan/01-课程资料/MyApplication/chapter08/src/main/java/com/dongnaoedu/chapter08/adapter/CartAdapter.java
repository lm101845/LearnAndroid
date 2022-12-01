package com.dongnaoedu.chapter08.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongnaoedu.chapter08.R;
import com.dongnaoedu.chapter08.entity.CartInfo;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context mContext;
    private List<CartInfo> mCartList;

    public CartAdapter(Context mContext, List<CartInfo> mCartList) {
        this.mContext = mContext;
        this.mCartList = mCartList;
    }

    @Override
    public int getCount() {
        return mCartList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            // 获取布局文件item_cart.xml的根视图
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cart, null);
            holder.iv_thumb = convertView.findViewById(R.id.iv_thumb);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_desc = convertView.findViewById(R.id.tv_desc);
            holder.tv_count = convertView.findViewById(R.id.tv_count);
            holder.tv_price = convertView.findViewById(R.id.tv_price);
            holder.tv_sum = convertView.findViewById(R.id.tv_sum);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartInfo info = mCartList.get(position);
        holder.iv_thumb.setImageURI(Uri.parse(info.goods.picPath));
        holder.tv_name.setText(info.goods.name);
        holder.tv_desc.setText(info.goods.description);
        holder.tv_count.setText(String.valueOf(info.count));
        holder.tv_price.setText(String.valueOf((int) info.goods.price));
        // 设置商品总价
        holder.tv_sum.setText(String.valueOf((int) (info.count * info.goods.price)));
        return convertView;
    }

    public final class ViewHolder {
        public ImageView iv_thumb;
        public TextView tv_name;
        public TextView tv_desc;
        public TextView tv_count;
        public TextView tv_price;
        public TextView tv_sum;

    }
}
