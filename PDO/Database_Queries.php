<?php
/**
 * Created by PhpStorm.
 * User: nikhilkamath
 * Date: 19/06/18
 * Time: 9:54 AM
 */

require 'config.php';


function getUserData($pdo)
{


    $stmt = $pdo->prepare('Select * from user') or die("failed" . mysql_error());
    $stmt->execute();

    $json = json_encode($rows = $stmt->fetchAll(PDO::FETCH_ASSOC));
    echo $json;


}

//try {
//
//    $name = "test";
//    $email = "test@test.com";
//    $password = "123456";
////    registerUser($pdo, $name, $email, $password);
//    loginUser($pdo, $email, $password);
//
//} catch (PDOException $ex) {
//    die(json_decode($ex));
//}


// code to register the user
function registerUser($pdo, $name, $email, $password)
{
    try {
        $uniqueId = uniqid('', true);
        $hash = hashPassword($password);
        $encrypted_password = $hash["encrypted"];
        $salt = $hash["salt"];
        $stmt = $pdo->prepare("INSERT INTO user (unique_user_id, username , email_address , password , created_at , modified_at , salt )
VALUES (?, ?, ?, ?, now(), now(), ?)") or die(mysql_error());
        $stmt->execute(array($uniqueId, $name, $email, $encrypted_password, $salt));

        if ($stmt) {
            $stmt = $pdo->prepare("SELECT username,email_address from user where email_address=?");
            $stmt->execute(array($email));
            $rows = $stmt->fetch(PDO::FETCH_ASSOC);
            return $rows;
        }
    } catch (PDOException $e) {
        print_r($e);
            return false;
    }

}


function loginUser($pdo, $email, $password){

    try{
        $stmt = $pdo->prepare("SELECT * from user where email_address=?");
        $stmt->execute(array($email));
        if($stmt){
            $rows = $stmt->fetch(PDO::FETCH_ASSOC);
            $encrypted_password = $rows['password'];
            $salt = $rows['salt'];

            if ($encrypted_password == dehashPassword($password,$salt)){
                return $rows;
            }else{
                return false;
            }
        }
    }catch (Exception $e){
        print_r($e);
        return false;
    }

}

//check if user exists or not?
function isUserExists($pdo, $email){
    $affected_rows = 0;
    try{
        $stmt = $pdo->prepare("SELECT * from user where email_address=?");
        $stmt->execute(array($email));
        $affected_rows = $stmt->rowCount();
        if($affected_rows>0){
            return true;
        }else{
            return false;
        }
    }catch (Exception $e){
        print_r($e);
        return false;
    }
}


/*
 * Code for hashing and validating password is inspired from: https://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/
 *
 */
function hashPassword($password)
{

    $salt = sha1(rand());
    $salt = substr($salt, 0, 10);
    $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
    $hash = array("salt" => $salt, "encrypted" => $encrypted);
    return $hash;
}

function dehashPassword($password, $salt){
    $hash = base64_encode(sha1($password . $salt, true) . $salt);

    return $hash;
}

?>