package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

        if (savedInstanceState == null) {
            // Begin a new FragmentTransaction for adding a HomeFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Add the HomeFragment to the fragment container
            fragmentTransaction.setReorderingAllowed(true)
                    .add(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }

}
