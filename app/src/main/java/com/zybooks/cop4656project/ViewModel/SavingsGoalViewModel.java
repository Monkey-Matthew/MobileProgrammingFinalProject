package com.zybooks.cop4656project.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.zybooks.cop4656project.models.SavingsGoal;
import com.zybooks.cop4656project.repo.BudgetRepository;

import java.util.List;

public class SavingsGoalViewModel extends AndroidViewModel {
    private BudgetRepository mBudgetRepo;
    private final MutableLiveData<Long> mSavingsGoalIdLiveData = new MutableLiveData<>();
    public SavingsGoalViewModel(@NonNull Application application) {
        super(application);
        mBudgetRepo = BudgetRepository.getInstance(application.getApplicationContext());
    }

    public void addSavingsGoal(SavingsGoal goal) {
        mBudgetRepo.addSavingsGoal(goal);
    }
    public void deleteSavingsGoal(SavingsGoal goal) {
        mBudgetRepo.deleteSavingsGoal(goal);
    }
    public LiveData<List<SavingsGoal>> loadSavingsGoal() {
        return mBudgetRepo.getSavingsGoals();
    }
}
