package com.ronjit.banglaenglishdictionary.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ronjit.banglaenglishdictionary.MainActivity;
import com.ronjit.banglaenglishdictionary.R;

public class BookmarkFragment extends Fragment {

    TextView bookMark_bangla, bookMark_english;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_bookmark, container, false);
//        listView = myView.findViewById(R.id.listView);
        bookMark_bangla = myView.findViewById(R.id.bookMark_bangla);
        bookMark_english = myView.findViewById(R.id.bookMark_english);

        bookMark_bangla.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MainActivity.class);
            intent.putExtra("open_fragment_bangla", "bookmark_fragment_bangla");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
        bookMark_english.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MainActivity.class);
            intent.putExtra("open_fragment_english", "bookmark_fragment_english");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        return myView;
    } // oncreate ends----------------


} // public class end ----------------