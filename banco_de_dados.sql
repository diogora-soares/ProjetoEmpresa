CREATE DATABASE empresa;
CREATE USER 'devuser'@'localhost' IDENTIFIED BY '123456';

GRANT ALL PRIVILEGES ON *.* TO 'devuser'@'localhost';
SELECT * FROM mysql.user;
FLUSH PRIVILEGES;

USE empresa;
CREATE TABLE pessoa (
    id             INT PRIMARY KEY AUTO_INCREMENT,
    nome           VARCHAR(100),
    email          VARCHAR(100)
);
SET foreign_key_checks = 0;
SET foreign_key_checks = 1;
truncate TABLE pessoa;
truncate TABLE funcionario;
CREATE TABLE funcionario (
    id             INT PRIMARY KEY,
    matricula      VARCHAR(20),
    departamento   VARCHAR(50),
    FOREIGN KEY (id) REFERENCES pessoa(id)
);

CREATE TABLE projeto (
    id             INT PRIMARY KEY AUTO_INCREMENT,
    nome           VARCHAR(100),
    descricao      TEXT,
    id_funcionario INT,
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);


INSERT INTO pessoa (id, nome, email) VALUES
(1, 'Ana Oliveira', 'ana.oliveira@email.com'),
(2, 'Bruno Silva', 'bruno.silva@email.com'),
(3, 'Carla Mendes', 'carla.mendes@email.com'),
(4, 'Daniel Souza', 'daniel.souza@email.com'),
(5, 'Eduarda Lima', 'eduarda.lima@email.com'),
(6, 'Felipe Rocha', 'felipe.rocha@email.com'),
(7, 'Gustavo Pinto', 'gustavo.pinto@email.com'),
(8, 'Helena Duarte', 'helena.duarte@email.com'),
(9, 'Isabela Ferreira', 'isabela.ferreira@email.com'),
(10, 'João Matos', 'joao.matos@email.com');

SELECT * FROM funcionario; 
INSERT INTO funcionario (id, matricula, departamento) VALUES
(1, 'F001', 'TI'),
(2, 'F002', 'RH'),
(3, 'F003', 'Financeiro'),
(4, 'F004', 'TI'),
(5, 'F005', 'Marketing'),
(6, 'F006', 'TI'),
(7, 'F007', 'RH'),
(8, 'F008', 'Logística'),
(9, 'F009', 'Financeiro'),
(10, 'F010', 'TI');

INSERT INTO projeto (nome, descricao, id_funcionario) VALUES
('Sistema ERP', 'Implantação de sistema de gestão empresarial.', 1),
('Treinamento Interno', 'Capacitação dos novos funcionários.', 2),
('Auditoria Financeira', 'Revisão dos processos contábeis e fiscais.', 3),
('App Corporativo', 'Desenvolvimento de app interno para gestão de tarefas.', 4),
('Campanha Digital', 'Divulgação institucional nas redes sociais.', 5),
('Infraestrutura', 'Revisão da rede interna e servidores.', 6),
('Recrutamento 2025', 'Planejamento de contratações para o próximo ano.', 7),
('Estoque Inteligente', 'Implementação de sistema de controle por RFID.', 8),
('Contabilidade 4.0', 'Automatização dos relatórios contábeis.', 9),
('Monitoramento TI', 'Sistema de monitoramento proativo da infraestrutura.', 10);


SELECT * FROM pessoa;
SELECT * FROM Funcionario;

DELETE FROM pessoa
WHERE id  = ""

DELETE FROM funcionario
WHERE id  = ""


SELECT p.id, p.nome, p.email, f.matricula, f.departamento FROM pessoa p INNER JOIN funcionario f ON p.id = f.id 
