<?php 

// include db connect class
require_once __DIR__ . '/db_connect.php';
	
// connecting to db
$db = new DB_CONNECT();

// username and password sent from form
$myusername=$_POST['myusername'];
$mypassword=$_POST['mypassword']; 

// To protect MySQL injection (more detail about MySQL injection)
$myusername = stripslashes($myusername);
$mypassword = stripslashes($mypassword);
$myusername = mysql_real_escape_string($myusername);
$mypassword = MD5($mypassword);

$sql = "SELECT * FROM Uzytkownicy WHERE login='$myusername' AND haslo='$mypassword'";
$result = mysql_query($sql);

// Mysql_num_row is counting table row
$count = mysql_num_rows($result);

// If result matched $myusername and $mypassword, table row must be 1 row

if($count==1) {
	// Register $myusername, $mypassword and redirect to file "login_success.php"
	session_start();
	$_SESSION['username'] = $myusername;
	
	header("location:login_success.php");
}
else {
	echo "Wrong Username or Password";
}