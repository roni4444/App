package com.myroutineapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HabitDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Habit habit);

    @Query("DELETE FROM Habit_table")
    void deleteAll();

    @Query("SELECT * FROM Habit_table ORDER BY Habit_Name ASC")
    LiveData<List<Habit>> getHabitList();

}
