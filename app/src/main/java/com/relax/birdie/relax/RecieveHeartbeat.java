package com.relax.birdie.relax;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;



//import from bluetooth

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.UUID;

/*
* We will recieve heartbeat data here and then show it on activity as we receive it.
* We will store first 10 sec*6 's value and last 10 sec*6 value.
* Then we will take their difference and then show string accordingly.
* Pass string to show heartbeat data section.
*
* */

public class RecieveHeartbeat extends AppCompatActivity {
    private TextView heartBeat ;
    private Button nextButton, debug;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_heartbeat);
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("mood");
        heartBeat = findViewById(R.id.heartBeat);
        heartBeat.setText("You said your mood is : " + message);
        nextButton =  findViewById(R.id.nextButton);
        debug = findViewById(R.id.debugButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecieveHeartbeat.this, HeartrateShowing.class);
                intent.putExtra("mood" , message);

                startActivity(intent);
                finish();
            }
        });

        debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecieveHeartbeat.this, ReceiveHeartbeatBluetooth.class);
                
                startActivity(intent);
                finish();
            }
        });


}


}
