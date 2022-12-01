package com.dongnaoedu.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioHorizontalActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_horizontal);
        RadioGroup rb_gender = findViewById(R.id.rg_gender);
        tv_result = findViewById(R.id.tv_result);
        rb_gender.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_male:
                tv_result.setText("哇哦，你是个帅气的男孩");
                break;
            case R.id.rb_female:
                tv_result.setText("哇哦，你是个漂亮的女孩");
                break;
        }
    }
}