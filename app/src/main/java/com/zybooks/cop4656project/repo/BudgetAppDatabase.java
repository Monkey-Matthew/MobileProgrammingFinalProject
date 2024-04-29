package com.zybooks.cop4656project.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.models.Transaction;

@Database(entities = {Transaction.class, Budget.class}, version = 6, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class BudgetAppDatabase extends RoomDatabase {
    //initialize Daos
    public abstract TransactionDao transactionDao();
    public abstract BudgetDao budgetDao();
}
