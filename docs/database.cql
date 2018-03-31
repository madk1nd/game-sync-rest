CREATE TABLE users (
uuid VARCHAR(36) NOT NULL PRIMARY KEY,
money INT NOT NULL,
country CHAR(2) NOT NULL, 
registration_time TIMESTAMP
) ENGINE=InnoDB;


INSERT INTO `users` (`uuid`,`money`,`country`,`registration_time`) VALUES ("gfdhgfhfg", 534435, "UK", '2014-02-23 22:00:00');
INSERT INTO `users` (`uuid`,`money`,`country`,`registration_time`) VALUES ("wqerqwe", 5634345, "US", '2017-04-16 22:00:00');
INSERT INTO `users` (`uuid`,`money`,`country`,`registration_time`) VALUES ("gdjythjdfghdf", 53242, "UA", '2002-09-16 22:00:00');
INSERT INTO `users` (`uuid`,`money`,`country`,`registration_time`) VALUES ("yturyujhcfg", 637, "KZ", '2006-07-15 22:00:00');
INSERT INTO `users` (`uuid`,`money`,`country`,`registration_time`) VALUES ("qawersdfg", 234523, "GE", '1999-01-01 22:00:00');
INSERT INTO `users` (`uuid`,`money`,`country`,`registration_time`) VALUES ("dfgjhdfghdfg", 476234, "SP", '2012-11-03 22:00:00');
INSERT INTO `users` (`uuid`,`money`,`country`,`registration_time`) VALUES ("utyujghdjfh", 45234, "BL", '2016-06-27 22:00:00');
INSERT INTO `users` (`uuid`,`money`,`country`,`registration_time`) VALUES ("hiufjhfter", 43345234, "UK", '2015-12-05 22:00:00');


CREATE TABLE activity (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_uuid VARCHAR(36) NOT NULL,
activity INT NOT NULL,
time TIMESTAMP,
FOREIGN KEY (user_uuid) REFERENCES users(uuid)
) ENGINE=InnoDB;

INSERT INTO `activity` (`user_uuid`,`activity`,`time`) VALUES ("hiufjhfter", 43345234, '2015-12-05 22:00:00');

CREATE DEFINER=`root`@`localhost` FUNCTION `ordered_uuid`(uuid BINARY(36))
RETURNS binary(16) DETERMINISTIC
RETURN UNHEX(CONCAT(SUBSTR(uuid, 15, 4),SUBSTR(uuid, 10, 4),SUBSTR(uuid, 1, 8),SUBSTR(uuid, 20, 4),SUBSTR(uuid, 25)));

select hex(id),test from test where id=ordered_uuid('8e696f4f-ad30-4d87-a3bd-91c8b5b2a265');

INSERT INTO test VALUES (ordered_uuid('e0fa9149-e288-4877-b1b4-22524175aabd'),'primer1');
INSERT INTO test VALUES (ordered_uuid('8e696f4f-ad30-4d87-a3bd-91c8b5b2a265'),'primer1');