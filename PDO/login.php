<?php
/**
 * Created by PhpStorm.
 * User: nikhilkamath
 * Date: 19/06/18
 * Time: 3:00 PM
 */

require_once 'Database_Queries.php';
require_once 'config.php';
require_once 'Database_Queries.php';
require_once 'error_logger.php';


$response = array("error" => FALSE);

if (isset($_POST['email']) && isset($_POST['password'])) {
    $email = $_POST['email'];
    $password = $_POST['password'];

    if (isUserExists($pdo, $email)) {
        openConnection();
        $row = loginUser($pdo, $email, $password);
        if (!$row) {
            $response["error"] = TRUE;
            $response["error_msg"] = "Oops! Something went wrong";
            echo json_encode($response);

        } else {
            $response["error"] = FALSE;
            $response["username"] = $row['username'];
            $response["userId"] = $row['id'];
            $response["email"] = $row['email_address'];
            $response["id"] = $row['id'];
            echo json_encode($response);
        }

    } else {
        $response["error"] = TRUE;
        $response["error_msg"] = "You are not a registered user";
        echo json_encode($response);
    }

    closeConnection();
    tracker();

}
