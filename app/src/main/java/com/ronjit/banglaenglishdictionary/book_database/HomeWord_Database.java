package com.ronjit.banglaenglishdictionary.book_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class HomeWord_Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "home_word.db";
    private static final int DB_VERSION = 1;
    public HomeWord_Database(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //======================================
    public Cursor getAllData (){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Home_Word",null);
        return cursor;
    }
}
