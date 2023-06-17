package com.example.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;


public class MyGeofenceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent.hasError()) {
            Log.e("location>>>>", "GeofencingEvent error: " + geofencingEvent.getErrorCode());
            return;
        }

        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            // Handle geofence enter event
            Toast.makeText(context, "Geofence enter event", Toast.LENGTH_SHORT).show();
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            // Handle geofence exit event
            Toast.makeText(context, "Geofence exit event", Toast.LENGTH_SHORT).show();
        }
    }
}
