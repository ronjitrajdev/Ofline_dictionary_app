package com.ronjit.banglaenglishdictionary.book_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class OfficeWord_database extends SQLiteAssetHelper {
    private static final String DB_NAME = "office_word.db";
    private static final int DB_VERSION = 1;
    public OfficeWord_database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //==========================================
    public Cursor getAllData (){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Office_Word", null);
        return cursor;
    }
}
