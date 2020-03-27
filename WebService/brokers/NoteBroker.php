<?php
require_once(__DIR__ . "/../model/types/BrokerRequest.php");
require_once(__DIR__ . "/../model/types/BrokerResponse.php");
require_once(__DIR__ . "/../model/NotesModel.php");
require_once(__DIR__ . "/../model/types/ModelResponse.php");
require_once(__DIR__ . "/../model/types/Note.php");

class NoteBroker {
  static public function get(BrokerRequest $request) : BrokerResponse {
    $userId = $request->id;
    $response = NotesModel::getNotes($userId);

    return new BrokerResponse($response->responseObject);
  }
  static public function post(BrokerRequest $request) : BrokerResponse {
    $response = NotesModel::createNote(new Note($request->body));

    return new BrokerResponse($response->responseObject);
  }
}