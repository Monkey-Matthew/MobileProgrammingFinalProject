package com.zybooks.cop4656project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputSpending extends AppCompatActivity {

    //New budget amount
    int newBudgetSpent = HomeActivity.budgetSpent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_spendingchoice);

        // Find the EditText for price by its ID
        EditText editTextPrice = findViewById(R.id.editTextPrice);

        Button backButton = findViewById(R.id.button_back);

        Button submitButton = findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the price entered by the user
                String priceString = editTextPrice.getText().toString();

                //Convert the price string to a double (assuming price is in decimal format)
                newBudgetSpent = Integer.parseInt(priceString);

                //Update the value of budgetSpent in HomeActivity
                HomeActivity.budgetSpent = HomeActivity.budgetSpent + newBudgetSpent;

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