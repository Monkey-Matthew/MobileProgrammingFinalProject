package com.zybooks.cop4656project;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import java.util.List;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.models.Transaction;
import com.zybooks.cop4656project.repo.BudgetRepository;

public class PieChartActivity extends AppCompatActivity {
    private TextView textViewSpent, textViewBudgetLeft;
    private PieChart pieChart;
    private BudgetRepository mbudgetRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart);

        mbudgetRepo = BudgetRepository.getInstance(this);

        //initialize information text views
        textViewSpent = findViewById(R.id.textViewSpent);
        textViewBudgetLeft = findViewById(R.id.textViewBudgetLeft);
        pieChart = findViewById(R.id.piechart);

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> finish());

        loadBudgetData();
    }

    //get the budget data from the database
    private void loadBudgetData() {
        mbudgetRepo.getBudget().observe(this, budget -> {
            if (budget != null) {
                mbudgetRepo.getTransactions().observe(this, transactions -> {
                    double totalSpent = calculateTotalSpent(transactions);
                    updateUI(budget, totalSpent);
                });
            }
        });
    }

    //using all transactions, get the total amount spent
    private double calculateTotalSpent(List<Transaction> transactions) {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getAmount();
        }
        return total;
    }

    //calculate total spend and remaining budget and update the pie chart
    private void updateUI(Budget budget, double totalSpent) {
        double initialBudget = budget.getMonthlyIncome() - budget.getMonthlySaveGoal();
        double budgetLeft = initialBudget - totalSpent;

        textViewSpent.setText(String.format("$%.2f", totalSpent));
        textViewBudgetLeft.setText(String.format("$%.2f", budgetLeft));

        pieChart.clearChart();
        pieChart.addPieSlice(new PieModel("Budget Spent", (float) totalSpent, Color.parseColor("#FF5722")));
        pieChart.addPieSlice(new PieModel("Budget Left", (float) budgetLeft, Color.parseColor("#000000")));
        pieChart.startAnimation();
    }
}
