package com.myroutineapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class HabitRepository {

    private HabitDAO habitDAO;
    private LiveData<List<Habit>> allHabits;

    HabitRepository(Application application) {
        HabitRoomDatabase db = HabitRoomDatabase.getDatabase(application);
        habitDAO = db.habitDAO();
        allHabits = habitDAO.getHabitList();
    }

    LiveData<List<Habit>> getAllHabits() {
        return allHabits;
    }

    void insert(final Habit habit) {
        HabitRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                habitDAO.insert(habit);
            }
        });
    }
}
