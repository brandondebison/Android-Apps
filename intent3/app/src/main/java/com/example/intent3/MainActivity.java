package com.example.intent3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Constant to allow us to track where the intent comes from
    private static final int SECOND_ACTIVITY_CODE =1000;

    private Button b;
    private TextView t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button);
        t = findViewById(R.id.textView);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SECOND_ACTIVITY_CODE && resultCode == Activity.RESULT_OK){
            t.setText(data.getStringExtra("secondactivity"));
        }
    }
}
