CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetNoteByUser`(IN parameter_user_id INT(11))
BEGIN
	SELECT * FROM notes 
    WHERE user_id = parameter_user_id;
END