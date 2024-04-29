package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class RemoveFromSavings extends AppCompatActivity {

    //essentially the same as addtosavings, but with remove
    private BudgetRepository mbudgetRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_fromsavings);

        mbudgetRepo = BudgetRepository.getInstance(this);

        Button applyChangesButton = findViewById(R.id.button_apply_changes);
        Button backButton = findViewById(R.id.button_back);
        EditText editText = findViewById(R.id.edit_text_remove_savings);

        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double removedSavings = Double.parseDouble(editText.getText().toString());
                    updateSavings(removedSavings);
                } catch (NumberFormatException e) {
                    Toast.makeText(RemoveFromSavings.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
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
    private void updateSavings(double removedSavings) {
        LiveData<Budget> liveData = mbudgetRepo.getBudget();
        liveData.observe(this, new Observer<Budget>() {
            @Override
            public void onChanged(Budget budget) {
                if (budget != null) {
                    mbudgetRepo.removeSavings(budget, removedSavings);
                    liveData.removeObserver(this);
                    Toast.makeText(RemoveFromSavings.this, "Savings updated successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RemoveFromSavings.this, "No budget found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
