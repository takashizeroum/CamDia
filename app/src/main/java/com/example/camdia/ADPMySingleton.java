package com.example.camdia;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class ADPMySingleton {

    private static ADPMySingleton myInstance;
    private RequestQueue requestQueue;
    private static Context myContext;

    public ADPMySingleton(Context context) {
        myContext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(myContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized ADPMySingleton getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new ADPMySingleton(context);
        }
        return myInstance;
    }

    public <T> void addToRequestQue(Request<T> request) {
        requestQueue.add(request);
    }

}
