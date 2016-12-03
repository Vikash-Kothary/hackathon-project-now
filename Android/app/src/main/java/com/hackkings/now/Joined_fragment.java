package com.hackkings.now;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Joined_fragment extends Fragment {
    ListView listView;
    List<String > joinedEvents;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.joined_fragment , container, false);
        if (rootview != null){
            listView = (ListView)rootview.findViewById(R.id.listviewJoinedFragment);
            listView.setVerticalScrollBarEnabled(true);

            joinedEvents = new ArrayList<String>();
            addEvents();
        }
        return rootview;

    }

    public void addEvents (){
        joinedEvents.add("House Party");
        joinedEvents.add("Football Match");
        joinedEvents.add("Cinam with mates");
        joinedEvents.add("Christmas diner");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1 , joinedEvents);
        listView.setAdapter(arrayAdapter);

    }

}
