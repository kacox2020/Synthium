<?php
require_once(__DIR__ . "/Base.php");

class Song extends Base
{
    public $songTitle, $songArtist, $songLength, $songID, $songURL;
    public function __construct($sourceObject) {
        parent::__construct($sourceObject);
    }

}