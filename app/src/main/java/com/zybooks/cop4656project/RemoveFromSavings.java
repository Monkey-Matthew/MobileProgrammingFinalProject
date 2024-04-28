package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RemoveFromSavings extends AppCompatActivity
{
    int savings1AmountSaved = HomeActivity.savings1AmountSaved;
    int budgetSpent = HomeActivity.budgetSpent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_fromsavings);

        Button applyChangesButton = findViewById(R.id.button_apply_changes);
        Button backButton = findViewById(R.id.button_back);

        // Find the EditText by its ID
        EditText editText = findViewById(R.id.edit_text_remove_savings);

        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the new budget amount from the EditText
                String newBudgetString = editText.getText().toString();

                // Convert the string to an integer
                savings1AmountSaved = savings1AmountSaved - Integer.parseInt(newBudgetString);

                budgetSpent = budgetSpent - Integer.parseInt(newBudgetString);

                // Update the value of HomeActivity.initialBudget
                HomeActivity.savings1AmountSaved = savings1AmountSaved;

                HomeActivity.budgetSpent = budgetSpent;

                // Finish the current activity and go back to the previous activity
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the previous activity
                finish();
            }
        });
    }
}