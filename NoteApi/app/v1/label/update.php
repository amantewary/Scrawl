<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, userization, X-Requested-With');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Labels.php';
  include_once '../../includes/HttpLogger.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $label = new Labels($db);
  $data = json_decode(file_get_contents("php://input"));
  $label->new_name = $data->new_name;
  $label->user_id = $data->user_id;
  $label->old_name = $data->old_name;
  error_log('Request Access To Update Labels By User ID: ' . $label->user_id . 'with old label name ' . $label->old_name . ' & new label name ' . $label->new_name);
  if($label->update()) {
    echo json_encode(
      array('message' => 'Label Updated')
    );
      $database->disconnect($db);
  } else {
    echo json_encode(
      array('message' => 'Label Not Updated')
    );
      $database->disconnect($db);
  }