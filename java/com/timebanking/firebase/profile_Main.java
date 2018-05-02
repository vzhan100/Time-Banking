package com.timebanking.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class profile_Main extends AppCompatActivity {

    TextView value;
    RatingBar rb;
    Button submitbtn;
    ListView tv_name;
    EditText tvaddress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    long minutes, aminutes;
    String userEmail, userId, userName;
    ArrayList<String> minutesList  = new ArrayList<>();
    ArrayList<String> nameList  = new ArrayList<>();
    ArrayList<String> aminutesList  = new ArrayList<>();

    // Editing values into database


    // Rating system
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
//getting views
        tv_name = (ListView) findViewById(R.id.testlistview);



        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        userEmail = user.getEmail();
        //getting the reference of artists node
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        addValueEventListener(databaseReference);




        //list to store artists

        rb = (RatingBar) findViewById(R.id.ratingBar);
        value = (TextView) findViewById(R.id.value);


        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                {
                    value.setText("Value is " + rating);
                }
            }
        });
    }
    private void addValueEventListener(final DatabaseReference userReference) {
        /*add ValueEventListener to update data in realtime*/
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*this is called when first passing the data and
                 * then whenever the data is updated*/
                /*get the data children*/
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()) {
                    String email;
                    /*get the values as a user object*/
                    User user = iterator.next().getValue(User.class);

                    //One of the values we can get from authentication without accessing the database
                    //is the current user email so I'm using it to find and access the current user's minutes
                    //Once I get the minutes the uMinutes variable will be used to check that the user
                    //has enough available minutes on their account before requesting a job.
                    email = user.getEmail();
                    if (email != null) {
                        if (email.equals(userEmail)) {
                            minutes = user.getMinutes();
                            aminutes = user.getaMinutes();
                            userName = user.getName();
                        }
                    }
                    //These print statements are for testing and debugging purposes
                }
                nameList.add(userName);
                ArrayAdapter nameAdapter = new ArrayAdapter<String>(profile_Main.this, android.R.layout.simple_list_item_1, nameList);
                tv_name.setAdapter(nameAdapter);
                System.out.println("Minutes :" + minutes);
                System.out.println("Available Minutes :" + aminutes);
                System.out.println("Name: " + userName);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /*listener failed or was removed for security reasons*/
            }
        });
    }


    public void onButtonClickListener() {

        rb = (RatingBar) findViewById(R.id.ratingBar);
        submitbtn = (Button) findViewById(R.id.button2);

        submitbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(profile_Main.this,
                                String.valueOf(rb.getRating()),
                                Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }


}
