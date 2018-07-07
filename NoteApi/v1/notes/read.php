<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Notes.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $note = new Notes($db);
  $result = $note->read();
  $num = $result->rowCount();
  if($num > 0) {
    $notes_arr = array();
    $notes_arr['data'] = array();

    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);

      $note_item = array(
        'id' => $id,
        'title' => $title,
        'body' => html_entity_decode($body),
        'url' => $note->url,
        'author_id' => $author_id,
        'label_name' => $label_name
      );
      array_push($notes_arr['data'], $note_item);
    }
    echo json_encode($notes_arr);
  } else {
    echo json_encode(
      array('message' => 'No Note Found')
    );
  }
