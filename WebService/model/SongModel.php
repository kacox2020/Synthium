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

    public static function getSongs($songID) {
        $database = new Database();
        $results = $database->executeSql("SELECT songTitle, songArtist, songLength FROM tblNotes WHERE songID = ?", "i", array($songID));
        return new ModelResponse($results);
    }

    public static function updateSong(Song $song) {
        $database = new Database();

        $database->executeSql("UPDATE tblSongs SET songTitle = ?, songArtist = ? WHERE songLength = ?", "ssi", array($song->songTitle, $song->songArtist, $song->songLength));

        return new ModelResponse();
    }
    public static function deleteSong($songID) {
        $database = new Database();

        $database->executeSql("DELETE FROM tblSongs WHERE noteId = ?", "i", array($songID));

        return new ModelResponse();
    }

}