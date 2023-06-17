package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.firebase.databinding.ActivityNotificationsDemoAreaBinding;

public class MapsDemoArea extends AppCompatActivity {
    private ActivityNotificationsDemoAreaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsDemoAreaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapContainer, new GoogleMapsFragment());
        fragmentTransaction.commit();
    }

}