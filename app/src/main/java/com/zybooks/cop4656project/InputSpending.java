package com.zybooks.cop4656project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.cop4656project.models.Transaction;
import com.zybooks.cop4656project.repo.BudgetRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputSpending extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_spendingchoice);

        BudgetRepository mbudgetRepo = BudgetRepository.getInstance(this);

        //initialize all edittexts in the activity
        EditText editTextMemo = findViewById(R.id.editTextMemo);
        EditText editTextPrice = findViewById(R.id.editTextPrice);
        EditText editTextDate = findViewById(R.id.editTextDate);
        Button submitButton = findViewById(R.id.button_submit);
        Button backButton = findViewById(R.id.button_back);

        //check input on click of the submit button
        submitButton.setOnClickListener(v -> {
            String memo = editTextMemo.getText().toString();
            String dateString = editTextDate.getText().toString();
            Date date;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd", Locale.getDefault());
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                Toast.makeText(InputSpending.this, "Invalid date format. Please use MM/dd.", Toast.LENGTH_SHORT).show();
                return;
            }

            double price;
            try {
                price = Double.parseDouble(editTextPrice.getText().toString().replace("$", ""));
            } catch (NumberFormatException e) {
                Toast.makeText(InputSpending.this, "Invalid amount format", Toast.LENGTH_SHORT).show();
                return;
            }

            //if everything checks out, insert the transaction to the database
            Transaction transaction = new Transaction(price, date, memo);
            mbudgetRepo.addTransaction(transaction);
            Toast.makeText(this, "Transaction added successfully", Toast.LENGTH_SHORT).show();
            finish();
        });

        backButton.setOnClickListener(v -> finish());
    }
}
