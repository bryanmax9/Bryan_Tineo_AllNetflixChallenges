package com.company.chatterbook.models;

public class ChatterPost {

    private String text;

    public ChatterPost(String text) {
        this.text = text;
    }

    //we are creatting a getter since the  string is private, tthis will allow tto display in json when requested a  get request
    public String getText() {
        return text;
    }
}
