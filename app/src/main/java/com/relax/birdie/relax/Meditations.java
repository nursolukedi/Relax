package com.relax.birdie.relax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Meditations extends AppCompatActivity {
    ListView listViewMeditations;
    ImageView meditations;
    MeditationAdaptor meditationAdaptor;
    Button backButton;
// meditations activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditations);
        meditations = findViewById(R.id.listOfMeditations);
        listViewMeditations = findViewById(R.id.listViewMeditations);
        backButton = findViewById(R.id.backButton);

        // list and adaptor and any other list related info
        meditationAdaptor = new MeditationAdaptor(Meditations.this, Meditation.meditations);
        listViewMeditations.setAdapter(meditationAdaptor);
        listViewMeditations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MeditationInfo.class);
                Meditation.Meditate meditates = (Meditation.Meditate) listViewMeditations.getItemAtPosition(position);
                intent.putExtra("meditationId",meditates.getMeditationName());
                finish();
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Meditations.this, "It is gonna be fine", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(Meditations.this, Dashboard.class));
            }
        });
    }


}
