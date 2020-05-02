<?php
require_once(__DIR__ . "/Base.php");


class Playlist extends Base
{
    public $playlistID, $playlistName;
    public function __construct($sourceObject) {
        parent::__construct($sourceObject);
    }
}