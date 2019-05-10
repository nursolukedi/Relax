package com.relax.birdie.relax;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Calendar extends AppCompatActivity {
    TextView calendarTv;
    ListView listViewEvent;
    Button dashboard;
    Events.Event[] events;
    EventAdaptor eventAdaptor;
    //View v = (View) R.layout.activity_calendar;
    private static final int MY_CAL_REQ = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarTv = findViewById(R.id.calendarTv);
        listViewEvent = findViewById(R.id.listViewEvents);
        dashboard = findViewById(R.id.dashboard);

      //  getDataFromCalendarTable(v);
        eventAdaptor = new EventAdaptor(Calendar.this, Events.events);

        listViewEvent.setAdapter(eventAdaptor);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Calendar.this, Dashboard.class));
            }
        });


    }
  /* commented for time purposes
   public void getDataFromCalendarTable(View v) {
        Cursor cur = null;
        ContentResolver cr = getContentResolver();

        String[] mProjection =
                {
                        CalendarContract.Calendars.ALLOWED_ATTENDEE_TYPES,
                        CalendarContract.Calendars.ACCOUNT_NAME,
                        CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                        CalendarContract.Calendars.CALENDAR_LOCATION,
                        CalendarContract.Calendars.CALENDAR_TIME_ZONE
                };

        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"cal@zoftino.com", "cal.zoftino.com",
                "cal@zoftino.com"};

        if (ActivityCompat.checkSelfPermission(Calendar.this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Calendar.this, new String[]{Manifest.permission.READ_CALENDAR}, MY_CAL_REQ );
        }

        cur = cr.query(uri, mProjection, selection, selectionArgs, null);
        int cursorCount = 0 ;
        while (cur.moveToNext() && cursorCount < 10 ) {
            String displayName = cur.getString(cur.getColumnIndex(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME));
            String accountName = cur.getString(cur.getColumnIndex(CalendarContract.Calendars.ACCOUNT_NAME));

           //events[cursorCount] = displayName;
           cursorCount++;

        }
    }*/


}

