package com.example.spades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RouteListingPreference;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity {

//    private static final String CHANNEL_ID = "my_channel";
//    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    private Button security;
    private Button alert;
    private Button contactNeighbour;

    private Button contactGuard;

    private ImageButton imageButton;

    private ImageButton sosBtn;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NEIGHBOUR_CONTACT = "contactNeighbour";
    private static final String KEY_GUARD_CONTACT = "contactGuard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFCMToken();

        FirebaseApp.initializeApp(this);

//   GETTING THE IDs OF ALL THE VIEWS ------------
        security = findViewById(R.id.security);
        alert = findViewById(R.id.alert);
        contactNeighbour = findViewById(R.id.contact);
        contactGuard = findViewById(R.id.contact_security);
        imageButton = findViewById(R.id.imageButton);
        sosBtn = findViewById(R.id.imageButton2);

//   WORKING OF SOS BUTTON -----
        sosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "SOS", Toast.LENGTH_SHORT);
                toast.show();
        }
        });
    }


//    OPENS THE SECURITY CHECK ACTIVITY ------ SECURITY CHECK BUTTON
    public void securityCheck(View v) {
        Intent intent = new Intent(this, security_check.class);
        startActivity(intent);
    }

//    OPENS THE ALERT ACTIVITY --------- ALERT BUTTON
    public void alert(View v) {
        Intent intent = new Intent(this, com.example.spades.alert.class);
        startActivity(intent);
    }

//    OPENS THE ACCOUNT ACTIVITY --------- ACCOUNT BUTTON
    public void account(View v) {
        Intent intent = new Intent(this, account.class);
        startActivity(intent);
    }

//    OPENS THE DIALER FOR CALLING THE NEIGHBOUR -------- CALL NEIGHBOUR BUTTON
    public void dial_neighbour(View v) {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String contactN = sharedPreferences.getString(KEY_NEIGHBOUR_CONTACT, null);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+contactN));
        startActivity(intent);
    }

//    OPENS THE DIALER FOR CALLING THE NEIGHBOUR -------- CALL NEIGHBOUR BUTTON
    public void dial_guard(View v) {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String contactG = sharedPreferences.getString(KEY_GUARD_CONTACT, null);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+contactG));
        startActivity(intent);
    }

    void getFCMToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String token = task.getResult();
                FirebaseMessaging.getInstance().subscribeToTopic("Alert");
                Log.i("My token ", token);
            }
        });
    }
    
}
