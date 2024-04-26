package com.zybooks.cop4656project.repo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.models.Category;
import com.zybooks.cop4656project.models.SavingsGoal;
import com.zybooks.cop4656project.models.Transaction;

@Database(entities = {Transaction.class, Budget.class, SavingsGoal.class, Category.class}, version = 1)
public abstract class BudgetAppDatabase extends RoomDatabase{
    public abstract TransactionDao transactionDao();
    public abstract BudgetDao budgetDao();
    public abstract CategoryDao categoryDao();
    public abstract SavingsGoalDao savingsGoalDao();

    private static volatile BudgetAppDatabase INSTANCE;

    static BudgetAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BudgetAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BudgetAppDatabase.class, "budget_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
