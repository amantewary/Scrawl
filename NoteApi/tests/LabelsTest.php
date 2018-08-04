<?php
/**
 * User: amantewary
 * Date: 09-07-2018
 * Time: 12:03 AM
 */

require '../app/includes/Labels.php';

class LabelsTest extends PHPUnit\Framework\TestCase
{

    public function testRead()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Labels($pdoMock);
        $this->assertEquals($stmtMock,$test->read());
    }

    public function testReadFail()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->will($this->throwException(new PDOException()));
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Labels($pdoMock);
        $this->assertEquals(new PDOException(),$test->read());
    }

    public function testCreate()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Labels($pdoMock);
        $this->assertEquals($stmtMock,$test->create());
    }

    public function testCreateFail()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->will($this->throwException(new PDOException()));
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Labels($pdoMock);
        $this->assertEquals(new PDOException(),$test->create());
    }

    public function testDelete()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('rowCount')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Labels($pdoMock);
        $this->assertEquals($stmtMock,$test->delete());
    }

    public function testDeleteFail()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('rowCount')
            ->willReturn(false);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Labels($pdoMock);
        $this->assertNull($test->delete());
    }
}
