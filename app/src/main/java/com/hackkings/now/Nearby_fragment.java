package com.hackkings.now;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Nearby_fragment extends Fragment {
    ListView listView;
    List<String> nearbyevents;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.nearby_fragment , container , false);
        if (rootview != null){
            listView = (ListView)rootview.findViewById(R.id.listviewNearbyFragment);
            listView.setVerticalScrollBarEnabled(true);
            nearbyevents = new ArrayList<String>();
            addEvents();
        }
        return rootview;
    }

    public void addEvents () {
        nearbyevents.add(" Luxman's event");
        nearbyevents.add("Vikaesh's event");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1 , nearbyevents);
        listView.setAdapter(arrayAdapter);


    }


}
