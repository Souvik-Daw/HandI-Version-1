package com.example.handi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    Button english;
    Button hindi;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //logout=findViewById(R.id.logout);
        english=findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,topic.class);
                in.putExtra("mainToTopic","English");
                startActivity(in);
            }
        });
        hindi=findViewById(R.id.hindi);
        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,topic.class);
                in.putExtra("mainToTopic","Hindi");
                startActivity(in);
            }
        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT ).show();
//                CentralStorage cs = new CentralStorage(MainActivity.this);
//                cs.clearData();
//                cs.removeData("userid");
//                startActivity(new Intent(MainActivity.this,login.class));
//                MainActivity.this.finish();
//            }
//        });
    }
}