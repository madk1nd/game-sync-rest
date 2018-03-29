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