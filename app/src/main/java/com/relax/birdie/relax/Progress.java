 package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class Progress extends AppCompatActivity implements View.OnClickListener{
    private Button returnButton;
    TextView progressText ;
    String progressString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressText = findViewById(R.id.progressText);
        returnButton = findViewById(R.id.buttonToDashboard);
        returnButton.setOnClickListener(this);
        progressString = "Date : 15-5-19 11:14, Stress : High, Meditation Tried : None \n " +
                "\"Date : 14-5-19 13:15, Stress : High, Meditation Tried : Go outside \n " +
                "\"Date : 14-5-19 10:43, Stress : High, Meditation Tried : Go outside \n" +
                "\"Date : 13-5-19 09:20, Stress : Normal, Meditation Tried : None \n " +
                "\"Date : 09-5-19 21:19, Stress : Low, Meditation Tried : Meditate \n ";
        progressText.setText(progressString);
    }

    @Override
    public void onClick(View v) {
        if(v == returnButton){
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
        }
    }


}