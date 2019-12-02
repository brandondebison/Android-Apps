package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int numberCalculated = 0;
    private EditText eName;
    private EditText eCel;
    private TextView tOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = (EditText)findViewById(R.id.enterName);
        eCel = (EditText)findViewById(R.id.enterCelsius);
        tOut = (TextView)findViewById(R.id.outputLabel);
        Button b = (Button)findViewById(R.id.buttonF);
        b.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){
        // Entry comes in as a string and needs to be changed into a double for calculations
        double number = Double.parseDouble(eCel.getText().toString());
        // Getting the name
        String name = eName.getText().toString();
        //Calculation
        double numCal = ((number * 1.8) + 32);
        // Changing the textview / label
        tOut.setText(name +" the calculated fahrenheit is " + numCal);

    }

}
