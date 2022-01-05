package com.ionelaturcuman.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ionelaturcuman.newsreader.feature.newslist.fragment.NewsDisplayFragment;

public class NewsDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewsDisplayFragment.newInstance())
                    .commitNow();
        }
    }
}