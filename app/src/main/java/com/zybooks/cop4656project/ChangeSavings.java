package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeSavings extends AppCompatActivity
{
    //Initial budget value
    int savingsGoal = HomeActivity.savings1Goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_savings);

        Button applyChangesButton = findViewById(R.id.button_apply_changes);
        Button backButton = findViewById(R.id.button_back);

        // Find the EditText by its ID
        EditText editText = findViewById(R.id.edit_text_new_savingsgoal);

        // Convert the integer variable to a string
        String budgetAmountString = String.valueOf(HomeActivity.savings1Goal);

        // Set the text of the EditText to the string representation of the integer variable
        editText.setText(budgetAmountString);

        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the new budget amount from the EditText
                String newBudgetString = editText.getText().toString();

                // Convert the string to an integer
                savingsGoal = Integer.parseInt(newBudgetString);

                // Update the value of HomeActivity.initialBudget
                HomeActivity.savings1Goal = savingsGoal;

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
