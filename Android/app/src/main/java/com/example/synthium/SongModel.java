package com.example.synthium;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SongModel {
    List<Song> songs = new ArrayList<>();

    public List<Song> getMusic() {
        return songs;
    }

    public void setMusic(JSONObject songObject) throws JSONException{
        Song song = new Song(songObject.getString("songTitle"), songObject.getString("songArtist"), songObject.getInt("songID"), songObject.getInt("songLength"));
        songs.add(song);
    }
}
