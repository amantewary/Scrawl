<?php

require_once 'Database_Queries.php';
require_once 'config.php';
require_once 'error_logger.php';


$response = array("error" => FALSE);

if (isset($_POST['email'])) {
    $email = $_POST['email'];

    if (isUserExists($pdo, $email)) {
        $response["error"] = FALSE;
        echo json_encode($response);

    } else {
        $response["error"] = TRUE;
        $response["error_msg"] = "User not exists";
        echo json_encode($response);
    }

    tracker();

}
