package com.hackkings.now;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

public class CreateEventActivity extends AppCompatActivity {

    private String eventTitle;
    private String eventDescription;
    private double eventLongitude;
    private double eventLatitude;
    private int eventRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        setupToolbar();
    }

    private void setupPusher(String eventTitle) {

        Pusher pusher = new Pusher("d91131e2dc8d85d998b3");

        Channel channel = pusher.subscribe(eventTitle);

        channel.bind("new_notification", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplication())
                                .setSmallIcon(R.drawable.ic_star_black_24dp)
                                .setContentTitle(eventName)
                                .setContentText(data);
                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplication());
// Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
                int mId = 12345;
                mNotificationManager.notify(mId, mBuilder.build());
            }
        });
        pusher.connect();
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_save:
                eventTitle = ((EditText) findViewById(R.id.event_title)).getText().toString();
                eventDescription = "";
                eventLongitude = Double.parseDouble(((EditText) findViewById(R.id.event_title)).getText().toString());
                eventLatitude = Double.parseDouble(((EditText) findViewById(R.id.event_title)).getText().toString());
                eventRadius = Integer.parseInt(((EditText) findViewById(R.id.event_title)).getText().toString());
                setupPusher(eventTitle);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
