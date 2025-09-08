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
import com.ronjit.banglaenglishdictionary.book_database.ShoppingWord_Database;

import java.util.ArrayList;
import java.util.HashMap;


public class Shopping_Word extends Fragment {
    private RecyclerView recyclerView_Shopping;
    ArrayList<HashMap<String,String> > arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;
    private myShoppingAdapter adapter;
    private ShoppingWord_Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_shopping__word, container, false);
        recyclerView_Shopping = myView.findViewById(R.id.recyclerView_Shopping);
        adapter = new myShoppingAdapter();
        recyclerView_Shopping.setAdapter(adapter);
        recyclerView_Shopping.setLayoutManager(new LinearLayoutManager(requireContext()));
        database = new ShoppingWord_Database(requireContext());
        loadEnglishData(database.getAllDate());
        return myView;
    } //=======================================
    private class myShoppingAdapter extends RecyclerView.Adapter<myShoppingAdapter.myViewHolder>{
        private class myViewHolder extends RecyclerView.ViewHolder{
            TextView word_Text;
            public myViewHolder(@NonNull View itemView) {
                super(itemView);
                word_Text = itemView.findViewById(R.id.word_Text);
            }
        }
        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.word_item,parent,false);
            return new myViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String shopping_word = hashMap.get("shopping_word");
            holder.word_Text.setText(shopping_word);

            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom);
            holder.itemView.startAnimation(animation);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }
    //=========================================
    private void loadEnglishData(Cursor cursor){
        arrayList.clear(); // নতুন ডাটা সেট করার আগে লিস্ট পরিষ্কার করা

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    hashMap = new HashMap<>();
                    hashMap.put("id", String.valueOf(cursor.getInt(0)));
                    hashMap.put("shopping_word", cursor.getString(1));
                    arrayList.add(hashMap);
                }
            }
            cursor.close();
        }

        adapter.notifyDataSetChanged();
    }

}//===========================================