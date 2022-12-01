package com.dongnaoedu.chapter05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        findViewById(R.id.btn_alert).setOnClickListener(this);
        tv_alert = findViewById(R.id.tv_alert);
    }

    @Override
    public void onClick(View v) {
        // 创建提醒对话框的建造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置对话框的标题文本
        builder.setTitle("尊敬的用户");
        // 设置对话框的内容文本
        builder.setMessage("你真的要卸载我吗？");
        // 设置对话框的肯定按钮文本及其点击监听器
        builder.setPositiveButton("残忍卸载", (dialog, which) -> {
            tv_alert.setText("虽然依依不舍，但是只能离开了");
        });
        // 设置对话框的否定按钮文本及其点击监听器
        builder.setNegativeButton("我再想想", (dialog, which) -> {
            tv_alert.setText("让我再陪你三百六十五个日夜");
        });

        // 根据建造器构建提醒对话框对象
        AlertDialog dialog = builder.create();
        // 显示提醒对话框
        dialog.show();
    }
}