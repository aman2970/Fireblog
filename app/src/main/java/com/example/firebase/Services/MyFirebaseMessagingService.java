package com.example.firebase.Services;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.firebase.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private MediaPlayer mediaPlayer;
    private String imageUrl;
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

//        // Retrieve the notification data
//        String title = Objects.requireNonNull(remoteMessage.getNotification()).getTitle();
//        String body = remoteMessage.getNotification().getBody();
//
//        // Create and display the notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "fireblog")
//                .setSmallIcon(R.drawable.fire_logo)
//                .setContentTitle(title)
//                .setContentText(body)
//                .setPriority(NotificationCompat.PRIORITY_MAX)
//                .setAutoCancel(true);
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(0, builder.build());

        // Extract notification data
        if(remoteMessage.getNotification() != null){
            String title = Objects.requireNonNull(remoteMessage.getNotification()).getTitle();
            String body = remoteMessage.getNotification().getBody();
            if(remoteMessage.getNotification().getImageUrl() != null){
                imageUrl =remoteMessage.getNotification().getImageUrl().toString();
            }

            Bitmap imageBitmap = getBitmapfromUrl(imageUrl);


            // Create custom notification with image

            if (imageBitmap != null) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "fireblog")
                        .setSmallIcon(R.drawable.fire_logo)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setLargeIcon(imageBitmap)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(imageBitmap)
                                .bigLargeIcon(null))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(0, builder.build());
            } else {
                // If image retrieval fails, create notification without image
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "fireblog")
                        .setSmallIcon(R.drawable.fire_logo)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(0, builder.build());
            }
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.popup_noti_sound);
            mediaPlayer.start();
        }


        if(remoteMessage.getData().size() >0){
            String type = remoteMessage.getData().get("type");
            String map = remoteMessage.getData().get("map");
            Log.d("data>>>>>>",type);
            Log.d("data>>>>>>",map);
        }

    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            Log.e("awesome", "Error in getting notification image: " + e.getLocalizedMessage());
            return null;
        }
    }

}
