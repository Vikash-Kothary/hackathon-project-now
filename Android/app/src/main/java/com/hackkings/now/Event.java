package com.hackkings.now;

import com.loopj.android.http.RequestParams;

/**
 * Created by Vikash Kothary on 13-Dec-15.
 */
public class Event extends RequestParams {

    public Event(String name, String description, double latitude, double longitude, int radius) {
        put("name", name);
        put("description", description);
        put("latitude", String.valueOf(latitude));
        put("longitude", String.valueOf(longitude));
        put("radius", String.valueOf(radius));
    }

    public Event(String name) {
        put("name", name);
        put("description", "");
        put("latitude", String.valueOf(0.0));
        put("longitude", String.valueOf(0.0));
        put("radius", String.valueOf(1));
    }
}
