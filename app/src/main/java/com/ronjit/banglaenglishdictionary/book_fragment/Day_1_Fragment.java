package com.ronjit.banglaenglishdictionary.book_fragment;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ronjit.banglaenglishdictionary.R;
import com.ronjit.banglaenglishdictionary.book_database.Day_1_Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Day_1_Fragment extends Fragment {

    private RecyclerView recyClerView_daily_English;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private MyAdapter adapter;
    private Day_1_Database database;
    TextToSpeech tts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.day_1_fragment, container, false);

        recyClerView_daily_English = myView.findViewById(R.id.recyClerView_daily_English);

        database = new Day_1_Database(requireContext());
        adapter = new MyAdapter();
        recyClerView_daily_English.setAdapter(adapter);
        recyClerView_daily_English.setLayoutManager(new LinearLayoutManager(requireContext()));

        tts = new TextToSpeech(getContext(), status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.US);
            }
        });

        // Load all data sequentially
        loadVocabulary(database.getVocabulary(1));
        loadSentence(database.getSentence(1));
        loadDialogue(database.getDialogue(1));
        loadPractice(database.getPractice(1));

        return myView;
    }

    //================ Adapter =================
    //================ Adapter =================
    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_VOCABULARY = 0;
        private static final int TYPE_SENTENCE = 1;
        private static final int TYPE_DIALOGUE = 2;
        private static final int TYPE_PRACTICE = 3;

        @Override
        public int getItemViewType(int position) {
            return Integer.parseInt(arrayList.get(position).get("viewType"));
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            switch (viewType) {
                case TYPE_VOCABULARY:
                    return new VocabularyViewHolder(inflater.inflate(R.layout.item_vocabulary, parent, false));
                case TYPE_SENTENCE:
                    return new SentenceViewHolder(inflater.inflate(R.layout.item_sentence, parent, false));
                case TYPE_DIALOGUE:
                    return new DialogueViewHolder(inflater.inflate(R.layout.item_dialogue, parent, false));
                case TYPE_PRACTICE:
                    return new PracticeViewHolder(inflater.inflate(R.layout.item_practice, parent, false));
                default:
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            HashMap<String, String> data = arrayList.get(position);

            if (holder instanceof VocabularyViewHolder) {
                String section = data.get("section");
                String word = data.get("word").replace("\\n", "\n");
                String meaning = data.get("Meaning").replace("\\n", "\n");
                ((VocabularyViewHolder) holder).tvSection.setText(section);
                ((VocabularyViewHolder) holder).tvWord.setText(word+ " ➔ ");
                ((VocabularyViewHolder) holder).tvMeaning.setText(" ("+meaning+")");
                ((VocabularyViewHolder) holder).btnPlay.setOnClickListener(v -> {
                    if (tts != null) {
                        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });

            } else if (holder instanceof SentenceViewHolder) {
                String section = data.get("section");
                String sentence = data.get("sentence").replace("\\n", "\n");
                String meaning = data.get("meaning").replace("\\n", "\n");
                ((SentenceViewHolder) holder).tvSection.setText(section);
                ((SentenceViewHolder) holder).tvSentence.setText(sentence+ " ➔ ");
                ((SentenceViewHolder) holder).tvMeaning.setText(meaning);
                ((SentenceViewHolder) holder).btnPlay.setOnClickListener(v -> {
                    if (tts != null) {
                        tts.speak(sentence, TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });

            } else if (holder instanceof DialogueViewHolder) {
                String section = data.get("section");
                String dialogue = data.get("dialogue").replace("\\n", "\n");
                String meaning = data.get("Meaning").replace("\\n", "\n");
                ((DialogueViewHolder) holder).tvSection.setText(section);
                ((DialogueViewHolder) holder).tvDialogue.setText(dialogue);
                ((DialogueViewHolder) holder).tvMeaning.setText(meaning);

            } else if (holder instanceof PracticeViewHolder) {
                String section = data.get("section");
                String question = data.get("question").replace("\\n", "\n");
                String answer = data.get("answer").replace("\\n", "\n");
                ((PracticeViewHolder) holder).tvSection.setText(section);
                ((PracticeViewHolder) holder).tvQuestion.setText(
                        question + " ➔ " + " ("+answer+")"
                );
                ((PracticeViewHolder) holder).btnPlay.setOnClickListener(v -> {
                    if (tts != null) {
                        tts.speak(answer, TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        // ====== ViewHolders ======
        class VocabularyViewHolder extends RecyclerView.ViewHolder {
            TextView tvWord, tvMeaning, tvSection;
            ImageButton btnPlay;

            public VocabularyViewHolder(View view) {
                super(view);
                tvWord = view.findViewById(R.id.word);
                tvMeaning = view.findViewById(R.id.meaning);
                btnPlay = view.findViewById(R.id.btnPlay);
                tvSection = view.findViewById(R.id.tvSection);
            }
        }

        class SentenceViewHolder extends RecyclerView.ViewHolder {
            TextView tvSentence, tvMeaning, tvSection;
            ImageButton btnPlay;

            public SentenceViewHolder(View view) {
                super(view);
                tvSentence = view.findViewById(R.id.sentence);
                tvMeaning = view.findViewById(R.id.meaning);
                btnPlay = view.findViewById(R.id.btnPlay);
                tvSection = view.findViewById(R.id.tvSection);

            }
        }

        class DialogueViewHolder extends RecyclerView.ViewHolder {
            TextView tvDialogue, tvMeaning, tvSection;

            public DialogueViewHolder(View view) {
                super(view);
                tvDialogue = view.findViewById(R.id.dialogue);
                tvMeaning = view.findViewById(R.id.meaning);
                tvSection = view.findViewById(R.id.tvSection);
            }
        }

        class PracticeViewHolder extends RecyclerView.ViewHolder {
            TextView tvQuestion, tvSection;
            ImageButton btnPlay;

            public PracticeViewHolder(View view) {
                super(view);
                tvQuestion = view.findViewById(R.id.tvQuestion);
                btnPlay = view.findViewById(R.id.btnPlay);
                tvSection = view.findViewById(R.id.tvSection);

            }
        }
    }

    //================ Load lessons from Cursor =================
    //================ Load Methods =================
    private void loadVocabulary(Cursor cursor) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                HashMap<String, String> map = new HashMap<>();
                map.put("section", cursor.getString(2));
                map.put("word", cursor.getString(3));
                map.put("Meaning", cursor.getString(4));
                map.put("viewType", "0");
                arrayList.add(map);
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }

    private void loadSentence(Cursor cursor) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                HashMap<String, String> map = new HashMap<>();
                map.put("section", cursor.getString(2));
                map.put("sentence", cursor.getString(3));
                map.put("meaning", cursor.getString(4));
                map.put("viewType", "1");
                arrayList.add(map);
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }

    private void loadDialogue(Cursor cursor) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                HashMap<String, String> map = new HashMap<>();
                map.put("section", cursor.getString(2));
                map.put("dialogue", cursor.getString(3));
                map.put("Meaning", cursor.getString(4));
                map.put("viewType", "2");
                arrayList.add(map);
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }


    private void loadPractice(Cursor cursor) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                HashMap<String, String> map = new HashMap<>();
                map.put("section", cursor.getString(2));
                map.put("question", cursor.getString(3));
                map.put("answer", cursor.getString(4));
                map.put("viewType", "3");
                arrayList.add(map);
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }
}
