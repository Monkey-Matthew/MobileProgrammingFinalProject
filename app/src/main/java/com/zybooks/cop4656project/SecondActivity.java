package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class SecondActivity extends AppCompatActivity {

    //Creates two static ints that can be referenced in other activities and xml files.
    public static int budgetSpent = 3000;
    public static int budgetLeft = 2000;

    //Creates the object pieChart class.
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //This refrences the pieChartLayout so that we can have the layout act like a button.
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

        //Calls to setData for the pie chart.
        setData();
    }

    private void setData()
    {
        //Sets the data and the colors to the pie chart.
        pieChart.addPieSlice(
                new PieModel(
                        "BudgetLeft",
                        budgetSpent,
                        Color.parseColor("#000000")));
        pieChart.addPieSlice(
                new PieModel(
                        "BudgentSpent",
                        budgetLeft,
                        Color.parseColor("#FF5722")));

        //Animates the pie chart.
        pieChart.startAnimation();

    }
}