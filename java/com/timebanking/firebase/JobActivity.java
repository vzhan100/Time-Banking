/*
CIS 454
Time Banker App
Author: Joseph Urie
Date: April 11th, 2018
*/

package com.timebanking.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class JobActivity extends AppCompatActivity {
    //Globals
    public static final String[] testNames = {"ann", "bob", "joe", "sue", "james", "jon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List View and Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, testNames);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do Something in response to button
                Intent intent = new Intent(view.getContext(), DisplayMessageActivity.class);
                startActivity(intent);
            }
        });
    }
}
