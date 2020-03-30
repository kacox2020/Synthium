<?php
require_once(__DIR__ . "/../model/types/BrokerRequest.php");
require_once(__DIR__ . "/../model/types/BrokerResponse.php");
require_once(__DIR__ . "/../model/SongModel.php");
require_once(__DIR__ . "/../model/types/ModelResponse.php");
require_once(__DIR__ . "/../model/types/Song.php");


class SongBroker
{
    static public function get(BrokerRequest $request) : BrokerResponse {
        $songID = $request->id;
        $response = SongModel::getSongs($songID);

        return new BrokerResponse($response->responseObject);
    }
    static public function post(BrokerRequest $request) : BrokerResponse {
        $response = SongModel::createSong(new Song($request->body));

        return new BrokerResponse($response->responseObject);
    }
}