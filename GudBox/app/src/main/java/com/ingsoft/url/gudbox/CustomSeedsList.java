package com.ingsoft.url.gudbox;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jcdur on 29/08/2016.
 */
public class CustomSeedsList extends ArrayAdapter<String> {

    private String[] names;
    private String[] desc;
    private Integer[] imageId;
    private Activity context;

    public CustomSeedsList(Activity context, String[] names, String[] desc, Integer[] imageId){
        super(context, R.layout.seeds_list_layout, names);
        this.context = context;
        this.names = names;
        this.desc = desc;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.seeds_list_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView);

        textViewName.setText(names[position]);
        textViewDesc.setText(desc[position]);
        image.setImageResource(imageId[position]);
        return  listViewItem;
    }


}
