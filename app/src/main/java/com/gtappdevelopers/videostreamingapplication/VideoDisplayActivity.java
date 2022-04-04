package com.gtappdevelopers.videostreamingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class VideoDisplayActivity extends AppCompatActivity {

    private ImageView videIV;
    private ImageButton playIB;
    private TextView videTitleTV, videoCategoryTV, videoDescTV;
    String videoTitle, videoDesc, videoCategory, videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_display);
        videoTitle = getIntent().getStringExtra("videoTitle");
        videoDesc = getIntent().getStringExtra("videoDesc");
        videoCategory = getIntent().getStringExtra("videoCategory");
        videoID = getIntent().getStringExtra("videoID");

        videIV = findViewById(R.id.idIVVideo);
        playIB = findViewById(R.id.idIBPlay);
        videTitleTV = findViewById(R.id.idTVTitle);
        videoDescTV = findViewById(R.id.idTVVideoDesc);
        videoCategoryTV = findViewById(R.id.idTVCategory);

        playIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VideoDisplayActivity.this, VideoPlayerActivity.class);
                i.putExtra("videoID", videoID);
                startActivity(i);
            }
        });

        String thumbnailUrl = "http://img.youtube.com/vi/" + videoID + "/hqdefault.jpg";
        Picasso.get().load(thumbnailUrl).into(videIV);
        videTitleTV.setText(videoTitle);
        videoCategoryTV.setText(videoCategory);
        videoDescTV.setText(videoDesc);

    }
}