package com.dongnaoedu.chapter09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ChangeDirectionActivity extends AppCompatActivity {

    private TextView tv_monitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_direction);
        tv_monitor = findViewById(R.id.tv_monitor);
        Log.d("ning","onCreate");
    }

    // 在配置项变更时触发。比如屏幕方向发生变更等等
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation){
            case Configuration.ORIENTATION_PORTRAIT:
                tv_monitor.setText("当前屏幕为竖屏方向");
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                tv_monitor.setText("当前屏幕为横屏方向");
                break;
            default:
                break;
        }
    }
}