package com.ronjit.banglaenglishdictionary.book_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class PhoneWord_Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "phone_call_word.db";
    private static final int DB_VERSION = 1;
    public PhoneWord_Database(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //=========================================================
    public Cursor getAllData (){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Phone_Word",null);
        return cursor;
    }
}
