package com.zybooks.cop4656project.repo;

import androidx.room.Dao;
import androidx.room.Insert;

import com.zybooks.cop4656project.models.SavingsGoal;

@Dao
public interface SavingsGoalDao {
    @Insert
    void insert(SavingsGoal goal);
}
