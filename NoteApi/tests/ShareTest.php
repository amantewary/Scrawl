<?php

use PHPUnit\Framework\TestCase;

require '../app/includes/Share.php';

class ShareTest extends TestCase
{
    public function testCreate()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Share($pdoMock);
        $this->assertTrue($test->create());
    }

    public function testCreateFail()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(false);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Share($pdoMock);
        $this->assertEquals(new PDOException(),$test->create());
    }

    public function testReadNoteIdByUserId()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Share($pdoMock);
        $this->assertEquals($stmtMock,$test->readNoteIdByUserId());
    }

    public function testReadNoteIdByUserIdFail()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(false);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Share($pdoMock);
        $this->assertEquals(new PDOException(),$test->readNoteIdByUserId());
    }

    public function testDelete()
    {
        $stmtMock = $this->createMock(\PDOStatement::class);
        $pdoMock = $this->createMock(\PDO::class);
        $stmtMock->method('execute')
            ->willReturn(true);
        $pdoMock->method('prepare')
            ->willReturn($stmtMock);
        $test = new Share($pdoMock);
        $this->assertTrue($test->delete());
    }

}
