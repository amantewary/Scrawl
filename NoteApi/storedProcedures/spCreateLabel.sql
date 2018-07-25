CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spCreateLabel`(IN parameter_name VARCHAR(255), parameter_user_id INT(11))
BEGIN
	INSERT INTO labels 
		SET 
			name = parameter_name,
			user_id = parameter_user_id;
END