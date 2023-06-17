package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import com.example.firebase.databinding.ActivityVideoPlayerBinding;

public class VideoPlayerActivity extends AppCompatActivity {


    private ActivityVideoPlayerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            String videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
            Uri uri = Uri.parse(videoUrl);
            binding.videoView.setVideoURI(uri);
            binding.videoView.start();

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(binding.videoView);
            binding.videoView.setMediaController(mediaController);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}