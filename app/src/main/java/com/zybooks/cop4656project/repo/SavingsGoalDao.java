package com.zybooks.cop4656project.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.models.SavingsGoal;
import com.zybooks.cop4656project.models.Transaction;

import java.util.List;

@Dao
public interface SavingsGoalDao {
    @Insert
    void insert(SavingsGoal goal);

    @Delete
    void deleteSavingsGoal(SavingsGoal goal);

    @Query("SELECT * FROM savings_goals ORDER BY target_date COLLATE NOCASE")
    LiveData<List<SavingsGoal>> getSavingsGoals();
}
