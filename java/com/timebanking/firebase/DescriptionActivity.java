package com.timebanking.firebase;

import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import com.timebanking.firebase.R;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;
import static java.sql.DriverManager.println;

public class DescriptionActivity extends AppCompatActivity {
    private static final String TAG = "DescriptionActivity";


    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference databaseReference;
    //FirebaseUser user;


    private Button acceptButton;
    private ListView mListView;
    private String userId;
    private String jobId;



    ArrayList<String> namelist;
    ArrayList<String> emaillist;
    ArrayList<String> locationlist;
    ArrayList<String> payllist;
    ArrayList joblist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //mFirebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = mFirebaseDatabase.getReference("users").child();
        mAuth = FirebaseAuth.getInstance();
        mListView = (ListView) findViewById(R.id.listview);
        acceptButton = findViewById(R.id.accept);



        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptJob();
            }
        });



    }

    private void setAdapter(){
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        userId = mFirebaseUser.getUid();
        jobId = databaseReference.child("users").child(userId).child("accepted_jobs").push().getKey();
    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();
        println("this is the user id: " + userId);


    }
/*
    public void addValueEventListener (final DatabaseReference jobsReference){
        jobsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                joblist = new ArrayList<>();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
*/

    private void acceptJob() {
        //Remove Job from DataBase
    }





}
