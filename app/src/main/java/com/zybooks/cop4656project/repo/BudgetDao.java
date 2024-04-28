package com.zybooks.cop4656project.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.zybooks.cop4656project.models.Budget;

@Dao
public interface BudgetDao {
    @Insert
    void insert(Budget budget);

    @Delete
    void deleteBudget(Budget budget);

    @Query("SELECT COUNT(*) FROM budget")
    LiveData<Integer> countBudgets();
}

