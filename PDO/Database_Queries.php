<?php
/**
 * Created by PhpStorm.
 * User: nikhilkamath
 * Date: 19/06/18
 * Time: 9:54 AM
 */

require 'config.php';
require 'error_logger.php';
class Database_Queries
{
    function getUserData($pdo)
    {


        try {
            $stmt = $pdo->prepare('CALL spGetUserData()') or die("failed" . mysql_error());
            error_log("\r\nConnection Opened at " . date("d-m-Y (D) H:i:s", time()) . "\r\n", 3, "log.txt");

            $stmt->execute();

            $json = json_encode($rows = $stmt->fetchAll(PDO::FETCH_ASSOC));
            echo $json;
        } catch (PDOException $e) {
            print_r($e);
            error_log("Time: " . date("d-m-Y (D) H:i:s", time()) . " Error " . $e, 3, "log.txt");
        }


    }

// code to register the user
    function registerUser($pdo, $name, $email, $password)
    {
        try {
            $uniqueId = uniqid('', true);
            $hash = hashPassword($password);
            $encrypted_password = $hash["encrypted"];
            $salt = $hash["salt"];
            $stmt = $pdo->prepare("CALL spRegisterUser(?, ?, ?, ?, now(), now(), ?)") or die(mysql_error());
            error_log("\r\nConnection Opened at " . date("d-m-Y (D) H:i:s", time()) . "\r\n", 3, "log.txt");

            $stmt->execute(array($uniqueId, $name, $email, $encrypted_password, $salt));

            if ($stmt) {
                $stmt = $pdo->prepare("SELECT username,email_address from user where email_address=?");
                $stmt->execute(array($email));
                $rows = $stmt->fetch(PDO::FETCH_ASSOC);

                return $rows;
            }
        } catch (PDOException $e) {
            print_r($e);
            error_log("Time: " . date("d-m-Y (D) H:i:s", time()) . " Error " . $e, 3, "log.txt");
            return false;
        }

    }


    function loginUser($pdo, $email, $password)
    {

        try {
            $stmt = $pdo->prepare("CALL spGetRegisteredUser(?)");
            error_log("\r\nConnection Opened at " . date("d-m-Y (D) H:i:s", time()) . "\r\n", 3, "log.txt");

            $stmt->execute(array($email));
            if ($stmt) {
                $rows = $stmt->fetch(PDO::FETCH_ASSOC);
                $encrypted_password = $rows['password'];
                $salt = $rows['salt'];

                if ($encrypted_password == dehashPassword($password, $salt)) {

                    return $rows;
                } else {
                    return false;
                }
            }
        } catch (Exception $e) {
            print_r($e);
            error_log("Time: " . date("d-m-Y (D) H:i:s", time()) . " Error " . $e, 3, "log.txt");
            return false;
        }

    }

//check if user exists or not?
    function isUserExists($pdo, $email)
    {
        $affected_rows = 0;
        try {
            $stmt = $pdo->prepare("CALL spGetRegisteredUser(?)");
            error_log("\r\nConnection Opened at " . date("d-m-Y (D) H:i:s", time()) . "\r\n", 3, "log.txt");

            $stmt->execute(array($email));
            $affected_rows = $stmt->rowCount();
            if ($affected_rows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception $e) {
            print_r($e);
            error_log("Time: " . date("d-m-Y (D) H:i:s", time()) . " Error " . $e, 3, "log.txt");
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

    function dehashPassword($password, $salt)
    {
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
        return $hash;
    }

    function closeConnection()
    {
        $stmt = null;
        $pdo = null;
        error_log("\r\nConnection Closed at " . date("d-m-Y (D) H:i:s", time()) . "\r\n", 3, "log.txt");

    }

    function openConnection()
    {
        error_log("\r\nConnection Opened at " . date("d-m-Y (D) H:i:s", time()) . " " . "Request From " . $_SERVER['REMOTE_ADDR'] . " \r\n", 3, "log.txt");

    }

    function tracker()
    {
        error_log("\r\nTime: " . date("d-m-Y (D) H:i:s", time()) . "      Request Agent " . $_SERVER['HTTP_USER_AGENT'] . "\r\n" . "Request Method " . $_SERVER['REQUEST_METHOD'] . "\r\n Requested at " . $_SERVER['REQUEST_TIME'] . "\r\nConnection Status " . connection_status() . "\r\n ", 3, "tracker.txt");

    }
}
