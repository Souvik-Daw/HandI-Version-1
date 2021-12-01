package com.example.handi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class subTopic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_topic);
        Intent intent = getIntent();
        String topic = intent.getStringExtra("topicAdaptorTosubTopic");
        Toast.makeText(this,topic,Toast.LENGTH_LONG).show();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<String> list = new ArrayList<>();
        db.collection(topic)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG1, document.getId() + " => " + document.getData());

                                list.add(document.getId());

                                String[] myArray = new String[list.size()];



                                for(int i=0;i<list.size();i++)
                                {
                                    myArray[i]=list.get(i);
                                }
                                //Toast.makeText(this,myArray[0],Toast.LENGTH_LONG).show();
                                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv2);
                                subTopicAdapter adapter = new subTopicAdapter(myArray,subTopic.this,topic);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(subTopic.this));
                                recyclerView.setAdapter(adapter);
                            }
                        } else {
                            //Log.d(TAG1, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}