CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetNoteById`(IN parameter_id INT(11))
BEGIN
	SELECT n.id, n.label_name, n.title, n.body, n.url, n.user_id, n.created_at 
    FROM notes n  
    WHERE n.id = parameter_id 
    LIMIT 0,1;
END