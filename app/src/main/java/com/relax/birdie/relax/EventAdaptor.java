package com.relax.birdie.relax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class  EventAdaptor extends ArrayAdapter<Events.Event>
{
    // meditation adaptor for any list.
    private static class ViewHolder {
        TextView eventText;
        ImageView pictureId;

    }

    private final LayoutInflater layoutInflater;
    private final Context context;
    private ViewHolder holder;
    private final Events.Event[] events;

    public EventAdaptor(Context context, Events.Event[] events)
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
    public Events.Event getItem(int position)
    {
        return events[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.event_list_view,null);
            holder = new ViewHolder();
            holder.eventText = (TextView) convertView.findViewById(R.id.eventText);
            holder.pictureId = (ImageView) convertView.findViewById(R.id.eventPic);

            convertView.setTag( holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

       Events.Event event = events[position];

        if(event != null)
        {
            holder.pictureId.setImageResource(event.getPictureId());
            String eventInfo = event.getEventName() + "\n Event type is : " + event.getEventType() + "\n and event date and time is : " + event.getEventDate() + "  - "  + event.getEventTime() +"\n" ;
            holder.eventText.setText(eventInfo);
            return convertView;
        }

        return null;

    }
}