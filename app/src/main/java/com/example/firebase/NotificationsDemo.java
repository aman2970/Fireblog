package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.RemoteViews;

import com.example.firebase.databinding.ActivityNotificationsDemoBinding;

public class NotificationsDemo extends AppCompatActivity {
    private ActivityNotificationsDemoBinding binding;
    private MediaPlayer mediaPlayer;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsDemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //NOTIFICATION CREATION ON CLICK OF A BUTTON

     /*   binding.createButton.setOnClickListener(v -> {

            Intent activityIntent = new Intent(this,LoginActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_MUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "fireblog")
                    .setSmallIcon(R.drawable.fire_logo)
                    .setContentTitle("NOTIFICATION")
                    .setContentText("NOTIFICATION")
                    .setColor(Color.RED)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .addAction(R.drawable.fire_logo,"CLICK ME",contentIntent)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(0, builder.build());
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.popup_noti_sound);
            mediaPlayer.start();
        });*/


        //CREATING A BIG TEXT STYLE NOTIFICATION
      /*  binding.createButton.setOnClickListener(v -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "fireblog")
                    .setSmallIcon(R.drawable.fire_logo)
                    .setContentTitle("NOTIFICATION")
                    .setContentText("NOTIFICATION")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("glglgugoutouuououououroufuyfiuyuyuyuvyuguyguguvvvvvvvvvvvvvvvvvvvvvvvuiiytdiytdyfouhggofdsagousfodugougsvouvoucsoug uygvougdougougvaougvouagouavouagouavguyjhh" +
                            "hiuhhpiugpiuyipuipugipuypiupiuipubuiiuoiubooooooooooooooooooooooooooooooooooooooooooittttttttttttttttttttttttttttttttttttttttttttttdtuuuuuuuuuuuufityityiu ouuouuuuuuuuuuuuuuuuuuuuuuuvyuyyyyyyyyyyyyyyyyyyyyyyyyotbbbbbbbbby" +
                            " oooooooooooooo oujuyf ourouyf ououfouiy sutrarkurrururuvut7vot7ot78ocr67e56ie65cr76rovvr8rv67r67vvr67r76vr6yiry6ir6cr6icri6cryiric6cr6icr677r6"))
                    .setColor(Color.RED)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(0, builder.build());
        });*/

     /*   //PROGRESS NOTIFICATION
        binding.createButton.setOnClickListener(v -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "fireblog")
                    .setSmallIcon(R.drawable.fire_logo)
                    .setContentTitle("Download")
                    .setContentText("Download in progress...")
                    .setColor(Color.RED)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setProgress(100, 0, true)
                    .setOngoing(true)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(0, builder.build());

            new Thread(() -> {
                SystemClock.sleep(2000);
                for (int progress = 0; progress <= 100; progress += 10) {
                    builder.setProgress(100, progress, false);
                    notificationManager.notify(0, builder.build());
                    SystemClock.sleep(1000);
                }
                builder.setContentText("Download Finished")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                notificationManager.notify(0, builder.build());
            }).start();

        });*/


        // SHOW CUSTOM NOTIFICATION ON CLICK
        binding.createButton.setOnClickListener(v -> {
            RemoteViews collapsedView = new RemoteViews(getPackageName(),R.layout.custom_notification_collapsed);
            RemoteViews expandedView = new RemoteViews(getPackageName(),R.layout.custom_notification_expanded);

            Notification notification = new NotificationCompat.Builder(this,"fireblog")
                    .setSmallIcon(R.drawable.fire_logo)
                    .setCustomContentView(collapsedView)
                    .setCustomBigContentView(expandedView)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1,notification);

            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.popup_noti_sound);
            mediaPlayer.start();

        });

    }
}