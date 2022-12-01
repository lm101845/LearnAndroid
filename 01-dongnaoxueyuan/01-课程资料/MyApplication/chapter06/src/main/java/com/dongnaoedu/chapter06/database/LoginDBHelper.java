package com.dongnaoedu.chapter06.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dongnaoedu.chapter06.enity.LoginInfo;
import com.dongnaoedu.chapter06.enity.User;

import java.util.ArrayList;
import java.util.List;

public class LoginDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "login.db";
    private static final String TABLE_NAME = "login_info";
    private static final int DB_VERSION = 1;
    private static LoginDBHelper mHelper = null;
    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;

    private LoginDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // 利用单例模式获取数据库帮助器的唯一实例
    public static LoginDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new LoginDBHelper(context);
        }
        return mHelper;
    }

    // 打开数据库的读连接
    public SQLiteDatabase openReadLink() {
        if (mRDB == null || !mRDB.isOpen()) {
            mRDB = mHelper.getReadableDatabase();
        }
        return mRDB;
    }

    // 打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
        if (mWDB == null || !mWDB.isOpen()) {
            mWDB = mHelper.getWritableDatabase();
        }
        return mWDB;
    }

    // 关闭数据库连接
    public void closeLink() {
        if (mRDB != null && mRDB.isOpen()) {
            mRDB.close();
            mRDB = null;
        }

        if (mWDB != null && mWDB.isOpen()) {
            mWDB.close();
            mWDB = null;
        }
    }

    // 创建数据库，执行建表语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " phone VARCHAR NOT NULL," +
                " password VARCHAR NOT NULL," +
                " remember INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(LoginInfo info) {
        // 如果存在则先删除，再添加
        try {
            mWDB.beginTransaction();
            delete(info);
            insert(info);
            mWDB.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mWDB.endTransaction();
        }
    }

    public long delete(LoginInfo info) {
        return mWDB.delete(TABLE_NAME, "phone=?", new String[]{info.phone});
    }

    public long insert(LoginInfo info) {
        ContentValues values = new ContentValues();
        values.put("phone", info.phone);
        values.put("password", info.password);
        values.put("remember", info.remember);
        return mWDB.insert(TABLE_NAME, null, values);
    }

    public LoginInfo queryTop() {
        LoginInfo info = null;
        String sql = "select * from " + TABLE_NAME + " where remember = 1 ORDER BY _id DESC limit 1";
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            info = new LoginInfo();
            info.id = cursor.getInt(0);
            info.phone = cursor.getString(1);
            info.password = cursor.getString(2);
            info.remember = (cursor.getInt(3) == 0) ? false : true;
        }
        return info;
    }

    public LoginInfo queryByPhone(String phone) {
        LoginInfo info = null;
        String sql = "select * from " + TABLE_NAME;
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.query(TABLE_NAME, null, "phone=? and remember=1", new String[]{phone}, null, null, null);
        if (cursor.moveToNext()) {
            info = new LoginInfo();
            info.id = cursor.getInt(0);
            info.phone = cursor.getString(1);
            info.password = cursor.getString(2);
            info.remember = (cursor.getInt(3) == 0) ? false : true;
        }
        return info;
    }
}
