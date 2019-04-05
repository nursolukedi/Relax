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

    private Button signOut;
    private ImageView calendarImage;
    private ImageView personalImage;
    private ImageView settingsImage;
    private ImageView progressImage;
    private ImageView howStressedImage;
    private ImageView meditationsImage;
    // Variable - Other variables
    private FirebaseAuth firebaseAuth;
    private int heartRate;
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

        // Get current heart rate
        heartRate = getHeartRate();

        // Initialize a mock user
        mockUser = new User("email", "name", "surname", 30, 1, 3);
    }
    public void onClick(View v) {
       if (v == signOut) { // Sign out
        //   firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        else if (v == howStressed) { // Check whether the user is stressed
           Random rand = new Random();
           int adjustment = (int)(rand.nextGaussian()*5);
           int newHeartRate = heartRate + adjustment;
           if (mockUser.isStressed(newHeartRate)) {
               Toast.makeText(this, "You seem like you are stressed. What about meditation?", Toast.LENGTH_LONG).show();
               finish();
               startActivity(new Intent(this, Meditations.class));
           }
           else {
               Toast.makeText(this, "Great! You seem fine.", Toast.LENGTH_SHORT).show();
           }
        }
        else if (v == meditations) { // How stressed test activity
            finish();
            startActivity(new Intent(this, Meditations.class));
        }
        else if (v == settings || v == progress || v == calendar || v == personal ) {
           Toast.makeText(this, "This section is under construction", Toast.LENGTH_LONG).show();
       }
    }

    /**
     * Get current heart rate. Substituted with a random number generator.
     * @return Current heart rate
     */
    public int getHeartRate() {
        int mean = 80;
        int stdDeviation = 20;
        Random rand = new Random();
        return (int)(rand.nextGaussian() * stdDeviation + mean);
    }
}
