package com.example.amantewary.scrawl.Handlers;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.amantewary.scrawl.MainActivity;
import com.example.amantewary.scrawl.R;

public class NotificationHandler extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel";

    private NotificationManager manager;

    public NotificationHandler(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel() {
        NotificationChannel newChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        newChannel.enableLights(true);
        newChannel.enableVibration(true);
        newChannel.setLightColor(R.color.colorPrimary);
        newChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getManager().createNotificationChannel(newChannel);
    }

    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public NotificationCompat.Builder getChannelNotification(String title, String message) {
        Intent clickIntent = new Intent(this, MainActivity.class);
        PendingIntent clickPendingIntent = PendingIntent.getActivity(this, 1, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_timer_icon)
                .setAutoCancel(true)
                .setContentIntent(clickPendingIntent);
    }
}
