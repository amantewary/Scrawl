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

    //retrieve notes of share_to
    $owned_note = new Notes($db);
    $owned_note->user_id = $share->share_to;

    error_log('Request to the notes owned by the user: ' . $note->user_id);

    $owned_note_result = $owned_note->readByUser();
    $owned_note_num = $owned_note_result->rowCount();
    if($owned_note_num > 0) {
        $owned_notes_arr = array();
        while($owned_notes_row = $owned_note_result->fetch(PDO::FETCH_ASSOC)) {
            extract($owned_notes_row);

            $owned_note_item = array(
                'id' => $id,
                'title' => $title,
                'body' => html_entity_decode($body),
                'url' => $note->url,
                'user_id' => $user_id,
                'label_name' => $label_name
            );
            array_push($owned_notes_arr, $owned_note_item);
        }

        $all_notes = array_merge($note_arr, $owned_notes_arr);

        echo json_encode($all_notes);
        $database->disconnect($db);

    } else {
        echo json_encode($note_arr);
        $database->disconnect($db);
    }

} else {
    echo json_encode(
        array('message' => 'No Note Found')
    );
    $database->disconnect($db);
}
