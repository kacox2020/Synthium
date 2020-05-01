<?php
require_once(__DIR__ . "/../model/types/BrokerRequest.php");
require_once(__DIR__ . "/../model/types/BrokerResponse.php");
require_once(__DIR__ . "/../model/SongPlaylistRelationshipModel.php");
require_once(__DIR__ . "/../model/types/ModelResponse.php");
require_once(__DIR__ . "/../model/types/SongPlaylistRelationship.php");

class SongPlaylistRelationshipBroker
{
    static public function get(BrokerRequest $request) : BrokerResponse {
        $playlistID = $request->id;

        if ($playlistID == "") {
            $response = SongPlaylistRelationshipModel::getSongPlaylistRelationships();
        } else {
            $response = SongPlaylistRelationshipModel::getSongPlaylistRelationship($playlistID);
        }

        return new BrokerResponse($response->responseObject);
    }
    static public function post(BrokerRequest $request) : BrokerResponse {
        $response = SongPlaylistRelationshipModel::createPlaylistRelationship(new SongPlaylistRelationship($request->body));

        return new BrokerResponse($response->responseObject);
    }
    static public function put(BrokerRequest $request) : BrokerResponse {
        $response = SongPlaylistRelationshipModel::updateSongPlaylistRelationship(new SongPlaylistRelationship($request->body));

        return new BrokerResponse($response->responseObject);
    }
    static public function delete(BrokerRequest $request) : BrokerResponse {
        $playlistID = $request->id;
        $response = SongPlaylistRelationshipModel::deleteSongPlaylistRelationship($playlistID);

        return new BrokerResponse($response->responseObject);
    }
}