package com.example.amantewary.scrawl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.example.amantewary.scrawl.Handlers.NotificationHandler;

public class NotificationReceiver extends BroadcastReceiver {

    public NotificationHandler notificationHandler;

    @Override
    public void onReceive(Context context, Intent intent) {
        String noteTitle = intent.getStringExtra("notetitle");
        notificationHandler = new NotificationHandler(context);
        /*
            Setting notification message.
         */
        NotificationCompat.Builder builder = notificationHandler.getChannelNotification("Check " + noteTitle, "Your Note is Ready");
        notificationHandler.getManager().notify(1, builder.build());
        /*
            Playing Notification Sound
         */
        MediaPlayer notifyTone = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
        notifyTone.start();
    }

}
