package com.example.mysharedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {


    SharedPreferences prefDet;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView result = findViewById(R.id.resultView);
        Button logOut = findViewById(R.id.btnLogOut);
        prefDet = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(Details.this, MainActivity.class);
        result.setText("Hello " + prefDet.getString("username", null));

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefDet.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });



    }
}
