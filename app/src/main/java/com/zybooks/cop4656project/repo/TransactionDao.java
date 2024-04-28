package com.zybooks.cop4656project.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.models.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);
    @Delete
    void deleteTransaction(Transaction transaction);
    @Query("SELECT * FROM transactions ORDER BY date COLLATE NOCASE")
    LiveData<List<Transaction>> getTransactions();
}