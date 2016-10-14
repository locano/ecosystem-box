package com.ingsoft.url.gudbox;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeedsListFragment extends Fragment {

    private ListView listView;
    private String names[] = {
            "Strawberry",
            "Carrot",
            "Banana",
            "Watermelon",
            "Pear",
            "Lemon",
            "Chili",
            "Apple",
            "Orange",
            "Tomato",
            "Pumpkin",
            "Grapes"
    };
    private String desc[] = {
            "January - June",
            "April - January",
            "All year",
            "May - September",
            "June - December",
            "All year",
            "September - December",
            "July - February",
            "October - May",
            "April - December",
            "October - March",
            "August - December"
    };

    private Integer imageId[] = {
            R.drawable.ic_strawberry,
            R.drawable.ic_carrot,
            R.drawable.ic_banana,
            R.drawable.ic_watermelon,
            R.drawable.ic_pear,
            R.drawable.ic_lemon,
            R.drawable.ic_chili,
            R.drawable.ic_apple,
            R.drawable.ic_orange,
            R.drawable.ic_tomato,
            R.drawable.ic_pumpkin,
            R.drawable.ic_grapes
    };

    public SeedsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_seeds_list, container, false);
        final Activity parentActivity = getActivity();

        //Retrieving seeds from database
        InternalAPI api = new InternalAPI(this.getActivity());
        List<ServerSeed> seeds = api.getAllServerSeeds();
        List<String> seedNames = new ArrayList<>();
        List<String> seedDescriptions = new ArrayList<>();
        List<Integer> seedPics = new ArrayList<>();

        for (ServerSeed seed: seeds) {
            seedNames.add(seed.name);
            String desc = String.valueOf(seed.days) + " days - ";
            if (api.isItGoodSeason(seed)) desc += "Good season to plant!";
            else desc += "Not a good season to plant.";
            seedDescriptions.add(desc);

            int randomNum = 0 + (int)(Math.random() * (imageId.length - 1));
            seedPics.add(imageId[randomNum]); //TODO: Fix this with correct image
        }

        names = new String[seedNames.size()];
        desc = new String[seedDescriptions.size()];
        imageId = new Integer[seedPics.size()];

        seedNames.toArray(names);
        seedDescriptions.toArray(desc);
        seedPics.toArray(imageId);

        CustomSeedsList customSeedsList = new CustomSeedsList(parentActivity, names, desc, imageId);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(customSeedsList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent intent = new Intent(getActivity(), seedInfo.class);
                intent.putExtra("itemName", names[i]);
                startActivity(intent);
            }
        });

        return view;
    }

}
