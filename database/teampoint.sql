-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 03 jan. 2021 à 11:11
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `teampoint`
--

-- --------------------------------------------------------

--
-- Structure de la table `board`
--

CREATE TABLE `board` (
  `idBoard` int(10) NOT NULL,
  `userOwner` int(10) NOT NULL,
  `idPermission` int(10) NOT NULL,
  `boardName` varchar(25) NOT NULL,
  `parentWorkspace` int(10) NOT NULL,
  `boardCreationDate` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `board`
--

INSERT INTO `board` (`idBoard`, `userOwner`, `idPermission`, `boardName`, `parentWorkspace`, `boardCreationDate`) VALUES
(0, 1, 0, 'TestBoard', 0, '0000-00-00');

-- --------------------------------------------------------

--
-- Structure de la table `cell`
--

CREATE TABLE `cell` (
  `idCell` int(10) NOT NULL,
  `idBoard` int(10) NOT NULL,
  `idColumn` int(10) NOT NULL,
  `idItem` int(10) NOT NULL,
  `idItemCollection` int(10) NOT NULL,
  `cellValue` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cell`
--

INSERT INTO `cell` (`idCell`, `idBoard`, `idColumn`, `idItem`, `idItemCollection`, `cellValue`) VALUES
(0, 0, 0, 0, 0, 'ValueTest'),
(44, 0, 0, 0, 0, 'sa');

-- --------------------------------------------------------

--
-- Structure de la table `column`
--

CREATE TABLE `column` (
  `idColumn` int(10) NOT NULL,
  `idColumnType` int(10) NOT NULL,
  `idBoard` int(10) NOT NULL,
  `columnName` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `column`
--

INSERT INTO `column` (`idColumn`, `idColumnType`, `idBoard`, `columnName`) VALUES
(0, 0, 0, 'Columntest');

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE `item` (
  `idItem` int(10) NOT NULL,
  `idBoard` int(10) NOT NULL,
  `idItemCollection` int(10) NOT NULL,
  `itemName` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `item`
--

INSERT INTO `item` (`idItem`, `idBoard`, `idItemCollection`, `itemName`) VALUES
(0, 0, 0, 'Item Test');

-- --------------------------------------------------------

--
-- Structure de la table `itemcollection`
--

CREATE TABLE `itemcollection` (
  `idItemCollection` int(10) NOT NULL,
  `idBoard` int(10) NOT NULL,
  `itemCollectionName` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `itemcollection`
--

INSERT INTO `itemcollection` (`idItemCollection`, `idBoard`, `itemCollectionName`) VALUES
(0, 0, 'collection Test');

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

CREATE TABLE `type` (
  `idType` int(10) NOT NULL,
  `nameType` varchar(25) NOT NULL,
  `descriptionType` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`idType`, `nameType`, `descriptionType`) VALUES
(0, 'TimeLineType', 'two date'),
(1, 'textType', 'field of text');

-- --------------------------------------------------------

--
-- Structure de la table `typepermission`
--

CREATE TABLE `typepermission` (
  `idTypePermission` int(10) NOT NULL,
  `labelPermission` varchar(25) NOT NULL,
  `description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `typepermission`
--

INSERT INTO `typepermission` (`idTypePermission`, `labelPermission`, `description`) VALUES
(0, 'EditEverything', 'member of a board can edit every row and column');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `idUser` int(10) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `firstName` varchar(25) DEFAULT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(500) NOT NULL,
  `phoneNumber` varchar(10) DEFAULT NULL,
  `profileDescription` varchar(500) DEFAULT NULL,
  `birthday` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `name`, `firstName`, `email`, `password`, `phoneNumber`, `profileDescription`, `birthday`) VALUES
(1, 'Nicolas', 'Galois', 'galoisnicolas@gmail.com', 'toto', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_workspace`
--

CREATE TABLE `user_workspace` (
  `idUser` int(10) NOT NULL,
  `idWorkspace` int(10) NOT NULL,
  `userRole` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_workspace`
--

INSERT INTO `user_workspace` (`idUser`, `idWorkspace`, `userRole`) VALUES
(1, 44, 'WorkspaceAdmin'),
(1, 45, 'WorkspaceAdmin');

-- --------------------------------------------------------

--
-- Structure de la table `workspace`
--

CREATE TABLE `workspace` (
  `idWorkspace` int(10) NOT NULL,
  `idUserOwner` int(10) NOT NULL,
  `workspaceName` varchar(25) NOT NULL,
  `workspaceCreationDate` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `workspace`
--

INSERT INTO `workspace` (`idWorkspace`, `idUserOwner`, `workspaceName`, `workspaceCreationDate`) VALUES
(0, 1, 'Test', '0000-00-00'),
(44, 1, 'sa', '2021-01-02'),
(45, 1, 'as', '2021-01-02');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `board`
--
ALTER TABLE `board`
  ADD PRIMARY KEY (`idBoard`),
  ADD KEY `FOREIGN_PARENT_WORKSPACE` (`parentWorkspace`),
  ADD KEY `FOREIGN_USER_OWNER` (`userOwner`),
  ADD KEY `FOREIGN_PERMISSION` (`idPermission`);

--
-- Index pour la table `cell`
--
ALTER TABLE `cell`
  ADD PRIMARY KEY (`idCell`),
  ADD KEY `FOREIGN_CELL_BOARD` (`idBoard`),
  ADD KEY `FOREIGN_COLUMN_CELL` (`idColumn`),
  ADD KEY `FOREIGN_ITEM_COLLECTION_CELL` (`idItemCollection`),
  ADD KEY `FOREIGN_ITEM_ROW` (`idItem`);

--
-- Index pour la table `column`
--
ALTER TABLE `column`
  ADD PRIMARY KEY (`idColumn`),
  ADD KEY `FOREIGN_COLUMN_BOARD_KEY` (`idBoard`),
  ADD KEY `FOREIGN_COLUMN_TYPE` (`idColumnType`);

--
-- Index pour la table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`idItem`),
  ADD KEY `FOREIGN_BOARD_ITEM_KEY` (`idBoard`),
  ADD KEY `FOREIGN_ITEM_COLLECTION_ITEM_KEY` (`idItemCollection`);

--
-- Index pour la table `itemcollection`
--
ALTER TABLE `itemcollection`
  ADD PRIMARY KEY (`idItemCollection`),
  ADD KEY `FOREIGN_BOARD_ITEM_COLLECTION_KEY` (`idBoard`);

--
-- Index pour la table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`idType`);

--
-- Index pour la table `typepermission`
--
ALTER TABLE `typepermission`
  ADD PRIMARY KEY (`idTypePermission`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- Index pour la table `user_workspace`
--
ALTER TABLE `user_workspace`
  ADD KEY `FOREIGN_USER_WORKSPACE_USER` (`idUser`),
  ADD KEY `FOREIGN_WORKSPACE_USER_WORKSPACE` (`idWorkspace`);

--
-- Index pour la table `workspace`
--
ALTER TABLE `workspace`
  ADD PRIMARY KEY (`idWorkspace`),
  ADD KEY `FOREIGN_ADMIN_OWNER_WORKSPACE` (`idUserOwner`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `board`
--
ALTER TABLE `board`
  MODIFY `idBoard` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `cell`
--
ALTER TABLE `cell`
  MODIFY `idCell` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `column`
--
ALTER TABLE `column`
  MODIFY `idColumn` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `item`
--
ALTER TABLE `item`
  MODIFY `idItem` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `itemcollection`
--
ALTER TABLE `itemcollection`
  MODIFY `idItemCollection` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT pour la table `workspace`
--
ALTER TABLE `workspace`
  MODIFY `idWorkspace` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `board`
--
ALTER TABLE `board`
  ADD CONSTRAINT `FOREIGN_PARENT_WORKSPACE` FOREIGN KEY (`parentWorkspace`) REFERENCES `workspace` (`idWorkspace`),
  ADD CONSTRAINT `FOREIGN_PERMISSION` FOREIGN KEY (`idPermission`) REFERENCES `typepermission` (`idTypePermission`),
  ADD CONSTRAINT `FOREIGN_USER_OWNER` FOREIGN KEY (`userOwner`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `cell`
--
ALTER TABLE `cell`
  ADD CONSTRAINT `FOREIGN_CELL_BOARD` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`),
  ADD CONSTRAINT `FOREIGN_COLUMN_CELL` FOREIGN KEY (`idColumn`) REFERENCES `column` (`idColumn`),
  ADD CONSTRAINT `FOREIGN_ITEM_COLLECTION_CELL` FOREIGN KEY (`idItemCollection`) REFERENCES `itemcollection` (`idItemCollection`),
  ADD CONSTRAINT `FOREIGN_ITEM_ROW` FOREIGN KEY (`idItem`) REFERENCES `item` (`idItem`);

--
-- Contraintes pour la table `column`
--
ALTER TABLE `column`
  ADD CONSTRAINT `FOREIGN_COLUMN_BOARD_KEY` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`),
  ADD CONSTRAINT `FOREIGN_COLUMN_TYPE` FOREIGN KEY (`idColumnType`) REFERENCES `type` (`idType`);

--
-- Contraintes pour la table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FOREIGN_BOARD_ITEM` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`),
  ADD CONSTRAINT `FOREIGN_BOARD_ITEM_KEY` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`),
  ADD CONSTRAINT `FOREIGN_ITEM_COLLECTION_ITEM_KEY` FOREIGN KEY (`idItemCollection`) REFERENCES `itemcollection` (`idItemCollection`);

--
-- Contraintes pour la table `itemcollection`
--
ALTER TABLE `itemcollection`
  ADD CONSTRAINT `FOREIGN_BOARD_ITEM_COLLECTION_KEY` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`);

--
-- Contraintes pour la table `user_workspace`
--
ALTER TABLE `user_workspace`
  ADD CONSTRAINT `FOREIGN_USER_WORKSPACE_USER` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `FOREIGN_WORKSPACE_USER_WORKSPACE` FOREIGN KEY (`idWorkspace`) REFERENCES `workspace` (`idWorkspace`);

--
-- Contraintes pour la table `workspace`
--
ALTER TABLE `workspace`
  ADD CONSTRAINT `FOREIGN_ADMIN_OWNER_WORKSPACE` FOREIGN KEY (`idUserOwner`) REFERENCES `user` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
