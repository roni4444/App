package com.myroutineapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Habit_table")
public class Habit {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "Habit_Name")
    private String habitName;

    @NotNull
    @ColumnInfo(name = "Habit_Description")
    private String habitDesc;

    @NotNull
    @ColumnInfo(name = "Habit_Completion_Time")
    private String habitTime;

    public Habit() {
    }

    public Habit(@NotNull String Name, @NotNull String Desc, @NotNull String Time) {
        this.habitName = Name;
        this.habitDesc = Desc;
        this.habitTime = Time;
    }

    public void setHabitName(@NotNull String habitName) {
        this.habitName = habitName;
    }

    public void setHabitDesc(@NotNull String habitDesc) {
        this.habitDesc = habitDesc;
    }

    public void setHabitTime(@NotNull String habitTime) {
        this.habitTime = habitTime;
    }


    public String getHabitName() {
        return this.habitName;
    }

    public String getHabitDesc() {
        return this.habitDesc;
    }

    public String getHabitTime() {
        return this.habitTime;
    }
}
