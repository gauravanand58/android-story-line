package com.wattpad.storyline;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface StoryDao {
    @Query("SELECT * from story")
    Maybe<List<Story>> getStoryLine();

    @Query("DELETE FROM story")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllOrders(List<Story> stories);

//    @Query("SELECT COUNT(*) FROM story WHERE last_update <= :timeout")
//    int hasFreshStories(long timeout);
}
