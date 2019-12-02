package com.example.woundscanada.ui.tools;
import com.example.woundscanada.R;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ToolsFragment extends Fragment {

    private ToolsViewModel mViewModel;

    public static ToolsFragment newInstance() {
        return new ToolsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //db handler code goes here i think which would be second parameter in ListAdapter SimpleAdapter code
//        DbHandler dbHandler = new DbHandler(this);
//        ArrayList<HashMap<String, String>> userList = dbHandler.GetUsers();
        String[] userList = {"tools", "fragment", "example"};

        View v = inflater.inflate(R.layout.tools_fragment, container, false);
        ListView lvBestPractice = v.findViewById(R.id.list_tools);

        //for DB need to use arrayadapter then simpleradapter from the SQL example
        //but to test the program i've used arrayadapter for the hardcoded array of strings
        //first 3 params are related to the data ur getting, the control/data working with, and list row (resource.
        //the last 2 are a list of column names added to the map (like headers), and the second are linked to where they will be display
//        ListAdapter adapter = new SimpleAdapter(Details.this, userList, R.layout.list_row, new String[]{"name", "designation", "location"}, new int[]{R.id.name, R.id.designation, R.id.location});

        ArrayAdapter adapter;
        adapter = new ArrayAdapter(getActivity(), R.layout.list_row, R.id.txtPDFName, userList);


        lvBestPractice.setAdapter(adapter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ToolsViewModel.class);
        // TODO: Use the ViewModel
    }

}
