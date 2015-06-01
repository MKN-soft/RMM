<?php

// array for JSON response
$response = array();

$dupa = "dupa";

$response['dupa'] = $dupa;
$response['success'] = 1;
$response['error'] = 0;

echo json_encode($response);