package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class AddToSavings extends AppCompatActivity {

    private BudgetRepository budgetRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tosavings);

        budgetRepository = BudgetRepository.getInstance(this);

        Button applyChangesButton = findViewById(R.id.button_apply_changes);
        Button backButton = findViewById(R.id.button_back);
        EditText editText = findViewById(R.id.edit_text_add_savings);

        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double addedSavings = Double.parseDouble(editText.getText().toString());
                    updateSavings(addedSavings);
                } catch (NumberFormatException e) {
                    Toast.makeText(AddToSavings.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void updateSavings(double addedSavings) {
        budgetRepository.getBudget().observe(this, budget -> {
            if (budget != null) {
                budget.setAmountSaved(budget.getAmountSaved() + addedSavings);
                budgetRepository.updateAmountSaved(budget);
                finish();
            } else {
                Toast.makeText(this, "No budget found.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
