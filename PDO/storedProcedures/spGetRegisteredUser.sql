CREATE DEFINER=`CSCI5308_19_DEVINT_USER`@`%` PROCEDURE `spGetRegisteredUser`(IN parameter_email_address VARCHAR(45))
BEGIN
	SELECT * 
    FROM user 
    WHERE email_address = parameter_email_address;
END