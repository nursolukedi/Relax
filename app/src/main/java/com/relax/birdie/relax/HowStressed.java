package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class HowStressed extends AppCompatActivity {
    Button checkButton, happy, calm, stressed, sad ;
    int heartRate;
    User mockUser;
    ImageView howStressedImage;
    String mood = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_stressed);
        checkButton = findViewById(R.id.checkButton);
        happy = findViewById(R.id.happy);
        calm = findViewById(R.id.calm);
        stressed = findViewById(R.id.stressed);
        sad = findViewById(R.id.sad);
        howStressedImage = findViewById(R.id.howStressedImage);

        mockUser = new User("email", "name", "surname", 30, 1, 3);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mood.equals(""))
                {
                    Toast.makeText(HowStressed.this, "Please select your mood ", Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent = new Intent(HowStressed.this, RecieveHeartbeat.class);
                    Bundle moodBundle= new Bundle();
                    moodBundle.putString("mood", mood);
                    intent.putExtras(moodBundle);
                    startActivity(intent);}
            }
        });
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood ="happy";
            }
        });
        calm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "calm";
            }
        });
        stressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "stressed" ;
            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "sad";
            }
        });


    }


            // Saving yourself code.
           /* Random rand = new Random();
            int adjustment = (int)(rand.nextGaussian()*5);
            int newHeartRate = heartRate + adjustment;
            if (mockUser.isStressed(newHeartRate)) {
                Toast.makeText(this, "You are under stress. Heart rate = " + newHeartRate, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Meditations.class));
            }
            else {
                Toast.makeText(this, "You are NOT under stress. Heart rate = " + newHeartRate, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Dashboard.class));
            }
            //burda } eksik //

            public int getHeartRate() {
            int mean = 100;
            int stdDeviation = 20;
            Random rand = new Random();
            return (int)(rand.nextGaussian() * stdDeviation + mean);
            }

            */
}
