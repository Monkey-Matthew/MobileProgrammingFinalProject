package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zybooks.cop4656project.repo.BudgetRepository;

public class MainActivity extends AppCompatActivity {

    private Button welcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeButton = findViewById(R.id.welcome_button);
        welcomeButton.setVisibility(View.INVISIBLE);

        //need the budgetrepository to check if there is an item in the database
        BudgetRepository budgetRepository = new BudgetRepository(getApplication());
        budgetRepository.getBudgetCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                if (count != null && count > 0) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                } else {
                    welcomeButton.setVisibility(View.VISIBLE);
                }
            }
        });

        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InformationActivity.class));
                finish();
            }
        });
    }
}
