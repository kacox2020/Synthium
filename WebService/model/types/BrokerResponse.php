<?php
class BrokerResponse {
  public $responseObject, $responseCode;

  public function __construct($responseObject, $code = 0) {
    $this->responseObject = $responseObject;
    $this->responseCode = $code;
  }
}