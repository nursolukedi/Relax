package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import com.relax.birdie.relax.MeditationAdaptor;
public class HeartrateShowing extends AppCompatActivity{

    TextView heartrateInfo, meditationInfoTV;
    ListView listView;
    Button backDashboard;
    MeditationAdaptor meditationAdaptor;
    Meditation.Meditate[] meditations = new Meditation.Meditate[3];
    Meditation.Meditate[] meditationInstance = Meditation.meditations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate_showing);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("mood");


        //initalization

        heartrateInfo = findViewById(R.id.heartRate);
        String messageString = "Your current heartrate is : 120 and you said your mood was : "  + message;
        heartrateInfo.setText(messageString);
        meditationInfoTV = findViewById(R.id.recommendTV);
        listView = findViewById(R.id.listView);
        backDashboard = findViewById(R.id.back);

        // list and adaptor and any other list related info
        int validCount = 0 ;
        for(int i = 0 ; i < meditationInstance.length ; i++  )
        {
          if( meditationInstance[i].getMeditationMoodType().equals(message) )
          {
              if( validCount > meditations.length )
                  break;
              else{
                meditations[validCount] = Meditation.meditations[i] ;
                validCount ++;}
          }
        }

        meditationAdaptor = new MeditationAdaptor(HeartrateShowing.this, meditations);
        listView.setAdapter(meditationAdaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MeditationInfo.class);
                Meditation.Meditate meditates = (Meditation.Meditate) listView.getItemAtPosition(position);
                intent.putExtra("meditationId",meditates.getMeditationName());
                startActivity(intent);
            }
        });
        // Listen all of the buttons
        backDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(HeartrateShowing.this, "Hope it works", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(HeartrateShowing.this, Dashboard.class));

            }
        });

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
