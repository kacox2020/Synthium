<?php
require_once(__DIR__ . "/Database.php");

class NotesModel {
  public static function createNote(Note $note) {
    $database = new Database();
    $results = $database->executeSql("INSERT INTO tblNotes (title, contents, userId) VALUES (?,?,?)", "ssi", array($note->title, $note->contents, $note->userId));
    return new ModelResponse($results[0]);
  }

  public static function getNotes($userId) {
    $database = new Database();
    $results = $database->executeSql("SELECT id, title, contents FROM tblNotes WHERE userId = ?", "i", array($userId));
    return new ModelResponse($results);
  }

  public static function updateNote(Note $note) {
    $database = new Database();

    $database->executeSql("UPDATE tblNotes SET noteTitle = ?, noteContents = ? WHERE noteId = ?", "ssi", array($note->title, $note->contents, $note->id));

    return new ModelResponse();
  }
  public static function deleteNote($noteId) {
    $database = new Database();

    $database->executeSql("DELETE FROM tblNotes WHERE noteId = ?", "i", array($noteId));

    return new ModelResponse();
  }
}

