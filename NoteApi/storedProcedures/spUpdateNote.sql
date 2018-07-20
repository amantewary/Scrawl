CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spUpdateNote`(IN parameter_label_name VARCHAR(255), parameter_title VARCHAR(255), parameter_body TEXT, parameter_url VARCHAR(255), parameter_user_id INT(11), parameter_id INT(11), parameter_status VARCHAR(255), parameter_date VARCHAR(45))
BEGIN
	UPDATE notes 
		SET 
			label_name = parameter_label_name,
			title = parameter_title, 
			body = parameter_body, 
			url = parameter_url, 
			user_id = parameter_user_id,
            status = parameter_status,
            date = parameter_date
		WHERE
			id = parameter_id;
END