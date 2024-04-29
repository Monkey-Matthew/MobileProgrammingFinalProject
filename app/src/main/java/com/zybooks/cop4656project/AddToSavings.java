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

public class AddToSavings extends AppCompatActivity {

    //declare budget repository locally
    private BudgetRepository mbudgetRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tosavings);

        //initialize budget repository
        mbudgetRepo = BudgetRepository.getInstance(this);

        //initialize buttons
        Button applyChangesButton = findViewById(R.id.button_apply_changes);
        Button backButton = findViewById(R.id.button_back);
        EditText editText = findViewById(R.id.edit_text_add_savings);

        //savings are updated on click of apply
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

        //finish the activity if the back button is clicked
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //use livedata to ensure savings are only added once
    private void updateSavings(double addedSavings) {
        LiveData<Budget> liveData = mbudgetRepo.getBudget();
        liveData.observe(this, new Observer<Budget>() {
            @Override
            public void onChanged(Budget budget) {
                if (budget != null) {
                    mbudgetRepo.addSavings(budget, addedSavings);
                    liveData.removeObserver(this);
                    Toast.makeText(AddToSavings.this, "Savings updated successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddToSavings.this, "No budget found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
