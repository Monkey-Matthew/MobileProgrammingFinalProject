package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class ChangeSavings extends AppCompatActivity {
    private BudgetRepository mBudgetRepository;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_savings);

        mBudgetRepository = new BudgetRepository(getApplication());
        editText = findViewById(R.id.edit_text_new_savingsgoal);

        mBudgetRepository.getBudget().observe(this, budget -> {
            if (budget != null) {
                editText.setText(String.valueOf(budget.getMonthlySaveGoal()));
            }
        });

        setupButtons();
    }

    private void setupButtons() {
        Button applyChangesButton = findViewById(R.id.button_apply_changes);
        Button backButton = findViewById(R.id.button_back);

        mBudgetRepository.getBudget().observe(this, budget -> {
            if (budget != null) {
                applyChangesButton.setOnClickListener(v -> {
                    try {
                        double newIncome = Double.parseDouble(editText.getText().toString());
                        budget.setMonthlySaveGoal(newIncome);
                        mBudgetRepository.updateSavings(budget);
                        Toast.makeText(this, "Monthly general save goal.", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Invalid number format.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "No budget data available.", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}

