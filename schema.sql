-- Criação da base de dados e tabela
CREATE DATABASE IF NOT EXISTS db_login;
USE db_login;

CREATE TABLE if not exit usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(10) NOT NULL,
    nome VARCHAR(30) NOT NULL,
    senha CHAR(32) NOT NULL
);

