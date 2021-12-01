package com.example.handi;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import java.io.IOException;

public class subTopicAdapter extends RecyclerView.Adapter<subTopicAdapter.ViewHolder> {
    private String listdata[];
    String topic;
    Context c;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // RecyclerView recyclerView;
    public subTopicAdapter(String[] listdata,Context p,String topic)
    {
        this.topic=topic;
        this.c=p;
        this.listdata = listdata;
    }
    @Override
    public subTopicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.subtopic, parent, false);
        subTopicAdapter.ViewHolder viewHolder = new subTopicAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(subTopicAdapter.ViewHolder holder, int position) {
        final String myListData = listdata[position];
        holder.textView.setText(listdata[position]);
        DocumentReference docRef = db.collection(topic).document(myListData);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        String image=document.getData().get("img")+"";
                        Glide.with(c)
                                .load(image)
                                .into(holder.imageView);


                    } else {
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n=listdata.length;
                //Toast.makeText(view.getContext(),"click on item: "+myListData,Toast.LENGTH_LONG).show();

                //MediaPlayer music = MediaPlayer.create(c, R.raw.a);
                //music.start();
                DocumentReference docRef = db.collection(topic).document(myListData);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                String p=document.getData().get("audio")+"";
                                String a=document.getData().get("audio2")+"";
                                String video=document.getData().get("video")+"";

                                String url = p; // your URL here
                                MediaPlayer mediaPlayer = new MediaPlayer();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mediaPlayer.setAudioAttributes(
                                            new AudioAttributes.Builder()
                                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                                    .build()
                                    );
                                }
                                try {
                                    mediaPlayer.setDataSource(url);
                                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                                    mediaPlayer.start();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Intent in=new Intent(c,lession_video.class);
                                    in.putExtra("subtopicAdaptorTolessionvideo",video);
                                    in.putExtra("subtopicAdaptorTolessionvideo2",a);
                                    c.startActivity(in);
                                }
                            } else {
                                //Log.d(TAG, "No such document");
                            }
                        } else {
                            //Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });


            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ImageView imageView;
        //Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.iv);
            this.textView = (TextView) itemView.findViewById(R.id.tv2);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl2);
        }
    }
}
