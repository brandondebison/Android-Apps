package com.example.intent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int bigno, smallno  = 0;
        int rem = 1;

        //get bundle data from the intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundledata");

        TextView gcftext = findViewById(R.id.textView);

        if (bundle != null && !bundle.isEmpty()) {
            int first = bundle.getInt("fno", 1);
            int second = bundle.getInt("sno", 1);

            if(first > second) {
                bigno = first;
                smallno = second;
            } else {
                bigno = second;
                smallno = first;
            }

            while ( (rem = bigno % smallno) != 0) {
                bigno = smallno;
                smallno = rem;
            }
            gcftext.setText(String.format("GCF = %d", smallno));

        }

        Button close = findViewById(R.id.button2);
        assert close != null;
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}
