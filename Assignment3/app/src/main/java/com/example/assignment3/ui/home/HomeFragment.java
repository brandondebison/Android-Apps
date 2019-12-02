package com.example.assignment3.ui.home;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignment3.R;

public class HomeFragment extends Fragment {

    private EditText username;
    private Button submitBtn;
    private SharedPreferences preferences;
    //background tap variables lines needed are marked for auto close off focus
    private Context mContext; //Mark
    private Activity mActivity; //Mark
    private ConstraintLayout cLayout; //Mark


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
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

        username = root.findViewById(R.id.txtName);
        submitBtn = root.findViewById(R.id.submitNBtn);

        preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Saving the Information to shared preferences

                String name = username.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", name);
                editor.commit();
            }
        });

        return root;




    }

//


}