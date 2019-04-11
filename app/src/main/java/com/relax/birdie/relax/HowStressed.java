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

        checkButton.setOnClickListener(this);
        happy.setOnClickListener(this);
        calm.setOnClickListener(this);
        stressed.setOnClickListener(this);
        sad.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RecieveHeartbeat.class);
        if(v == checkButton) {
        }
            if(v == happy){
            intent.putExtra("mood", "happy");
            }
            else if (v == calm){
                intent.putExtra("mood", "calm");
            }
            else if ( v == sad){
                intent.putExtra("mood", "sad");
            }
            else if(v == stressed ){
                intent.putExtra("mood", "stressed");
            }
            startActivity(intent);

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

}
