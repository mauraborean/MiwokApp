package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class FamilyMembersActivity extends AppCompatActivity {

    private final String FAMILY_MEMBERS_ACTIVITY = FamilyMembersActivity.class.getSimpleName();

    private OnClickWordListener onClickWordListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        
        setActionBar();
        createListItemView();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(FAMILY_MEMBERS_ACTIVITY, "Stopping Miwok app!");
        onClickWordListener.releaseMediaPlayer();
    }
    
    private void setActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled( Boolean.TRUE );
    }

    private void createListItemView() {
        List<Word> words = WordsDictionary.getFamilyMembersList();
        setListView(words);
    }

    private void setListView(List<Word> words) {
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        createOnClickWordListener(words);
        listView.setOnItemClickListener(onClickWordListener);
    }

    private void createOnClickWordListener(List<Word> words) {
        onClickWordListener = new OnClickWordListener(FamilyMembersActivity.this, words);
    }
}
