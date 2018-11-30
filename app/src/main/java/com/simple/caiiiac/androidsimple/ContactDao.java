package com.simple.caiiiac.androidsimple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactDao {
    private DBOpenHelper mHelper;

    public ContactDao(Context c) {
        mHelper = new DBOpenHelper(c);
    }


    // 插入数据
    public boolean insertContact(String username, String phone) {
        SQLiteDatabase database = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBOpenHelper._USERNAME, username);
        values.put(DBOpenHelper._PHONE, phone);

        // 返回值: 新插入数据的id,-1说明插入失败
        long rowID = database.insert(DBOpenHelper.TABLE_NAME, null, values);
        return rowID != -1;
    }

    // 更新手机号
    public boolean updateContact(String username, String newPhone) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper._PHONE, newPhone);

        // 返回值: 更新了多少行数据
        int updateRows = database.update(DBOpenHelper.TABLE_NAME, values,
                DBOpenHelper._USERNAME+"=?", new String[]{username});
        return updateRows > 0;
    }

    // 删除数据
    public boolean deleteContact(String username) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        int deleteRows = database.delete(DBOpenHelper.TABLE_NAME,"username=?",new String[]{username});
        return deleteRows > 0;
    }

    // 查询
    public String queryContact(String searchPhone) {
        String result = "";

        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME, new String[]{DBOpenHelper._USERNAME, DBOpenHelper._PHONE},
                "phone=?", new String[]{searchPhone}, null, null, null);
        while (cursor.moveToNext()) {
            // 传入列名, 返回索引
            int usernameIndex = cursor.getColumnIndex(DBOpenHelper._USERNAME);
            // 传入列的索引,返回该列下某一行的值
            String username = cursor.getString(usernameIndex);

            int phoneIndex = cursor.getColumnIndex(DBOpenHelper._PHONE);
            String phone = cursor.getString(phoneIndex);

            result += (username + ": " + phone + "\n");
        }
        return result;
    }
}

