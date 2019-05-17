package com.relax.birdie.relax;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Personal extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseDatabase db;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    private ImageView userPhoto;
    private TextView userName, userInfo;
    private String name= " ";
    private String userInformation = "";
    private Button dashboard, progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        db = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        userInfo = findViewById(R.id.userInfo);
        userName = findViewById(R.id.userName);
        userPhoto = findViewById(R.id.userPhoto);
        dashboard = findViewById(R.id.dashboardButton1);
        progress = findViewById(R.id.personalStats);

        userInformation = " Name Surname :" + name + "\n" ;
        userInformation = userInformation + " Email : OLMADU " + "\n" ;
        userInformation = userInformation + " Age : 22 "+ "\n";
        userInformation = userInformation + " Gender : Male "+ "\n";
        userInformation = userInformation + " Fitness Level : 3 ";

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Personal.this, Dashboard.class));
            }
        });

        userName.setText(name);
        userInfo.setText(userInformation);
        getProfile();
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Personal.this, Progress.class));
            }
        });



    }

    private void getProfile() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                String userid = user.getUid();
                mDatabase = mDatabase.child("users").child(userid);

                name = name + " " + dataSnapshot.child("users").child(userid).child("name").getValue();
                name = name + " " + dataSnapshot.child("users").child(userid).child("surname").getValue();
                //userName.setText(currentUser);
                //  Glide.with(getActivity()).load(dataSnapshot.child("userPhoto").getValue().toString()).into(userImageView);

                userInformation = " Name Surname :" + name + "\n" ;
                userInformation = userInformation + " Email : "+ dataSnapshot.child("users").child(userid).child("email").getValue() + "\n" ;
                userInformation = userInformation + " Age : "+ dataSnapshot.child("users").child(userid).child("age" ).getValue()+ "\n";
                userInformation = userInformation + " Gender : "+ dataSnapshot.child("users").child(userid).child("gender").getValue()+ "\n";
                userInformation = userInformation + " Fitness Level :"+ dataSnapshot.child("users").child(userid).child("fitnessLevel").getValue();
                userInfo.setText(userInformation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
