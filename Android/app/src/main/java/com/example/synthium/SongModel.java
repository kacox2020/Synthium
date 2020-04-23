package com.example.synthium;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SongModel {
    List<Song> songs = new ArrayList<>();
    public List<Song> getMusic() {
        return songs;
    }

    public void setMusic(JSONObject songObject) throws JSONException {
        Song song = new Song(songObject.getString("songTitle"), songObject.getString("songArtist"), songObject.getInt("songID"), songObject.getInt("songLength"));
        songs.add(song);
    }
}
