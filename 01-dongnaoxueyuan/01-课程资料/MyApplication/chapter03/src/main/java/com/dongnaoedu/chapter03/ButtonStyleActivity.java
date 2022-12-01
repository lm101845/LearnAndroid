package com.dongnaoedu.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dongnaoedu.chapter03.util.DateUtil;

public class ButtonStyleActivity extends AppCompatActivity {

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        tv_result = findViewById(R.id.tv_result);
    }

    public void doClick(View view) {
        String desc = String.format("%s 您点击了按钮： %s", DateUtil.getNowTime(), ((Button) view).getText());
        tv_result.setText(desc);
    }
}