package com.example.pubu.myapplication.domain;

/**
 * Created by pubu on 2015/12/31.
 */
public class FeedItem {
    private String title;
    private String thumbnail;

    public FeedItem(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public FeedItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
