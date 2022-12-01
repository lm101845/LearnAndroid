package com.dongnaoedu.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dongnaoedu.chapter06.util.FileUtil;
import com.dongnaoedu.chapter06.util.ToastUtil;

import java.io.File;

public class FileWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private CheckBox ck_married;
    private String path;
    private TextView tv_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);
        tv_txt = findViewById(R.id.tv_txt);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                String name = et_name.getText().toString();
                String age = et_age.getText().toString();
                String height = et_height.getText().toString();
                String weight = et_weight.getText().toString();

                StringBuilder sb = new StringBuilder();
                sb.append("姓名:").append(name);
                sb.append("\n年龄:").append(age);
                sb.append("\n身高:").append(height);
                sb.append("\n体重:").append(weight);
                sb.append("\n婚否:").append(ck_married.isChecked() ? "是" : "否");

                String fileName = System.currentTimeMillis() + ".txt";
                String directory = null;
                // 外部存储的私有空间
                directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
                // 外部存储的公共空间
                //directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

                // 内部存储私有空间
                directory = getFilesDir().toString();
                path = directory + File.separatorChar + fileName;
                Log.d("ning", path);
                FileUtil.saveText(path, sb.toString());
                ToastUtil.show(this, "保存成功");
                break;
            case R.id.btn_read:
                tv_txt.setText(FileUtil.openText(path));
                break;
        }
    }
}