package edu.neu.my12306.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.neu.my12306.entity.Account;

public class UserDao {
    private MyOpenHelper helper;
    private Context context;

    public UserDao(Context context) {
        this.context = context;
        helper = new MyOpenHelper(context);
    }
    public boolean checkLogin(String username,String password){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from account where username=? and password=?";
        Cursor cursor = db.rawQuery(sql,new String[]{username,password});
        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public boolean register(Account account){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "insert into account values(null,?,?,?,?,?,?,?)";
        Object[] obj ={account.getUsername(),
                account.getName(),account.getCertificateType(),
                account.getCertificateNumber(),account.getPassengerType(),
                account.getPhoneNumber(),account.getPassword()};
        db.execSQL(sql,obj);
        db.close();
        return true;
    }

    public boolean queryByName(String username){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from account where username=?";
        Cursor cursor = db.rawQuery(sql,new String[]{username});
        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public Account queryAccountByName(String username){
        Account account = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from account where username=?";
        Cursor cursor = db.rawQuery(sql,new String[]{username});
        while(cursor.moveToNext()){
            account = new Account();
            account.setUsername(cursor.getString(1));
            account.setName(cursor.getString(2));
            account.setCertificateType(cursor.getString(3));
            account.setCertificateNumber(cursor.getString(4));
            account.setPassengerType(cursor.getString(5));
            account.setPhoneNumber(cursor.getString(6));
            account.setPassword(cursor.getString(7));
        }
        return account;

    }
    public void changeAccount(Account account) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "update account set name=?,certificateType=?,certificateNumber=?,passengerType=?,phoneNumber=? where username=?";
        db.execSQL(sql,new String[]{account.getName(),account.getCertificateType(),
                account.getCertificateNumber(),account.getPassengerType(),account.getPhoneNumber(),account.getUsername()});
    }
}
