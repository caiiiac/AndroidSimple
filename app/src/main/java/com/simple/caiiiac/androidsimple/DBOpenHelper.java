package com.simple.caiiiac.androidsimple;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBOpenHelper extends SQLiteOpenHelper implements BaseColumns {

    // 数据库名
    public static final String DB_NAME = "contact.db";

    // 版本
    public static final int DB_VERSION = 1;

    // 表内容
    public static final String TABLE_NAME = "contactinfo";
    public static final String _USERNAME = "username";
    public static final String _PHONE = "phone";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + _ID + " integer primary key autoincrement," + _USERNAME + " text," + _PHONE + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
