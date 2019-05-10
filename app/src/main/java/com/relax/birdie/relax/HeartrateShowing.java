package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class HeartrateShowing extends AppCompatActivity{

    TextView heartrateInfo, meditationInfoTV;
    ListView listView;
    Button backDashboard;
    MeditationAdaptor meditationAdaptor;
    Meditation.Meditate[] meditations = new Meditation.Meditate[3];
    Meditation.Meditate[] meditationInstance = Meditation.meditations;

    int madeUpHeartrate = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate_showing);
        Bundle bundle = getIntent().getExtras();
        String message = Objects.requireNonNull(bundle).getString("mood");


        //initalization
        madeUpHeartrate = heartRateGenerate();
        heartrateInfo = findViewById(R.id.heartRate);
        String messageString = "Your current heartrate is : "+ madeUpHeartrate + " and you said your mood was : "  + message + "and it seems \n your stress level is : " +  recommendMeditation(madeUpHeartrate) ;
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
        //Toast.makeText(getApplicationContext(), SharedEmail.value, Toast.LENGTH_SHORT).show();
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

        else if(heartRate > 80)
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

// Saving yourself code.
    public int heartRateGenerate(){
        int mean = 100;
        int stdDeviation = 20;
        Random rand = new Random();
        return (int)(rand.nextGaussian() * stdDeviation + mean);
    }
}
