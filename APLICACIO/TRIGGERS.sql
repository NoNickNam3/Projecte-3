CREATE DEFINER=`root`@`localhost` trigger prova_trigger_time_stamp
BEFORE INSERT
ON tracking 
FOR EACH ROW
begin
	IF(new.momento > CURRENT_TIMESTAMP) THEN
    	signal sqlstate '20000' set MESSAGE_TEXT = 'La data no pot ser futura.';
    END IF;
end