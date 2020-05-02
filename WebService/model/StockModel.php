<?php
require_once(__DIR__ . "/types/Stock.php");
require_once(__DIR__ . "/types/ModelResponse.php");
require_once(__DIR__ . "/JSONDatasource.php");
class StockModel {
  static public function addStock(Stock $stock) : ModelResponse {
    $stocks = JSONDatasource::getData();
    if ($stocks == null) {
      $stocks = array();
    }
    array_push($stocks, $stock);
    JSONDatasource::saveData($stocks);
    return new ModelResponse();

  }

  static public function getStocks() : ModelResponse {
    $stocks = JSONDatasource::getData();
    return new ModelResponse($stocks);
  }
}