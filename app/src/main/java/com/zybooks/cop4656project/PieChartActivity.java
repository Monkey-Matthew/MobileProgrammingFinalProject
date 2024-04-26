package com.zybooks.cop4656project;

//Libraries
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class PieChartActivity extends AppCompatActivity {


    //Declares and initializes 2 variables which are refrenced from the SecondActivity.java file.
    int budgetSpent = SecondActivity.budgetSpent;
    int budgetLeft = SecondActivity.budgetLeft;

    //Creates the object of TextView and PieChart class.
    TextView textViewSpent, textViewBudgetLeft;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart);


        //Grabs the ids for the textViews and the pie chart to alter them in the method below.
        textViewSpent = findViewById(R.id.textViewSpent);
        textViewBudgetLeft = findViewById(R.id.textViewBudgetLeft);
        pieChart = findViewById(R.id.piechart);

        //Calls method to set the data and change the pie chart.
        setData();
    }

    private void setData()
    {

        //Assigns the text to be what is defined in SecondActivity.java values for budgetSpent and budgetLeft.
        textViewSpent.setText(String.valueOf(budgetSpent));
        textViewBudgetLeft.setText(String.valueOf(budgetLeft));

        //Set the data and color to the pie chart (we can always change colors later)
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