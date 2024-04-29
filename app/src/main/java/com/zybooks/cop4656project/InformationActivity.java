package com.zybooks.cop4656project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

import java.util.Date;

public class InformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //initialize all edittexts in the information screen
        final EditText nameEditText = findViewById(R.id.name_edit_text);
        final EditText monthlyIncomeEditText = findViewById(R.id.editTextPrice);
        final EditText monthlySaveGoalEditText = findViewById(R.id.save_goal_edit_text);
        final RadioGroup savingsTypeRadioGroup = findViewById(R.id.radioGroupSavingsType);

        Button submitButton = findViewById(R.id.button_submit);
        //where the magic happens, parse all input and validate,
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                double monthlyIncome = 0;
                double monthlySaveGoal = 0;

                try {
                    monthlyIncome = Double.parseDouble(monthlyIncomeEditText.getText().toString());
                    monthlySaveGoal = Double.parseDouble(monthlySaveGoalEditText.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(InformationActivity.this, "Please enter valid numbers for income and save goal.", Toast.LENGTH_LONG).show();
                    return;
                }

                int savingsType = getSavingsTypeFromRadioGroup(savingsTypeRadioGroup);

                Date currentDate = new Date();
                //create new budget and insert it into the database
                Budget budget = new Budget(0, name, monthlyIncome, currentDate, monthlySaveGoal, savingsType,0);
                saveBudget(budget);

                Intent intent = new Intent(InformationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    //get the type of savings
    private int getSavingsTypeFromRadioGroup(RadioGroup radioGroup) {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        return radioGroup.indexOfChild(radioButton);
    }

    //insert budget into the database
    private void saveBudget(Budget budget) {
        BudgetRepository mBudgetRepo = BudgetRepository.getInstance(getApplicationContext());
        mBudgetRepo.addBudget(budget);
    }
}