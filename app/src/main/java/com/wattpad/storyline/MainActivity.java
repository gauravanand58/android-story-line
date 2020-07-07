package com.wattpad.storyline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.wattpad.storyline.model.Story;
import com.wattpad.storyline.viewmodel.StoryViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private StoryListAdapter mStoryListAdapter;
    private StoryViewModel mStoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.story_recycler_view);
        mStoryViewModel = new ViewModelProvider(this).get(StoryViewModel.class);
        mStoryViewModel.getStoryList().observe(this, new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                mStoryListAdapter.setStories(stories);
            }
        });

        mStoryListAdapter = new StoryListAdapter(this);
        mRecyclerView.setAdapter(mStoryListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
