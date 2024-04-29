package com.zybooks.cop4656project;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.zybooks.cop4656project.models.Budget;
import com.zybooks.cop4656project.repo.BudgetRepository;


import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


public class HomeFragment extends Fragment {


    private PieChart pieChart;
    private PieChart savings1PieChart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        BudgetRepository budgetRepository = BudgetRepository.getInstance(requireContext());

        pieChart = view.findViewById(R.id.piechart);
        savings1PieChart = view.findViewById(R.id.savings1piechart);

        view.findViewById(R.id.pieChartView).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), PieChartActivity.class)));
        view.findViewById(R.id.savings1pieChartView).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), SavingsActivity.class)));
        view.findViewById(R.id.addStatementButton).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), InputSpending.class)));
        view.findViewById(R.id.bottomRightButton).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), SettingsActivity.class)));


        budgetRepository.getBudget().observe(getViewLifecycleOwner(), this::updateUI);

        TextView viewStatementsTextView = view.findViewById(R.id.viewStatementsButton);
        viewStatementsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ViewStatements.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void updateUI(Budget budget) {
        if (budget != null) {
            double adjustedSaveGoal = calculateSaveGoal(budget.getMonthlySaveGoal(), budget.getSavingsType());
            double budgetLeft = budget.getMonthlyIncome() - adjustedSaveGoal;
            double savingsLeft = adjustedSaveGoal;


            pieChart.clearChart();
            savings1PieChart.clearChart();

            pieChart.addPieSlice(new PieModel("Spent", 0, Color.parseColor("#FF5722")));
            pieChart.addPieSlice(new PieModel("Left", (float) budgetLeft, Color.parseColor("#000000")));

            savings1PieChart.addPieSlice(new PieModel("Saved", 0, Color.parseColor("#FF5722")));
            savings1PieChart.addPieSlice(new PieModel("Goal Left", (float) savingsLeft, Color.parseColor("#000000")));

            pieChart.startAnimation();
            savings1PieChart.startAnimation();
        }
    }

    private double calculateSaveGoal(double saveGoal, long savingsType) {
        switch ((int) savingsType) {
            case 1: //aggressive
                return saveGoal * 0.5;
            case 2: //normal
                return saveGoal * 0.75;
            case 3: //conservative
                return saveGoal;
            default:
                return saveGoal;
        }
    }
}
