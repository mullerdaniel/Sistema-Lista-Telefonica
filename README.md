# Sistema-Telefonico

* Fazer um sistema telefonico integrado com banco de dados.


CREATE DATABASE Lista_Telefonica;
USE Lista_Telefonica;

CREATE TABLE contato (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
telefone VARCHAR(20) NOT NULL,
email VARCHAR(100),
observacao TEXT
);
