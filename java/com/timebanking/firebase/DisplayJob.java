/*
CIS 454
Time Banker App
Author: Joseph Urie
Date: April 11th, 2018
*/

package com.timebanking.firebase;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

public class DisplayJob extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressBar progressBar=findViewById(R.id.progressBar);
                final TextView textCounter = findViewById(R.id.textView2);
                progressBar.setProgress(100);
                CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        textCounter.setText("Time Remaining: \n"+String.valueOf(millisUntilFinished/1000));
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
