package com.timebanking.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;




public class AddJobActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        Spinner paySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddJobActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pay_amounts));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paySpinner.setAdapter(myAdapter);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addJob (View view){

    }
}
