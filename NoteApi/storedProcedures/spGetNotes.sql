CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetNotes`()
BEGIN
	SELECT n.id, n.label_name, n.title, n.body, n.url, n.user_id, n.created_at FROM notes n ORDER BY n.created_at DESC;
END