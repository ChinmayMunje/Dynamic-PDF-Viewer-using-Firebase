package com.gtappdevelopers.videostreamingapplication.Data;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class VideoRVModal {
    private String videoTitle;
    private String videoDesc;
    private String videoID;
    private String videoCategory;
    @ServerTimestamp
    private Date timestamp;

    public VideoRVModal() {
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(String videoCategory) {
        this.videoCategory = videoCategory;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
