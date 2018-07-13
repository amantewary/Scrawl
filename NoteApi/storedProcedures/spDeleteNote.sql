CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spDeleteNote`(IN parameter_id INT(11))
BEGIN
	DELETE FROM notes 
    WHERE id = parameter_id;
END