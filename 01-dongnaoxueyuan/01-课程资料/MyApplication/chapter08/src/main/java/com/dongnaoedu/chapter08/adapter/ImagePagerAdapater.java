package com.dongnaoedu.chapter08.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.dongnaoedu.chapter08.ViewPagerActivity;
import com.dongnaoedu.chapter08.entity.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapater extends PagerAdapter {

    private final Context mContext;
    private final ArrayList<GoodsInfo> mGoodsList;
    // 声明一个图像视图列表
    private List<ImageView> mViewList = new ArrayList<>();

    public ImagePagerAdapater(Context mContext, ArrayList<GoodsInfo> mGoodsList) {
        this.mContext = mContext;
        this.mGoodsList = mGoodsList;
        // 给每个商品分配一个专用的图像视图
        for (GoodsInfo info : mGoodsList) {
            ImageView view = new ImageView(mContext);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            view.setImageResource(info.pic);
            mViewList.add(view);
        }
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    // 实例化指定位置的页面，并将其添加到容器中
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 添加一个view到container中，而后返回一个跟这个view可以关联起来的对象，
        // 这个对象能够是view自身，也能够是其余对象，
        // 关键是在isViewFromObject可以将view和这个object关联起来
        ImageView item = mViewList.get(position);
        container.addView(item);
        return item;
    }

    // 从容器中销毁指定位置的页面
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGoodsList.get(position).name;
    }
}
