package com.ronjit.banglaenglishdictionary.book_fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.ronjit.banglaenglishdictionary.R;
import com.ronjit.banglaenglishdictionary.book_database.HomeWord_Database;

import java.util.ArrayList;
import java.util.HashMap;


public class Home_Word extends Fragment {
    private RecyclerView recyClerView_dailyHome;
    private myWordAdapter adapter;
    ArrayList<HashMap<String,String> > arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;
    private HomeWord_Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home__word, container, false);
        recyClerView_dailyHome = myView.findViewById(R.id.recyClerView_dailyHome);

        adapter = new myWordAdapter();
        recyClerView_dailyHome.setAdapter(adapter);
        recyClerView_dailyHome.setLayoutManager(new LinearLayoutManager(requireContext()));
        database = new HomeWord_Database(requireContext());
        loadEnglishData(database.getAllData());
        return myView;
    } //=======================
    private class myWordAdapter extends RecyclerView.Adapter<myWordAdapter.myHolder>{
        private class myHolder extends RecyclerView.ViewHolder{
            TextView word_Text;
            public myHolder(@NonNull View itemView) {
                super(itemView);
                word_Text = itemView.findViewById(R.id.word_Text);
            }
        }
        @NonNull
        @Override
        public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.word_item,parent,false);

            return new myHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull myHolder holder, int position) {
            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String daily_word = hashMap.get("home_word");
            holder.word_Text.setText(daily_word);

            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom);
            holder.itemView.startAnimation(animation);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }
    //==================================
    private void loadEnglishData(Cursor cursor){
        arrayList.clear(); // নতুন ডাটা সেট করার আগে লিস্ট পরিষ্কার করা

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    hashMap = new HashMap<>();
                    hashMap.put("id", String.valueOf(cursor.getInt(0)));
                    hashMap.put("home_word", cursor.getString(1));
                    arrayList.add(hashMap);
                }
            }
            cursor.close();
        }

        adapter.notifyDataSetChanged();
    }
} //=================================