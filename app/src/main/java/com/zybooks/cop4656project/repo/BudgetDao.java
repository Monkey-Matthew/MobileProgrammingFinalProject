package com.zybooks.cop4656project.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zybooks.cop4656project.models.Budget;

@Dao
public interface BudgetDao {
    @Insert
    void insert(Budget budget);

    @Delete
    void deleteBudget(Budget budget);

    @Query("SELECT COUNT(*) FROM budget")
    LiveData<Integer> countBudgets();
    @Query("SELECT * FROM budget LIMIT 1")
    LiveData<Budget> getBudget();
    @Update
    void updateMonthlyIncome(Budget budget);
    @Update
    void updateSavings(Budget budget);
    @Update
    void updateSavingHabits(Budget budget);
    @Update
    void updateAutoSavings(Budget budget);
    @Update
    void updateAmountSaved(Budget budget);
}

