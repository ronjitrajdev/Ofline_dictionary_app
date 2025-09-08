package com.ronjit.banglaenglishdictionary.book_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Day_1_Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "day_1.db";
    private static final int DB_VERSION = 1;
    public Day_1_Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //======================================
    public Cursor getVocabulary(int day){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM vocabulary WHERE day=?", new String[]{String.valueOf(day)});
    }

    public Cursor getSentence(int day){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM sentence WHERE day=?", new String[]{String.valueOf(day)});
    }

    public Cursor getDialogue(int day){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM dialogue WHERE day=?", new String[]{String.valueOf(day)});
    }

    public Cursor getPractice(int day){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM practice WHERE day=?", new String[]{String.valueOf(day)});
    }


}
