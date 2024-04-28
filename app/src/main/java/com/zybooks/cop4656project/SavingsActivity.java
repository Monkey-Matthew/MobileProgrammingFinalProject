package com.zybooks.cop4656project;

//Libraries
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;

public class SavingsActivity extends AppCompatActivity {


    //Declares and initializes 2 variables which are referenced from the SecondActivity.java file.
    int amountSaved = HomeActivity.savings1AmountSaved;
    int amountLeft = HomeActivity.savings1Goal - HomeActivity.savings1AmountSaved;

    double goalPercentage = ((double) amountSaved / (amountSaved + amountLeft)) * 100;


    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savings);

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedGoalPercentage = df.format(goalPercentage);

        // Grab reference to TextView
        TextView textViewGoalStatus = findViewById(R.id.textViewGoalStatus);

        // Set the text value for the TextView
        textViewGoalStatus.setText("You are " +  formattedGoalPercentage + "% done.");

        // Grab reference to TextView
        TextView amountLeftTextView = findViewById(R.id.amountLeftTextView);

        // Set the text value for the TextView
        amountLeftTextView.setText("$" +  amountLeft + " left to save out of $" + (amountLeft + amountSaved));

        Button backButton = findViewById(R.id.button_back);

        // Find the TextView by its ID
        TextView addToSavingstextView = findViewById(R.id.addToSavingsButton);
        TextView removeFromSavingstextView = findViewById(R.id.removeFromSavingsButton);

        // Set OnClickListener to open InputSpending activity when the TextView is clicked
        addToSavingstextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavingsActivity.this, AddToSavings.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener to open InputSpending activity when the TextView is clicked
        removeFromSavingstextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavingsActivity.this, RemoveFromSavings.class);
                startActivity(intent);
            }
        });

        //Grabs the ids for the textViews and the pie chart to alter them in the method below.
        pieChart = findViewById(R.id.savings1piechart);

        //Calls method to set the data and change the pie chart.
        setData();

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
        // Clear any existing pie slices
        pieChart.clearChart();
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

    @Override
    public void onResume() {
        super.onResume();

        amountSaved = HomeActivity.savings1AmountSaved;
        amountLeft = HomeActivity.savings1Goal - HomeActivity.savings1AmountSaved;
        goalPercentage = ((double) amountSaved / (amountSaved + amountLeft)) * 100;

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedGoalPercentage = df.format(goalPercentage);
        TextView textViewGoalStatus = findViewById(R.id.textViewGoalStatus);
        textViewGoalStatus.setText("You are " +  formattedGoalPercentage + "% done.");

        TextView amountLeftTextView = findViewById(R.id.amountLeftTextView);
        amountLeftTextView.setText("$" +  amountLeft + " left to save out of $" + (amountLeft + amountSaved));

        setData();
    }
}