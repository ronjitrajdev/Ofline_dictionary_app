package com.ronjit.banglaenglishdictionary.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ronjit.banglaenglishdictionary.NoteAdapter;
import com.ronjit.banglaenglishdictionary.NoteItem;
import com.ronjit.banglaenglishdictionary.R;
import com.ronjit.banglaenglishdictionary.database.Notebook_Database;

import java.util.List;


public class Note_Book extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddNote;
    private Notebook_Database dbHelper;
    private NoteAdapter adapter;
    private List<NoteItem> noteList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View myView = inflater.inflate(R.layout.notebook_fragment, container, false);
        recyclerView = myView.findViewById(R.id.recyclerNote);
        fabAddNote = myView.findViewById(R.id.fabAddNote);
        dbHelper = new Notebook_Database(requireContext());

        noteList = dbHelper.getAllNotes();
        adapter = new NoteAdapter(getContext(), noteList, dbHelper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        fabAddNote.setOnClickListener(v -> showAddNoteDialog());



        return myView;
    } // onCreate end--------------

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Note");

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_note, null, false);
        final EditText inputWord = viewInflated.findViewById(R.id.inputWord);
        final EditText inputNote = viewInflated.findViewById(R.id.inputNote);

        builder.setView(viewInflated);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String word = inputWord.getText().toString().trim();
            String note = inputNote.getText().toString().trim();

            if (!word.isEmpty()) {
                dbHelper.addNote(word, note);
                noteList.clear();
                noteList.addAll(dbHelper.getAllNotes());
                adapter.notifyItemInserted(noteList.size() - 1);
            } else {
                Toast.makeText(getContext(), "Word can't be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
    //==========================================



} // public class end -------------