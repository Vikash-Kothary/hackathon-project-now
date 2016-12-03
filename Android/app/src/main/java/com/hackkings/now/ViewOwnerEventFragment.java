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

/**
 * A placeholder fragment containing a simple view.
 */
public class ViewOwnerEventFragment extends Fragment {
    private ArrayList<String> eventHistory;
    private ArrayAdapter<String> arrayAdapter;

    public ViewOwnerEventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_view_event, container, false);
        ListView listView = (ListView) rootview.findViewById(R.id.listView_messages);
        eventHistory = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, eventHistory);
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
        return rootview;
    }

    public void onNewMessage(String message) {

    }


}
