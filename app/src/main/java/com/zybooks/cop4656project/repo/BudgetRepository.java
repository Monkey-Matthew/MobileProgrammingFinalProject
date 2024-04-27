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
import com.zybooks.cop4656project.models.SavingsGoal;
import com.zybooks.cop4656project.models.Transaction;

public class BudgetRepository {

    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService mDatabaseExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static BudgetRepository mBudgetRepo;
    private final BudgetDao mBudgetDao;
    private final CategoryDao mCategoryDao;
    private final TransactionDao mTransactionDao;
    private final SavingsGoalDao mSavingsGoalDao;

    public static BudgetRepository getInstance(Context context) {
        if(mBudgetRepo == null) {
            mBudgetRepo = new BudgetRepository(context);
        }
        return mBudgetRepo;
    }

    private static final String DB_NAME = "budget1.db";

    private BudgetRepository(Context context) {
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
        mCategoryDao = database.categoryDao();
        mTransactionDao = database.transactionDao();
        mSavingsGoalDao = database.savingsGoalDao();
    }

    public void addBudget(Budget budget) {
        mDatabaseExecutor.execute(() -> {
            mBudgetDao.insert(budget);
        });
    }
    public void addTransaction(Transaction transaction) {
        mDatabaseExecutor.execute(() -> {
            mTransactionDao.insert(transaction);
        });
    }
    public void addSavingsGoal(SavingsGoal goal) {
        mDatabaseExecutor.execute(() -> {
            mSavingsGoalDao.insert(goal);
        });
    }

    public void deleteTransaction(Transaction transaction) {
        mDatabaseExecutor.execute(() -> {
            mTransactionDao.deleteTransaction(transaction);
        });
    }
    public void deleteSavingsGoal(SavingsGoal goal) {
        mDatabaseExecutor.execute(() -> {
            mSavingsGoalDao.deleteSavingsGoal(goal);
        });
    }

    public LiveData<List<Transaction>> getTransactions() {
        return mTransactionDao.getTransactions();
    }

    public LiveData<List<SavingsGoal>> getSavingsGoals() {
        return mSavingsGoalDao.getSavingsGoals();
    }
}
