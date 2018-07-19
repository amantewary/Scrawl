<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once '../../includes/ConnectDb.php';
include_once '../../includes/Notes.php';
include_once '../../includes/HttpLogger.php';

if (isset($_GET['share_to']) && isset($_GET['userid'])) {
    $share_to = $_GET['share_to'];
    $userid = $_GET['userid'];

    $database = new ConnectDb();
    $db = $database->connect();

    $note = new Notes($db);

    error_log('Request to all shared and owned notes for user: ' . $share_to . ' with userid: '. $userid);

    $result = $note->readAllNotesByUser($share_to, $userid);
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
}

