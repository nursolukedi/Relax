package com.relax.birdie.relax;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.List;

/*
* We will recieve heartbeat data here and then show it on activity as we receive it.
* We will store first 10 sec*6 's value and last 10 sec*6 value.
* Then we will take their difference and then show string accordingly.
* Pass string to show heartbeat data section.
*
* */

public class RecieveHeartbeat extends AppCompatActivity {
    private static final int DISCOVER_DURATION = 300;
    private static final int REQUEST_BLU = 1;
    private ProgressDialog progressDialog;
    private TextView heartBeat ;
    private BluetoothAdapter BTAdapter;
    private DeviceListFragment mDeviceListFragment;
    private Button nextButton;
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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecieveHeartbeat.this, HeartrateShowing.class);
                intent.putExtra("mood" , message);
                startActivity(intent);
            }
        });
        /*
        BTAdapter = BluetoothAdapter.getDefaultAdapter();

        // Phone does not support Bluetooth so let the user know and exit.
        if (BTAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        if (BTAdapter == null)
        {
            System.out.println("BTAdapter is null");
        }

        if (BTAdapter.isEnabled() == false) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }

        */
        progressDialog = new ProgressDialog(this);

        // If validations are OK, it will first show a progress bar
        progressDialog.setMessage("Receiveing your heartbeat in...");
        progressDialog.show();

    }
    public void sendViaBluetooth(){
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if(btAdapter == null )
        {
            Toast.makeText(this, "Bluetooth is not currently available on device. ", Toast.LENGTH_LONG).show();
        }
        else {
            enableBluetooth();
        }
    }

    public void enableBluetooth(){
        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVER_DURATION);
        startActivityForResult(discoveryIntent, REQUEST_BLU);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == DISCOVER_DURATION && requestCode == REQUEST_BLU ){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            File f = new File(Environment.getExternalStorageDirectory(), "inputHeartBeat");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));

            PackageManager pm = getPackageManager();
            List<ResolveInfo> appList = pm.queryIntentActivities(intent, 0);

            if(appList.size() > 0 )
            {
                String packageName = null;
                String className = null;
                boolean found = false;

                for(ResolveInfo info: appList){
                    packageName = info.activityInfo.packageName;
                    if(packageName.equals("com.relax.birdie.relax")){
                        className = info.activityInfo.packageName;
                        found = true;
                        break ; // pass to heartrate showing page
                    }
                }
                if(!found)
                {
                    Toast.makeText(this, "Relax is not found", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Bluetooth is cancelled", Toast.LENGTH_LONG).show();

                }
            }
        }
    }
}
