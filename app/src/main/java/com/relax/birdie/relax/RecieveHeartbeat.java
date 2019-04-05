package com.relax.birdie.relax;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;

import java.io.File;
import java.util.List;

public class RecieveHeartbeat extends AppCompatActivity {
    private static final int DISCOVER_DURATION = 300;
    private static final int REQUEST_BLU = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_heartbeat);

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
                        break ;}
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
