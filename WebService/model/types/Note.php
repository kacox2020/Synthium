<?php
require_once(__DIR__ . "/Base.php");
class Note extends Base {
  public $title, $contents, $userId, $id;
  public function __construct($sourceObject) {
    parent::__construct($sourceObject);
  }
}