package com.testography.pushnotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Lilian on 1/30/2017.
 */

public class FirebaseMessagingService extends
        com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("message"));
    }

    private void showNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this)
                .setAutoCancel(true)
                .setContentTitle("FCM Text")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager
                        .TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
}
