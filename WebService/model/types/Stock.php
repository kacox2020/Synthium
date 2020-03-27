<?php
require_once(__DIR__ . "/Base.php");
class Stock extends Base {
  public $symbol, $price;
  public function __construct($sourceObject) {
    parent::__construct($sourceObject);
  }
}