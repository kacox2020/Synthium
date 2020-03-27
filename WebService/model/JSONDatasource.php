<?php
class JSONDatasource {
  static private $dataFile = "/facstaff/bkrupp/www/330/data.json";
  static public function getData() {
    return json_decode(file_get_contents(JSONDatasource::$dataFile));
  }
  static public function saveData($data) {
    $json = json_encode($data);
    file_put_contents(JSONDatasource::$dataFile, $json);
  }

}