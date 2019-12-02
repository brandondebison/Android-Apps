package com.example.assignment2;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    //background tap variables lines needed are marked for auto close off focus
    private Context mContext; //Mark
    private Activity mActivity; //Mark
    private ConstraintLayout cLayout; //Mark

    private EditText enteredNumber;
    private TextView enteredName;
    private Button btnSubmitCal;
    private Switch choice;
    private boolean degree; // true is cel false is fah
    private String usersName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Background tap code
        mContext = getApplicationContext(); //Mark
        mActivity = SecondActivity.this; //Mark
        cLayout = findViewById(R.id.constraint_layout); //Mark, for this you need to add an id in text section of activity main      android:id="@+id/constraint_layout">
        cLayout.setOnClickListener(new View.OnClickListener() { //Mark
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//Mark
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0); //Mark
            }
        });

        // Setting up variables
        enteredName = findViewById(R.id.tVUserName);
        enteredNumber = findViewById(R.id.eTNumber);
        choice = findViewById(R.id.switch1);

        //Creating the intent/bundle to get information
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("usersName");

        if (bundle != null && !bundle.isEmpty()) {
            usersName = bundle.getString("enteredName", "" );
            enteredName.setText("Welcome "+ usersName + "\nPlease select Celsius or Fahrenheit and enter a degree.");
        }

        // listener for the switch button
        choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                choice.setText("Celcius");
                //System.out.println("degree = " + degree);
                if (choice.isChecked() != true) {
                    choice.setText("Fahrenheit");
                    //System.out.println("degree = " + degree);
                }
            }
        });

        btnSubmitCal = findViewById(R.id.btnSubmitCal);
        findViewById(R.id.btnSubmitCal).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        degree = choice.isChecked();
        //setting the decision variable
        if (degree == true ) {
            System.out.println("degree = " + degree);
            choice.setText("Celcius");
        } else {
            choice.setText("Fahrenheit");
            System.out.println("degree = " + degree);
        }

        //Switch statement due to multiple buttons
        switch (view.getId()) {
            case R.id.btnSubmitCal:
                boolean b = TextUtils.isEmpty(enteredNumber.getText());
                //Setting up the new intent/bundle for next var move
                if (!b) {
                    Intent intent2 = new Intent(this, ThirdActivity.class);
                    int eNum =  Integer.parseInt(enteredNumber.getText().toString());
                    String eName = usersName;
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("enteredNumber", eNum);
                    bundle2.putString("enteredName", eName);
                    bundle2.putBoolean("choice", degree);
                    intent2.putExtra("calculate", bundle2);
                    startActivity(intent2);
                }  break;


            default:
                Log.i(getClass().getName(), "Cannot resolve click");

        }


    }




}
