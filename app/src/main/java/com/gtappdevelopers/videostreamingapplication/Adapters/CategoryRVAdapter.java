package com.gtappdevelopers.videostreamingapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.firebase.ui.firestore.paging.LoadingState;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.gtappdevelopers.videostreamingapplication.Data.CategoryRVModal;
import com.gtappdevelopers.videostreamingapplication.Data.VideoRVModal;
import com.gtappdevelopers.videostreamingapplication.R;
import com.gtappdevelopers.videostreamingapplication.ViewHoler.CategoryViewHoler;

public class CategoryRVAdapter  extends FirestorePagingAdapter<CategoryRVModal, CategoryViewHoler> {
    private TextView categoryTV;
    private Context ctx;
    private ProgressBar loadingPB;
    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    public CategoryRVAdapter(@NonNull FirestorePagingOptions<CategoryRVModal> options,Context context,ProgressBar loadingPB) {
        super(options);
        this.ctx = context;
        this.loadingPB = loadingPB;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryViewHoler categoryViewHoler, int i, @NonNull CategoryRVModal categoryRVModal) {
        categoryTV = categoryViewHoler.itemView.findViewById(R.id.idTVCategory);
        categoryViewHoler.setCategoryNameTV(categoryRVModal.getCategoryName());

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
        firebaseFirestore.setFirestoreSettings(settings);
        FirestorePagingAdapter videoRVAdapter = new VideoRVAdapter(getFirestorePaging(firebaseFirestore,categoryRVModal.getCategoryName()));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL,false);
        categoryViewHoler.setVideosRV(videoRVAdapter,layoutManager);
    }

    @NonNull
    @Override
    public CategoryViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv_item,parent,false);
        return new CategoryViewHoler(view);
    }

    private Query getChildQuery(FirebaseFirestore firebaseFirestore,String category){
        Query query = firebaseFirestore.collection("Videos").whereEqualTo("videoCategory",category);
        query.orderBy("timestamp",Query.Direction.DESCENDING);
        return  query;
    }

    private FirestorePagingOptions<VideoRVModal> getFirestorePaging(FirebaseFirestore firebaseFirestore,String category){
        Query baseQUery = getChildQuery(firebaseFirestore,category);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPrefetchDistance(15)
                .setPageSize(15)
                .build();

        return new FirestorePagingOptions.Builder<VideoRVModal>()
                .setLifecycleOwner((LifecycleOwner)ctx)
                .setQuery(baseQUery,config,VideoRVModal.class)
                .build();
    }

    private void toggleProgressBar(boolean noData, boolean initialLoad){
        if(initialLoad){
            loadingPB.setVisibility(View.VISIBLE);
        }else if(noData){
            loadingPB.setVisibility(View.GONE);
            Toast.makeText(ctx, "No data found..", Toast.LENGTH_SHORT).show();
        }else{
            loadingPB.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onLoadingStateChanged(@NonNull LoadingState state) {
        switch (state){
            case LOADING_INITIAL:
                toggleProgressBar(true,true);
                break;
            case LOADING_MORE:
                super.onLoadingStateChanged(state);
                break;
            case LOADED:
                int count = getItemCount();
                if(count>0){
                    toggleProgressBar(false,false);
                }else{
                    toggleProgressBar(true,false);
                }
                break;
            case ERROR:
                retry();
                super.onLoadingStateChanged(state);
        }
    }
}
