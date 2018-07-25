<?php
/**
 * Created by PhpStorm.
 * User: nikhilkamath
 * Date: 11/07/18
 * Time: 8:33 AM
 */

use PHPUnit\Framework\TestCase;
require 'Database_Queries.php';

class AllTestCases extends TestCase
{

    // Make a dummy config for mocking
   function createConnection(){
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

           return $pdo;
       } catch (PDOException $exception) {
           die(json_encode(array('outcome' => false, 'message' => 'Unable to connect')));
       }
   }

    // Test of isUserExistsFunctionWhen given a fake email
    public function testIsUserExistsFalse(){
        $email = "fake@email.com";
        $foo = false;
        $this->assertFalse(isUserExists($this->createConnection(), $email), $foo);
    }

    //Test of isUserExistsFunction when give a genuine email
    public function testIsUserExistsTrue(){
        $email = "test6@test.com";
        $foo = true;
        $this->assertTrue(isUserExists($this->createConnection(), $email), $foo);
    }

    /**
     * @expectedException Error
    */
    //Test isUserExistsFunction exception
    public function testIsUserExistsException(){

            $email = "test6@test.com";
            $pdo = $this->createConnection();
            $pdo = null;
            isUserExists($pdo,$email);

    }



    // Test of register given a fake email but not providing all arguments
    /**
     * @expectedException ArgumentCountError
    */

    public function testregiserFalsearguments(){
        $email = "fake@email.com";
        $name = "fakeName";
        $password = "fakePassword";
        $foo = false;
        $this->assertFalse(registerUser($this->createConnection(), $email), $foo);
    }

    // Test of register given a fake email but not providing all arguments
    /**
     * @expectedException Error
     */

    public function testregiserFalsePDO(){
        $email = "fake@email.com";
        $name = "fakeName";
        $password = "fakePassword";
        $foo = false;
        $this->assertFalse(registerUser($name, $email, $password), $foo);
    }


}
