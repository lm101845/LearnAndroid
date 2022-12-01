package com.dongnaoedu.chapter08.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dongnaoedu.chapter08.R;


public class StaticFragment extends Fragment {

    private static final String TAG = "fragment";


    // 把碎片贴到页面上
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "Fragment onAttach");
    }

    // 页面创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Fragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Fragment onCreateView");
        return inflater.inflate(R.layout.fragment_static, container, false);
    }

    //在活动页面创建之后
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Fragment onActivityCreated");
    }


    // 页面启动
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Fragment onStart");
    }

    // 页面恢复
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Fragment onResume");
    }

    // 页面暂停
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Fragment onPause");
    }

    // 页面停止
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Fragment onStop");
    }

    // 销毁碎片视图
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "Fragment onDestroyView");
    }

    // 页面销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment onDestroy");
    }

    // 把碎片从页面撕下来
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "Fragment onDetach");
    }
}