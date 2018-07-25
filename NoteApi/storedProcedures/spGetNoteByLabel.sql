CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetNoteByLabel`(IN parameter_label VARCHAR(255), parameter_user_id INT(11))
BEGIN
	SELECT * FROM notes 
    WHERE label_name = parameter_label
    AND user_id = parameter_user_id;
END