package com.example.synthium;

public class Song {
    public String songTitle, songArtist;
    public int songID, songLength;

    public Song(String songTitle, String songArtist, int songID, int songLength) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songID = songID;
        this.songLength = songLength;
    }

}
