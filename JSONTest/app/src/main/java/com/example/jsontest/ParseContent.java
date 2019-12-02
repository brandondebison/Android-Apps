package com.example.jsontest;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ParseContent {

    private final String KEY_SUCCESS = "status";
    private final String KEY_MSG = "message";
    private Activity activity;
    ArrayList<HashMap<String, String >> arrayList;


    public ParseContent(Activity activity) {
        this.activity = activity;
    }

    public boolean isSuccess(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString(KEY_SUCCESS).equals("true")) {
                return true;
            } else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorCode(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(KEY_MSG);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "No Data!";
    }

    public ArrayList<PlayersModel> getInfo(String response) {
        ArrayList<PlayersModel> playersModelArrayList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")) {

                arrayList = new ArrayList<HashMap<String, String>>();
                JSONArray dataArray = jsonObject.getJSONArray("data");

                for (int i=0; i < dataArray.length(); i++) {
                    PlayersModel playersModel = new PlayersModel();
                    JSONObject dataObj = dataArray.getJSONObject(i);

                    playersModel.setName(dataObj.getString(ProgramConstants.Params.NAME));
                    playersModel.setCountry(dataObj.getString(ProgramConstants.Params.COUNTRY));
                    playersModel.setCity(dataObj.getString(ProgramConstants.Params.CITY));

                    playersModelArrayList.add(playersModel);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return playersModelArrayList;
    }

}
