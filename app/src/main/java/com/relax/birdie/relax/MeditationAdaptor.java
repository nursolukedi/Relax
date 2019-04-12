package com.relax.birdie.relax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MeditationAdaptor extends ArrayAdapter<Meditation.Meditate>
{
    // meditation adaptor for any list.
    private static class ViewHolder {
        TextView meditationName;
        ImageView pictureId;

    }

    private final LayoutInflater layoutInflater;
    private final Context context;
    private ViewHolder holder;
    private final Meditation.Meditate[] meditates;

    public MeditationAdaptor(Context context, Meditation.Meditate[] meditates)
    {
        super(context, 0 , meditates);
        this.meditates = meditates;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return meditates.length;
    }

    @Override
    public Meditation.Meditate getItem(int position)
    {
        return meditates[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null)
            {
                convertView = layoutInflater.inflate(R.layout.meditation_list_info,null);
                holder = new ViewHolder();
                holder.meditationName = (TextView) convertView.findViewById(R.id.meditationName);
                holder.pictureId = (ImageView) convertView.findViewById(R.id.pictureId);

                convertView.setTag( holder);
            }
            else
            {
                holder = (ViewHolder)convertView.getTag();
            }

        Meditation.Meditate meditate = meditates[position];

        if(meditate != null)
        {
            holder.pictureId.setImageResource(meditate.getPicture());
            String meditationText = meditate.getMeditationName() + "\n  Meditation mood is : " + meditate.getMeditationMoodType() + "\n Meditation level is for : " + meditate.getMeditationLevel() +"\n";
            holder.meditationName.setText(meditationText);
            return convertView;
        }

        return null;

    }
}