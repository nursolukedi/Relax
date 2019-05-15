package com.relax.birdie.relax;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {


    // Variables - Layout elements of profile activity
    private ImageButton howStressed;
    private ImageButton meditations;
    private ImageButton settings;
    private ImageButton progress;
    private ImageButton personal;
    private ImageButton calendar;

    private ImageView signOut;
    private ImageView calendarImage;
    private ImageView personalImage;
    private ImageView settingsImage;
    private ImageView progressImage;
    private ImageView howStressedImage;
    private ImageView meditationsImage;
    // Variable - Other variables
    private FirebaseAuth firebaseAuth;

    private User mockUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize widget objects
        howStressed = findViewById(R.id.howStressedButton);
        meditations = findViewById(R.id.meditationsButton);
        settings = findViewById(R.id.settingsButton);
        personal = findViewById(R.id.personalButton);
        progress = findViewById(R.id.progressButton);
        calendar = findViewById(R.id.calendarButton);
        signOut = findViewById(R.id.signOut);

        howStressedImage = findViewById(R.id.howStressedTV);
        meditationsImage = findViewById(R.id.meditationsTV);
        settingsImage = findViewById(R.id.settingsTV);
        personalImage = findViewById(R.id.personalTV);
        progressImage = findViewById(R.id.progressTV);
        calendarImage = findViewById(R.id.calendarTV);


        // Listen all of the buttons
        howStressed.setOnClickListener(this);
        meditations.setOnClickListener(this);
        settings.setOnClickListener(this);
        progress.setOnClickListener(this);
        calendar.setOnClickListener(this);
        personal.setOnClickListener(this);
        signOut.setOnClickListener(this);


        // Initialize a mock user
        mockUser = new User("email", "name", "surname", 30, 1, 3);
    }
    public void onClick(View v) {
        if (v == signOut) { // Sign out
           //firebaseAuth.signOut();
            finish();
            startActivity(new Intent(Dashboard.this, LoginActivity.class));
            Toast.makeText(Dashboard.this, "User signed out", Toast.LENGTH_SHORT).show();

        }
        else
       if (v == howStressed) { // Check whether the user is stressed
           Intent intent = new Intent(getApplicationContext(), HowStressed.class);
               startActivity(intent);
           }

        else if (v == meditations) { // How stressed test activity
            startActivity(new Intent(Dashboard.this, Meditations.class));
        }
        else if (v == settings)
       {
           startActivity(new Intent(Dashboard.this, Settings.class));
       }
        else if (v == progress )
        {
            //startActivity(new Intent(Dashboard.this, Progress.class));
        }
            else if (v == calendar )
        {
            startActivity(new Intent(Dashboard.this, Calendar.class));
        }
        else if (v == personal ) {
           startActivity(new Intent(Dashboard.this, Personal.class));
       }
    }

}
