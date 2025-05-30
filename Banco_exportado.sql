-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.32-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para empresa
CREATE DATABASE IF NOT EXISTS `empresa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `empresa`;

-- Copiando estrutura para tabela empresa.funcionario
CREATE TABLE IF NOT EXISTS `funcionario` (
  `id` int(11) NOT NULL,
  `matricula` varchar(20) DEFAULT NULL,
  `departamento` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela empresa.funcionario: ~10 rows (aproximadamente)
DELETE FROM `funcionario`;
INSERT INTO `funcionario` (`id`, `matricula`, `departamento`) VALUES
	(1, 'F001', 'TI'),
	(3, 'F003', 'Financeiro'),
	(4, 'F099', 'Financeiro'),
	(5, 'F005', 'Marketing'),
	(6, 'F006', 'TI'),
	(7, 'F007', 'RH'),
	(8, 'F008', 'Logística'),
	(9, 'F009', 'Financeiro'),
	(13, 'F012', 'TI');

-- Copiando estrutura para tabela empresa.pessoa
CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela empresa.pessoa: ~12 rows (aproximadamente)
DELETE FROM `pessoa`;
INSERT INTO `pessoa` (`id`, `nome`, `email`) VALUES
	(1, 'Ana Oliveira', 'ana.oliveira@email.com'),
	(3, 'Carla Mendes', 'carla.mendes@email.com'),
	(4, 'Quincas Cruz', 'quincas@email.com'),
	(5, 'Eduarda Lima', 'eduarda.lima@email.com'),
	(6, 'Felipe Rocha', 'felipe.rocha@email.com'),
	(7, 'Gustavo Pinto', 'gustavo.pinto@email.com'),
	(8, 'Helena Duarte', 'helena.duarte@email.com'),
	(9, 'Isabela Ferreira', 'isabela.ferreira@email.com'),
	(11, 'Mateus Costa', 'mateus.costa@gmail.com'),
	(12, 'pedro raul', 'pedradas@gmail.com'),
	(13, 'pedro raul', 'pedradas@gmail.com');

-- Copiando estrutura para tabela empresa.projeto
CREATE TABLE IF NOT EXISTS `projeto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `descricao` text DEFAULT NULL,
  `id_funcionario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_funcionario` (`id_funcionario`),
  CONSTRAINT `projeto_ibfk_1` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela empresa.projeto: ~10 rows (aproximadamente)
DELETE FROM `projeto`;
INSERT INTO `projeto` (`id`, `nome`, `descricao`, `id_funcionario`) VALUES
	(1, 'Sistema ERP', 'Implantação de sistema de gestão empresarial.', 1),
	(3, 'Auditoria Financeira', 'Revisão dos processos contábeis e fiscais.', 3),
	(4, 'App Corporativo', 'Desenvolvimento de app interno para gestão de tarefas.', 4),
	(5, 'Campanha Digital', 'Divulgação institucional nas redes sociais.', 5),
	(6, 'Infraestrutura', 'Revisão da rede interna e servidores.', 6),
	(7, 'Recrutamento 2025', 'Planejamento de contratações para o próximo ano.', 7),
	(8, 'Estoque Inteligente', 'Implementação de sistema de controle por RFID.', 8),
	(9, 'Contabilidade 4.0', 'Automatização dos relatórios contábeis.', 9);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
