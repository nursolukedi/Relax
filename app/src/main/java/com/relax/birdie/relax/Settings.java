package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Button changeInformation, backDashboard, deleteStats, contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changeInformation = findViewById(R.id.changeUserInfo);
        backDashboard= findViewById(R.id.dashboardButton);
        deleteStats= findViewById(R.id.deleteStats);
        contact= findViewById(R.id.contact) ;

        changeInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "Will be implemented ", Toast.LENGTH_SHORT).show();

            }
        });


        backDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });

        deleteStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "Will be implemented ", Toast.LENGTH_SHORT).show();

            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "Will be implemented ", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
