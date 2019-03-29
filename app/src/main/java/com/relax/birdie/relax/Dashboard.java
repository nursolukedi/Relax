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

//import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {


    // Variables - Layout elements of profile activity
    private ImageButton howStressed;
    private ImageButton meditations;
    private ImageButton settings;
    private ImageButton progress;
    private ImageButton personal;
    private ImageButton calendar;

    private Button signOut;
    private ImageView calendarImage;
    private ImageView personalImage;
    private ImageView settingsImage;
    private ImageView progressImage;
    private ImageView howStressedImage;
    private ImageView meditationsImage;
    // Variable - Other variables
  //  private FirebaseAuth firebaseAuth;
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

        howStressedImage = findViewById(R.id.howStressedTV);
        meditationsImage = findViewById(R.id.meditationsTV);
        settingsImage = findViewById(R.id.settingsTV);
        personalImage = findViewById(R.id.personalTV);
        progressImage = findViewById(R.id.progressTV);
        calendarImage = findViewById(R.id.calendarTV);

        signOut = findViewById(R.id.signOut);

        // Listen all of the buttons
        howStressed.setOnClickListener(this);
        meditations.setOnClickListener(this);
        settings.setOnClickListener(this);
        progress.setOnClickListener(this);
        calendar.setOnClickListener(this);
        personal.setOnClickListener(this);
        signOut.setOnClickListener(this);
    }
    public void onClick(View v) {
       if (v == signOut) { // Sign out
        //   firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        else if (v == howStressed) { // How stressed test activity
            finish();
            startActivity(new Intent(this, HowStressed.class));
        }
        else if (v == meditations) { // How stressed test activity
            finish();
            startActivity(new Intent(this, Meditations.class));
        }
        else if (v == settings) { // How stressed test activity
            finish();
            startActivity(new Intent(this, Settings.class));
        }
        else if (v == progress) { // How stressed test activity
            finish();
            startActivity(new Intent(this, Progress.class));
        }
       else if (v == calendar) { // How stressed test activity
           finish();
           startActivity(new Intent(this, Calendar.class));
       }
       else if (v == personal) { // How stressed test activity
           finish();
           startActivity(new Intent(this, Personal.class));
       }
    }
}
