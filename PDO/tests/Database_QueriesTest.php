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


    //Will Return False As Salt is Not given while testing.
    public function testLoginUser()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $stmtMock->method('fetch')
            ->willReturn(\PDO::FETCH_ASSOC);
        $test = new Database_Queries();
        $this->assertEquals(false,$test->loginUser($pdoMock, "123@abc.com", "123456"));
    }


    public function testRegisterUser()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $stmtMock->method('fetch')
            ->willReturn(\PDO::FETCH_ASSOC);
        $test = new Database_Queries();
        $this->assertEquals(2,$test->registerUser($pdoMock, "NAME", "123@abc.com", "123456"));
    }

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
