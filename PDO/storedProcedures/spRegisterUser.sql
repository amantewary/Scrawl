CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spRegisterUser`(IN parameter_unique_user_id VARCHAR(50), parameter_username VARCHAR(45), parameter_email_address VARCHAR(45), parameter_password VARCHAR(45), parameter_created_at DATETIME, parameter_modified_at DATETIME, parameter_salt VARCHAR(45))
BEGIN
	INSERT INTO user 
		(
        unique_user_id, 
		username , 
		email_address , 
		password , 
		created_at , 
		modified_at , 
		salt 
        )
	VALUES 
		(
        parameter_unique_user_id, 
        parameter_username, 
        parameter_email_address, 
        parameter_password, 
        parameter_created_at, 
        parameter_modified_at, 
        parameter_salt
        );
END