package com.example.woundscanada.ui.bestPractices;
import com.example.woundscanada.CustomAdapter;
import com.example.woundscanada.ObjectSerializer;
import com.example.woundscanada.PlayersModel;
import com.example.woundscanada.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.preference.PreferenceManager;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BestPracticesFragment extends Fragment {

    private ArrayList<PlayersModel> playersModelArrayList;
    private CustomAdapter customAdapter;
    SharedPreferences shared;
    private ArrayList<PlayersModel> bestPractices;



    private BestPracticesViewModel mViewModel;

    public static BestPracticesFragment newInstance() {
        return new BestPracticesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(null, "TEST in BEST PRACT");
        //db handler code goes here i think which would be second parameter in ListAdapter SimpleAdapter code
//        DbHandler dbHandler = new DbHandler(this);
//        ArrayList<HashMap<String, String>> userList = dbHandler.GetUsers();
        String[] userList = {"best", "practice", "example"};

        View v = inflater.inflate(R.layout.best_practices_fragment, container, false);
        ListView lvBestPractice = v.findViewById(R.id.list_best_practices);

        //for DB need to use arrayadapter then simpleradapter from the SQL example
        //but to test the program i've used arrayadapter for the hardcoded array of strings
        //first 3 params are related to the data ur getting, the control/data working with, and list row (resource.
        //the last 2 are a list of column names added to the map (like headers), and the second are linked to where they will be display
//        ListAdapter adapter = new SimpleAdapter(Details.this, userList, R.layout.list_row, new String[]{"name", "designation", "location"}, new int[]{R.id.name, R.id.designation, R.id.location});

        ArrayAdapter adapter;
//        adapter = new ArrayAdapter(getActivity(), R.layout.list_row, R.id.txtPDFName, userList);
//        customAdapter = new CustomAdapter(getActivity(), playersModelArrayList);



//        SharedPreferences shared = this.getActivity().getSharedPreferences("JSON", Context.MODE_PRIVATE);
//        playersModelArrayList = shared;
//        try {
//            Log.d(null, "TEST in BEST PRACT try");
//            customAdapter = new CustomAdapter(getActivity(), playersModelArrayList);
////            playersModelArrayList = (ArrayList<PlayersModel>) ObjectSerializer.deserialize(shared.getString("data", ObjectSerializer.serialize(new ArrayList<PlayersModel>())));
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Log.d(null, "TEST before custome adapter");
//


//        SharedPreferences prefs = this.getActivity().getSharedPreferences("JSON", Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = prefs.getString("data", null);
//        Type type = new TypeToken<ArrayList<PlayersModel>>() {}.getType();

//        Gson gson = new Gson();
//        String response=prefs.getString(key , "data");
//        ArrayList<ModelClass> lstArrayList = gson.fromJson(response,
//                new TypeToken<List<ModelClass>>(){}.getType());

        String key = "Key";
        SharedPreferences shref;
        SharedPreferences.Editor editor;
        shref = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String response=shref.getString(key , "");
        ArrayList<PlayersModel> lstArrayList = gson.fromJson(response,
                new TypeToken<List<PlayersModel>>(){}.getType());



//        Log.d(null, json +": stuff");
//
//        customAdapter = new CustomAdapter(getActivity(), (ArrayList<PlayersModel>) type);
        customAdapter = new CustomAdapter(this.getActivity(), lstArrayList);
        lvBestPractice.setAdapter(customAdapter);
//
//
//
//
//        lvBestPractice.setAdapter(customAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BestPracticesViewModel.class);
        // TODO: Use the ViewModel
    }

}
