package com.locsysrepo.sensors;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by root on 06/03/18.
 */

public interface OnGpsDataCallback {
    public void onGpsSample(Location loc);
}
