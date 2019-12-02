package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ParseContent parseContent;
    private final int jsoncode = 1;
    private ListView listView;
    private ArrayList<PlayersModel> playersModelArrayList;
    private CustomAdapter customAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseContent = new ParseContent(this);
        listView = findViewById(R.id.lv);

        try {
            parseJson();
        } catch (IOException e) {
            // this exception is for if the network fails, no connection
            e.printStackTrace();
        } catch (JSONException e) {
            // this will throw if there are errors in the data itself that is being gathered
            e.printStackTrace();
        }

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

    }


    public void onTaskCompleted (String response, int serviceCode) {
        Log.d("responseJson", response.toString());
        switch (serviceCode){
            case jsoncode:
                if(parseContent.isSuccess(response)) {
                    ProgramUtils.removeSimpleProgressDialog();
                    playersModelArrayList = parseContent.getInfo(response);
                    customAdapter = new CustomAdapter(this, playersModelArrayList);
                    listView.setAdapter(customAdapter);

                } else {
                    Toast.makeText(MainActivity.this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }
}
