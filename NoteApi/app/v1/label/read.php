<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Labels.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $label = new Labels($db);
  $label->user_id = isset($_GET['user_id']) ? $_GET['user_id'] : die();
  error_log('Request Access to Labels By User ID: ' . $label->user_id);
  $result = $label->read();
  $num = $result->rowCount();
  if($num > 0) {
    $labels_arr = array();
    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);
      $labels_item = array(
        'id' => $id,
        'name' => $name
      );
      array_push($labels_arr, $labels_item);
    }
    echo json_encode($labels_arr);
    $database->disconnect($db);
  } else {
    echo json_encode(
      array('message' => 'No Labels Found')
    );
    $database->disconnect($db);
  }