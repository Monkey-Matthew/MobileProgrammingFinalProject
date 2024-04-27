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

    private Button nameButton;
    private Button monthlyBudgetButton;
    private Button monthlySaveGoalButton;
    private Button savingsButton;
    private Button automaticSavingsButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        nameButton = findViewById(R.id.nameButton);
        monthlyBudgetButton = findViewById(R.id.monthlyBudgetButton);
        monthlySaveGoalButton = findViewById(R.id.monthlySaveGoalButton);
        savingsButton = findViewById(R.id.describeSavingsButton);
        automaticSavingsButton = findViewById(R.id.automaticSavingsButton);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
