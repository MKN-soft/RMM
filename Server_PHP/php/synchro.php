<?php

// array for JSON response
$response = array();

// 1 params -     SALT
// 2 params -	  ARRAY COUNT
// 3...n params - PARAMS


// HABITS DETAILS :
// 0 - is Done?
// 1 - Date
// 2 - Frequency
// 3 - Last Date
// 4 - Best Score
// 5 - Average 
// 6- Success Percent

// $_POST['salt'] = "152223232556ac8741223b1.97180652";
// $_POST['count'] = "2";
// $_POST['habit0'] = "true;01 czerwca 2015;1;8.5.2015;8;1;57.14286";
// $_POST['habit1'] = "true;01 czerwca 2015;1;8.5.2015;8;1;57.14286";

// echo json_encode($_POST);

// $habit0 = $_POST['habit0'] = "true;01 czerwca 2015;1;8.5.2015;8;1;57.14286";
// 
// $habitsDetails = explode(";", $habit0);


if (isset($_POST['salt'])) {
	

	// Authorize salt
	$salt = $_POST['salt'] = "152223232556ac8741223b1.97180652";
	
	// include db connect class
	require_once __DIR__ . '/db_connect.php';
	
	// connecting to db
	$db = new DB_CONNECT();
	
	$sql = "SELECT id, login, haslo, salt FROM Uzytkownicy WHERE salt = '$salt' "; 
	
	$result = mysql_query($sql);
	// check if user exists
	if (!empty($result)) {
		// success
		// Prepare data to send
		
		// getting result
		while($row = mysql_fetch_object($result)){
              // save data from db 
              $userid_db = $row->id;
			  $username_db = $row->login;
			  $password_db = $row->haslo;
			  $salt_db = $row->salt;
			  break;
    	}
		
		$habits = array();
		
		// Get habits from $_POST
		$count = $_POST['count'];
		for ($i = 0; $i < $count; $i++) 
			$habits[$i] =  $_POST['habit'.$i];
		
	
		$habitsDetails = array();
		
		
		// Get details about habits
		for ($i = 0; $i < $count; $i++) 
			$habitsDetails[$i] = explode(";", $habits[$i]);
		
		
		for ($i = 0; $i < $count; $i++) 
			$habitsDetails[$i][1]++;
		
			
		// Send to Database MYSQL
		for ($i = 0; $i < $count; $i++) {
			$sql1 = "INSERT INTO `Nawyki` (`uzytkownik_id` , `czy_sie_udalo`, `data_wprowadzenia`,
				`czestotliwosc`, `kiedy_ostatnio_aktualizowano_nawyk`) 
				VALUES ('".$userid_db."', '".$habitsDetails[$i][0]."', '".$habitsDetails[$i][1]."', '".$habitsDetails[$i][2]."', '".$habitsDetails[$i][3]."')";
			$result = mysql_query($sql1);
		}
		
		if (!empty($result)) {
			// success
			// INSERT STATISTICS DATA
			for ($i = 0; $i < $count; $i++) {
				$id_habit[$i] = $habitsid_db = mysql_insert_id();
				
				$sql2 = "INSERT INTO `Statystyki` (`nawyki_id`, `ilosc_nawykow`, `najlepsza_passa`, `srednia_dlugosc_ciagu`, `%_powodzen`)
					VALUES ('".$habitsid_db."', '".$habitDetails[$i][7]."', '".$habitsDetails[$i][4]."', '".$habitsDetails[$i][5]."', '".$habitsDetails[$i][6]."')";
				
				$result = mysql_query($sql2);
			}
			
			if (!empty($result)) {
				// success
				$response['success'] = 1;
				$response['error'] = 0;
				$response['message'] = "Synchro succeed";
				$response['count'] = $count;
				for ($i = 0; $i < $count; $i++)
					$response['id'.$i] = $id_habit[$i];
	
				// "echoing" JSON response
				echo json_encode($response);	
			}
			else {
				// cannot put statistics
				$response['success'] = 0;
				$response['error'] = 5;
				$response['message'] = "Cannot put statistics";
					
				// "echoing" JSON response
				echo json_encode($response);
			}
						
		}
		else {
			// cannot put habits
			$response['success'] = 0;
			$response['error'] = 4;
			$response['message'] = "Cannot put habits";
				
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
