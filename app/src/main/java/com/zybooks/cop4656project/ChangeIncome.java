package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class ChangeIncome extends AppCompatActivity {
    private BudgetRepository mBudgetRepo;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_income);

        mBudgetRepo = new BudgetRepository(getApplication());
        editText = findViewById(R.id.edit_text_new_budget);

        mBudgetRepo.getBudget().observe(this, budget -> {
            if (budget != null) {
                editText.setText(String.valueOf(budget.getMonthlyIncome()));
            }
        });

        setupButtons();
    }

    private void setupButtons() {
        Button applyChangesButton = findViewById(R.id.button_apply_changes);
        Button backButton = findViewById(R.id.button_back);

        mBudgetRepo.getBudget().observe(this, budget -> {
            if (budget != null) {
                applyChangesButton.setOnClickListener(v -> {
                    try {
                        double newIncome = Double.parseDouble(editText.getText().toString());
                        budget.setMonthlyIncome(newIncome); // Update the budget with new income
                        mBudgetRepo.updateIncome(budget); // Persist the updated budget
                        Toast.makeText(this, "Monthly income updated.", Toast.LENGTH_SHORT).show();
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

