package com.example.mythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        tv = findViewById(R.id.textView);
        TAG = getClass().getSimpleName();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                int i = 0;
                while (i < 15) {
                    try {
                        Thread.sleep(2000);
                        tv.setText(String.format("Value of i = %d", i));
                        Log.i(TAG, String.format("Value of i = %d", i));
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                */
                Worker w = new Worker();
                w.execute(tv);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Clicked");
            }
        });
    }
}
