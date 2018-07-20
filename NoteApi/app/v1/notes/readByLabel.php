<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Notes.php';
  include_once '../../includes/HttpLogger.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $note = new Notes($db);
  $note->label_name = isset($_GET['label_name']) ? $_GET['label_name'] : die();
  $note->user_id = isset($_GET['user_id']) ? $_GET['user_id'] : die();
  error_log('Request to Access Notes with label: ' . $note->label_name);
  $result = $note->readByLabel();
  $num = $result->rowCount();
  if($num > 0) {
    $notes_arr = array();
    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);

      $note_item = array(
        'id' => $id,
        'title' => $title,
        'body' => html_entity_decode($body),
        'url' => $note->url,
        'user_id' => $user_id,
        'label_name' => $label_name
      );
      array_push($notes_arr, $note_item);
    }
    echo json_encode($notes_arr);
      $database->disconnect($db);
  } else {
    echo json_encode(
      array('message' => 'No Note Found')
    );
      $database->disconnect($db);
  }