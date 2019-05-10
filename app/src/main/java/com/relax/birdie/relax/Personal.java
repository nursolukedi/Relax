package com.relax.birdie.relax;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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


    private ImageView userPhoto;
    private TextView userName, userInfo;
    private String name= "";
    private String userInformation = "";
    private Button settings, progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        userInfo = findViewById(R.id.userInfo);
        userName = findViewById(R.id.userName);
        userPhoto = findViewById(R.id.userPhoto);
        settings = findViewById(R.id.settings);
        progress = findViewById(R.id.personalStats);

        getProfile();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Personal.this, Settings.class));
            }
        });

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Personal.this, Progress.class));
            }
        });


    }

    private void getProfile() {
        mDatabase= db.getReference("users/"+mAuth.getCurrentUser().getUid());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue(String.class);
                name = name + " " + dataSnapshot.child("surname").getValue(String.class);
                userName.setText(name);
                //  Glide.with(getActivity()).load(dataSnapshot.child("userPhoto").getValue().toString()).into(userImageView);

                userInformation = " Name Surname :" + name + "\n" ;
                userInformation = userInformation + " Email : "+ dataSnapshot.child("email").getValue(String.class) + "\n" ;
                userInformation = userInformation + " Age : "+ dataSnapshot.child("age" ).getValue(String.class)+ "\n";
                userInformation = userInformation + " Gender : "+ dataSnapshot.child("gender").getValue(String.class)+ "\n";
                userInformation = userInformation + " Fitness Level :"+ dataSnapshot.child("fitnessLevel").getValue(String.class);
                userInfo.setText(userInformation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
