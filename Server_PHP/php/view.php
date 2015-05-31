<?php   
	$serwer = "192.168.1.3";
	$login = "Marcin";
	$haslo = "pz_2015";
	$baza = "projekt_zespolowy";
    // łączymy się z bazą danych
    if (mysql_connect($serwer, $login, $haslo) and mysql_select_db($baza)) {
        
        // zapytanie do bazy danych
        $result = mysql_query("SELECT * FROM test WHERE 1=1")
        or die("Błąd w zapytaniu!");
        
        mysql_close();
    }
    else echo "Nie mogę połączyć się z bazą danych!";
    
    // wyświetlany wyniki zapytania
    while($rek = mysql_fetch_array($result)) {
        echo $rek['username']."<br />";
    }