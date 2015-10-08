package com.demo.develop.newtestapp.classes;

public class Click {
    private int rating;
    private String text;
    private int timeStamp;

    public Click(int rating, int timeStamp) {
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

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
}
