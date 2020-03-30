<?php
require_once(__DIR__ . "/Base.php");

class Song extends Base
{
    public $songTitle, $songArtist, $songLength, $songID;
    public function __construct($sourceObject) {
        parent::__construct($sourceObject);
    }

}