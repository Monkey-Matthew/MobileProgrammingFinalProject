package com.zybooks.cop4656project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.cop4656project.repo.BudgetRepository;

public class ViewStatements extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_statements);

        listView = findViewById(R.id.listView);

        BudgetRepository mbudgetRepo = BudgetRepository.getInstance(this);
        mbudgetRepo.getTransactions().observe(this, transactions -> {
            StatementAdapter adapter = new StatementAdapter(this, R.layout.transaction_item, transactions);
            listView.setAdapter(adapter);
        });

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> finish());
    }
}

