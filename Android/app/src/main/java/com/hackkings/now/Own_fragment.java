package com.hackkings.now;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Own_fragment extends Fragment {
    List <String> events;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.own_fragment_view , container , false);
        if(rootView != null) {
            listView = (ListView)rootView.findViewById(R.id.listviewOwnFragment);
            listView.setVerticalScrollBarEnabled(true);
            events = new ArrayList<>();
            addEvents();

        }
        return rootView;
    }

    public void addEvents (){

        events.add("HackKings");
//        events.add("RandomEvent");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1 , events);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = arrayAdapter.getItem(position).toString();
                Intent intent = new Intent(getActivity(), ViewEventActivity.class);
                intent.putExtra(MainActivity.EVENT_SELECTED, selectedItem);
                startActivity(intent);
            }
        });

    }


}
