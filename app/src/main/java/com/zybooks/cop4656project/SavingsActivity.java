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

public class SavingsActivity extends AppCompatActivity {


    //Declares and initializes 2 variables which are referenced from the SecondActivity.java file.
    int amountSaved = HomeActivity.savings1AmountSaved;
    int amountLeft = HomeActivity.savings1AmountLeft;


    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savings);


        //Grabs the ids for the textViews and the pie chart to alter them in the method below.
        pieChart = findViewById(R.id.savings1piechart);

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


        //Set the data and color to the pie chart (we can always change colors later)
        pieChart.addPieSlice(
                new PieModel(
                        "AmountSaved",
                        amountSaved,
                        Color.parseColor("#FF5722")));
        pieChart.addPieSlice(
                new PieModel(
                        "AmountLeft",
                        amountLeft,
                        Color.parseColor("#000000")));

        //Animates the pie chart.
        pieChart.startAnimation();
    }
}