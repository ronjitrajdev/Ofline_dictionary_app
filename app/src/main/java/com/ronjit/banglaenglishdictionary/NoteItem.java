package com.ronjit.banglaenglishdictionary;

public class NoteItem {
    private int id;
    private String word;
    private String note;

    public NoteItem(int id, String word, String note) {
        this.id = id;
        this.word = word;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getNote() {
        return note;
    }
}

