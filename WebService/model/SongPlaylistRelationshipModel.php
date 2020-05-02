<?php
require_once(__DIR__ . "/Database.php");
require_once(__DIR__ . "/types/SongPlaylistRelationship.php");
require_once(__DIR__ . "/types/ModelResponse.php");
require_once(__DIR__ . "/JSONDatasource.php");

class SongPlaylistRelationshipModel
{
    public static function createPlaylistRelationship(SongPlaylistRelationship $songPlaylistRelationship) {
        $database = new Database();
        $results = $database->executeSql("INSERT INTO tblSongPlaylistRelationship (playlistID, songID) VALUES (?,?)", "ssis", array($songPlaylistRelationship->playlistID, $songPlaylistRelationship->songID));
        return new ModelResponse($results[0]);
    }


    public static function getSongPlaylistRelationships () {
        $database = new Database();
        $results = $database->executeSql("SELECT * FROM tblSongPlaylistRelationship ");

        return new ModelResponse($results);
    }

    // Gets a singular SongPlaylistRelationship
    public static function getSongPlaylistRelationship($playlistID) {
        $database = new Database();
        $results = $database->executeSql("SELECT songID, playlistID FROM tblSongPlaylistRelationship WHERE playlistID = ?", "i", array($playlistID));
        return new ModelResponse($results);
    }

    public static function updateSongPlaylistRelationship(SongPlaylistRelationship $songPlaylistRelationship) {
        $database = new Database();

        $database->executeSql("UPDATE tblSongPlaylistRelationship SET playlistID = ?, songID = ? WHERE songID = ? AND playlistID = ?", "iiii", array($songPlaylistRelationship->playlistID, $songPlaylistRelationship->songID));

        return new ModelResponse();
    }
    public static function deleteSongPlaylistRelationship($playlistID) {
        $database = new Database();

        $database->executeSql("DELETE FROM tblSongPlaylistRelationship WHERE playlistID = ?", "i", array($playlistID));

        return new ModelResponse();
    }

}