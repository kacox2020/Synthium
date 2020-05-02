package com.example.synthium;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaylistModel {
    List<Playlist> playlistList = new ArrayList<>();
    public List<Playlist> getPlaylists() {
        return playlistList;
    }

    public void setPlaylist(JSONObject playlistObject) throws JSONException {
        Playlist playlist = new Playlist(playlistObject.getInt("playlistID"), playlistObject.getString("playlistName"));
        playlistList.add(playlist);
    }
}
