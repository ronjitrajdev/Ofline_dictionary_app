package com.ronjit.banglaenglishdictionary.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ronjit.banglaenglishdictionary.MainActivity;
import com.ronjit.banglaenglishdictionary.R;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {


   private GridView categoryListView;
    ArrayList<HashMap<String ,String> > arrayList;
    HashMap<String, String> hashMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        categoryListView = myView.findViewById(R.id.categoryListView);
        dayImage();
        categoryList categoryList = new categoryList();
        categoryListView.setAdapter(categoryList);
        return myView;


    }
    //==============================================

    private class categoryList extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.category_item, parent, false);
            }
            ImageView dayImage = view.findViewById(R.id.dayImage);
            LinearLayout leane_day = view.findViewById(R.id.leane_day);
            hashMap = arrayList.get(position);
          String image =  hashMap.get("image");

          dayImage.setImageResource(Integer.parseInt(image));
          //==========================================================
            leane_day.setOnClickListener(v ->{
                Intent intent = new Intent(requireContext(), MainActivity.class);

                // প্রতিটি position অনুযায়ী আলাদা fragment খোলার জন্য extra পাঠানো
                intent.putExtra("open_day_fragment", position + 1); // 1 থেকে 48 পর্যন্ত
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            });
            //===========================================================
            return view;
        }

    }
    //================================================
    private void dayImage (){
        arrayList = new ArrayList<>();
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_1));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_2));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_3));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_4));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_5));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_6));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_7));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_8));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_9));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_10));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_11));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_12));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_13));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_14));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_15));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_16));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_17));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_18));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_19));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_20));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_21));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_22));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_23));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_24));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_25));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_26));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_27));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_28));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_29));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_30));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_31));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_32));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_33));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_34));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_35));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_36));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_37));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_38));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_39));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_40));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_41));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_42));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_43));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_44));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_45));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_46));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_47));
        arrayList.add(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("image", String.valueOf(R.drawable.day_48));
        arrayList.add(hashMap);






    }
} // public class ends --------------