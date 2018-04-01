CREATE TABLE users (
uuid BINARY(16) NOT NULL PRIMARY KEY,
money INT NOT NULL,
country CHAR(2) NOT NULL, 
time TIMESTAMP
) ENGINE=InnoDB;


INSERT INTO `users` (`uuid`,`money`,`country`,`time`) VALUES (ordered_uuid('e0fa9149-e288-4877-b1b4-22524175aabd'), 534435, "UK", NOW());
INSERT INTO `users` (`uuid`,`money`,`country`,`time`) VALUES (ordered_uuid('8e696f4f-ad30-4d87-a3bd-91c8b5b2a265'), 5634345, "US", NOW());

CREATE TABLE activity (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_uuid BINARY(16) NOT NULL,
activity INT NOT NULL,
time TIMESTAMP,
FOREIGN KEY (user_uuid) REFERENCES users(uuid)
) ENGINE=InnoDB;

INSERT INTO `activity` (`user_uuid`,`activity`,`time`) VALUES (ordered_uuid('e0fa9149-e288-4877-b1b4-22524175aabd'), 43345234, NOW());

CREATE DEFINER=`root`@`localhost` FUNCTION `ordered_uuid`(uuid BINARY(36))
RETURNS binary(16) DETERMINISTIC
RETURN UNHEX(CONCAT(SUBSTR(uuid, 15, 4),SUBSTR(uuid, 10, 4),SUBSTR(uuid, 1, 8),SUBSTR(uuid, 20, 4),SUBSTR(uuid, 25)));

SELECT `money`,`country` FROM users WHERE `uuid`=ordered_uuid('8e696f4f-ad30-4d87-a3bd-91c8b5b2a265') ;

// e0fa9149-e288-4877-b1b4-22524175aabd
// 8e696f4f-ad30-4d87-a3bd-91c8b5b2a265
//    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID());
//    }