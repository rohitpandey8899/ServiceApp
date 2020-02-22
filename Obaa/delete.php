<?php 
include_once "config.php";
header('Content-Type:application/json');
$response = array();

$contact = $_POST['contact'];

$sql = mysqli_query($conn, "DELETE * FROM booking WHERE contact = '$contact'");

$data = mysqli_fetch_array($sql);

if (sizeof($data) != 0) {

	$response['success'] = "TRUE";
	$response['contact'] = $data['contact'];
	
	
}else{

	$response['success'] = "FALSE";
	$response['message'] = "Wrong Username or Password.";
	
}
echo json_encode($response);

?>