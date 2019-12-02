package com.example.mysingleton;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView txtWelcome  = (TextView) findViewById(R.id.txtWelcome);

        //Displaying our username using singleton class getter.
        txtWelcome.setText("Welcome\n" + SingletonSession.Instance().getUsername() +" You have made a Singleton App ");
    }
}