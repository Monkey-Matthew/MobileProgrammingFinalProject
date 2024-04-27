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

    private TextView nameEditText;
    private TextView monthlyBudgetEditText;
    private TextView monthlySaveGoalEditText;
    private TextView savingsEditText;
    private ToggleButton automaticSavingsToggleButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        nameEditText = findViewById(R.id.nameEditText);
        monthlyBudgetEditText = findViewById(R.id.monthlyBudgetEditText);
        monthlySaveGoalEditText = findViewById(R.id.monthlySaveGoalEditText);
        savingsEditText = findViewById(R.id.describeSavingsEditText);
        automaticSavingsToggleButton = findViewById(R.id.automaticSavingsToggleButton);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        automaticSavingsToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToggleAutomaticSavings();
            }
        });
    }

    private void onNextButtonClicked() {
        showToast("Next button clicked!");
    }

    private void onToggleAutomaticSavings() {
        String status = automaticSavingsToggleButton.isChecked() ? "Automatic Savings ON" : "Automatic Savings OFF";
        showToast(status);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
