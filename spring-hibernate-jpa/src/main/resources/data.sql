CREATE TABLE person(
	id INTEGER NOT NULL,
	name VARCHAR(255) NOT NULL,
	location VARCHAR(255),
	birth_date TIMESTAMP,
	PRIMARY KEY(id)
);

INSERT INTO person (id,name,location,birth_date) values (10001,'Homs','Mexico',CURRENT_TIMESTAMP());
INSERT INTO person (id,name,location,birth_date) values (10002,'Marina','Mexico',CURRENT_TIMESTAMP());
INSERT INTO person (id,name,location,birth_date) values (10003,'Kayle','Mexico',CURRENT_TIMESTAMP());