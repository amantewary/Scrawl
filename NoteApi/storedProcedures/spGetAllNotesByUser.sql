CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetAllNotesByUser`(IN para_email VARCHAR(45), IN para_userid int(11))
BEGIN
  SELECT * FROM notes 
	WHERE id in 
	(SELECT note_id FROM shares WHERE share_to = para_email)
	UNION ALL
	SELECT * FROM notes
	WHERE user_id = para_userid;
END