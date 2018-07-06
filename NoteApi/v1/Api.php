<?php 
	require_once '../includes/Operations.php';
	function isTheseParametersAvailable($params){
		$available = true; 
		$missingparams = ""; 
		foreach($params as $param){
			if(!isset($_POST[$param]) || strlen($_POST[$param])<=0){
				$available = false; 
				$missingparams = $missingparams . ", " . $param; 
			}
		}
		if(!$available){
			$response = array(); 
			$response['error'] = true; 
			$response['message'] = 'Parameters ' . substr($missingparams, 1, strlen($missingparams)) . ' missing';
			echo json_encode($response);
			die();
		}
	}
	$response = array();
	if(isset($_GET['apicall'])){
		
		switch($_GET['apicall']){
			case 'createnote':
				isTheseParametersAvailable(array('notetitle','notelabel','notebody'));
				$db = new Operations();
				$result = $db->createNote(
					$_POST['notetitle'],
					$_POST['notelabel'],
					$_POST['notebody']
				);
				if($result){
					$response['error'] = false; 
					$response['message'] = 'Note addedd successfully';
					$response['notes'] = $db->getNotes();
				}else{
					$response['error'] = true; 
					$response['message'] = 'Some error occurred please try again';
				}
				
			break; 

			case 'getnotes':
				$db = new Operations();
				$response['error'] = false; 
				$response['message'] = 'Request successfully completed';
				$response['notes'] = $db->getNotes();
			break; 
			
			
			case 'updatenote':
				isTheseParametersAvailable(array('id','notetitle','notelabel','notebody'));
				$db = new Operations();
				$result = $db->updateNote(
					$_POST['id'],
					$_POST['notetitle'],
					$_POST['notelabel'],
					$_POST['notebody']
				);
				
				if($result){
					$response['error'] = false; 
					$response['message'] = 'Note updated successfully';
					$response['notes'] = $db->getNotes();
				}else{
					$response['error'] = true; 
					$response['message'] = 'Some error occurred please try again';
				}
			break; 

			case 'deletenote':

				if(isset($_GET['id'])){
					$db = new Operations();
					if($db->deleteNote($_GET['id'])){
						$response['error'] = false; 
						$response['message'] = 'Note deleted successfully';
						$response['notes'] = $db->getNotes();
					}else{
						$response['error'] = true; 
						$response['message'] = 'Some error occurred please try again';
					}
				}else{
					$response['error'] = true; 
					$response['message'] = 'Nothing to delete, provide an id please';
				}
			break; 
		}
		
	}else{

		$response['error'] = true; 
		$response['message'] = 'Invalid API Call';
	}

	echo json_encode($response);
	
	
