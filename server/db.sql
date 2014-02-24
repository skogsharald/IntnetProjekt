use intnet;

drop table users;
drop table transfers;
drop table rates;

CREATE TABLE users(
	id int NOT NULL AUTO_INCREMENT,
	fname varchar(64),
	lname varchar(64),
	username varchar(64),
	password varchar(64),
	country varchar(64),
	email varchar(64),
	PRIMARY KEY(id)
);

CREATE TABLE transfers(
	id int NOT NULL AUTO_INCREMENT,
	fromUser int,
	toUser int,
	amount int,
	PRIMARY KEY(id)
);

CREATE TABLE rates(
	fromCurr varchar(64),
	toCurr varchar(64),
	rate float,
	PRIMARY KEY(fromCurr)
);

