package com.manish.obaa.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "obaaa";
    public static final String TABLE_ADDRESS = "addresss";
    private static final int DATABASE_VERSION = 1;

    //For Address.

    private static final String KEY_ID_ADDRESS = "id_address";
    private static final String KEY_NAME = "name";
    private static final String KEY_CONTACT_NO = "contact_no";
    private static final String KEY_HOUSE_NO = "house_no";
    private static final String KEY_COLONY = "colony";
    private static final String KEY_CITY = "city";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ADDRESS = "CREATE TABLE IF NOT EXISTS "
                + TABLE_ADDRESS + "("
                + KEY_ID_ADDRESS + " INTEGER PRIMARY KEY," //0
                + KEY_NAME + " TEXT,"
                + KEY_CONTACT_NO + " TEXT,"
                + KEY_HOUSE_NO + " TEXT," //1
                + KEY_COLONY + " TEXT," //2
                + KEY_CITY + " TEXT" //3
                + ")";
        db.execSQL(CREATE_TABLE_ADDRESS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addAddress(String name,String contact,String houseNo, String colony, String city) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_CONTACT_NO,contact);
        values.put(KEY_HOUSE_NO, houseNo);
        values.put(KEY_COLONY, colony);
        values.put(KEY_CITY, city);

        long rowInserted = database.insert(TABLE_ADDRESS, null, values);

        return rowInserted != -1;

    }

    public Cursor getAddressData() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ADDRESS, null);
    }


    public boolean isEmpty(String table) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT count(*) FROM " + table;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        return count <= 0;
    }

    public void deleteAddress(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ADDRESS, KEY_ID_ADDRESS + " = " + id, null);
        db.close();
    }

    public void updateAddress(String id, String houseNo, String colony, String pinCode, String city) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOUSE_NO, houseNo);
        values.put(KEY_COLONY, colony);
        values.put(KEY_CITY, city);

        database.update(TABLE_ADDRESS, values, KEY_ID_ADDRESS + " = " + id, null);
    }


    public void clearTable(String table) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + table);
        db.close();
    }
}