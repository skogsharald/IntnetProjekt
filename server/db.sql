use intnet;

drop table users;
drop table transfers;
drop table rates;
drop table countries;

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
	amount float,
	fromCurr varchar(64),
	type varchar(64),
	dt datetime,
	PRIMARY KEY(id)
);

CREATE TABLE rates(
	id int NOT NULL AUTO_INCREMENT,
	fromCurr varchar(64),
	toCurr varchar(64),
	rate float,
	PRIMARY KEY(id)
);

CREATE TABLE countries(
	countryName varchar(64),
	currency varchar(64),
	PRIMARY KEY(countryName)
);

INSERT INTO rates(fromCurr, toCurr, rate) VALUES ('SEK', 'USD', 0.154);
INSERT INTO rates(fromCurr, toCurr, rate) VALUES ('USD', 'SEK', 6.4935);
INSERT INTO rates(fromCurr, toCurr, rate) VALUES ('GBP', 'SEK', 10.8051);
INSERT INTO rates(fromCurr, toCurr, rate) VALUES ('GBP', 'USD', 1.664);
INSERT INTO rates(fromCurr, toCurr, rate) VALUES ('SEK', 'GBP', 0.09254);
INSERT INTO rates(fromCurr, toCurr, rate) VALUES ('USD', 'GBP', 0.60096);
INSERT INTO countries VALUES ('USA', 'USD');
INSERT INTO countries VALUES ('Sweden', 'SEK');
INSERT INTO countries VALUES ('GreatBritain', 'GBP');





