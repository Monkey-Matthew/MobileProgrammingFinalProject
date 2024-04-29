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

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class HomeFragment extends Fragment {

    //Creates two static ints that can be referenced in other activities and xml files.
    int initialBudget = HomeActivity.initialBudget;
    int budgetSpent = HomeActivity.budgetSpent;
    int budgetLeft = initialBudget - budgetSpent;

    int savings1Goal = HomeActivity.savings1Goal;
    int savings1AmountSaved = HomeActivity.savings1AmountSaved;
    int savings1AmountLeft = savings1Goal - savings1AmountSaved;

    //Creates the object pieChart class.
    private PieChart pieChart;
    private PieChart savings1pieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home, container, false);

        //This references the pieChartLayout so that we can have the layout act like a button.
        LinearLayout pieChartLayout = view.findViewById(R.id.pieChartView);

        pieChartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), PieChartActivity.class);
                startActivity(intent);
            }
        });

        //This references the pieChartLayout so that we can have the layout act like a button.
        LinearLayout settings1PieChartLayout = view.findViewById(R.id.savings1pieChartView);

        settings1PieChartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), SavingsActivity.class);
                startActivity(intent);
            }
        });

        // Find the TextView by its ID
        TextView textView = view.findViewById(R.id.addStatementButton);

        // Set OnClickListener to open InputSpending activity when the TextView is clicked
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), InputSpending.class);
                startActivity(intent);
            }
        });


        TextView viewStatementsTextView = view.findViewById(R.id.viewStatementsButton);

        viewStatementsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ViewStatements.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the settings button
        Button settingsButton = view.findViewById(R.id.bottomRightButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SettingsActivity when the button is clicked
                Intent settingsIntent = new Intent(requireActivity(), SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        //Grabs the id for the pieChart so we can update how it looks.
        pieChart = view.findViewById(R.id.piechart);
        savings1pieChart = view.findViewById(R.id.savings1piechart);

        //Calls to setData for the pie chart.
        setData();

        return view;
    }

    private void setData() {
        // Clear any existing pie slices
        pieChart.clearChart();
        savings1pieChart.clearChart();

        // Sets the data and the colors to the pie chart for the overall budget
        pieChart.addPieSlice(
                new PieModel(
                        "Budget Spent",
                        budgetSpent,
                        Color.parseColor("#FF5722")));
        pieChart.addPieSlice(
                new PieModel(
                        "Budget Left",
                        budgetLeft,
                        Color.parseColor("#000000")));

        // Sets the data and the colors to the pie chart for savings 1
        savings1pieChart.addPieSlice(
                new PieModel(
                        "Amount Saved",
                        savings1AmountSaved,
                        Color.parseColor("#FF5722")));
        savings1pieChart.addPieSlice(
                new PieModel(
                        "Amount Left",
                        savings1AmountLeft,
                        Color.parseColor("#000000")));


        // Animates the pie charts
        pieChart.startAnimation();
        savings1pieChart.startAnimation();
    }

    @Override
    public void onResume() {
        super.onResume();

        initialBudget = HomeActivity.initialBudget;
        budgetSpent = HomeActivity.budgetSpent;
        budgetLeft = initialBudget - budgetSpent;

        savings1Goal = HomeActivity.savings1Goal;
        savings1AmountSaved = HomeActivity.savings1AmountSaved;
        savings1AmountLeft = savings1Goal - savings1AmountSaved;

        setData();
    }
}
