package com.zybooks.cop4656project.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zybooks.cop4656project.models.Transaction;
import com.zybooks.cop4656project.repo.BudgetRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private BudgetRepository mBudgetRepo;
    private final MutableLiveData<Long> mTransactionIdLiveData = new MutableLiveData<>();
    public TransactionViewModel(@NonNull Application application) {
        super(application);
        mBudgetRepo = BudgetRepository.getInstance(application.getApplicationContext());
    }

    public void addTransaction(Transaction transaction) {
        mBudgetRepo.addTransaction(transaction);
    }
    public void deleteTransaction(Transaction transaction) {
        mBudgetRepo.deleteTransaction(transaction);
    }
    public LiveData<List<Transaction>> loadTransactions() {
        return mBudgetRepo.getTransactions();
    }
}
