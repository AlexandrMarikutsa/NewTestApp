package com.demo.develop.newtestapp.classes;

public class Click {
    private int rating;
    private String text;
    private long timeStamp;

    public Click(int rating, long timeStamp) {
        this.rating = rating;
        this.timeStamp = timeStamp;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
