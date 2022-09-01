package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.notification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String CHANNEL_ID = "name of notification";
    private static final int NOTIFICATION_ID = 100;
    private static final int REQ_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.notification,null);
        BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
        Bitmap largeIcon= bitmapDrawable.getBitmap();

        Intent iNotify=new Intent(getApplicationContext(),MainActivity.class);
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent= PendingIntent.getActivity(this,REQ_CODE,iNotify,PendingIntent.FLAG_UPDATE_CURRENT);
        binding.SendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notification= new Notification.Builder(MainActivity.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.notification)
                            .setContentText("New message")
                            .setSubText("New message from Shariful")
                            .setContentIntent(pendingIntent)
                            .setChannelId(CHANNEL_ID)
                            .build();
                    nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel",NotificationManager.IMPORTANCE_HIGH));
                }else {
                    notification= new Notification.Builder(MainActivity.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.notification)
                            .setContentText("New message")
                            .setSubText("New message from Shariful")
                            .setContentIntent(pendingIntent)
                            .build();
                }
                nm.notify(NOTIFICATION_ID,notification);
            }
        });




    }
}