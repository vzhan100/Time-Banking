package com.timebanking.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;





public class profile_Main extends AppCompatActivity {

    TextView value;
    RatingBar rb;
    Button submitbtn;
    EditText tv_name;
    EditText tvaddress;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//
//        profilePic = findViewById(R.id.ivProfilePic);
//        profileName = findViewById(R.id.tvProfileName);
//        profileAge = findViewById(R.id.tvProfileAge);
//        profileEmail = findViewById(R.id.tvProfileEmail);
//        profileUpdate = findViewById(R.id.btnProfileUpdate);
//        changePassword = findViewById(R.id.btnChangePassword);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
//
//
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
//            profileName.setText("Name: " + userProfile.getUserName());
//            profileAge.setText("Age: " + userProfile.getUserAge());
//            profileEmail.setText("Email: " + userProfile.getUserEmail());
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
//        }
//    });
//
//        profileUpdate.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            startActivity(new Intent(ProfileActivity.this, UpdateProfile.class));
//        }
//    });
//
//        changePassword.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            startActivity(new Intent(ProfileActivity.this, UpdatePassword.class));
//        }
//    });
//}
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()){
//            case android.R.id.home:
//                onBackPressed();
//        }
//        return super.onOptionsItemSelected(iteem);

        // Rating ssytem
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        rb = (RatingBar) findViewById(R.id.ratingBar);
        value = (TextView) findViewById(R.id.value);


        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                {
                    value.setText("Value is " + rating );
                }
            }
        });
    }

//    public void onButtonClickListener(){
//
//        rb = (RatingBar) findViewById(R.id.ratingBar);
//        submitbtn = (Button) findViewById(R.id.button);
//
//        submitbtn.setOnClickListener(
//           new View.OnClickListener(){
//               @Override
//               public void onClick(View v){
//                   Toast.makeText(profile_Main.this,
//                           String.valueOf(rb.getRating()),
//                           Toast.LENGTH_SHORT).show();
//               }
//           }
//        );
//
//
//    }



}
