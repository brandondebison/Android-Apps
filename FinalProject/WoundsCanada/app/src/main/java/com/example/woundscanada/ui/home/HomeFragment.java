package com.example.woundscanada.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.woundscanada.AboutActivity;
import com.example.woundscanada.MainActivity;
import com.example.woundscanada.R;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private HomeViewModel homeViewModel;

    private Button aboutBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        //this is view, changed form root to v
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        //initialize button to trigger intent for About page
        aboutBtn = v.findViewById(R.id.aboutBtn);

        aboutBtn.setOnClickListener(this);

        return v;
    }

    //change home fragment to about
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aboutBtn:
                //intent to About page
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
        }
    }




}