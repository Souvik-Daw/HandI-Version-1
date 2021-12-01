package com.example.handi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class lession_text extends AppCompatActivity {
    TextView text;
    Button btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lession_text);
        text=findViewById(R.id.text);
        btn=findViewById(R.id.btn);
        btn2=findViewById(R.id.btn2);
        Intent intent = getIntent();
        String lesson = intent.getStringExtra("videoToText");
        String audio = intent.getStringExtra("videoToText2");
        text.setText(lesson);
        String url = audio; // your URL here
        MediaPlayer mediaPlayer = new MediaPlayer();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mediaPlayer.setAudioAttributes(
                                new AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .setUsage(AudioAttributes.USAGE_MEDIA)
                                        .build()
                        );
                    }
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    mediaPlayer.start();
                }
                catch (Exception e)
                {

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    mediaPlayer.pause();
                }
                catch (Exception e)
                {

                }
            }
        });
    }
}