<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: DELETE');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
include_once '../../includes/ConnectDb.php';
include_once '../../includes/Share.php';
include_once '../../includes/HttpLogger.php';
$database = new ConnectDb();
$db = $database->connect();
$share = new Share($db);
$data = json_decode(file_get_contents("php://input"));
$share->id = $data->id;
error_log('Request to Access To Delete Share ID: ' . $share->id);
if($share->delete()) {
    echo json_encode(
        array('message' => 'Share record Deleted')
    );
    $database->disconnect($db);
} else {
    echo json_encode(
        array('message' => 'Share record Not Deleted')
    );
    $database->disconnect($db);
}

