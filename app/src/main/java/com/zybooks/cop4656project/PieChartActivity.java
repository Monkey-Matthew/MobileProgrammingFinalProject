package com.zybooks.cop4656project;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class PieChartActivity extends AppCompatActivity {

    private TextView textViewSpent, textViewBudgetLeft;
    private PieChart pieChart;
    private BudgetRepository budgetRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart);


        budgetRepository = BudgetRepository.getInstance(this);


        textViewSpent = findViewById(R.id.textViewSpent);
        textViewBudgetLeft = findViewById(R.id.textViewBudgetLeft);
        pieChart = findViewById(R.id.piechart);


        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> finish());


        loadBudgetData();
    }

    private void loadBudgetData() {
        budgetRepository.getBudget().observe(this, new Observer<Budget>() {
            @Override
            public void onChanged(Budget budget) {
                if (budget != null) {
                    updateUI(budget);
                }
            }
        });
    }


    private void updateUI(Budget budget) {
        double budgetSpent = budget.getMonthlySaveGoal();
        double budgetLeft = (budget.getMonthlyIncome() - budget.getMonthlySaveGoal());


        textViewSpent.setText(String.valueOf(0));
        textViewBudgetLeft.setText(String.valueOf(budgetLeft));


        pieChart.clearChart();
        pieChart.addPieSlice(new PieModel("Budget Spent", 0, Color.parseColor("#FF5722")));
        pieChart.addPieSlice(new PieModel("Budget Left", (float) budgetLeft, Color.parseColor("#000000")));
        pieChart.startAnimation();
    }
}