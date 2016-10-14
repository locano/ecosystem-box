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


/**
 * A simple {@link Fragment} subclass.
 */
public class FarmingListFragment extends Fragment {

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
            "20 Mar 2016",
            "20 Mar 2016",
            "20 Mar 2016",
            "20 Mar 2016",
            "12 Apr 2016",
            "12 Apr 2016",
            "12 Apr 2016",
            "12 Apr 2016",
            "30 Sep 2016",
            "30 Sep 2016",
            "30 Sep 2016",
            "30 Sep 2016"
    };

    private String daysLeft[] = {
            "2",
            "1",
            "16",
            "33",
            "20",
            "10",
            "5",
            "21",
            "30",
            "9",
            "7",
            "22"
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

    public FarmingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_farming_list, container, false);
        final Activity parentActivity = getActivity();
        CustomFarmingList customFarmingList = new CustomFarmingList(parentActivity, names, desc, daysLeft, imageId);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(customFarmingList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent intent = new Intent(getActivity(), PlantedSeedInfoActivity.class);
                intent.putExtra("itemName", names[i]);
                startActivity(intent);
            }
        });

        return view;
    }

}
