<?php
/**
 * Created by PhpStorm.
 * User: nikhilkamath
 * Date: 19/06/18
 * Time: 11:27 AM
 */

require_once 'Database_Queries.php';
require_once 'config.php';

$response = array("error" => FALSE);


if (isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password'])) {
    $username = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    if (isUserExists($pdo, $email)) {
        openConnection();
        $response["error"] = TRUE;
        $response["error_msg"] = "User with " . $email . " already exists!";
        echo json_encode($response);
    } else {
        $row = registerUser($pdo, $username, $email, $password);
        if (!$row) {
            $response["error"] = TRUE;
            $response["error_msg"] = "Oops! Something went wrong";
            echo json_encode($response);
        } else {
            $response["error"] = FALSE;
            $response["username"] = $row['username'];
            $response["email"] = $row['email_address'];
            echo json_encode($response);


        }

    }
    closeConnection();
    tracker();
}