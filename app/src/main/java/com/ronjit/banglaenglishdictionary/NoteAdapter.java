package com.ronjit.banglaenglishdictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ronjit.banglaenglishdictionary.database.Notebook_Database;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final List<NoteItem> noteList;
    private final Context context;
    private final Notebook_Database dbHelper;
    private int selectedPosition = -1;

    public NoteAdapter(Context context, List<NoteItem> noteList, Notebook_Database dbHelper) {
        this.context = context;
        this.noteList = noteList;
        this.dbHelper = dbHelper;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView wordText, noteText;
        ImageButton deleteBtn;
        RelativeLayout noteBook_item;

        public ViewHolder(View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.wordText);
            noteText = itemView.findViewById(R.id.noteText);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            noteBook_item = itemView.findViewById(R.id.noteBook_item);
        }
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        NoteItem item = noteList.get(position);

        holder.wordText.setText(item.getWord());
        holder.noteText.setText(item.getNote());

        // Show or hide delete button based on selection
        holder.deleteBtn.setVisibility(position == selectedPosition ? View.VISIBLE : View.GONE);

        // Long click to show delete button
        holder.noteBook_item.setOnLongClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            return true;
        });

        // Single click to deselect (hide delete)
        holder.noteBook_item.setOnClickListener(v -> {
            if (selectedPosition != -1 && selectedPosition != position) {
                selectedPosition = -1;
                notifyDataSetChanged();
            }
        });

        // Delete item
        holder.deleteBtn.setOnClickListener(v -> {
            dbHelper.deleteNote(item.getId());
            noteList.remove(position);
            notifyItemRemoved(position);
            selectedPosition = -1;
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
