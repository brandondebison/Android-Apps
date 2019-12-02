package com.example.woundscanada.ui.bestPractices;
import com.example.woundscanada.AssetDisplayActivity;
import com.example.woundscanada.CustomAdapter;
import com.example.woundscanada.PlayersModel;
import com.example.woundscanada.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BestPracticesFragment extends Fragment {

    private CustomAdapter customAdapter;


    private BestPracticesViewModel mViewModel;
    //the name of the file
    String[] pdfFiles = {
            "Prevention-and-Management-of-Burns.pdf",
            "1.pdf",
            "2.pdf",
            "3.pdf",
            "4.pdf",
            "5.pdf"
    };
    //the title to display in the list view
    String[] pdfTitles = {
            "Prevention and Management of Burns",
            "One",
            "Two",
            "Three",
            "Four",
            "Five"
    };

    ListView listView;

    public static BestPracticesFragment newInstance() {
        return new BestPracticesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //db handler code goes here i think which would be second parameter in ListAdapter SimpleAdapter code
//        DbHandler dbHandler = new DbHandler(this);
//        ArrayList<HashMap<String, String>> userList = dbHandler.GetUsers();
        //String[] userList = {"best", "practice", "example"};

        View v = inflater.inflate(R.layout.best_practices_fragment, container, false);
//        ListView lvBestPractice = v.findViewById(R.id.list_best_practices);
        ListView lvBestPractice = v.findViewById(R.id.pdfListView);

//
//        //for DB need to use arrayadapter then simpleradapter from the SQL example
//        //but to test the program i've used arrayadapter for the hardcoded array of strings
//        //first 3 params are related to the data ur getting, the control/data working with, and list row (resource.
//        //the last 2 are a list of column names added to the map (like headers), and the second are linked to where they will be display
////        ListAdapter adapter = new SimpleAdapter(Details.this, userList, R.layout.list_row, new String[]{"name", "designation", "location"}, new int[]{R.id.name, R.id.designation, R.id.location});
//
//        ArrayAdapter adapter;
//        adapter = new ArrayAdapter(getActivity(), R.layout.list_row, R.id.txtPDFName, userList);
//
//
//        lvBestPractice.setAdapter(adapter);


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

        listView = v.findViewById(R.id.pdfListView);//link the listviews

        //make the array list adapter to display in the listview
        ArrayList<String> pdfList = new ArrayList<>();
        for(int i = 0; i < pdfFiles.length; i++){
            pdfList.add(pdfTitles[i]);//populate the list with the titles of the PDFs
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, pdfList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pdfFile = pdfFiles[position];
                Intent intent = new Intent(getActivity(), AssetDisplayActivity.class);
                intent.putExtra("pdfFile", pdfFile);//pass in the file id selected
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BestPracticesViewModel.class);
        // TODO: Use the ViewModel
    }

}
