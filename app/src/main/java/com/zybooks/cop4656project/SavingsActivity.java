package com.zybooks.cop4656project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class SavingsActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savings);

        BudgetRepository mbudgetRepo = BudgetRepository.getInstance(this);
        pieChart = findViewById(R.id.savings1piechart);
        TextView textViewGoalStatus = findViewById(R.id.textViewGoalStatus);
        TextView amountLeftTextView = findViewById(R.id.amountLeftTextView);
        Button backButton = findViewById(R.id.button_back);
        TextView addToSavingstextView = findViewById(R.id.addToSavingsButton);
        TextView removeFromSavingstextView = findViewById(R.id.removeFromSavingsButton);

        mbudgetRepo.getBudget().observe(this, new Observer<Budget>() {

            @Override
            public void onChanged(Budget budget) {
                if (budget != null) {
                    double amountSaved = budget.getAmountSaved();
                    double savingsGoal = budget.getMonthlySaveGoal();
                    double amountLeft = (savingsGoal - amountSaved);
                    double goalPercentage = savingsGoal > 0 ? (double) amountSaved / savingsGoal * 100 : 0;

                    DecimalFormat df = new DecimalFormat("#.##");
                    String formattedGoalPercentage = df.format(goalPercentage);

                    textViewGoalStatus.setText("You are " + formattedGoalPercentage + "% done.");
                    amountLeftTextView.setText("$" + amountLeft + " left to save out of $" + savingsGoal);

                    pieChart.clearChart();
                    pieChart.addPieSlice(new PieModel("Amount Saved", (float) amountSaved, Color.parseColor("#FF5722")));
                    pieChart.addPieSlice(new PieModel("Amount Left", (float) amountLeft, Color.parseColor("#000000")));
                    pieChart.startAnimation();
                }
            }
        });

        addToSavingstextView.setOnClickListener(v -> startActivity(new Intent(this, AddToSavings.class)));
        removeFromSavingstextView.setOnClickListener(v -> startActivity(new Intent(this, RemoveFromSavings.class)));

        backButton.setOnClickListener(v -> finish());
    }
}
