<?php
$info = date("Y-m-d  H:i:s")."	".
$_SERVER['REMOTE_ADDR']."	".
$_SERVER['REQUEST_METHOD']."	".
$_SERVER['REQUEST_URI']. "\n";
$fp = fopen(dirname(__FILE__) .'/request.log', 'a');
fwrite($fp, $info);
fclose($fp);