-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 06, 2022 alle 17:29
-- Versione del server: 10.4.14-MariaDB
-- Versione PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mcsite`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `domanda`
--

CREATE TABLE `domanda` (
  `id` int(11) NOT NULL,
  `domanda` varchar(255) NOT NULL,
  `idquiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `domanda`
--

INSERT INTO `domanda` (`id`, `domanda`, `idquiz`) VALUES
(2, 'Quale personaggio tratto da un  romanzo horror è nato a Napoli?', 1),
(3, 'Qual è il santo patrono di Napoli?', 1),
(4, 'Napoli è la città con più teatri in Italia', 1),
(5, 'Qual è il significato antico del nome Neapolis?', 1),
(6, 'Quale evento ha avuto luogo a Napoli nel 1994? ', 1),
(7, 'Chi fu il primo docente della cattedra di Economia Politica nel 1754?', 1),
(8, 'Quali sono i principali colori del logo cittadino di Napoli?', 1),
(9, 'In Europa, la citta di Napoli ha il centro storico più grande', 1),
(10, 'Su quale mare si affaccia il golfo di Napoli?', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `quiz`
--

CREATE TABLE `quiz` (
  `id` int(11) NOT NULL,
  `citta` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `quiz`
--

INSERT INTO `quiz` (`id`, `citta`) VALUES
(1, 'Napoli'),
(2, 'Roma');

-- --------------------------------------------------------

--
-- Struttura della tabella `reply`
--

CREATE TABLE `reply` (
  `id` int(11) NOT NULL,
  `risposta` varchar(255) NOT NULL,
  `esatta` tinyint(1) NOT NULL,
  `iddomanda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `reply`
--

INSERT INTO `reply` (`id`, `risposta`, `esatta`, `iddomanda`) VALUES
(1, 'Toto e Peppino', 0, 2),
(2, 'Frankestein', 1, 2),
(3, 'Fedez', 0, 2),
(4, 'Dracula', 0, 2),
(5, 'San Ciro', 0, 3),
(6, 'San Pasquale', 0, 3),
(7, 'San Giuseppe', 0, 3),
(8, 'San Gennaro', 0, 3),
(9, 'Vero', 1, 4),
(10, 'Falso', 0, 4),
(11, 'Città grande', 0, 5),
(12, 'Città della bellezza', 0, 5),
(13, 'Città vecchia', 0, 5),
(14, 'Città nuova', 1, 5),
(15, 'Viene costruita la prima base NATO', 0, 6),
(16, 'Arriva Diego Armando Maradona', 0, 6),
(17, 'Napoli ospita il G7', 1, 6),
(18, 'Il sindaco si dimette', 0, 6),
(19, 'Antonio de Curtis', 0, 7),
(20, 'Cioffo Giuseppe', 0, 7),
(21, 'Bartolomeo Intieri', 1, 7),
(22, 'Luigi de Rosa', 0, 7),
(23, 'Falso', 0, 9),
(24, 'Vero', 1, 9),
(25, 'Rosso-Oro', 1, 8),
(26, 'Azzurro', 0, 8),
(27, 'Bianco-Blu', 0, 8),
(28, 'Giallo', 0, 8),
(29, 'Ionio', 0, 10),
(30, 'Adriatico', 0, 10),
(31, 'Tirreno', 1, 10),
(32, 'Egeo', 0, 10);

-- --------------------------------------------------------

--
-- Struttura della tabella `svolgimento_quiz`
--

CREATE TABLE `svolgimento_quiz` (
  `id` int(11) NOT NULL,
  `idutente` int(11) NOT NULL,
  `idquiz` int(11) NOT NULL,
  `punteggio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `svolgimento_quiz`
--

INSERT INTO `svolgimento_quiz` (`id`, `idutente`, `idquiz`, `punteggio`) VALUES
(58, 1, 1, 4),
(60, 2, 1, 6),
(61, 1, 1, 0),
(62, 1, 1, 0),
(63, 1, 1, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `id` int(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`id`, `username`, `email`, `password`) VALUES
(1, 'erinnis', 'erinnis@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(2, 'bernardo', 'b@gmail.com', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5'),
(5, 'test', 't@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(8, 'test11', 'test11@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(10, 'davide', 'davide@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(11, 'mario', 'mario@gmail.com', '6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `domanda`
--
ALTER TABLE `domanda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idquiz` (`idquiz`);

--
-- Indici per le tabelle `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `reply`
--
ALTER TABLE `reply`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iddomanda` (`iddomanda`);

--
-- Indici per le tabelle `svolgimento_quiz`
--
ALTER TABLE `svolgimento_quiz`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idquiz` (`idquiz`),
  ADD KEY `idutente` (`idutente`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `domanda`
--
ALTER TABLE `domanda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `quiz`
--
ALTER TABLE `quiz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `reply`
--
ALTER TABLE `reply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT per la tabella `svolgimento_quiz`
--
ALTER TABLE `svolgimento_quiz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `domanda`
--
ALTER TABLE `domanda`
  ADD CONSTRAINT `domanda_ibfk_1` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`id`);

--
-- Limiti per la tabella `reply`
--
ALTER TABLE `reply`
  ADD CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`iddomanda`) REFERENCES `domanda` (`id`);

--
-- Limiti per la tabella `svolgimento_quiz`
--
ALTER TABLE `svolgimento_quiz`
  ADD CONSTRAINT `svolgimento_quiz_ibfk_1` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`id`),
  ADD CONSTRAINT `svolgimento_quiz_ibfk_2` FOREIGN KEY (`idutente`) REFERENCES `utente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
