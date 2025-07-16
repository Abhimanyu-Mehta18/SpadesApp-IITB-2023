package com.example.spades;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class security_check extends AppCompatActivity {
    VideoView videoView;
//    String videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_check);
        VideoView videoView = findViewById(R.id.videoView);
//        Uri uri = Uri.parse(videoUrl);
//        videoView.setVideoURI(uri);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.cctv);
        videoView.start();
//        String url = "http://www.example.com";
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }
}