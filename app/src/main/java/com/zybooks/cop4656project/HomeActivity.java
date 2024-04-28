package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class HomeActivity extends AppCompatActivity {

    //Creates two static ints that can be referenced in other activities and xml files.
    public static int initialBudget = 5000;
    public static int budgetSpent = 3000;
    public static int budgetLeft = initialBudget - budgetSpent;

    public static int savings1Goal = 10000;
    public static int savings1AmountSaved = 3000;
    public static int savings1AmountLeft = savings1Goal - savings1AmountSaved;


    //Creates the object pieChart class.
    PieChart pieChart;
    PieChart savings1pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //This references the pieChartLayout so that we can have the layout act like a button.
        LinearLayout pieChartLayout = findViewById(R.id.pieChartView);

        pieChartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PieChartActivity.class);
                startActivity(intent);
            }
        });

        //This references the pieChartLayout so that we can have the layout act like a button.
        LinearLayout settings1PieChartLayout = findViewById(R.id.savings1pieChartView);

        settings1PieChartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Savings1Activity.class);
                startActivity(intent);
            }
        });



        // Find the TextView by its ID
        TextView textView = findViewById(R.id.addStatementButton);

        // Set OnClickListener to open InputSpending activity when the TextView is clicked
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InputSpending.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the settings button
        Button settingsButton = findViewById(R.id.bottomRightButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SettingsActivity when the button is clicked
                Intent settingsIntent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        //Grabs the id for the pieChart so we can update how it looks.
        pieChart = findViewById(R.id.piechart);
        savings1pieChart = findViewById(R.id.savings1piechart);

        //Calls to setData for the pie chart.
        setData();
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
    protected void onResume() {
        super.onResume();

        setData();
    }

}
