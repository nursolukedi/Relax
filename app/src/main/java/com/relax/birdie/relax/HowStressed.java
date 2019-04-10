package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Random;

public class HowStressed extends AppCompatActivity implements View.OnClickListener {
    private Button checkButton;
    private Button happy;
    private Button calm;
    private Button stressed;
    private Button sad;
    private int heartRate;
    private User mockUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_stressed);
        checkButton = findViewById(R.id.checkButton);
        happy = findViewById(R.id.checkButton);
        calm = findViewById(R.id.checkButton);
        stressed = findViewById(R.id.checkButton);
        sad = findViewById(R.id.checkButton);

        mockUser = new User("email", "name", "surname", 30, 1, 3);
        heartRate = getHeartRate();

        checkButton.setOnClickListener(this);
        happy.setOnClickListener(this);
        calm.setOnClickListener(this);
        stressed.setOnClickListener(this);
        sad.setOnClickListener(this);


    }
/*
    public void onClick(View v) {
        if (v == stressed) { // Check whether the user is stressed
            Random rand = new Random();
            int adjustment = (int)(rand.nextGaussian()*5);
            int newHeartRate = heartRate + adjustment;
            if (mockUser.isStressed(newHeartRate)) {
                Toast.makeText(this, "You seem like you are stressed. What about meditation?", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(this, Meditations.class));
            }
        }
        else if (v == happy || v == calm || v == sad) { // How stressed test activity
            finish();
            startActivity(new Intent(this, RecieveHeartbeat.class));
        }
    }
*/
    @Override
    public void onClick(View v) {
        if(v == checkButton) {
            Random rand = new Random();
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
        }

    }
/*
     * Get current heart rate. Substituted with a random number generator.
     * @return Current heart rate
     */
    public int getHeartRate() {
        int mean = 100;
        int stdDeviation = 20;
        Random rand = new Random();
        return (int)(rand.nextGaussian() * stdDeviation + mean);
    }
}
