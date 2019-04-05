package com.relax.birdie.relax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Random;

public class HowStressed extends AppCompatActivity implements View.OnClickListener {
    private Button checkButton;
    private SeekBar seekBar;
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

        seekBar = findViewById(R.id.seekBar);
        checkButton.setOnClickListener(this);
        mockUser = new User("email", "name", "surname", 30, 1, 3);
        heartRate = getHeartRate();

    }

    @Override
    public void onClick(View v) {
        if(v == checkButton) {
            Random rand = new Random();
            int adjustment = (int)(rand.nextGaussian()*5);
            int newHeartRate = heartRate + adjustment;
            if (mockUser.isStressed(newHeartRate)) {
                Toast.makeText(this, "You are under stress. Heart rate = " + newHeartRate, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "You are NOT under stress. Heart rate = " + newHeartRate, Toast.LENGTH_SHORT).show();
            }
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
