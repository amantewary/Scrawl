CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetLabels`(IN parameter_user_id INT)
BEGIN
	SELECT * FROM labels 
    WHERE user_id IN (parameter_user_id, 0)
    ORDER BY created_at DESC;
END