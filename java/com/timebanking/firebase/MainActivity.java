
package com.timebanking.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.timebanking.firebase.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gotoDesc(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DescriptionActivity.class);
        startActivity(intent);
    }


}
