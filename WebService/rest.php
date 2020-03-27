<?php
require_once(__DIR__ . "/brokers/StockBroker.php");
require_once(__DIR__ . "/brokers/NoteBroker.php");

$request = explode("/", $_SERVER["PATH_INFO"]);
$method = strtolower($_SERVER["REQUEST_METHOD"]);

$resource = $request[1];
$id = $request[2];

$body = json_decode(file_get_contents("php://input"));

$request = new BrokerRequest($body, $id);

$resource = ucfirst($resource) . "Broker";

if (method_exists($resource, $method)) {
  $response = call_user_func(array($resource, $method), $request);
  echo json_encode($response);
}
else {
  // We do not support this
  http_response_code(405);
}