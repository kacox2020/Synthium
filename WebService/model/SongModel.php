<?php
require_once(__DIR__ . "/types/Song.php");
require_once(__DIR__ . "/types/ModelResponse.php");
require_once(__DIR__ . "/JSONDatasource.php");


class SongModel
{
    public static function createSong(Song $song) {
        $database = new Database();
        $results = $database->executeSql("INSERT INTO tblSongs (songTitle, songArtist, songLength) VALUES (?,?,?)", "ssi", array($song->songTitle, $song->songArtist, $song->songLength));
        return new ModelResponse($results[0]);
    }

    // Gets all songs
    public static function getSongs() {
        $database = new Database();
        $results = $database->executeSql("SELECT * FROM tblSongs");

        return new ModelResponse($results);
    }

    // Gets a singular song
    public static function getSong($songID) {
        $database = new Database();
        $results = $database->executeSql("SELECT songTitle, songArtist, songLength FROM tblSongs WHERE songID = ?", "i", array($songID));
        return new ModelResponse($results);
    }

    public static function updateSong(Song $song) {
        $database = new Database();

        $database->executeSql("UPDATE tblSongs SET songTitle = ?, songArtist = ?, songLength = ? WHERE songID = ?", "ssii", array($song->songTitle, $song->songArtist, $song->songLength, $song->songID));

        return new ModelResponse();
    }
    public static function deleteSong($songID) {
        $database = new Database();

        $database->executeSql("DELETE FROM tblSongs WHERE songID = ?", "i", array($songID));

        return new ModelResponse();
    }

}