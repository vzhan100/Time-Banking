/*
CIS 454
Time Banker App
Author: Joseph Urie
Group #18
Date: April 29th, 2018
*/

package com.timebanking.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobActivity extends AppCompatActivity {
    //Globals
    ListView listView;
    public static ArrayList<Job> jobList;
    ArrayAdapter adapter;
    public static String jobID;
    FirebaseDatabase database;
    DatabaseReference jobRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        //Firebase Authentication - Verify who is logged in
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userId = firebaseUser.getUid();

        //List View
        listView = findViewById(R.id.listView);

        //Database
        database = FirebaseDatabase.getInstance();
        jobRef = database.getReference("users").child(userId).child("accepted_jobs");
        addValueEventListener(jobRef);

        //When you click the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do Something in response to button
                Intent intent = new Intent(view.getContext(), DisplayJob.class);
                intent.putExtra(jobID, position);
                startActivity(intent);
            }
        });
    }

    private void addValueEventListener(final DatabaseReference databaseReference) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobList = new ArrayList<>();
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                for (DataSnapshot aSnapshotIterator : snapshotIterator) {
                    Job value = aSnapshotIterator.getValue(Job.class);
                    jobList.add(value);
                }
                ArrayAdapter adapter = setupAdapter(jobList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private ArrayAdapter setupAdapter(final ArrayList<Job> jobList) {
        adapter = new ArrayAdapter<Job>(this, android.R.layout.simple_list_item_2, android.R.id.text1, jobList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);
                String displayText1 = (jobList.get(position).getTitle()); //+"\nPosted by: "+jobList.get(position).getUserId()
                text1.setText(displayText1);
                text1.setTextSize((float) 18.0); //Make Job Title bigger
                String displayText2 = (jobList.get(position).getDesc()+
                                        "\nPay: "+jobList.get(position).getPay()+
                                        "\n"+jobList.get(position).getAddress());
                text2.setText(displayText2);
                text2.setTextSize((float) 12.0);
                return view;
            }
        };
        return adapter;
    }

    //Sends you to the home page when clicked
    public void sendHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Sends you to your profile page when clicked
    public void sendProfile(View view) {
        Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
    }

    /*
    //View notifications when clicked
    public void sendNotifications(View view) {
        Intent intent = new Intent(this, //class here//);
        startActivity(intent);
    }
    */
}
