package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // Find the button in settings_activity.xml
        Button openChangeBudgetButton = findViewById(R.id.changeBudgetButton);
        Button backButton = findViewById(R.id.button_back);

        // Set OnClickListener for the button
        openChangeBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the activity to display change_budget.xml
                Intent intent = new Intent(SettingsActivity.this, ChangeBudget.class);
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
