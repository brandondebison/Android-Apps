package com.example.sqlexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Details extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DbHandler dbHandler = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = dbHandler.GetUsers();
        ListView lv = findViewById(R.id.user_list);

        ListAdapter adapter = new SimpleAdapter(Details.this, userList, R.layout.list_row,
                new String[]{"name", "designation","location"},
                new int[]{R.id.name, R.id.designation, R.id.location});

        lv.setAdapter(adapter);
        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Details.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
