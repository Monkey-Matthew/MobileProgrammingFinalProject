package com.zybooks.cop4656project.repo;

import androidx.room.Dao;
import androidx.room.Insert;

import com.zybooks.cop4656project.models.Transaction;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);
}
