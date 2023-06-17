package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

public class GoogleMapsFragment extends Fragment implements LocationListener {
    private List<LatLng> fenceCoordinates;
    private Polygon fencePolygon;
    private GoogleMap googleMap;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap googleMap) {
            //NORMAL LOCATION TAG
/*
            LatLng location = new LatLng(28.4526, 77.0701);
            googleMap.addMarker(new MarkerOptions().position(location).title("Marker on Zypp"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15.0f));
*/

            //WITH ZOOM AND ANIMATE CAMERA EFFECTS
         /*   LatLng location = new LatLng(28.4526, 77.0701);
            float zoomLevel = 19.0f;
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(location)
                    .zoom(zoomLevel)
                    .build();

            googleMap.addMarker(new MarkerOptions().position(location).title("Zypp Electric"));

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null);*/


            //CREATING A SHAPE ON MAP WITH COLORS

           /* fenceCoordinates = new ArrayList<>();
            fenceCoordinates.add(new LatLng(28.452628249838217, 77.06993623925176));
            fenceCoordinates.add(new LatLng(28.452836952855026, 77.07013740492764));
            fenceCoordinates.add(new LatLng(28.45268661597848, 77.07028157366163));
            fenceCoordinates.add(new LatLng(28.45250309732539, 77.0701029702936));


            PolygonOptions polygonOptions2 = new PolygonOptions()
                    .addAll(fenceCoordinates)
                    .strokeColor(Color.BLACK)
                    .strokeWidth(5f)
                    .fillColor(Color.GREEN);

            fencePolygon = googleMap.addPolygon(polygonOptions2);

            LatLng location = new LatLng(28.4526, 77.0701);
            googleMap.addMarker(new MarkerOptions().position(location).title("Marker on Zypp"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,20.0f));*/

            ArrayList<LatLng> fenceCoordinates = new ArrayList<>();
            fenceCoordinates.add(new LatLng(28.452628249838217, 77.06993623925176));
            fenceCoordinates.add(new LatLng(28.452836952855026, 77.07013740492764));
            fenceCoordinates.add(new LatLng(28.45268661597848, 77.07028157366163));
            fenceCoordinates.add(new LatLng(28.45250309732539, 77.0701029702936));

            float radius = 100; // radius in meters

            Geofence geofence = new Geofence.Builder()
                    .setRequestId("myGeofence") // unique identifier for the geofence
                    .setCircularRegion(fenceCoordinates.get(0).latitude, fenceCoordinates.get(0).longitude, radius)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                    .setExpirationDuration(Geofence.NEVER_EXPIRE)
                    .build();

            GeofencingRequest geofencingRequest = new GeofencingRequest.Builder()
                    .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                    .addGeofence(geofence)
                    .build();

            Intent intent = new Intent(getActivity(), MyGeofenceReceiver.class); // MyGeofenceReceiver is your custom BroadcastReceiver
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            GeofencingClient geofencingClient = LocationServices.getGeofencingClient(getActivity());
            geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Geofence added successfully
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to add geofence
                        }
                    });

            PolygonOptions polygonOptions = new PolygonOptions()
                    .addAll(fenceCoordinates)
                    .strokeColor(Color.BLACK)
                    .strokeWidth(5f)
                    .fillColor(Color.GREEN);

            googleMap.addPolygon(polygonOptions);

            LatLng location = new LatLng(28.4526, 77.0701);
            googleMap.addMarker(new MarkerOptions().position(location).title("Marker on Zypp"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,20.0f));

        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_google_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}