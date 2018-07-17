CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetNoteByLabel`(IN parameter_label VARCHAR(25))
BEGIN
	SELECT * FROM notes 
    WHERE label_name = parameter_label;
END