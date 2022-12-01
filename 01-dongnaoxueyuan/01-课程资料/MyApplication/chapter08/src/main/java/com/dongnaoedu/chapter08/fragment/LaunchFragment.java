package com.dongnaoedu.chapter08.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dongnaoedu.chapter08.R;
import com.dongnaoedu.chapter08.util.ToastUtil;

public class LaunchFragment extends Fragment {

    public static LaunchFragment newInstance(int count, int position, int image_id) {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        args.putInt("count", count);
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        Bundle arguments = getArguments();
        int count = arguments.getInt("count", 0);
        int position = arguments.getInt("position", 0);
        int imageId = arguments.getInt("image_id", 0);

        View view = LayoutInflater.from(context).inflate(R.layout.item_launch, container, false);
        ImageView iv_launch = view.findViewById(R.id.iv_launch);
        RadioGroup rg_indicate = view.findViewById(R.id.rg_indicate);
        Button btn_start = view.findViewById(R.id.btn_start);
        iv_launch.setImageResource(imageId);

        // 每个页面都分配一组对应的单选按钮
        for (int j = 0; j < count; j++) {
            RadioButton radio = new RadioButton(context);
            radio.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            radio.setPadding(10,10,10,10);
            rg_indicate.addView(radio);
        }

        // 当前位置的单选按钮要高亮显示，比如第二个引导页就高亮第二个单选按钮
        ((RadioButton)rg_indicate.getChildAt(position)).setChecked(true);

        // 如果是最后一个引导页，则显示入口按钮，以便用户点击按钮进入主页
        if (position == count - 1){
            btn_start.setVisibility(View.VISIBLE);
            btn_start.setOnClickListener(v -> {
                ToastUtil.show(context, "欢迎您开启美好生活");
            });
        }

        return view;
    }
}