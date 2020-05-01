<?php

require_once(__DIR__ . "/../model/types/BrokerRequest.php");
require_once(__DIR__ . "/../model/types/BrokerResponse.php");
require_once(__DIR__ . "/../model/PlaylistModel.php");
require_once(__DIR__ . "/../model/types/ModelResponse.php");
require_once(__DIR__ . "/../model/types/Playlist.php");
class PlaylistBroker
{
    static public function get(BrokerRequest $request) : BrokerResponse {
        $playlistID = $request->id;

        if ($playlistID == "") {
            $response = PlaylistModel::getPlaylists();
        } else {
            $response = PlaylistModel::getPlaylist($playlistID);
        }

        return new BrokerResponse($response->responseObject);
    }
    static public function post(BrokerRequest $request) : BrokerResponse {
        $response = PlaylistModel::createPlaylist(new Playlist($request->body));

        return new BrokerResponse($response->responseObject);
    }
    static public function put(BrokerRequest $request) : BrokerResponse {
        $response = PlaylistModel::updatePlaylist(new Playlist($request->body));

        return new BrokerResponse($response->responseObject);
    }
    static public function delete(BrokerRequest $request) : BrokerResponse {
        $playlistID = $request->id;
        $response = PlaylistModel::deletePlaylist($playlistID);

        return new BrokerResponse($response->responseObject);
    }
}