package com.example.intent2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //background tap variables lines needed are marked for auto close off focus
    private Context mContext; //Mark
    private Activity mActivity; //Mark
    private ConstraintLayout cLayout; //Mark

    private EditText fno;
    private EditText sno;
    private Button btn;

    private final String TAG = "GCF app"; //for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Background tap code
        mContext = getApplicationContext(); //Mark
        mActivity = MainActivity.this; //Mark
        cLayout = findViewById(R.id.constraint_layout); //Mark, for this you need to add an id in text section of activity main      android:id="@+id/constraint_layout">
        cLayout.setOnClickListener(new View.OnClickListener() { //Mark
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//Mark
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0); //Mark
            }
        });

        fno = findViewById(R.id.firstno);
        sno = findViewById(R.id.secondno);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                processButtonTask();
                break;
                default:
                    Log.i(TAG, "click object not found");
        }

    }

    private void processButtonTask () {
        boolean a = TextUtils.isEmpty(fno.getText());
        boolean b = TextUtils.isEmpty(sno.getText());

        if (!a && !b) {
            int firstnumber = Integer.parseInt(fno.getText().toString());
            int secondnumber = Integer.parseInt(sno.getText().toString());

            Intent intent = new Intent(this, SecondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("fno", firstnumber);
            bundle.putInt("sno", secondnumber);
            intent.putExtra("bundledata", bundle);
            startActivity(intent);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fno.setText("");
        sno.setText("");
    }
}
