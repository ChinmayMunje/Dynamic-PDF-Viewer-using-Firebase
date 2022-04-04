package com.gtappdevelopers.videostreamingapplication.ViewHoler;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gtappdevelopers.videostreamingapplication.R;
import com.squareup.picasso.Picasso;

public class VideoViewHoler extends RecyclerView.ViewHolder {
    public void setVideoIV(String imgUrl) {
        Picasso.get().load(imgUrl).into(videoIV);
    }

    private ImageView videoIV;

    public VideoViewHoler(@NonNull View itemView) {
        super(itemView);
        videoIV = itemView.findViewById(R.id.idIVVideo);
    }

}
