<?php

/*
 * A class file to check if user exists
 * 
 * ERROR LISTS:
 * 0 - no error
 * 1 - bad password 
 * 2 - User not exists
 * 3 - Totally fail
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['username']) && isset($_POST['password'])) {
	$username = $_POST['username'];
	$password = md5($_POST['password']);
	
	// include db connect class
	require_once __DIR__ . '/db_connect.php';
	
	// connecting to db
	$db = new DB_CONNECT();
	
	// mysql select query
	$sql = "SELECT id, login, haslo, salt FROM Uzytkownicy WHERE login = '".$username."' "; 
	$result = mysql_query($sql);

	// check if user exists
	if (!empty($result)) {
		
		// getting result
		while($row = mysql_fetch_object($result)){
              // save data from db 
              $userid_db = $row->id;
			  $username_db = $row->login;
			  $password_db = $row->haslo;
			  $salt_db = $row->salt;
			  break;
    	}
		
		if ($username_db == $username) {
			// user exists
			if ($password_db == $password) {
				// password is OK
				// user logging in
				$response['success'] = 1;
				$response['error'] = 0;
				$response['message'] = "Login successfully!";
				$response['salt'] = $salt;
				
				// "echoing" JSON response
				echo json_encode($response); 
			}
			else {
				// password isnt ok
				$response['success'] = 0;
				$response['error'] = 1;
				$response['message'] = "Bad password";
				
				// "echoing" JSON response
				echo json_encode($response); 
			}
		}
		else {
			// fail
			$response['success'] = 0;
			$response['error'] = 2;
			$response['message'] = "User not exists";
			
			// "echoing" JSON response
			echo json_encode($response);
		}
	}
	else {
		// fail
		$response['success'] = 0;
		$response['error'] = 2;
		$response['message'] = "User not exists";
		
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