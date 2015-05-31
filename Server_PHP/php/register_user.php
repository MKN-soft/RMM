<?php

/*
 * A class file to check if user exists
 * 
 * ERROR LISTS:
 * 0 - no error
 * 1 - bad password 
 * 2 - User not exists
 * 3 - Totally fail
 * 4 - WTF
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['username']) && isset($_POST['password'])) {
	$username = $_POST['username'];
	$password = md5($_POST['password']);
	
	// generate totally random salt
	$salt = uniqid(mt_rand(), true);
	
	// include db connect class
	require_once __DIR__ . '/db_connect.php';
	
	// connecting to db
	$db = new DB_CONNECT();
	
	$sql = "INSERT INTO `Uzytkownicy` (`login` , `haslo`, `salt`) VALUES ('$username', '$password', '$salt')";
	
	$result = mysql_query($sql);
	
	if (!empty($result)) {
		// success
		$response['success'] = 1;
		$response['error'] = 0;
		$response['message'] = "User created";
		$response['salt'] = $salt;
		
	
		// "echoing" JSON response
		echo json_encode($response);	
	}
	else {
		// error
		$response['success'] = 0;
		$response['error'] = 1;
		$response['message'] = "Cannot create user";
	
		// "echoing" JSON response
		echo json_encode($response);	
	}
}
else {
	// totally fail
	$response['success'] = 0;
	$response['error'] = 3;
	$response['message'] = "Required field(s) is missing";
	
	// "echoing" JSON response
	echo json_encode($response);	
}