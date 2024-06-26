package com.zybooks.cop4656project.repo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import android.content.Context;
import androidx.room.Room;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.models.Transaction;

public class BudgetRepository {

    //background processes for adding data to the database
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService mDatabaseExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static BudgetRepository mBudgetRepo;
    private final BudgetDao mBudgetDao;
    private final TransactionDao mTransactionDao;
    private final LiveData<Integer> budgetCount;

    public static BudgetRepository getInstance(Context context) {
        if(mBudgetRepo == null) {
            mBudgetRepo = new BudgetRepository(context);
        }
        return mBudgetRepo;
    }

    private static final String DB_NAME = "budget1.db";

    public BudgetRepository(Context context) {
        RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };
        BudgetAppDatabase database = Room.databaseBuilder(context, BudgetAppDatabase.class, DB_NAME)
                .addCallback(databaseCallback)
                .fallbackToDestructiveMigration()
                .build();

        mBudgetDao = database.budgetDao();
        mTransactionDao = database.transactionDao();
        budgetCount = mBudgetDao.countBudgets();
    }

    public LiveData<Budget> getBudget() {
        return mBudgetDao.getBudget();
    }

    //all functions that make use of the daos
    public void addBudget(Budget budget) {
        mDatabaseExecutor.execute(() -> {
            mBudgetDao.insert(budget);
        });
    }
    public void deleteBudget(Budget budget) {
        mDatabaseExecutor.execute(() -> {
            mBudgetDao.deleteBudget(budget);
        });
    }
    public void updateIncome(Budget budget) {
        mDatabaseExecutor.execute(() -> {
            mBudgetDao.updateMonthlyIncome(budget);
        });
    }
    public void addSavings(Budget budget, double addedSavings) {
        mDatabaseExecutor.execute(() -> {
            double newSavings = budget.getAmountSaved() + addedSavings;
            if (newSavings > budget.getMonthlySaveGoal()) {
                newSavings = budget.getMonthlySaveGoal();
            }
            budget.setAmountSaved(newSavings);
            mBudgetDao.updateAmountSaved(budget);
        });
    }

    public void removeSavings(Budget budget, double removedSavings) {
        mDatabaseExecutor.execute(() -> {
            double newAmountSaved = budget.getAmountSaved() - removedSavings;
            if (newAmountSaved < 0) {
                newAmountSaved = 0;
            }
            budget.setAmountSaved(newAmountSaved);
            mBudgetDao.updateAmountSaved(budget);
        });
    }

    public void updateSavingHabits(Budget budget) {
        mDatabaseExecutor.execute(() -> {
            mBudgetDao.updateSavingHabits(budget);
        });
    }
    public void updateAmountSaved(Budget budget) {
        mDatabaseExecutor.execute(() -> {
            mBudgetDao.updateAmountSaved(budget);
        });
    }
    public void addTransaction(Transaction transaction) {
        mDatabaseExecutor.execute(() -> {
            mTransactionDao.insert(transaction);
        });
    }

    public void deleteTransaction(Transaction transaction) {
        mDatabaseExecutor.execute(() -> {
            mTransactionDao.deleteTransaction(transaction);
        });
    }

    public LiveData<List<Transaction>> getTransactions() {
        return mTransactionDao.getTransactions();
    }
    public void deleteAllTransactions() {
        mDatabaseExecutor.execute(() -> {
            mTransactionDao.deleteAllTransactions();
        });
    }

    public LiveData<Integer> getBudgetCount() {
        return budgetCount;
    }
}
