package com.gtappdevelopers.videostreamingapplication.ViewHoler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.gtappdevelopers.videostreamingapplication.R;

public class CategoryViewHoler extends RecyclerView.ViewHolder {
    private TextView categoryNameTV;
    private RecyclerView videosRV;
    private Context ctx;

    public TextView getCategoryNameTV() {
        return categoryNameTV;
    }

    public void setCategoryNameTV(String category) {
        this.categoryNameTV.setText(category);
    }

    public RecyclerView getVideosRV() {
        return videosRV;
    }

    public void setVideosRV(FirestorePagingAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        this.videosRV.setLayoutManager(layoutManager);
        this.videosRV.setAdapter(adapter);
    }

    public CategoryViewHoler(@NonNull View itemView) {
        super(itemView);
        categoryNameTV = itemView.findViewById(R.id.idTVCategory);
        videosRV = itemView.findViewById(R.id.idRVVideos);
    }
}
