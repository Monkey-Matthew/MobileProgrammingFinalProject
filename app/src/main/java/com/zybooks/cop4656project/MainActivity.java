package com.zybooks.cop4656project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Finds the id of the welcome button to use later in the code.
        Button welcomeButton = findViewById(R.id.welcome_button);

        //Sets onClickListener that will start the SecondActivity once it is clicked.
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to start the new activity.
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                //Starts the new activity.
                startActivity(intent);
            }
        });
    }
}
