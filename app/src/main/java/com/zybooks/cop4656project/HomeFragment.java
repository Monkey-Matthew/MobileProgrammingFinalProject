package com.zybooks.cop4656project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.models.Transaction;
import com.zybooks.cop4656project.repo.BudgetRepository;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

public class HomeFragment extends Fragment {
    private PieChart pieChart;
    private PieChart savings1PieChart;
    private BudgetRepository mbudgetRepo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        mbudgetRepo = BudgetRepository.getInstance(requireContext());

        pieChart = view.findViewById(R.id.piechart);
        savings1PieChart = view.findViewById(R.id.savings1piechart);

        view.findViewById(R.id.pieChartView).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), PieChartActivity.class)));
        view.findViewById(R.id.savings1pieChartView).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), SavingsActivity.class)));
        view.findViewById(R.id.addStatementButton).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), InputSpending.class)));
        view.findViewById(R.id.viewStatementsButton).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), ViewStatements.class)));
        view.findViewById(R.id.bottomRightButton).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), SettingsActivity.class)));
        loadBudgetData();

        return view;
    }

    //load the budget data from the repo
    private void loadBudgetData() {
        mbudgetRepo.getBudget().observe(getViewLifecycleOwner(), this::observeTransactions);
    }

    //update the charts based on the transactions
    private void observeTransactions(Budget budget) {
        if (budget != null) {
            mbudgetRepo.getTransactions().observe(getViewLifecycleOwner(), transactions -> {
                double totalSpent = calculateTotalSpent(transactions);
                updateBudgetPieChart(budget, totalSpent);
                updateSavingsPieChart(budget);
            });
        }
    }

    //get the total amount spent
    private double calculateTotalSpent(List<Transaction> transactions) {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getAmount();
        }
        return total;
    }

    //update the budget chart
    private void updateBudgetPieChart(Budget budget, double totalSpent) {
        double budgetLeft = budget.getMonthlyIncome() - budget.getMonthlySaveGoal() - totalSpent;
        pieChart.clearChart();
        pieChart.addPieSlice(new PieModel("Spent", (float) totalSpent, Color.parseColor("#FF5722")));
        pieChart.addPieSlice(new PieModel("Left", (float) budgetLeft, Color.parseColor("#000000")));
        pieChart.startAnimation();
    }

    //update the savings chart
    private void updateSavingsPieChart(Budget budget) {
        double adjustedSaveGoal = calculateAdjustedGoal(budget.getMonthlySaveGoal(), budget.getSavingsType());
        double savingsLeft = Math.max(0, adjustedSaveGoal - budget.getAmountSaved());
        savings1PieChart.clearChart();
        savings1PieChart.addPieSlice(new PieModel("Saved", (float) budget.getAmountSaved(), Color.parseColor("#FF5722")));
        savings1PieChart.addPieSlice(new PieModel("Goal Left", (float) savingsLeft, Color.parseColor("#000000")));
        savings1PieChart.startAnimation();
    }

    //calculate savings based on what the user inputs
    private double calculateAdjustedGoal(double saveGoal, long savingsType) {
        switch ((int) savingsType) {
            case 1: //aggressive
                return saveGoal;
            case 2: //normal
                return saveGoal * 0.75; //75% of the goal
            case 3: //conservative
                return saveGoal * 0.5; //50% of the goal
            default:
                return saveGoal;
        }
    }
}
