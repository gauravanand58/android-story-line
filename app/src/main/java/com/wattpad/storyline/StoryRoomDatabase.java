package com.wattpad.storyline;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Story.class}, version = 2, exportSchema = false)
public abstract class StoryRoomDatabase extends RoomDatabase {
    public abstract StoryDao storyDao();

    private static volatile StoryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static StoryRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (StoryRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StoryRoomDatabase.class, "story_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}