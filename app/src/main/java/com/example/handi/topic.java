package com.example.handi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.*;

public class topic extends AppCompatActivity {

    Button quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        quiz=findViewById(R.id.quiz);
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

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String topic=lang+"Quiz";
                ArrayList<question> customQuestion = new ArrayList<>();
                db.collection(topic)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        //Log.d(TAG, document.getId() + " => " + document.getData());
                                        customQuestion.add(new question(document.getData().get("question")+"",
                                                document.getData().get("option_1")+"",
                                                document.getData().get("option_2")+"",
                                                document.getData().get("option_3")+"",
                                                document.getData().get("option_4")+"",
                                                document.getData().get("answer")+""));
                                    }
                                    Intent in =new Intent(topic.this,play_quiz.class);
                                    in.putExtra("quizList", (Serializable) customQuestion);
                                    startActivity(in);
                                }
                                else
                                    {
                                    //Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });

                /*
                customQuestion.add(new question(document.getData().get("question")+"",
                                            document.getData().get("option_1")+"",
                                            document.getData().get("option_2")+"",
                                            document.getData().get("option_3")+"",
                                            document.getData().get("option_4")+"",
                                            document.getData().get("answer")+""));
                                    Intent in =new Intent(topic.this,play_quiz.class);
                                    in.putExtra("quizList", (Serializable) customQuestion);
                                    startActivity(in);
                customQuestion.add(new question("color of orange","red","orange","pink","black","B"));
                customQuestion.add(new question("color of apple","red","yello","pink","blue","A"));
                customQuestion.add(new question("color of sky","blue","orange","yellow","black","A"));
                */

            }
        });
    }
}