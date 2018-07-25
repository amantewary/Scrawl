CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spDeleteLabel`(IN parameter_name VARCHAR(255))
BEGIN
	DELETE FROM labels 
    WHERE name IN (parameter_name) 
    LIMIT 1;
END