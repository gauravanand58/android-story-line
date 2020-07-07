package com.wattpad.storyline.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wattpad.storyline.data.IStoryDao;
import com.wattpad.storyline.model.Story;

@Database(entities = {Story.class}, version = 2, exportSchema = false)
public abstract class StoryRoomDatabase extends RoomDatabase {
    public abstract IStoryDao storyDao();

    private static volatile StoryRoomDatabase INSTANCE;

    public static StoryRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (StoryRoomDatabase.class) {
                if (INSTANCE == null) {
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