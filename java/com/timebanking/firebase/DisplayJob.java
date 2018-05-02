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
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.timebanking.firebase.JobActivity.jobList;

public class DisplayJob extends AppCompatActivity {
    //Globals
    FirebaseDatabase database;
    DatabaseReference minRef;
    DatabaseReference userRef;

    int position;
    String jobAddress;
    String jobDesc;
    Long jobTime;
    String jobID;
    String jobPostID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_job);

        //Firebase Authentication - Verify who is logged in
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userId = firebaseUser.getUid();

        //Bringing over correct job data
        Intent intent = getIntent();
        position = intent.getIntExtra(JobActivity.jobID, 0);
        jobAddress = jobList.get(position).getAddress();
        jobDesc = jobList.get(position).getDesc();
        jobTime = jobList.get(position).getPay();
        jobID = jobList.get(position).getTitle();
        jobPostID = jobList.get(position).getUserId();

        //Database Access
        database = FirebaseDatabase.getInstance();
        minRef = database.getReference("users").child(userId);
        userRef = database.getReference("users").child(jobPostID);

        //Setting TextView Values
        TextView jobTitle = findViewById(R.id.jobTitle);
        jobTitle.setText(jobID);

        TextView jobDescription = findViewById(R.id.jobDescription);
        jobDescription.setText(jobDesc);

        TextView jobDisplay = findViewById(R.id.jobDisplay);
        jobDisplay.setText(jobAddress);

        TextView jobPay = findViewById(R.id.jobPay);
        jobPay.setText(String.valueOf(jobTime));

        TextView postID = findViewById(R.id.postID);
        String jobPostText = ("Posted by: " + userRef.child("name").getKey());
        postID.setText(jobPostText);

        final Long countdown = jobTime * 60000;

        //Button and Timer
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressBar progressBar = findViewById(R.id.progressBar);
                final TextView textCounter = findViewById(R.id.textView2);
                progressBar.setProgress(100);
                CountDownTimer countDownTimer = new CountDownTimer(countdown, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String displayText = ("Time Remaining:\n"+String.valueOf((millisUntilFinished/1000)/60)+":"+String.valueOf((millisUntilFinished/1000)%60));
                        textCounter.setText(displayText);
                        int progress = (int) (millisUntilFinished/6000);
                        progressBar.setProgress(progress);
                    }

                    @Override
                    public void onFinish() {
                        String displayText = ("Job Done!");
                        textCounter.setText(displayText);
                        progressBar.setProgress(0);
                        addMinutes(minRef); //User who completes job gets payed
                        subtractMinutes(userRef); //Deducts time from the user who posted
                        minRef.child("accepted_jobs").orderByChild("desc").equalTo(jobDesc).getRef().removeValue(); //Removes job
                    }
                };
                countDownTimer.start();
            }
        });
    }

    private void addMinutes(final DatabaseReference databaseReference) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = dataSnapshot.getValue(User.class);
                Long newMinutes = value.minutes;
                newMinutes += jobTime;
                databaseReference.child("minutes").setValue(newMinutes);
                Long newaMinutes = value.aMinutes;
                newaMinutes += jobTime;
                databaseReference.child("aMinutes").setValue(newaMinutes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void subtractMinutes(final DatabaseReference databaseReference) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = dataSnapshot.getValue(User.class);
                Long newMinutes = value.minutes;
                newMinutes -= jobTime;
                databaseReference.child("minutes").setValue(newMinutes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
