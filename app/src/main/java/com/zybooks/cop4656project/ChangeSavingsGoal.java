package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChangeSavingsGoal extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_savegoal);

        Button savings1button = findViewById(R.id.savings1button);
        Button savings2button = findViewById(R.id.savings2button);
        Button backButton = findViewById(R.id.button_back);

        savings1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the activity to display change_budget.xml
                Intent intent = new Intent(ChangeSavingsGoal.this, ChangeSavings1.class);
                startActivity(intent);
            }
        });

        savings2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the activity to display change_budget.xml
                Intent intent = new Intent(ChangeSavingsGoal.this, ChangeSavings2.class);
                startActivity(intent);
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