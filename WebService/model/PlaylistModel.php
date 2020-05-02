<?php
require_once(__DIR__ . "/Database.php");
require_once(__DIR__ . "/types/Playlist.php");
require_once(__DIR__ . "/types/ModelResponse.php");
require_once(__DIR__ . "/JSONDatasource.php");

class PlaylistModel{
public static function createPlaylist(Playlist $playlist) {
    $database = new Database();
    $results = $database->executeSql("INSERT INTO tblPlaylists (playlistName) VALUES (?)", "s", array($playlist->playlistName));
    return new ModelResponse($results[0]);
}


    public static function getPlaylists() {
    $database = new Database();
    $results = $database->executeSql("SELECT * FROM tblPlaylists");

    return new ModelResponse($results);
}

    // Gets a singular Playlist
    public static function getPlaylist($playlistID) {
    $database = new Database();
    $results = $database->executeSql("SELECT playlistName FROM tblPlaylists WHERE playlistID = ?", "i", array($playlistID));
    return new ModelResponse($results);
}

    public static function updatePlaylist(Playlist $playlist) {
    $database = new Database();

    $database->executeSql("UPDATE tblPlaylists SET playlistName WHERE playlistID = ?", "si", array($playlist->playlistID));

    return new ModelResponse();
}
    public static function deletePlaylist($playlistID) {
    $database = new Database();

    $database->executeSql("DELETE FROM tblPlaylists WHERE playlistID = ?", "i", array($playlistID));

    return new ModelResponse();
}


}