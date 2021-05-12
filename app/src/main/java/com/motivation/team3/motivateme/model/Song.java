package com.motivation.team3.motivateme.model;

public class Song {
    String name;
    String artist;
    int image;
    public Song(){

    }

    public Song(String name, String artist, int image) {
        this.name = name;
        this.artist = artist;
        this.image = image;
    }

    public String getName()
    {
        return name;
    }

    public String getArtist()
    {
        return artist;
    }

    public int getImage()
    {
        return image;
    }
}
