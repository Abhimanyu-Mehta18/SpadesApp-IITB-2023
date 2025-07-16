package com.example.spades;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseNotifs extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "my_channel";
    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NEIGHBOUR_CONTACT = "contactNeighbour";
    private static final String KEY_GUARD_CONTACT = "contactGuard";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        String action = remoteMessage.getData().get("action");

        if (action != null && action.equals("delivered")) {
            getFirebaseMessage(title, body);
        } else if (action != null && action.equals("abnormality")) {
            getFirebaseMessage2(title, body);
        }

        Log.i("Is working", "yes");
    }

    private void getFirebaseMessage(String title, String body) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String contactG = sharedPreferences.getString(KEY_GUARD_CONTACT, null);
        Intent callIntentGuard = new Intent(Intent.ACTION_DIAL);
        callIntentGuard.setData(Uri.parse("tel:" + contactG));
        PendingIntent callPendingIntent = PendingIntent.getActivity(this, 0, callIntentGuard, PendingIntent.FLAG_IMMUTABLE);

        String contactN = sharedPreferences.getString(KEY_NEIGHBOUR_CONTACT, null);
        Intent callIntentNeighbour = new Intent(Intent.ACTION_DIAL);
        callIntentNeighbour.setData(Uri.parse("tel:" + contactN));
        PendingIntent callPendingIntent2 = PendingIntent.getActivity(this, 0, callIntentNeighbour, PendingIntent.FLAG_IMMUTABLE);

        Intent intent = new Intent(this, alert.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_add_alert_24)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.baseline_add_alert_24, "Call Guard", callPendingIntent)
                .addAction(R.drawable.baseline_add_alert_24, "Call Neighbour", callPendingIntent2)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID + 1, notification);
        Log.i("Is working", "yes");
    }

    private void getFirebaseMessage2(String title, String body) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String contactG = sharedPreferences.getString(KEY_GUARD_CONTACT, null);
        Intent callIntentGuard = new Intent(Intent.ACTION_DIAL);
        callIntentGuard.setData(Uri.parse("tel:" + contactG));
        PendingIntent callPendingIntent = PendingIntent.getActivity(this, 0, callIntentGuard, PendingIntent.FLAG_IMMUTABLE);

        Intent intent = new Intent(this, alert.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Intent ItWasMeIntent = new Intent(this, alert.class);
        ItWasMeIntent.setAction("It was me");
        PendingIntent ItWasMePendingIntent = PendingIntent.getActivity(this, 0, ItWasMeIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent ringAlarm = new Intent(this, alert.class);
        ItWasMeIntent.setAction("Ring alarm");
        PendingIntent ringAlarmPendingIntent = PendingIntent.getActivity(this, 0, ItWasMeIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_add_alert_24)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.baseline_add_alert_24, "It was me", ItWasMePendingIntent)
                .addAction(R.drawable.baseline_add_alert_24, "Ring Alarm", ringAlarmPendingIntent)
                .addAction(R.drawable.baseline_add_alert_24, "Call Guard", callPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification2 = builder2.build();
        notificationManager.notify(NOTIFICATION_ID + 1, notification2);
        Log.i("Is working", "yes");
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "MyChannel";
            String channelDescription = "SUI";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);

            notificationManager.createNotificationChannel(channel);
        }
    }
}