package com.ensim.snowtam.Model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Singleton for RequestQueue
 */
public class SingletonRequestQueue {
    private static SingletonRequestQueue instance;
    private RequestQueue requestQueue;
    private final Context context;

    private SingletonRequestQueue(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if( instance == null ) instance = new SingletonRequestQueue(context);
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
