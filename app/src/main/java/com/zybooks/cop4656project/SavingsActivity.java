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

        //initalize all components needed for the activity
        BudgetRepository mbudgetRepo = BudgetRepository.getInstance(this);
        pieChart = findViewById(R.id.savings1piechart);
        TextView textViewGoalStatus = findViewById(R.id.textViewGoalStatus);
        TextView amountLeftTextView = findViewById(R.id.amountLeftTextView);
        Button backButton = findViewById(R.id.button_back);
        TextView addToSavingstextView = findViewById(R.id.addToSavingsButton);
        TextView removeFromSavingstextView = findViewById(R.id.removeFromSavingsButton);

        mbudgetRepo.getBudget().observe(this, new Observer<Budget>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Budget budget) {
                if (budget != null) {
                    double savingsGoal = calculateAdjustedGoal(budget.getMonthlySaveGoal(), budget.getSavingsType());
                    double amountSaved = budget.getAmountSaved();
                    double amountLeft = Math.max(0, savingsGoal - amountSaved);
                    double goalPercentage = savingsGoal > 0 ? amountSaved / savingsGoal * 100 : 0;

                    DecimalFormat df = new DecimalFormat("#.##");
                    String formattedGoalPercentage = df.format(goalPercentage);

                    textViewGoalStatus.setText("You are " + formattedGoalPercentage + "% done.");
                    amountLeftTextView.setText("$" + df.format(amountLeft) + " left to save out of $" + df.format(savingsGoal));

                    pieChart.clearChart();
                    pieChart.addPieSlice(new PieModel("Amount Saved", (float) amountSaved, Color.parseColor("#FF5722")));
                    pieChart.addPieSlice(new PieModel("Amount Left", (float) amountLeft, Color.parseColor("#000000")));
                    pieChart.startAnimation();
                } else {
                    textViewGoalStatus.setText("No budget data available.");
                    amountLeftTextView.setText("No data available.");
                }
            }
        });


        addToSavingstextView.setOnClickListener(v -> startActivity(new Intent(this, AddToSavings.class)));
        removeFromSavingstextView.setOnClickListener(v -> startActivity(new Intent(this, RemoveFromSavings.class)));

        backButton.setOnClickListener(v -> finish());
    }

    private double calculateAdjustedGoal(double saveGoal, long savingsType) {
        switch ((int) savingsType) {
            case 1: // Aggressive
                return saveGoal; //full goal amount considered
            case 2: // Normal
                return saveGoal * 0.75; //75% of the goal
            case 3: // Conservative
                return saveGoal * 0.5; //50% of the goal
            default:
                return saveGoal; //default to full goal if type is unknown
        }
    }

}
