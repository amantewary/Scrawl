<?php
// Headers
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, userization, X-Requested-With');
include_once '../../includes/ConnectDb.php';
include_once '../../includes/Share.php';
include_once '../../includes/HttpLogger.php';
$database = new ConnectDb();
$db = $database->connect();
$share = new Share($db);
$data = json_decode(file_get_contents("php://input"));

$share->share_from = $data->share_from;
$share->share_to = $data->share_to;
$share->note_id = $data->note_id;

if($share->create()) {
    echo json_encode(
        array('message' => 'New Share Created')
    );
    $database->disconnect($db);
} else {
    echo json_encode(
        array('message' => 'Share Not Created')
    );
    $database->disconnect($db);
}


