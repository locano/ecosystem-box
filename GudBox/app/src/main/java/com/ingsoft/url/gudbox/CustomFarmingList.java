package com.ingsoft.url.gudbox;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jcdur on 11/10/2016.
 */
public class CustomFarmingList extends ArrayAdapter<String> {

    private String[] names;
    private String[] farmed;
    private String[] daysLeft;
    private Integer[] imageId;
    private Activity context;

    public CustomFarmingList(Activity context, String[] names, String[] farmed, String[] daysLeft, Integer[] imageId){
        super(context, R.layout.farming_list_layout, names);
        this.context = context;
        this.names = names;
        this.farmed = farmed;
        this.daysLeft = daysLeft;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.farming_list_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewFarm = (TextView) listViewItem.findViewById(R.id.textViewDesc);
        TextView textViewDays = (TextView) listViewItem.findViewById(R.id.textViewDays);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView);
        String daysText;
        textViewName.setText(names[position]);
        textViewFarm.setText(farmed[position]);
        if (daysLeft[position].equals("1"))
            daysText = "Your seed will be ready tomorrow."; else
        daysText = "Your seed will be ready in " + daysLeft[position] + " days.";
        textViewDays.setText(daysText);
        image.setImageResource(imageId[position]);
        return  listViewItem;
    }

}
