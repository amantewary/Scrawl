CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spUpdateLabel`(IN parameter_label_name VARCHAR(255), parameter_user_id INT(11), parameter_old_name VARCHAR(255))
BEGIN
	SET SQL_SAFE_UPDATES = 0;
	UPDATE labels 
		SET 
			name = parameter_label_name
		WHERE
			user_id IN (parameter_user_id)
		AND
			name IN (parameter_old_name);
	SET SQL_SAFE_UPDATES = 1;
END