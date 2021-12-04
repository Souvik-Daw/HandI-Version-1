package com.example.handi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Welcome extends AppCompatActivity {
    CentralStorage storage;
    String value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        new Handler().postDelayed(
                () -> {

                        startActivity(new Intent(Welcome.this, MainActivity.class));

                    Welcome.this.finish();
                },
                5000
        );
    }
}