<?php

$username = isset($_POST['myHttpData']) ? $_POST['myHttpData'] : 'nie dziala';

$connection = mysql_connect('192.168.1.3', 'Marcin', 'pz_2015') or die(mysql_error());
$db = mysql_select_db('projekt_zespolowy', $connection) or die(mysql_error());
$query =  mysql_query("INSERT INTO test SET username='$username'");
mysql_close($connection);	