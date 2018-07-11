<?php
$host = 'db-5308.cs.dal.ca';
$db = 'CSCI5308_19_DEVINT';
$user = 'CSCI5308_19_DEVINT_USER';
$pass = 'CSCI5308_19_DEVINT_19248';


try {
    $dsn = "mysql:host=$host;dbname=$db;";
    $opt = [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
        PDO::ATTR_EMULATE_PREPARES => false, PDO::ATTR_PERSISTENT => false,
    ];
    $pdo = new PDO($dsn, $user, $pass, $opt);


    $status = $pdo->getAttribute(PDO::ATTR_CONNECTION_STATUS);

} catch (PDOException $exception) {
    error_log("Error ". $exception, 3, "log.txt");
    die(json_encode(array('outcome' => false, 'message' => 'Unable to connect')));
}

?>