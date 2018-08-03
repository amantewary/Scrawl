CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spDeleteLabel`(IN parameter_name VARCHAR(255), parameter_user_id INT(11))
BEGIN
	DELETE FROM labels 
    WHERE name IN (parameter_name)
    AND user_id IN (parameter_user_id)
    LIMIT 1;
END