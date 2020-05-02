<?php
class ModelResponse {
  public $responseObject, $responseCode;

  public function __construct($responseObject = null, $responseCode = 0) {
    $this->responseObject = $responseObject;
    $this->responseCode = $responseCode;
  }

}