package com.example.woundscanada;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myApp";

    private static SharedPreferences shared;

    private ParseContent parseContent;
    private final int jsoncode = 1;
    private ListView listView;
    private ArrayList<PlayersModel> bestPractices;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        Log.d(TAG, "in MAIN activity");
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_best_practices, R.id.navigation_publications, R.id.navigation_tools, R.id.navigation_events)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        parseContent = new ParseContent(this);
//        listView = findViewById(R.id.lv);

        try {
            parseJson();
        } catch (IOException e) {
            // this exception is for if the network fails, no connection
            e.printStackTrace();
        } catch (JSONException e) {
            // this will throw if there are errors in the data itself that is being gathered
            e.printStackTrace();
        }
        Log.d(null, "loaded");

    }

    private void parseJson() throws IOException, JSONException {

        if (!ProgramUtils.isNetworkAvailable(MainActivity.this)) {
            Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
        }

        ProgramUtils.showSimpleProgressDialog(MainActivity.this);
        new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void[] params) {
                String response = "";
                HashMap<String, String> map = new HashMap<>();

                try {
                    HttpRequest req = new HttpRequest(ProgramConstants.ServiceType.url);
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response = e.getMessage();
                }

                return response;
            }

            protected void onPostExecute(String result) {
                Log.d("data", result);
                onTaskCompleted(result, jsoncode);
            }
        }.execute();
        Log.d(null, "TEST after onPost Execute");
    }


    public void onTaskCompleted (String response, int serviceCode) {

        Log.d("responseJson", response.toString());
        switch (serviceCode) {
            case jsoncode:
                if (parseContent.isSuccess(response)) {
                    Log.d(TAG, "parsecontent");


                    ProgramUtils.removeSimpleProgressDialog();
                    bestPractices = parseContent.getInfo(response);
                    customAdapter = new CustomAdapter(this, bestPractices);


                    String key = "Key";

                    ArrayList<PlayersModel> ModelArrayList = bestPractices;

                    SharedPreferences shref;
                    SharedPreferences.Editor editor;
                    shref = this.getSharedPreferences("data", MODE_PRIVATE);

                    Gson gson = new Gson();
                    String json = gson.toJson(ModelArrayList);

                    editor = shref.edit();
                    editor.remove(key).commit();
                    editor.putString(key, json);
                    editor.commit();


                } else {
                    Toast.makeText(MainActivity.this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }

        }

    }



}
