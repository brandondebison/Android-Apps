package com.example.assignment3.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignment3.R;

public class NotificationsFragment extends Fragment {

    private SharedPreferences pref;
    private TextView label;
    private double calcuation, dblDegree;
    private String degree;
    private boolean CelOrFah;

    //background tap variables lines needed are marked for auto close off focus
    private Context mContext; //Mark
    private Activity mActivity; //Mark
    private ConstraintLayout cLayout; //Mark





    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
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

        label = root.findViewById(R.id.lblFinal);

        //Opening the Shared Preferences
        pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        // Collecting the Information
        CelOrFah = pref.getBoolean("choice", false);

        degree = pref.getString("degree", null);
        dblDegree = Double.parseDouble(degree);


        // Calculations and display function for each cel and fah
        if (CelOrFah == true) {
            calcuation = (dblDegree - 32) / 1.8;
            label.setText(pref.getString("username", null) + " Your Calulation is for: " + dblDegree + " is " + calcuation);

        } else {
            calcuation = (dblDegree * 1.8) + 32;
            label.setText(pref.getString("username", null) + " Your Calulation is: " + dblDegree + " is " + calcuation);

        }


        return root;
    }
}