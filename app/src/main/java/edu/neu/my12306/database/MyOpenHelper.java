package edu.neu.my12306.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    /*------构造函数的参数只需要传一个context--------*/
    public MyOpenHelper(Context context) {
        super( context, "my12306.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_account = "create table account(id Integer primary key autoincrement," +
                "username text,name text,certificateType text,certificateNumber text,passengerType" +
                " text, phoneNumber text,password text)";
        db.execSQL(sql_account);
        db.execSQL("insert into account values(?,?,?,?,?,?,?,?)",
                new Object[]{1,"1","1","身份证","123456789","普通乘客","18602497727","1"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
