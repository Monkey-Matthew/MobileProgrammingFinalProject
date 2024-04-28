package com.zybooks.cop4656project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.cop4656project.repo.BudgetRepository;

public class DescribeSavings extends AppCompatActivity {

    private BudgetRepository budgetRepository;
    RadioGroup savingsRadioGroup = findViewById(R.id.describeSavingsRadioGroup);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.describe_saving);

        Button confirmButton = findViewById(R.id.button_confirm);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the previous activity
                finish();
            }
        });
    }
}