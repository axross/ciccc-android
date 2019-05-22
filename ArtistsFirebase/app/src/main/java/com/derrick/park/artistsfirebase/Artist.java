package com.derrick.park.artistsfirebase;

import com.google.firebase.Timestamp;

import java.util.Date;

public class Artist {
    private String id;
    private String name;
    private String genre;
    private Timestamp addedDate;

    public Artist() {
        // firestore will use this to read data
    }

    public Artist(String id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.addedDate = new Timestamp(new Date());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }
}
