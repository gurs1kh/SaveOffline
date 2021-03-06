<?PHP
        require_once('dbconn.php');
       
    	try {
            //get input 
            $email = isset($_GET['email']) ? $_GET['email'] : '';
            $password = isset($_GET['password']) ? $_GET['password'] : '';
  
            //validate input
            if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
                echo '{"result": "fail", "error": "Please enter a valid email."}';
            } else if (strlen($password) < 6) {
                echo '{"result": "fail", "error": "Please enter a valid password (longer than five characters)."}';
            } else {    

                //build query
                $sql = "INSERT INTO Users (email, password)";
                $sql .= " VALUES ('$email', '$password')";
				
				
				$q = $conn->prepare($sql);
                //attempts to add record
                if ($q->execute()) {
					$sql = "SELECT uid FROM Users ";
					$sql .= " WHERE email = '" . $email . "'";
					
					$q = $conn->prepare($sql);
					$q->execute();
					$result = $q->fetch(PDO::FETCH_ASSOC);
					echo '{"result": "success", "userid": "' . $result['uid'] . '"}';
                    $db = null;
                } 
            }   
        } catch(PDOException $e) {
                if ((int)($e->getCode()) == 23000) {
                    echo '{"result": "fail", "error": "That email address has already been registered."}';
                } else {
                    echo 'Error Number: ' . $e->getCode() . '<br>';
                    echo '{"result": "fail", "error": "Unknown error (' . (((int)($e->getCode()) + 123) * 2) .')"}';
                }
        }
?>
