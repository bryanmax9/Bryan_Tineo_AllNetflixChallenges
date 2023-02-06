package com.company.chatterbook.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    //Inittializing tthe name and array of this object
    private String name;
    private List<ChatterPost> chatterPosts = new ArrayList<ChatterPost>();

    public User(String name) {
        this.name = name;
    }

    //getter for the list in order to get the private list of chatterpostts

    public List<ChatterPost> getChatterPosts() {
        return chatterPosts;
    }

    //setter in order to set a new list of chatterpost to the private listt
    public void setChatterPosts(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }

    public String getName() {
        return name;
    }
}
