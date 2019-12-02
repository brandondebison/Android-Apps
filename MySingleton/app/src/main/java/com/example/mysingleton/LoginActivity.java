package com.example.mysingleton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final EditText editUsername  = (EditText) findViewById(R.id.editUsername);

        btnLogin.setOnClickListener(new View.OnClickListener() {


            // This app is to set a username that will be accessible everywhere by calling the SingletonSession class. Currently there is only a username but you can put other variables should you wish.
            // Then using the getters and setters you create with the class you can update and change them.

            @Override
            public void onClick(View v) {
                // On login button click, storing our username into singleton class. If an there is already one created it will use that, if not create a new one
                SingletonSession.Instance().setUsername(editUsername.getText().toString());

                Intent welcomeActivity = new Intent(LoginActivity.this, WelcomeActivity.class);
                startActivity(welcomeActivity);
            }
        });
    }
}