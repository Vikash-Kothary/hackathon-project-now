package com.hackkings.now;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;

public class ViewEventActivity extends AppCompatActivity {
    Fragment fragment;
    private TabLayout tabLayout;
    private String eventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.EVENT_SELECTED)) {
            eventTitle = intent.getStringExtra(MainActivity.EVENT_SELECTED);
        }

        setupToolbar();
        setupTabLayout();
        setupListView();
        setupPusher();
    }

    private void setupListView() {

    }

    private void setupPusher() {
        Pusher pusher = new Pusher("d91131e2dc8d85d998b3");

        Channel channel = pusher.subscribe(eventTitle);

//        AsyncHttpClient client = new AsyncHttpClient();
//
//        String baseURL = "https://hackkings-now.herokuapp.com";
//        Event params = new Event(eventTitle);
//        client.post(baseURL + "/events/subscribe", params, new JsonHttpResponseHandler(){
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Toast.makeText(getApplicationContext(), "Something went right :)", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Toast.makeText(getApplicationContext(), "Something went wrong :(", Toast.LENGTH_LONG).show();
//            }
//        });


        pusher.connect();
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(eventTitle);
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void setupTabLayout(){
        tabLayout = (TabLayout) findViewById(R.id.containerTabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Official"));
        tabLayout.addTab(tabLayout.newTab().setText("Public"));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.chanelFragmentContainer, new ViewOwnerEventFragment()).commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment = null;

                int tabselected = tabLayout.getSelectedTabPosition();
                switch (tabselected) {
                    case 0:
                        fragment = new ViewOwnerEventFragment();
                        break;
                    case 1:
                        fragment = new Joined_fragment();
                        break;
                }
                if (fragment != null) {
                    fragmentTransaction.replace(R.id.chanelFragmentContainer, fragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
