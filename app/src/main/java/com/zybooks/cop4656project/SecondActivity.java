package com.zybooks.cop4656project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class SecondActivity extends AppCompatActivity {

    //Creates two static ints that can be referenced in other activities and xml files.
    public static int initialBudget = 5000;
    public static int budgetSpent = 3000;
    public static int budgetLeft = initialBudget - budgetSpent;

    //Creates the object pieChart class.
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //This references the pieChartLayout so that we can have the layout act like a button.
        LinearLayout pieChartLayout = findViewById(R.id.pieChartView);

        pieChartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, PieChartActivity.class);
                startActivity(intent);
            }
        });

        //Grabs the id for the pieChart so we can update how it looks.
        pieChart = findViewById(R.id.piechart);

        // Set OnClickListener for the settings button
        Button settingsButton = findViewById(R.id.bottomRightButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SettingsActivity when the button is clicked
                Intent settingsIntent = new Intent(SecondActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        //Calls to setData for the pie chart.
        setData();
    }

    private void setData() {
        // Clear any existing pie slices
        pieChart.clearChart();

        //Sets the data and the colors to the pie chart.
        pieChart.addPieSlice(
                new PieModel(
                        "BudgetLeft",
                        budgetSpent,
                        Color.parseColor("#000000")));
        pieChart.addPieSlice(
                new PieModel(
                        "BudgetSpent",
                        budgetLeft,
                        Color.parseColor("#FF5722")));

        //Animates the pie chart.
        pieChart.startAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setData();
    }

}
