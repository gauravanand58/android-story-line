package com.wattpad.storyline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView storyRecyclerView;
    private StoryListAdapter storyListAdapter;
    private StoryViewModel storyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storyRecyclerView = findViewById(R.id.story_recycler_view);
        storyViewModel = new ViewModelProvider(this).get(StoryViewModel.class);
        storyViewModel.getStoryList().observe(this, new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                storyListAdapter.setStories(stories);
            }
        });

        storyListAdapter = new StoryListAdapter(this);
        storyRecyclerView.setAdapter(storyListAdapter);
        storyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
