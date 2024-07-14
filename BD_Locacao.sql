CREATE DATABASE IF NOT EXISTS SistemaLocacao

USE SistemaLocacao

-- Tabela de Clientes
CREATE TABLE IF NOT EXISTS Clientes (
    email VARCHAR(100) PRIMARY KEY,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15),
    sexo ENUM('M', 'F', 'O') NOT NULL,
    data_nascimento DATE NOT NULL
);

-- Tabela de Locadoras
CREATE TABLE IF NOT EXISTS Locadoras (
    email VARCHAR(100) PRIMARY KEY,
    senha VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL
);

-- Tabela de Locações
CREATE TABLE IF NOT EXISTS Locacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf_cliente VARCHAR(11) NOT NULL,
    cnpj_locadora VARCHAR(14) NOT NULL,
    data_horario DATETIME NOT NULL,
    FOREIGN KEY (cpf_cliente) REFERENCES Clientes(cpf),
    FOREIGN KEY (cnpj_locadora) REFERENCES Locadoras(cnpj),
    UNIQUE (cpf_cliente, data_horario),
    UNIQUE (cnpj_locadora, data_horario)
);

-- Tabela de Administradores
CREATE TABLE IF NOT EXISTS Administradores (
    email VARCHAR(100) PRIMARY KEY,
    senha VARCHAR(100) NOT NULL
);
