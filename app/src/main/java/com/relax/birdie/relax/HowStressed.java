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
    Button checkButton, happy, calm, stressed, sad;
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
                if (mood.equals("")) {
                    Toast.makeText(HowStressed.this, "Please select your mood ", Toast.LENGTH_LONG).show();

                } else {
                    Intent intent = new Intent(HowStressed.this, RecieveHeartbeat.class);
                    Bundle moodBundle = new Bundle();
                    moodBundle.putString("mood", mood);
                    intent.putExtras(moodBundle);
                    startActivity(intent);
                }
            }
        });
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "happy";
                Toast.makeText(HowStressed.this, "You said your mood is happy :) ", Toast.LENGTH_SHORT).show();
            }
        });
        calm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "calm";
                Toast.makeText(HowStressed.this, "You said that you are calm :) ", Toast.LENGTH_SHORT).show();
            }
        });
        stressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "stressed";
                Toast.makeText(HowStressed.this, "You said that you are stressed :", Toast.LENGTH_SHORT).show();
            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "sad";
                Toast.makeText(HowStressed.this, "You said that you are sad :( ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
