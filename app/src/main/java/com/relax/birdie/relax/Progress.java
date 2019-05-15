package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Progress extends AppCompatActivity implements View.OnClickListener{
    private Button returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        returnButton = findViewById(R.id.buttonToDashboard);

        returnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == returnButton){
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
        }
    }

    //TODO Progress class
}
