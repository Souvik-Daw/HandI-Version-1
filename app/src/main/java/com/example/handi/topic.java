package com.example.handi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.util.*;

public class topic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Intent intent = getIntent();
        String lang = intent.getStringExtra("mainToTopic");
        if(lang.equals("English"))
        {
            String[] subTopic=new String[2];
            subTopic[0]="English_Letters";
            subTopic[1]="English_Lessons";
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            topicAdapter adapter = new topicAdapter(subTopic,topic.this);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
        else if(lang.equals("Hindi"))
        {
            String[] subTopic=new String[1];
            subTopic[0]="Hindi_Letters";
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            topicAdapter adapter = new topicAdapter(subTopic,topic.this);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(this,lang+"",Toast.LENGTH_LONG).show();
        }
    }
}