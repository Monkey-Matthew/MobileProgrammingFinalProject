package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class ChangeAutoSavings extends AppCompatActivity {

    private BudgetRepository mbudgetRepo;
    private SwitchCompat autoSavingsSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_autosavings);

        mbudgetRepo = new BudgetRepository(getApplication()); // Assuming BudgetRepository handles all DB operations.
        autoSavingsSwitch = findViewById(R.id.my_switch);
        Button confirmButton = findViewById(R.id.button_confirm);
        Button backButton = findViewById(R.id.button_back);

        // Load current budget's autoSavings state
        mbudgetRepo.getBudget().observe(this, budget -> {
            if (budget != null) {
                autoSavingsSwitch.setChecked(budget.getAutoSavings());
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAutoSavings();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void updateAutoSavings() {
        mbudgetRepo.getBudget().observe(this, budget -> {
            if (budget != null) {
                boolean newAutoSavingsState = autoSavingsSwitch.isChecked();
                budget.setAutoSavings(newAutoSavingsState);
                mbudgetRepo.updateAutoSavings(budget);
                finish();
            } else {
                Toast.makeText(ChangeAutoSavings.this, "No existing budget found.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
