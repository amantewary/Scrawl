<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once '../../includes/ConnectDb.php';
include_once '../../includes/Notes.php';
include_once '../../includes/Share.php';
include_once '../../includes/HttpLogger.php';

$database = new ConnectDb();
$db = $database->connect();

$share = new Share($db);

if (isset($_GET['share_to'])) {
    $share->share_to = $_GET['share_to'];
} else {
    $share->share_to = die();
}

error_log('Request to Access Share Record For Share_to: ' . $share->share_to);

$result = $share->readNoteIdByUserId();
$num = $result->rowCount();
if($num > 0) {
    $share_arr = array();
    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
        extract($row);

        $share_item = array(
            'id' => $id,
            'note_id' => $note_id,
            'share_from' => $share_from
        );
        array_push($share_arr, $share_item);
    }

    $note_arr = array();
    foreach ($share_arr as $item){
        $note = new Notes($db);
        $note->id = $item->note_id;

        error_log('Request to Access Shared Note ID: ' . $note->id);

        $note->read_single();
        $note_item = array(
            'id' => $note->id,
            'title' => $note->title,
            'body' => $note->body,
            'url' => $note->url,
            'user_id' => $note->user_id,
            'label_name' => $note->label_name
        );
        array_push($note_arr, $note_item);

    }

    $result_notes = $note->read_single();

    print_r(json_encode($note_arr));
    $database->disconnect($db);

} else {
    echo json_encode(
        array('message' => 'No Note Found')
    );
    $database->disconnect($db);
}
