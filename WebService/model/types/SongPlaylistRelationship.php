<?php
require_once(__DIR__ . "/Base.php");


class SongPlaylistRelationship extends Base
{
    public $playlistID, $songID;
    public function __construct($sourceObject) {
        parent::__construct($sourceObject);
    }
}