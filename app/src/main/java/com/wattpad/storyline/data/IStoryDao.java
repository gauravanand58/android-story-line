package com.wattpad.storyline.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wattpad.storyline.model.Story;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface IStoryDao {
    @Query("SELECT * from story")
    Maybe<List<Story>> getStoryLine();

    @Query("DELETE FROM story")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllStories(List<Story> stories);
}
