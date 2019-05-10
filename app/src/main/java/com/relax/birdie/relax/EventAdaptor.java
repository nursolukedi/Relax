package com.relax.birdie.relax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class  EventAdaptor extends ArrayAdapter<String>
{
    // meditation adaptor for any list.
    private static class ViewHolder {
        TextView eventText;
        ImageView pictureId;

    }

    private final LayoutInflater layoutInflater;
    private final Context context;
    private ViewHolder holder;
    private final  String[] events;

    public EventAdaptor(Context context, String[] events)
    {
        super(context, 0 , events);
        this.events = events;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return events.length;
    }

    @Override
    public String getItem(int position)
    {
        return events[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.event_list_view,null);
            holder = new ViewHolder();
            holder.eventText = (TextView) convertView.findViewById(R.id.eventComingUp);
            holder.pictureId = (ImageView) convertView.findViewById(R.id.pictureId);

            convertView.setTag( holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

       String event = events[position];

        if(event != null)
        {
           // holder.pictureId.setImageResource(event.getPicture());
           // String eventText = event.getEventName() + "\n Event type is : " + event.getEventType() + "\n and event date and time is : " + event.getEventDate() + "  - "  + event.getEventTime() +"\n" ;
            holder.eventText.setText(event);
            return convertView;
        }

        return null;

    }
}