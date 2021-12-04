package com.example.handi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class Result extends AppCompatActivity {

    TextView score , options ;
    Button backBtn, shareBtn ;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        lottieAnimationView=findViewById(R.id.imageView6);
        lottieAnimationView.animate().setDuration(10000);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.setAnimation(R.raw.trophy);
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        score = findViewById(R.id.score);
        options = findViewById(R.id.options);
        shareBtn = findViewById(R.id.shareBtn);
        backBtn = findViewById(R.id.backBtn);
        //Intent intent = getIntent();
        //String videoUrl2 = intent.getStringExtra("subtopicAdaptorTolessionvideo");
        int correctAnswers = Integer.parseInt(getIntent().getStringExtra("correct"));
        int totalQuestions = getIntent().getIntExtra("total", 0);

        score.setText(String.format("%d/%d", correctAnswers, totalQuestions));
    }
    public void share(View view) {
        Toast.makeText(Result.this,"Share with your friends about your success.",
                Toast.LENGTH_LONG).show();
    }

    public void back(View view)
    {
        Intent intent = new Intent(Result.this, MainActivity.class);
        startActivity(intent);
        Result.this.finish();
    }
}