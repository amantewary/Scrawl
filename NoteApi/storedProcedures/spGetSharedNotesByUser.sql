CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetSharedNotesByUser`(IN para_email VARCHAR(45))
BEGIN
  SELECT * FROM notes 
	WHERE id in 
	(SELECT note_id FROM shares WHERE share_to = para_email);
END