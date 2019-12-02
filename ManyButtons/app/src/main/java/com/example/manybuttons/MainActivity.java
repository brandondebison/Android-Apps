package com.example.manybuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonHandler bh = new ButtonHandler();
        findViewById(R.id.button1).setOnClickListener(bh);
        findViewById(R.id.button2).setOnClickListener(bh);
        findViewById(R.id.button3).setOnClickListener(bh);

    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view){
            switch(view.getId()) {
                case R.id.button1: show("Button 1");
                    break;
                case R.id.button2: show("Button 2");
                    break;
                case R.id.button3: show("Button 3");
                    break;
                default: System.out.println("No");
            }
        }
    }


    public void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Log.i(getClass().getName(), msg);
    }

}
