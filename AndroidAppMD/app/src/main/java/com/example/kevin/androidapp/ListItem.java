package com.example.kevin.androidapp;

/**
 * Created by Kevin on 25-3-2016.
 */
public class ListItem {
    private String title;
    private String description;


    //Constructor
    public ListItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    //setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
