package com.example.woundscanada.ui.publications;
import com.example.woundscanada.R;
import com.example.woundscanada.WebviewDisplay;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PublicationsFragment extends Fragment {

    private PublicationsViewModel mViewModel;
    ListView listView;
    String [] pdfURLs = {
            "https://www.woundscanada.ca/docman/public/wound-care-canada-magazine/wcc-2019-v17-no1/1415-wcc-spring-2019-v17n1-final-2/file",
            "https://www.woundscanada.ca/docman/public/wound-care-canada-magazine/2018-vol-16-no-2/1381-wcc-winter-2018-v16n2-r1-final/file"
    };
    String [] pdfTitles = {
            "Volume 17 - Issue 1",
            "Volume 16 - Issue 2"
    };

    public static PublicationsFragment newInstance() {
        return new PublicationsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.publications_fragment, container, false);
        listView = v.findViewById(R.id.pdfListView);//link the listviews

        //make the array list adapter to display in the listview
        ArrayList<String> pdfList = new ArrayList<>();
        for(int i = 0; i < pdfURLs.length; i++){
            pdfList.add(pdfTitles[i]);//populate the list with the titles of the PDFs
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, pdfList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pdfURL = pdfURLs[position];
                Intent intent = new Intent(getActivity(), WebviewDisplay.class);
                intent.putExtra("pdfURL", pdfURL);//pass in the file id selected
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PublicationsViewModel.class);
        // TODO: Use the ViewModel
    }

}
