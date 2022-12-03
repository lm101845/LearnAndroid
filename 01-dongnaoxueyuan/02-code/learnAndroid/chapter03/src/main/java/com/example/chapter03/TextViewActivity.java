package com.example.chapter03;

import android.widget.TextView;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author liming
 * @date 2022/12/3 11:39
 */
public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        TextView tv_hello = findViewById(R.id.tv_hello);
//        tv_hello.setText("你好，世界!")  //不要用硬编码
        tv_hello.setText(R.string.hello);
    }
}
