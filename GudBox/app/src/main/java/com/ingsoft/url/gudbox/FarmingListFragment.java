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

    public FarmingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_farming_list, container, false);
        final Activity parentActivity = getActivity();
        CustomList customList = new CustomList(parentActivity, names, desc, imageId);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(customList);

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
