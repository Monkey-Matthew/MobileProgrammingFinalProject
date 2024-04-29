package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zybooks.cop4656project.repo.BudgetRepository;

public class SettingsActivity extends AppCompatActivity {

    private BudgetRepository mbudgetRepo;
    private TextView welcomeMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Button openChangeIncomeButton = findViewById(R.id.changeIncomeButton);
        Button openChangeSavingButton = findViewById(R.id.button_change_save_goal);
        Button describeSavingButton = findViewById(R.id.button_describe_saving);
        Button resetButton = findViewById(R.id.button_reset);
        Button backButton = findViewById(R.id.button_back);

       mbudgetRepo = new BudgetRepository(getApplication());
        welcomeMessage = findViewById(R.id.text_welcome_message);

        updateWelcomeMessage();

        openChangeIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ChangeIncome.class);
                startActivity(intent);
            }
        });

        openChangeSavingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ChangeSavings.class);
                startActivity(intent);
            }
        });

        describeSavingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, DescribeSavings.class);
                startActivity(intent);
            }
        });
        //if the user resets, delete the budget and all transactions
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbudgetRepo.getBudget().observe(SettingsActivity.this, budget -> {
                    if (budget != null) {
                        mbudgetRepo.deleteBudget(budget);
                        mbudgetRepo.deleteAllTransactions();

                        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void updateWelcomeMessage() {
        mbudgetRepo.getBudget().observe(this, budget -> {
            if (budget != null && budget.getName() != null) {
                welcomeMessage.setText("Welcome, " + budget.getName() + "!");
            } else {
                welcomeMessage.setText("Welcome, User!");
            }
        });
    }
}
