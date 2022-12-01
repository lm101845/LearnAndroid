package com.dongnaoedu.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dongnaoedu.chapter06.dao.BookDao;
import com.dongnaoedu.chapter06.enity.BookInfo;
import com.dongnaoedu.chapter06.util.ToastUtil;

import java.util.List;

public class RoomWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_author;
    private EditText et_press;
    private EditText et_price;
    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_write);

        et_name = findViewById(R.id.et_name);
        et_author = findViewById(R.id.et_author);
        et_press = findViewById(R.id.et_press);
        et_price = findViewById(R.id.et_price);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

        // 从App实例中获取唯一的书籍持久化对象
        bookDao = MyApplication.getInstance().getBookDB().bookDao();
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String author = et_author.getText().toString();
        String press = et_press.getText().toString();
        String price = et_price.getText().toString();

        switch (v.getId()) {
            case R.id.btn_save:
                // 以下声明一个书籍信息对象，并填写它的各字段值
                BookInfo b1 = new BookInfo();
                b1.setName(name);
                b1.setAuthor(author);
                b1.setPress(press);
                b1.setPrice(Double.parseDouble(price));
                bookDao.insert(b1);
                ToastUtil.show(this, "保存成功");
                break;
            case R.id.btn_delete:
                BookInfo b2 = new BookInfo();
                b2.setId(1);
                bookDao.delete(b2);
                break;

            case R.id.btn_update:
                BookInfo b3 = new BookInfo();
                // 根据名字查询到数据库中已有的记录
                BookInfo b4 = bookDao.queryByName(name);
                b3.setId(b4.getId());
                b3.setName(name);
                b3.setAuthor(author);
                b3.setPress(press);
                b3.setPrice(Double.parseDouble(price));
                bookDao.update(b3);
                break;

            case R.id.btn_query:
                List<BookInfo> list = bookDao.queryAll();
                for (BookInfo b : list) {
                    Log.d("ning", b.toString());
                }
                break;
        }
    }
}