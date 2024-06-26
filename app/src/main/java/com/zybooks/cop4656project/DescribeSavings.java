package com.zybooks.cop4656project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class DescribeSavings extends AppCompatActivity {

    private BudgetRepository mbudgetRepo;
    private RadioGroup savingsRadioGroup;
    private Budget currentBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.describe_saving);

        mbudgetRepo = new BudgetRepository(getApplication());
        savingsRadioGroup = findViewById(R.id.describeSavingsRadioGroup);
        Button confirmButton = findViewById(R.id.button_confirm);


        mbudgetRepo.getBudget().observe(this, budget -> {
            if (budget != null) {
                currentBudget = budget;
            }
        });

        confirmButton.setOnClickListener(v -> {
            if (currentBudget != null) {
                int savingsType = getSavingsTypeFromRadioGroup(savingsRadioGroup);
                currentBudget.setSavingsType(savingsType);
                mbudgetRepo.updateSavingHabits(currentBudget);
                Toast.makeText(DescribeSavings.this, "Savings type updated.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DescribeSavings.this, "No existing budget found", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }

    //get the savings type from the radio group
    private int getSavingsTypeFromRadioGroup(RadioGroup radioGroup) {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        int idx = radioGroup.indexOfChild(findViewById(radioButtonID));
        return idx + 1;
    }
}
