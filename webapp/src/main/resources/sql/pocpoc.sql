CREATE TABLE account (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nom varchar(50),
	prenom varchar(50),
	adresse varchar(50),
	tel varchar(50)
);

CREATE TABLE contrat (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	numero varchar(50),
	datedebut date,
	datefin date,
	tel varchar(50)
);
