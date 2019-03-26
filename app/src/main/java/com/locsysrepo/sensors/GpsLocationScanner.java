package com.locsysrepo.sensors;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.content.ContextWrapper;

import com.locsysrepo.android.BackgroundService;

import static android.content.ContentValues.TAG;

/**
 * Created by zeljko on 06/03/18.
 */

public class GpsLocationScanner extends BroadcastReceiver implements LocationListener {

    private Context context;
    private LocationManager locationManager;
    private OnGpsDataCallback gpsDataCallback;
    private Location loc;

    public GpsLocationScanner(Context context, OnGpsDataCallback gpsDataCallback) {
        this.context = context;
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        this.gpsDataCallback = gpsDataCallback;
        context.registerReceiver(this, new IntentFilter(LocationManager.GPS_PROVIDER));
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    @Override
    public void onLocationChanged(Location loc) {
        Toast.makeText(context, "GPS avail", Toast.LENGTH_SHORT).show();

        Log.d("loc change", "loc change");
        Toast.makeText(
                context,
                "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                        + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v(TAG, latitude);
        this.loc = loc;
        gpsDataCallback.onGpsSample(loc);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context, "GPS not avail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (status == LocationProvider.OUT_OF_SERVICE ||
                status == LocationProvider.TEMPORARILY_UNAVAILABLE)
            Toast.makeText(context, "GPS not avail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}

