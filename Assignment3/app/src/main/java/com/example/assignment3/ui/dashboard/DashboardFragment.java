package com.example.assignment3.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignment3.R;

public class DashboardFragment extends Fragment {

    private SharedPreferences pref;
    private EditText number;
    private Button btn2;
    private TextView result;
    private Switch choice;
    boolean CelOrFah;

    //background tap variables lines needed are marked for auto close off focus
    private Context mContext; //Mark
    private Activity mActivity; //Mark
    private ConstraintLayout cLayout; //Mark

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        cLayout = root.findViewById(R.id.constraint_layout); //Mark, for this you need to add an id in text section of activity main      android:id="@+id/constraint_layout">
        cLayout.setOnClickListener(new View.OnClickListener() { //Mark
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//Mark
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0); //Mark
            }
        });


        // Connecting to the XML file
        choice = root.findViewById(R.id.switch1);
        result = root.findViewById(R.id.lblWelcome);
        btn2 = root.findViewById(R.id.btnTab2);
        number = root.findViewById(R.id.txtNumber);

        // listener for the switch button
        choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                choice.setText("Celcius");
                if (choice.isChecked() != true) {
                    choice.setText("Fahrenheit");
                }
            }
        });


        // Getting the information
        pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        // Collecting the Information and outputing to the label
        result.setText("Hello " + pref.getString("username", null));

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CelOrFah = choice.isChecked();
                // Saving the Information to shared preferences
                String degree = number.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("degree", degree);
                editor.putBoolean("choice", CelOrFah);

                editor.commit();
            }
        });



        return root;
    }
}