<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Labels.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $label = new Labels($db);
  $result = $label->read();
  $num = $result->rowCount();
  if($num > 0) {
    $labels_arr = array();
    $labels_arr['data'] = array();
    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);
      $labels_item = array(
        'id' => $id,
        'name' => $name
      );
      array_push($labels_arr['data'], $labels_item);
    }
    echo json_encode($labels_arr);

  } else {
    echo json_encode(
      array('message' => 'No Labels Found')
    );
  }