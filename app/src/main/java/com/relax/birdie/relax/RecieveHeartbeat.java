package com.relax.birdie.relax;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class RecieveHeartbeat extends AppCompatActivity {
    private TextView heartBeat ;
    private Button nextButton, debug;
    private String message;
    private ProgressBar loading;
    private int progressStatus = 0;
    private boolean suspended = false;
    private boolean stopped = false;
    private Handler handler = new Handler();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_heartbeat);
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("mood");
        heartBeat = findViewById(R.id.heartBeat);
        heartBeat.setText("You said your mood is : " + message);
        nextButton =  findViewById(R.id.nextButton);
        loading = findViewById(R.id.progressBar);
        nextButton.setVisibility(View.INVISIBLE);
        nextButton.postDelayed(new Runnable() {
            public void run() {
                nextButton.setVisibility(View.VISIBLE);
            }
        }, 7000);
        textView = findViewById(R.id.textView);
        textView.setText("Receving Bluetooth Information");

        loading.setMax(100);
        loading.setIndeterminate(false);

        initValues();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecieveHeartbeat.this, HeartrateShowing.class);
                intent.putExtra("mood" , message);
                startActivity(intent);

            }
        });


}

    private void initValues() { //Başlangıç değerlerini set ediyoruz.
        progressStatus=0;
        loading.setProgress(progressStatus);
        loading.setEnabled(true);
        suspended=false;
    }

}
