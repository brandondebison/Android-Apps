package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        double answer = 0;

        // setting up the intent / bundle collection
        TextView displayAnswer = findViewById(R.id.tVAnswer);
        Intent intent3 = getIntent();
        Bundle bundle3 = intent3.getBundleExtra(("calculate"));

        if ((bundle3 != null) & !bundle3.isEmpty()) {

            int eNum = bundle3.getInt("enteredNumber", 1);
            String eName = bundle3.getString("enteredName", "");
            boolean deg = bundle3.getBoolean("choice");

            //System.out.println("degree act 3 = " + deg);

            // Calculations and display function for each cel and fah
            if (deg == true) {
                answer = (eNum - 32) / 1.8;
                displayAnswer.setText("Hi " + eName + " Your calculated temp of " + eNum + " Fahrenheit is " + answer +" Celsius");

            } else {
                answer = (eNum * 1.8) + 32;
                displayAnswer.setText("Hi " + eName + " Your calculated value of " + eNum + " Celsius is " + answer + " Fahrenheit");

            }
        }

        btnClose = findViewById(R.id.btnClose);
        findViewById(R.id.btnClose).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        // switch statement due to multiple buttons
        switch (view.getId()) {
            case R.id.btnClose:
                finish();
                break;

            default:
                Log.i(getClass().getName(), "Cannot resolve click");
        }

    }

}
