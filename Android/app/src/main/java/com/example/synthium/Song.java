package com.example.synthium;

public class Song {
    public String songTitle, songArtist,songURL;
    public int songID, songLength;

    public Song(String songTitle, String songArtist, int songID, int songLength, String songUrl) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songID = songID;
        this.songLength = songLength;
        this.songURL = songUrl;
    }

}
