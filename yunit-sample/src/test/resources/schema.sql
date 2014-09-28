CREATE TABLE Person (
  id int NOT NULL,
  name varchar(45) NOT NULL,
  gender char(1) NOT NULL,
  birth_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

