package com.relax.birdie.relax;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HeartrateShowing extends AppCompatActivity{

    DatabaseReference dRef;
    String userID = "";
    TextView heartrateInfo, meditationInfoTV;
    ListView listView;
    Button backDashboard, helpful, notHelpful;
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
        helpful = findViewById(R.id.helpful);
        notHelpful = findViewById(R.id.nothelpful);
        dRef = FirebaseDatabase.getInstance().getReference();

        // Retrieve the ID of the user so that we can work with preference values
        dRef.child("users").orderByChild("email").equalTo(SharedEmail.value).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    userID = data.getKey();
                    //Toast.makeText(getApplicationContext(), "User ID: " + userID, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();;
            }
        });

//        // Retrieve user preferences from database
//        final Double[] userPreferences = new Double[12];
//        dRef.child("ratings").child(userID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot data: dataSnapshot.getChildren()) {
//                    String key = data.getKey();
//                    userPreferences[Integer.parseInt(Objects.requireNonNull(key))] = data.child(key).getValue(Double.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        for (int i=0; i<12; i++) {
//            Toast.makeText(getApplicationContext(), "Meditation: " + i + ", rating: " + userPreferences[i], Toast.LENGTH_SHORT).show();
//        }

        // list and adaptor and any other list related info
        int validCount = 0;
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
        if (madeUpHeartrate < 110) {
            listView.setVisibility(View.GONE);
            meditationInfoTV.setVisibility(View.GONE);
            helpful.setVisibility(View.GONE);
            notHelpful.setVisibility(View.GONE);
        }
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

        helpful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Thanks for your feedback! It will be used to make our system more effective.", Toast.LENGTH_SHORT).show();
            }
        });

        notHelpful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Thanks for your feedback! It will be used to make our system more effective.", Toast.LENGTH_SHORT).show();
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
