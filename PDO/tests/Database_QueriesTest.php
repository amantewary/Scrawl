<?php
/**
 * Created by PhpStorm.
 * User: amantewary
 * Date: 12-07-2018
 * Time: 09:31 AM
 */

use PHPUnit\Framework\TestCase;

require '../Database_Queries.php';
class Database_QueriesTest extends TestCase
{

    public function testGetUserData()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Database_Queries();
        $this->assertTrue($test->getUserData($pdoMock));
    }


//    public function testLoginUser()
//    {
//
//    }


//    public function testRegisterUser()
//    {
//        $arrResult=null;
//        $fetchMock = $this
//            ->getMockBuilder("stdClass" /* or whatever has a fetchAll */)
//            ->setMethods(array("fetch"))
//            ->getMock();
//        $fetchMock
//            ->expects($this->once())->method("fetch")
//            ->will($this->returnValue("hello!"));
//        $stmtMock = $this->createMock(\PDOStatement::class);
//        $pdoMock = $this->createMock(\PDO::class);
//        $stmtMock->method('execute')
//            ->willReturn(true);
//        $stmtMock->expects($this->at(0))
//            ->method('fetch')
//            ->will($this->returnValue($arrResult[0]));
//        $stmtMock->expects($this->at(1))
//            ->method('fetch')
//            ->will($this->returnValue($arrResult[1]));
//        $stmtMock->expects($this->at(2))
//            ->method('fetch')
//            ->will($this->returnValue($arrResult[2]));
//        $pdoMock->method('prepare')
//            ->willReturn($stmtMock);
//        $test = new Database_Queries();
//        $this->assertEquals(true,$test->getUserData($pdoMock));
//
//    }

    public function testIsUserExists()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $stmtMock->method('rowCount')
            ->willReturn(1);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Database_Queries();
        $this->assertTrue($test->getUserData($pdoMock));

    }

}
