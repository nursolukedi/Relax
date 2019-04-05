package com.relax.birdie.relax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HeartrateShowing extends AppCompatActivity {

    private TextView heartrateInfo, meditationInfoTV;
    private ListView listView;
    private Button backDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate_showing);

        //initalization

        heartrateInfo = findViewById(R.id.heartRate);
        meditationInfoTV = findViewById(R.id.recommendTV);
        listView = findViewById(R.id.listView);
        backDashboard = findViewById(R.id.back);


        // Listen all of the buttons
        heartrateInfo.setOnClickListener(this);
        meditationInfoTV.setOnClickListener(this);
        listView.setOnClickListener(this);
        backDashboard.setOnClickListener(this);
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
