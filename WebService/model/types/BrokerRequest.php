<?php
class BrokerRequest {
  public $body, $id;

  public function __construct($body, $id = null) {
    $this->body = $body;
    $this->id = $id;
  }

}