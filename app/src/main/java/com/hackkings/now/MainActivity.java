package com.hackkings.now;

import android.content.Intent;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createTabs();

    }

    public void createTabs() {

        tabs = (TabLayout) findViewById(R.id.tabLayout);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.addTab(tabs.newTab().setText("Own"));
        tabs.addTab(tabs.newTab().setText("Joined"));
        tabs.addTab(tabs.newTab().setText("Nearby"));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new Own_fragment()).commit();

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateEventActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), ViewEventActivity.class);
                startActivity(intent);
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = null;

                int tabselected = tabs.getSelectedTabPosition();
                switch (tabselected) {
                    case 0:
                        fragment = new Own_fragment();
                        break;
                    case 1:
                        fragment = new Joined_fragment();
                        break;
                    case 2:
                        fragment = new Nearby_fragment();
                        break;
                }
                if (fragment != null) {
                    fragmentTransaction.replace(R.id.fragmentContainer, fragment);
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

    public void changeFragment() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
