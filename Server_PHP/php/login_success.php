<?php
session_start();
if(!isset($_SESSION['username'])){
header("location:index.php");
}


require_once __DIR__ . '/db_connect.php';
$db = new DB_CONNECT();

$username = $_SESSION['username'];

$sql = "SELECT *
FROM `Uzytkownicy`
LEFT JOIN Nawyki ON Nawyki.uzytkownik_id = Uzytkownicy.id
LEFT JOIN Statystyki ON Statystyki.nawyki_id = Nawyki.id
WHERE Uzytkownicy.login = '".$username."' ";

$result = mysql_query($sql);

echo "Uzytkownik ".$username;
echo "<br>";

 
while($row = mysql_fetch_object($result)){
	echo "data wprowadzenia ".$row->data_wprowadzenia;
	echo "<br>";
	echo "czestotliwosc ".$row->czestotliwosc;
	echo "<br>";
	echo "kiedy ostatnio aktualizowano nawyk ".$row->kiedy_ostatnio_aktualizowano_nawyk;
	echo "<br>";
	echo "ilosc odbytych prob ".$row->ilosc_nawykow;
	echo "<br>";
	echo "najlepsza passa ".$row->najlepsza_passa;
	echo "<br>";
	echo "srednia_dlugosc_ciagu ".$row->srednia_dlugosc_ciagu;
	echo "<br>";
}



?>

<html>
<body>
</body>
</html>