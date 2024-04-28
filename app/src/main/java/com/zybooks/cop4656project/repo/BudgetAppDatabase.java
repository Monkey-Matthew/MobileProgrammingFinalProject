package com.zybooks.cop4656project.repo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.models.Category;
import com.zybooks.cop4656project.models.SavingsGoal;
import com.zybooks.cop4656project.models.Transaction;

@Database(entities = {Transaction.class, Budget.class, SavingsGoal.class, Category.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class BudgetAppDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();
    public abstract BudgetDao budgetDao();
    public abstract SavingsGoalDao savingsGoalDao();
}
