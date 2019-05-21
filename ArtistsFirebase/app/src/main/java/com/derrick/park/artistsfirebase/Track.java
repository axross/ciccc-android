package com.derrick.park.artistsfirebase;

import com.google.firebase.Timestamp;

import java.util.Date;

public class Track {
    private String name;
    private int rating;
    private Timestamp addedDate;

    public Track() {
        // firestore will use this to read data
    }

    public Track(String name, int rating) {
        this.name = name;
        this.rating = rating;
        this.addedDate = new Timestamp(new Date());
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }
}
