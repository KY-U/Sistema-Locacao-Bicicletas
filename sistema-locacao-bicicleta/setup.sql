CREATE DATABASE IF NOT EXISTS SistemaLocacao;

USE SistemaLocacao;

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

-- Inserção de Clientes
INSERT INTO Clientes (email, senha, cpf, nome, telefone, sexo, data_nascimento) VALUES
('user1@example.com', '123456', '12345678901', 'João da Silva', '(11) 987654321', 'M', '1990-05-15'),
('user2@example.com', 'abcdef', '98765432109', 'Maria Oliveira', '(21) 987654321', 'F', '1985-03-20'),
('user3@example.com', 'senha123', '45678901234', 'Pedro Santos', '(31) 987654321', 'M', '1995-11-10'),
('user4@example.com', '987654', '78901234567', 'Ana Pereira', '(41) 987654321', 'F', '1980-07-05'),
('user5@example.com', 'qwerty', '23456789012', 'Carlos Souza', '(51) 987654321', 'M', '2000-09-25');

-- Inserção de Locadoras
INSERT INTO Locadoras (email, senha, cnpj, nome, cidade) VALUES
('Lokabike@example.com', 'locadora123', '12345678000123', 'Lokabike', 'São Paulo'),
('RentBike@example.com', 'locadora456', '98765432000109', 'RentBike', 'Rio de Janeiro'),
('Bicilok@example.com', 'locadora789', '45678901000134', 'Bicilok', 'São Paulo');

-- Inserção de Locações
INSERT INTO Locacoes (cpf_cliente, cnpj_locadora, data_horario) VALUES
('12345678901', '12345678000123', '2024-07-22 14:00:00'),
('98765432109', '98765432000109', '2024-07-22 15:00:00'),
('45678901234', '45678901000134', '2024-07-22 16:00:00'),
('78901234567', '12345678000123', '2024-07-22 17:00:00'),
('23456789012', '98765432000109', '2024-07-22 18:00:00');

-- Inserção de Administradores
INSERT INTO Administradores (email, senha) VALUES
('admin1@example.com', 'adminpass1'),
('admin2@example.com', 'adminpass2'),
('admin3@example.com', 'adminpass3');