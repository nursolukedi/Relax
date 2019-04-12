package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MeditationInfo extends AppCompatActivity {
// meditation info on list
    ImageView meditationImage;
    TextView meditationName, meditationText;
    Button dashboardButton;
    String message;
    Meditation.Meditate showingMeditation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_info);
        meditationImage = findViewById(R.id.pictureId);
        meditationName = findViewById(R.id.meditationName);
        meditationText = findViewById(R.id.meditationText);
        dashboardButton = findViewById(R.id.dashboard);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("meditationId");

        Meditation.Meditate[] meditationInstance = Meditation.meditations;

        for(int i = 0 ; i < meditationInstance.length ; i++)
        {
           if( meditationInstance[i].getMeditationName().equals(message))
           {
               showingMeditation = meditationInstance[i] ;
               break;
           }
        }
        String meditationText;
        meditationText = showingMeditation.getMeditationName() + "\n  Meditation mood is : " + showingMeditation.getMeditationMoodType() + "\n Meditation level is for : " + showingMeditation.getMeditationLevel() +"\n";
        meditationName.setText(meditationText);

        meditationImage.setImageResource(showingMeditation.getPicture());

        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);
            }
        });





    }
}
