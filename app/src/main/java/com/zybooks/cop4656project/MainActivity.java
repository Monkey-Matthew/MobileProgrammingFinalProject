package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zybooks.cop4656project.repo.BudgetRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BudgetRepository budgetRepository = new BudgetRepository(getApplication());
        budgetRepository.getBudgetCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                Intent intent;
                if (count != null && count > 0) {
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, InformationActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
    }
}

