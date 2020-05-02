<?php
require_once(__DIR__ . "/../model/types/BrokerRequest.php");
require_once(__DIR__ . "/../model/types/BrokerResponse.php");
require_once(__DIR__ . "/../model/StockModel.php");
require_once(__DIR__ . "/../model/types/ModelResponse.php");
require_once(__DIR__ . "/../model/types/Stock.php");

class StockBroker {
  static public function get(BrokerRequest $request) : BrokerResponse {
    $response = StockModel::getStocks();

    return new BrokerResponse($response->responseObject);
  }
  static public function post(BrokerRequest $request) : BrokerResponse {
    $response = StockModel::addStock(new Stock($request->body));

    return new BrokerResponse($response->responseObject);
  }
}