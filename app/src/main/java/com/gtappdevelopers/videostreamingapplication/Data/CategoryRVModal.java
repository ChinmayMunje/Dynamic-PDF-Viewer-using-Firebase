package com.gtappdevelopers.videostreamingapplication.Data;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class CategoryRVModal {
    private String categoryName;
    @ServerTimestamp
    public Date timestamp;

    public CategoryRVModal() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
