package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HeartrateShowing extends AppCompatActivity implements View.OnClickListener{

    private TextView heartrateInfo, meditationInfoTV;
    private ListView listView;
    private Button backDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate_showing);
        Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("mood");


        //initalization

        heartrateInfo = findViewById(R.id.heartRate);
        String messageString = "Your current heartrate is : 120 and you said your mood was : "  + message;
        heartrateInfo.setText(messageString);
        meditationInfoTV = findViewById(R.id.recommendTV);
        listView = findViewById(R.id.listView);
        backDashboard = findViewById(R.id.back);


        // Listen all of the buttons
        backDashboard.setOnClickListener(this);

    }

    public void onClick(View v) {
       if(v == backDashboard )
       {
           Toast.makeText(this, "Hope it works", Toast.LENGTH_LONG).show();
           finish();
           startActivity(new Intent(this, Dashboard.class));
       }
    }

    public String recommendMeditation(int heartRate)
    {
        if(heartRate >= 110 )
        {
            return "High";
        }

        else if(heartRate > 80 && heartRate < 110)
        {
            return "Normal";
        }
        else if(heartRate < 80 )
        {
            return "Low";
        }
        else
        {
            return "Not Valid";
        }
    }


}
