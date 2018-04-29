/*
CIS 454
Time Banker App
Author: Joseph Urie
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.timebanking.firebase.JobActivity.jobList;

public class DisplayJob extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_job);

        //Database Access
        database = FirebaseDatabase.getInstance();
        dataRef = database.getReference();

        //Bringing over correct job data
        Intent intent = getIntent();
        int position = intent.getIntExtra(JobActivity.jobID, 0);
        String jobAddress = jobList.get(position).getAddress();
        String jobDesc = jobList.get(position).getDesc();
        final Long jobTime = jobList.get(position).getPay();
        String jobID = jobList.get(position).getTitle();
        String jobPostID = jobList.get(position).getUserId();

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
        postID.setText("Posted by: "+jobPostID);

        //Button and Timer
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressBar progressBar=findViewById(R.id.progressBar);
                final TextView textCounter = findViewById(R.id.textView2);
                progressBar.setProgress(100);
                CountDownTimer countDownTimer = new CountDownTimer((jobTime*60000),1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        textCounter.setText("Time Remaining:\n"+String.valueOf((millisUntilFinished/1000)/60)+":"+String.valueOf((millisUntilFinished/1000)%60));
                        int progress = (int) (millisUntilFinished/100);
                        progressBar.setProgress(progress);
                    }

                    @Override
                    public void onFinish() {
                        textCounter.setText("Job Done!");
                        progressBar.setProgress(0);
                    }
                };
                countDownTimer.start();
            }
        });
    }
}
