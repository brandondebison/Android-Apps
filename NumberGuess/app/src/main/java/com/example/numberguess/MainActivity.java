package com.example.numberguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private int numberToGuess = 0;
    private EditText e;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e = (EditText)findViewById(R.id.editText);
        t = (TextView)findViewById(R.id.textView);
        Button b = (Button)findViewById(R.id.button2);
        b.setOnClickListener(this);
        numberToGuess = initNumberToGuess();

    }

    @Override
    public void onClick(View View){
        int number = Integer.parseInt(e.getText().toString());
        if(number == numberToGuess) {
            t.setText(number + " is the correct number");
        } else if (number < numberToGuess) {
            t.setText(number + " is too low");
        } else {
            t.setText(number + " is too high");
        }
    }

    public int initNumberToGuess() {
        Random r = new Random();
        numberToGuess = r.nextInt(100)+50;
        Log.i("Number is: ", ""+ numberToGuess);
        return numberToGuess;

    }
}
