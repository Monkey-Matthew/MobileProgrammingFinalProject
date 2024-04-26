package com.zybooks.cop4656project.repo;

import androidx.room.Dao;
import androidx.room.Insert;

import com.zybooks.cop4656project.models.Budget;

@Dao
public interface BudgetDao {
    @Insert
    void insert(Budget budget);
}

