<?php

$connection = mysql_connect('192.168.1.3', 'Marcin', 'pz_2015') or die(mysql_error());
echo 'Connected!';
$db = mysql_select_db('projekt_zespolowy', $connection) or die(mysql_error());
echo '<br>';
echo 'Hello world!';
mysql_close($connection);