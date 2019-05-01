package com.relax.birdie.relax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {
    TextView calendarTv;
    ListView listViewEvent;
    Button dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarTv = findViewById(R.id.calendarTv);
        listViewEvent = findViewById(R.id.listViewEvents);
        dashboard = findViewById(R.id.dashboard);


    }
}
