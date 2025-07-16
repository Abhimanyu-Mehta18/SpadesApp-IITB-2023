package com.example.spades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class alert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        String postValue = "";
        if (getIntent().getAction() != null && getIntent().getAction().equals("It was me")) {
            Log.i("It was me: ", "yes");
            postValue = "{\"action\": \"cancel_alarm\"}";
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.cancel(2);
        } else if (getIntent().getAction() != null && getIntent().getAction().equals("Ring alarm")) {
            postValue = "{\"action\":\"ring_alarm\"}";
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.cancel(2);
        }
        Log.i("Alert working", "success"+postValue);
        OkHttpClient okhttpclient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .build();

        RequestBody formBody = new FormBody.Builder().add("TestValue", postValue).build();

        // FETCHING THE URL OF THE API ------
        Request request = new Request.Builder()
                .url("http://192.168.0.68:8080/updateAlert") //add the desired url
                .post(formBody)
                .build();

        okhttpclient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) {
                // This method is called when the HTTP request is successful
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        // Process the response body as needed
                        Log.i("API Response", responseBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle unsuccessful response
                    int responseCode = response.code();
                    Log.e("API Error", "Unsuccessful response: " + responseCode);

                    // You can add more detailed handling based on the response code
                }
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                // This method is called when the HTTP request fails
                e.printStackTrace();
                Log.e("API Error", "Request failed");
            }
        });
    }
}
