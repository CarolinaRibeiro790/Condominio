DROP DATABASE Condominio;

CREATE DATABASE Condominio;
USE Condominio;

CREATE TABLE Endereco(
	codEndereco int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rua varchar(100) NOT NULL,
    numero int NOT NULL,
    bairro varchar(100) NOT NULL,
    cep varchar(10) NOT NULL
);

CREATE TABLE Proprietario(
	codigoProprietario int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome varchar(200) NOT NULL,
    dataNascimento date NOT NULL, 
    rg varchar(30) NOT NULL,
    cpf varchar(30) NOT NULL,
    codEndereco int,
	FOREIGN KEY (codEndereco) REFERENCES Endereco(codEndereco)
);


CREATE TABLE Morador(
	codigo int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome varchar(200) NOT NULL,
    dataNascimento date NOT NULL, 
    rg varchar(30) NOT NULL,
    cpf varchar(30) NOT NULL,
	codEndereco int,
	FOREIGN KEY (codEndereco) REFERENCES Endereco(codEndereco)
);

CREATE TABLE CondominioMensalidade(
	codigoMensalidade int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    mes varchar(20),
    dataVencimento date,
    dataPagamento date NULL,
    statusMensalidade varchar(20),
    codEndereco int,
    codigoProprietario int,
    FOREIGN KEY (codEndereco) REFERENCES Endereco(codEndereco),
    FOREIGN KEY (codigoProprietario) REFERENCES Proprietario(codigoProprietario)
);

