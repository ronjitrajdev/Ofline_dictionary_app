package com.ronjit.banglaenglishdictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ronjit.banglaenglishdictionary.NoteItem;

import java.util.ArrayList;
import java.util.List;


public class Notebook_Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "notebook_words";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WORD = "word";
    private static final String COLUMN_NOTE = "note";
    public Notebook_Database( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WORD + " TEXT NOT NULL, " +
                COLUMN_NOTE + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //=====================================================
    public void addNote(String word, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD, word);
        values.put(COLUMN_NOTE, note);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    //===========================================================
    public List<NoteItem> getAllNotes() {
        List<NoteItem> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC", null);

        if (cursor.moveToFirst()) {
            do {
                NoteItem item = new NoteItem(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                noteList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }
    //================================================
    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    //======================================

}
