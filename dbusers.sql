-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 30-Nov-2020 às 00:54
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dbusers`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbhistorico`
--

CREATE TABLE `tbhistorico` (
  `tempo` double NOT NULL,
  `distancia` double NOT NULL,
  `id` int(11) NOT NULL,
  `datas` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tbhistorico`
--

INSERT INTO `tbhistorico` (`tempo`, `distancia`, `id`, `datas`) VALUES
(55, 20.3, 1, '2020-11-20'),
(6, 0.5, 1, '2020-11-20'),
(63, 26.1, 1, '2020-11-02');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbmural`
--

CREATE TABLE `tbmural` (
  `nome` varchar(200) NOT NULL,
  `data` date NOT NULL,
  `msg` varchar(90) NOT NULL,
  `extras` varchar(200) NOT NULL,
  `empresa` varchar(30) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tbmural`
--

INSERT INTO `tbmural` (`nome`, `data`, `msg`, `extras`, `empresa`, `id`) VALUES
('Paula', '2020-11-01', 'Estou Competindo agora, vamos la!vamos la!!vamos la!!vamos la!', 'http://192.168.0.11/Api/includes/img/paulinha.jpeg', 'Aco', 1),
('Anderson', '2020-11-01', 'caminhada amanhã ?', 'http://192.168.0.11/Api/includes/img/costas.jpg', 'Aco', 2),
('Maria ', '2020-11-03', 'Boa Tarde Pessoal', 'http://192.168.0.11/Api/includes/img/maria.jpeg', 'Aco', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbusers`
--

CREATE TABLE `tbusers` (
  `Nome` varchar(40) NOT NULL,
  `Login` varchar(20) NOT NULL,
  `Empresa` varchar(25) NOT NULL,
  `Senha` varchar(20) NOT NULL,
  `Id` int(11) NOT NULL,
  `Descricao` varchar(200) DEFAULT 'None',
  `Rank` int(11) DEFAULT 0,
  `Km` double NOT NULL DEFAULT 0.01,
  `Comp` int(11) NOT NULL DEFAULT 0,
  `Tempo` double NOT NULL DEFAULT 0.01,
  `extra` varchar(300) DEFAULT 'None'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tbusers`
--

INSERT INTO `tbusers` (`Nome`, `Login`, `Empresa`, `Senha`, `Id`, `Descricao`, `Rank`, `Km`, `Comp`, `Tempo`, `extra`) VALUES
('Paula', '123', 'Aco', '123', 1, 'administrador', 3, 10.3, 1, 3, 'http://192.168.0.11/Api/includes/img/paulinha.jpeg'),
('Cedae Teixeira', 'Cedae', '', '321', 52, 'usuario', 0, 0.01, 0, 0.01, 'http://192.168.0.11/Api/includes/img/foto da trilha.jpeg');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tbmural`
--
ALTER TABLE `tbmural`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tbusers`
--
ALTER TABLE `tbusers`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbmural`
--
ALTER TABLE `tbmural`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `tbusers`
--
ALTER TABLE `tbusers`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
