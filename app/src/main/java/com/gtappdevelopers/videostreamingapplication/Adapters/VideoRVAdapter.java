package com.gtappdevelopers.videostreamingapplication.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.gtappdevelopers.videostreamingapplication.Data.VideoRVModal;
import com.gtappdevelopers.videostreamingapplication.R;
import com.gtappdevelopers.videostreamingapplication.VideoDisplayActivity;
import com.gtappdevelopers.videostreamingapplication.ViewHoler.VideoViewHoler;

public class VideoRVAdapter extends FirestorePagingAdapter<VideoRVModal, VideoViewHoler> {
    private ImageView videoIV;

    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    public VideoRVAdapter(@NonNull FirestorePagingOptions<VideoRVModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VideoViewHoler videoViewHoler, int i, @NonNull VideoRVModal videoRVModal) {
        videoIV = videoViewHoler.itemView.findViewById(R.id.idIVVideo);
        String thumbnailUrl = "http://img.youtube.com/vi/" + videoRVModal.getVideoID() + "/hqdefault.jpg";
        videoViewHoler.setVideoIV(thumbnailUrl);
        videoViewHoler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(videoViewHoler.itemView.getContext(), VideoDisplayActivity.class);
                i.putExtra("videoTitle",videoRVModal.getVideoTitle());
                i.putExtra("videoCategory",videoRVModal.getVideoCategory());
                i.putExtra("videoDesc",videoRVModal.getVideoDesc());
                i.putExtra("videoID",videoRVModal.getVideoID());
                videoViewHoler.itemView.getContext().startActivity(i);

            }
        });
    }

    @NonNull
    @Override
    public VideoViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_rv_item, parent, false);
        return new VideoViewHoler(view);
    }
}
