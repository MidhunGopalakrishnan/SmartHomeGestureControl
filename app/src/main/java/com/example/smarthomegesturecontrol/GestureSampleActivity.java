package com.example.smarthomegesturecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class GestureSampleActivity extends AppCompatActivity {
    VideoView videoView;
    Uri videoUri;
    public static final String DIR = "/Download/ExpertGesture/";
    private String gestureFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_sample);
        String gesture = (String)getIntent().getExtras().get("Gesture");
        gestureFinal = gesture;

        String gestureFileName = gesture+".mp4";
        gestureFileName = gestureFileName.replaceAll("\\s","_");
        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory()+DIR+gestureFileName));

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();

        Button practiceButton = (Button) findViewById(R.id.button2);
        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestureSampleActivity.this,CaptureActivity.class);
                intent.putExtra("Gesture",gestureFinal);
                startActivity(intent);
            }
        });

    }
}