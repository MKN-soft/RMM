#!/usr/bin/php
<?php
/**
  * Skrypt do aktualizacji adresu IP dla usługi DynDNS
  * 
  * Przed uruchomieniem skryptu należy go odpowiednio skonfigurować
  * Jeśli posiadamy interpreter PHP z wyłączonym SafeMode oraz z dostępem do funkcji exec
  * ustawiamy zmienną $exec_allowed = true
  * należy także wpisać nazwę interfejsu sieciowego (najczęściej ppp0)
  * Jeśli nie wiemy jaka jest nazwa intefejsu sieciowego należy skorzystać z polecenia ifconfig
  *			
  * Jeśli niestety interpreter nie ma dostepu do funkcji exec albo znajdujemy się za firewallem 
  * i działamy jako Virtual Server (translacja określonych portów na lokalny adres IP) 
  * ustawiamy $exec_allowed = false
  * na innym, zdalnym serwerze umieszczamy plik yourip.php, zas w tym skrypcie podajemy do niego
  * pelna sciezke (url) w zmiennej $remote_script_url (w przypadku wykorzystania CURL)
  * Z tej funkcjonalności należy korzystać w ostateczności, gdyż powoduje ona stały ruch w sieci	
  * 
  * Do zmiennej $zone należy wpisać nazwę domeny, którą posiadamy i chcemy konfigurować
  * Do zmiennej $password należy wpisać hasło jakie otrzymaliśmy z Domeny.tv dla tej domeny
  * Do zmiennej $host należy wpisać nazwę subdomeny dla jakiej chcemy konfigurować adres IP
  * Jeśli chcemy aby skrypt ustawiał rekord A dla całej nazwy domenowej do zmiennej $hostname 
  * należy wpisać @
  *
  * W katalogu gdzie znajduje się skrypt należy również utworzyć plik ip.txt do którego
  * będzie zapiywany aktualny adres IP
  * Można to zrobić korzystając z polecenia touch ip.txt
  * Należy mu też nadać uprawnienia do zapisu (chmod 0666 ip.txt)				
  *
  * Przykład
  * $interface = 'ppp0';
  * $zone = 'domena.com';
  * $password = 'nYHJgvbs7j';
  * $hostname = 'www';
  *
  * UWAGA!!!!!!!!!!					
  * Usługa DynDNS umożliwia aktualizacje wyłącznie rekordu A dla subdomeny
  * Nie ma możliwości ustawienia rekordów MX czy NS ani jakichkolwiek innych		
  *							
  * @copyright MSERWIS
  * @author Jacek Partyka
  * @version 1.1 - 2010-10-11
  */				

$interface = 'eth0';
$zone = 'patra.waw.pl';
$password = '54fd58e1a7e41';
$hostname = '@';
$exec_allowed = true;
$remote_script_server = 'http://192.168.1.2/ddns/yourip.php'; // przykładowo www.yourserver.com
$remote_script_file = 'yourip.php';
$remote_script_url = ''; // przykładowo http://www.yourserver.com/yourip.php

/* ******************************************************************************* */

$curl = function_exists('curl_init');
$plik = file_get_contents(dirname(__FILE__).'/ip.txt');

if ($exec_allowed)
{
	$addr = exec('ifconfig '.$interface.' | grep "inet addr:"');
	$addr = trim($addr);
	$addr = substr($addr, 10);
	$znak = strpos($addr, " ");
	$addr = substr($addr, 0, $znak);
}
else
{
	if ($curl)
	{
		$ch = curl_init();
    	curl_setopt($ch, CURLOPT_URL, $remote_script);
    	curl_setopt($ch, CURLOPT_HEADER, 0);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
		$result = curl_exec($ch);
    	curl_close($ch);	
	}
	else
	{
		$connection = fsockopen($remote_script_server, 80, $errno, $errstr, 10);
        if (!$connection) {
			echo $errstr;
           unset($connection);
           echo "Nie mogę się połączyć z serwerem!";
           return;
        } else {
		    $out = "GET /".$remote_script_file." HTTP/1.1\r\n";
		    $out .= "Host: ".$remote_script_server."\r\n";
		    $out .= "Connection: Close\r\n\r\n";
		    fwrite($connection, $out);
			 $dane = '';
		    while (!feof($connection)) {
		        $dane .= fgets($connection, 128);
		    }
		    fclose($connection);

			 if (substr($dane, 0, 15) == 'HTTP/1.1 200 OK')
			 {
			 	$tmp = explode("\n", $dane);
				array_map('trim', $tmp);
				foreach($tmp as $key=>$val)
					if ($val == 'c')
					{
						$addr = $tmp[(int)$key+1];
						break;
					}				
			 }
			 else
			 {
			 	die("Nie mogę się polaczyc z serwerem");
			 }
        }
	}

	$result = trim($result);
	if (ereg('^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$', $result))
	{
		$addr = $result;
	}
	else
	{
		die("Nie moge pobrac adresu IP");
	}
	
}

if ($addr != $plik)
{
    $plik = fopen(dirname(__FILE__).'/ip.txt', 'w');
    fwrite($plik, $addr);
    fclose($plik);

	 if ($curl)
	 {
	    $ch = curl_init();
	    curl_setopt($ch, CURLOPT_URL, "http://dnsapi.domeny.tv/interface.php?HostName=$hostname&Zone=$zone&DomainPassword=$password");
	    curl_setopt($ch, CURLOPT_HEADER, 0);
	    curl_exec($ch);
	    curl_close($ch);
	 }
	 else
	 {
		$connection = fsockopen('dnsapi.domeny.tv', 80, $errno, $errstr, 10);
        if (!$connection) {
			echo $errstr;
           unset($connection);
           echo "Nie mogę się połączyć z serwerem dnsapi.domeny.tv!";
           return;
        } else {
		    $out = "GET /interface.php?HostName=$hostname&Zone=$zone&DomainPassword=$password HTTP/1.1\r\n";
		    $out .= "Host: dynamic.name-services.com\r\n";
		    $out .= "Connection: Close\r\n\r\n";
		    fwrite($connection, $out);
			 $dane = '';
		    while (!feof($connection)) {
		        $dane .= fgets($connection, 128);
		    }
		    fclose($connection);
      }
   }
}
?>