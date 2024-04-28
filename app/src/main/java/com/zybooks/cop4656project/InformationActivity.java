package com.zybooks.cop4656project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Button nameButton = findViewById(R.id.nameButton);
        Button monthlyBudgetButton = findViewById(R.id.monthlyBudgetButton);
        Button monthlySaveGoalButton = findViewById(R.id.monthlySaveGoalButton);
        Button savingsButton = findViewById(R.id.describeSavingsButton);
        Button automaticSavingsButton = findViewById(R.id.automaticSavingsButton);
        Button nextButton = findViewById(R.id.nextButton);

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });
        monthlyBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the activity to display change_budget.xml
                Intent intent = new Intent(InformationActivity.this, ChangeBudget.class);
                startActivity(intent);
            }
        });

        monthlySaveGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the activity to display change_budget.xml
                Intent intent = new Intent(InformationActivity.this, ChangeSavings.class);
                startActivity(intent);
            }
        });
        savingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the activity to display change_budget.xml
                Intent intent = new Intent(InformationActivity.this, DescribeSavings.class);
                startActivity(intent);
            }
        });
        automaticSavingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the activity to display change_budget.xml
                Intent intent = new Intent(InformationActivity.this, ChangeAutoSavings.class);
                startActivity(intent);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
