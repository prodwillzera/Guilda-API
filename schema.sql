CREATE DATABASE IF NOT EXISTS guilda_db;
-- drop database guilda_db;
USE guilda_db;

CREATE TABLE cacadores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  classe VARCHAR(50) NOT NULL,
  nivel INT NOT NULL,
  cidade_origem VARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE monstros (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  rank_dificuldade VARCHAR(20) NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  recompensa_ouro INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE missoes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  monstro_id INT NOT NULL,
  cacador_id INT,
  status VARCHAR(20) NOT NULL DEFAULT 'Disponível',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (monstro_id) REFERENCES monstros(id),
  FOREIGN KEY (cacador_id) REFERENCES cacadores(id)
);

INSERT INTO cacadores (nome, classe, nivel, cidade_origem) VALUES
('Artur Tuki', 'Mago', 10, 'Valdoria'),
('Cogu Mestre', 'Mago', 10000, 'Valdoria'),
('Biruta Tomas', 'Guerreiro', 9, 'Eldoria');

INSERT INTO monstros (nome, rank_dificuldade, tipo, recompensa_ouro) VALUES
('Goblin Verde', 'Fácil', 'Humanóide', 100),
('Sapo Verde', 'Fácil', 'Sapo', 67),
('Dragão Negro', 'Lendário', 'Dragão', 5000);

SELECT * FROM cacadores;

SELECT * FROM monstros;