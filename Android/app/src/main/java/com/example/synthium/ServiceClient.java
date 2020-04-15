package com.example.synthium;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ServiceClient {
    private static ServiceClient serviceClient;
    private static Context context;
    private RequestQueue requestQueue;

    private ServiceClient(Context ctx) {
        context = ctx;
    }

    // Create a service client if none but return back if existing (synch to avoid racing condition)
    synchronized public static ServiceClient getInstance(Context ctx) {
        if (serviceClient == null) {
            serviceClient = new ServiceClient(ctx);
        }

        return serviceClient;
    }

    synchronized public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public void addRequest(Request request) {
        this.getRequestQueue().add(request);
    }
}
