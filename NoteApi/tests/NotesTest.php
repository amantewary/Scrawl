<?php
/**
 * User: amantewary
 * Date: 09-07-2018
 * Time: 10:29 AM
 */
//TEST
use PHPUnit\Framework\TestCase;

require '../app/includes/Notes.php';
class NotesTest extends TestCase
{



    public function testRead()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Notes($pdoMock);
        $this->assertEquals($stmtMock,$test->read());
    }

    public function testReadFail()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(false);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Notes($pdoMock);
        $this->assertEquals(new PDOException(),$test->read());
    }

    public function testDelete()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Notes($pdoMock);
        $this->assertTrue($test->delete());
    }

    public function testRead_single()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Notes($pdoMock);
        $this->assertEquals(true,$test->read_single());
    }

    public function testRead_singleFail()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(false);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Notes($pdoMock);
        $this->assertEquals(new PDOException(),$test->read_single());
    }

    public function testCreate()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Notes($pdoMock);
        $this->assertTrue($test->create());
    }

    public function testUpdate()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Notes($pdoMock);
        $this->assertTrue($test->update());
    }
}
