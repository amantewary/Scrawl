<?php
/**
 * Created by PhpStorm.
 * User: nikhilkamath
 * Date: 11/07/18
 * Time: 8:33 AM
 */

use PHPUnit\Framework\TestCase;
require 'config.php';
require 'Database_Queries.php';

global $pdo_global;
$pdo_global = $pdo;

class AllTestCases extends TestCase
{
    // Test of isUserExistsFunctionWhen given a fake email
    public function testIsUserExistsFalse(){
        $email = "fake@email.com";
        $foo = false;
        $this->assertFalse(isUserExists($GLOBALS['pdo_global'], $email), $foo);
    }

    //Test of isUserExistsFunction when give a genuine email
    public function testIsUserExistsTrue(){
        $email = "test6@test.com";
        $foo = true;
        $this->assertTrue(isUserExists($GLOBALS['pdo_global'], $email), $foo);
    }

    /**
     * @expectedException Error
    */
    //Test isUserExistsFunction exception
    public function testIsUserExistsException(){
        try{
            $email = "test6@test.com";
            isUserExists($GLOBALS,$email);
        }catch (Exception $e){
            echo $e;
        }
    }

    

}
