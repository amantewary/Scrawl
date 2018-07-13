CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetLabels`()
BEGIN
	SELECT id, name, created_at FROM labels ORDER BY created_at DESC;
END