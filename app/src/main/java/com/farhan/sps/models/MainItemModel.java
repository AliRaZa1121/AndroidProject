package com.farhan.sps.models;

public class MainItemModel {

    int id;
    String title;
    int drawable;
    String backgroundColor;


    public MainItemModel(int id, String title, int drawable, String backgroundColor) {
        this.title = title;
        this.id = id;
        this.drawable = drawable;
        this.backgroundColor = backgroundColor;
    }


    public MainItemModel(int id, int drawable) {
        this.id = id;
        this.drawable = drawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}

