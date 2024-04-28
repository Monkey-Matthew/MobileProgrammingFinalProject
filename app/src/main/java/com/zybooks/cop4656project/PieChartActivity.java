package com.zybooks.cop4656project;

//Libraries
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class PieChartActivity extends AppCompatActivity {


    //Declares and initializes 2 variables which are referenced from the SecondActivity.java file.
    int budgetSpent = HomeActivity.budgetSpent;
    int budgetLeft = HomeActivity.initialBudget - HomeActivity.budgetSpent;

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

        Button backButton = findViewById(R.id.button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the previous activity
                finish();
            }
        });
    }

    private void setData()
    {

        //Assigns the text to be what is defined in SecondActivity.java values for budgetSpent and budgetLeft.
        textViewSpent.setText(String.valueOf(HomeActivity.budgetSpent));
        textViewBudgetLeft.setText(String.valueOf(budgetLeft));

        //Set the data and color to the pie chart (we can always change colors later)
        pieChart.addPieSlice(
                new PieModel(
                        "BudgetLeft",
                        budgetSpent,
                        Color.parseColor("#FF5722")));
        pieChart.addPieSlice(
                new PieModel(
                        "BudgentSpent",
                        budgetLeft,
                        Color.parseColor("#000000")));

        //Animates the pie chart.
        pieChart.startAnimation();
    }
}