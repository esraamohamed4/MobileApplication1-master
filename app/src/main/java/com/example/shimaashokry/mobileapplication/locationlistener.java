package com.example.shimaashokry.mobileapplication;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Shimaa Shokry on 4/24/2017.
 */

public class locationlistener implements LocationListener {
    Context context ;
    public locationlistener(Context context)
    {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {
            
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
