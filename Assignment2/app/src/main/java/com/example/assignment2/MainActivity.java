package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //background tap variables lines needed are marked for auto close off focus
    private Context mContext; //Mark
    private Activity mActivity; //Mark
    private ConstraintLayout cLayout; //Mark

    private EditText enteredName;
    private Button btnSubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Background tap code for auto close
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

        enteredName = findViewById(R.id.eTName);
        btnSubName = findViewById(R.id.btnSubName);
        btnSubName.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        boolean a = TextUtils.isEmpty(enteredName.getText());
    // ensuring that something has been entered before creating the intent
        if (!a) {
    // creating the intent and bundle
            Intent intent1 = new Intent(this, SecondActivity.class);
            String data =  enteredName.getText().toString();

            System.out.println("Onclick data is " +data );

            Bundle bundle = new Bundle();
            bundle.putString("enteredName", data);
            intent1.putExtra("usersName", bundle);
            startActivity(intent1);
       }

    }

    @Override
    protected void onStart() {
        super.onStart();
        enteredName.setText("");
    }
}
