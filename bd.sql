-- --------------------------------------------------------
-- Hôte :                        localhost
-- Version du serveur:           5.7.24 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             9.5.0.5332
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Listage de la structure de la base pour live_manager_db
CREATE DATABASE IF NOT EXISTS `live_manager_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `live_manager_db`;

-- Listage de la structure de la table live_manager_db. amo
CREATE TABLE IF NOT EXISTS `amo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `taux_amo_patronale` float NOT NULL,
  `taux_amo_salarial` float NOT NULL,
  `taux_particition` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.amo : ~0 rows (environ)
/*!40000 ALTER TABLE `amo` DISABLE KEYS */;
/*!40000 ALTER TABLE `amo` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. anciennete
CREATE TABLE IF NOT EXISTS `anciennete` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `debut_tranche` float NOT NULL,
  `fin_tranche` float NOT NULL,
  `taux_anciennete` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.anciennete : ~0 rows (environ)
/*!40000 ALTER TABLE `anciennete` DISABLE KEYS */;
/*!40000 ALTER TABLE `anciennete` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. apprenant
CREATE TABLE IF NOT EXISTS `apprenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lieu_naissance` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `numero_cni` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` char(1) DEFAULT NULL,
  `telephone_1` varchar(255) DEFAULT NULL,
  `telephone_2` varchar(255) DEFAULT NULL,
  `type_apprenant` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.apprenant : ~2 rows (environ)
/*!40000 ALTER TABLE `apprenant` DISABLE KEYS */;
INSERT INTO `apprenant` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `adresse`, `date_naissance`, `email`, `lieu_naissance`, `matricule`, `nom`, `numero_cni`, `photo`, `prenom`, `sexe`, `telephone_1`, `telephone_2`, `type_apprenant`) VALUES
	(1, '2019-07-16 08:32:30', '$2a$10$YEgH4VvmUC5LOxKWXRe0Tu/GC4K2ig6gn9cpMLJrvrZl9Ny3iE1/W', b'0', NULL, 'bafoussam ', '1999-06-07', 'kamgue@gmail.com', 'bamougoum', 'H79DH', 'kamgue', '009980', 'logo.png', 'blondin', 'M', '8899987', '88778877', 'Etudiant'),
	(2, '2019-07-16 08:33:25', '$2a$10$nNvRJVPxnoAjUukI7PWd2esqoVJoeSoyHoq7lVDG7I1lbiSvM64Ey', b'0', NULL, 'bafoussam ', '1999-05-07', 'tchichap@gmail.com', 'bafoussam', '66YU66E', 'Tchichap', '677887766', '', 'kamga', 'F', '8899987', '86876857', 'Elève');
/*!40000 ALTER TABLE `apprenant` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. apprenant_inscriptions
CREATE TABLE IF NOT EXISTS `apprenant_inscriptions` (
  `apprenant_id` bigint(20) NOT NULL,
  `inscriptions` bigint(20) NOT NULL,
  PRIMARY KEY (`apprenant_id`,`inscriptions`),
  UNIQUE KEY `UK_fmymj77yjvqypysgwalissbof` (`inscriptions`),
  CONSTRAINT `FK9oot1p6fskplqubdmj4q305e8` FOREIGN KEY (`apprenant_id`) REFERENCES `apprenant` (`id`),
  CONSTRAINT `FKat99so75eil6flvwtt78f61m2` FOREIGN KEY (`inscriptions`) REFERENCES `inscription` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.apprenant_inscriptions : ~0 rows (environ)
/*!40000 ALTER TABLE `apprenant_inscriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `apprenant_inscriptions` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. avances
CREATE TABLE IF NOT EXISTS `avances` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `valeur` bigint(20) DEFAULT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKse9l56sl6a8rdb3w04ngbnisp` (`personnel`),
  CONSTRAINT `FKse9l56sl6a8rdb3w04ngbnisp` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.avances : ~0 rows (environ)
/*!40000 ALTER TABLE `avances` DISABLE KEYS */;
/*!40000 ALTER TABLE `avances` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. banque
CREATE TABLE IF NOT EXISTS `banque` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiv6iikp7yiap526hlblpfrwe5` (`personnel`),
  CONSTRAINT `FKiv6iikp7yiap526hlblpfrwe5` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.banque : ~2 rows (environ)
/*!40000 ALTER TABLE `banque` DISABLE KEYS */;
INSERT INTO `banque` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `adresse`, `fax`, `nom`, `telephone`, `personnel`) VALUES
	(1, '2019-07-16 08:27:24', '$2a$10$bUunpkWFmhNUHDPBgAMhQuAS73K1E1Ar5.AVzGoqS0U74.7iVCUe6', b'0', NULL, 'bafoussam -  tamdja', '22333111', 'Western Union', '678776655', NULL),
	(2, '2019-07-16 08:27:46', '$2a$10$KnujUZJv0azDHm7nv2nJQu08uA4Oo.a6I2X3mVMJEFNi3CemPQB7W', b'0', NULL, 'bafoussam - Marcher A', '223331111', 'ECO BANK', '6655443322', NULL);
/*!40000 ALTER TABLE `banque` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. bulletin_paie
CREATE TABLE IF NOT EXISTS `bulletin_paie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `nbre_conges_payes` bigint(20) DEFAULT NULL,
  `nbre_heure_sup100` float NOT NULL,
  `nbre_heure_sup25` float NOT NULL,
  `nbre_heure_sup50` float NOT NULL,
  `nbre_heure_travaillees` float NOT NULL,
  `nbre_jours_feries` bigint(20) DEFAULT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrb97a3tdla3aemf17ifxi8rau` (`personnel`),
  CONSTRAINT `FKrb97a3tdla3aemf17ifxi8rau` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.bulletin_paie : ~0 rows (environ)
/*!40000 ALTER TABLE `bulletin_paie` DISABLE KEYS */;
/*!40000 ALTER TABLE `bulletin_paie` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. chapitre
CREATE TABLE IF NOT EXISTS `chapitre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `resume` text,
  `titre` varchar(255) DEFAULT NULL,
  `id_cours` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl2oe4111k7al4vjb3xujonumj` (`id_cours`),
  CONSTRAINT `FKl2oe4111k7al4vjb3xujonumj` FOREIGN KEY (`id_cours`) REFERENCES `cours` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.chapitre : ~2 rows (environ)
/*!40000 ALTER TABLE `chapitre` DISABLE KEYS */;
INSERT INTO `chapitre` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `resume`, `titre`, `id_cours`) VALUES
	(1, '2019-07-16 09:11:16', '$2a$10$xXyJ0fSlgG4aBfHVjLt79.pgV92SIVEzJYtQ8q4b/Z6Q0Arquq.2m', b'0', NULL, 'L\'automobile est un moyen de transport privé parmi les plus répandus. Sa capacité est généralement de deux à cinq personnes, mais peut varier de une à neuf places.\r\n\r\nL\'usage limite l\'emploi du terme automobile aux véhicules possédant quatre roues, de dimensions inférieures à celle des autobus et des camions, mais englobe parfois les camionnettes. Bien qu\'étant des « véhicules automobiles », les motocyclettes ne sont pas habituellement classées dans cette catégorie.\r\n\r\nLe terme « automobile » est à l\'origine un adjectif issu de la concaténation du préfixe grec ????? (soi-même) et du suffixe latin mobilis (mobile). Il a été créé lors de l\'invention des premières « voitures sans chevaux », qui étaient munies d\'un moteur avec source d\'énergie embarquée3 alors que les autres « voitures » (diligences, calèches, carrioles, chariots et autres véhicules terrestres) étaient mues par des animaux de trait (généralement des chevaux, avec les voitures hippomobiles, ou des bœufs).\r\n\r\nLe substantif « automobile » est attesté vers 1890, mais son genre, aujourd\'hui seulement féminin, a fait pour les linguistes l\'objet de débats4. L\'Académie française s\'est ainsi prononcée dès 1901 pour le genre féminin5, mais la polémique ne s\'est éteinte que bien après, le masculin étant attesté ponctuellement jusqu\'en 19444,[réf. nécessaire].\r\n\r\nLe terme majoritairement utilisé en France pour désigner une automobile est « voiture », nettement plus rarement « auto », quasiment jamais « automobile », qui apparaît comme désuet. En raison de sa large diffusion et de son usage dans les milieux les plus variés, l\'automobile est aujourd\'hui appelée par de nombreux noms familiers, comme « auto », « bagnole », ou « char »6 en Amérique du nord francophone, et argotiques, comme « tacot », « caisse », « tire »7, « guimbarde », « chignole », « charrette » en Europe, ainsi que « minoune » au Canada[réf. souhaitée].\r\n\r\nTechnique', 'le vehicule', NULL),
	(2, '2019-07-16 09:47:39', '$2a$10$Fj9zdZKasWZg7dVszY/0T.2XOKuIgf0lgHjL6nQlEX93q8JjhBY5m', b'0', NULL, 'Un cataclysme inconnu a dévasté le monde. Des incendies géants ont ravagé les villes et les campagnes tandis que la faune a disparu. Ce qui ressemble à un hiver nucléaire masque en permanence le soleil et des cendres recouvrent le paysage. L\'humanité a presque disparu, les quelques survivants se terrent tels des bêtes ou, ayant apparemment régressé, pratiquent le meurtre et le cannibalisme.\r\n\r\nDans ce décor apocalyptique, un père et son fils, que l\'auteur ne dénommera jamais autrement que « l\'homme » et « le petit », errent en direction du sud, leurs maigres possessions rassemblées dans un chariot de supermarché et des sacs à dos.', 'la route', NULL),
	(4, '2019-07-16 10:35:25', '$2a$10$5cWPYvS0aBFrZUKpubCnuOFv.I3aPtOaWhXZxOKtxzxk6InE1gRu2', b'0', NULL, 'go\r\n\r\n\r\n\r\ntest\r\n\r\n\r\n\r\ngo\r\n\r\n\r\n*\r\ngo\r\n\r\n\r\nblack', 'le terran', NULL);
/*!40000 ALTER TABLE `chapitre` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. chapitre_evaluations
CREATE TABLE IF NOT EXISTS `chapitre_evaluations` (
  `chapitre_id` bigint(20) NOT NULL,
  `evaluations` bigint(20) NOT NULL,
  UNIQUE KEY `UK_l5ibewshoopyt01htkov2vjl5` (`evaluations`),
  KEY `FKj7ewaf2yh57k2tvguv7n6tv1n` (`chapitre_id`),
  CONSTRAINT `FKj7ewaf2yh57k2tvguv7n6tv1n` FOREIGN KEY (`chapitre_id`) REFERENCES `chapitre` (`id`),
  CONSTRAINT `FKlmvtclu4cp4wjmbbibqedu753` FOREIGN KEY (`evaluations`) REFERENCES `evaluation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.chapitre_evaluations : ~0 rows (environ)
/*!40000 ALTER TABLE `chapitre_evaluations` DISABLE KEYS */;
/*!40000 ALTER TABLE `chapitre_evaluations` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. chapitre_suivres
CREATE TABLE IF NOT EXISTS `chapitre_suivres` (
  `chapitre_id` bigint(20) NOT NULL,
  `suivres` bigint(20) NOT NULL,
  UNIQUE KEY `UK_gtmr3hx1bpogrsduxbteifx5m` (`suivres`),
  KEY `FK4ufsptvpv687128egvkjoa2p8` (`chapitre_id`),
  CONSTRAINT `FK4ufsptvpv687128egvkjoa2p8` FOREIGN KEY (`chapitre_id`) REFERENCES `chapitre` (`id`),
  CONSTRAINT `FKdxlh733a1fb33w8j9x8py7gde` FOREIGN KEY (`suivres`) REFERENCES `suivre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.chapitre_suivres : ~0 rows (environ)
/*!40000 ALTER TABLE `chapitre_suivres` DISABLE KEYS */;
/*!40000 ALTER TABLE `chapitre_suivres` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. charges_patronales
CREATE TABLE IF NOT EXISTS `charges_patronales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.charges_patronales : ~0 rows (environ)
/*!40000 ALTER TABLE `charges_patronales` DISABLE KEYS */;
/*!40000 ALTER TABLE `charges_patronales` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. cnps
CREATE TABLE IF NOT EXISTS `cnps` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `plafond_salarial` float NOT NULL,
  `taux_patronal` float NOT NULL,
  `taux_salarial` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.cnps : ~0 rows (environ)
/*!40000 ALTER TABLE `cnps` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnps` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. code
CREATE TABLE IF NOT EXISTS `code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `identifier` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.code : ~12 rows (environ)
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `description`, `identifier`) VALUES
	(1, NULL, NULL, NULL, NULL, 'Un service de l\'auto-ecole', 'service'),
	(2, NULL, NULL, NULL, NULL, 'Catégorie de produit', 'categorie_produit'),
	(3, NULL, NULL, NULL, NULL, 'Unité de mesure d\'un produit', 'unite_mesure'),
	(4, NULL, NULL, NULL, NULL, 'Type de permis', 'type_permis'),
	(5, NULL, NULL, NULL, NULL, 'Standing d\'un logement', 'standing_logement'),
	(6, NULL, NULL, NULL, NULL, 'Etat d\'une formation', 'etat_formation'),
	(7, NULL, NULL, NULL, NULL, 'Type de pièce d\'identité d\'un client', 'type_piece_identite'),
	(8, NULL, NULL, NULL, NULL, 'Type de caisse comptable', 'type_caisse'),
	(9, NULL, NULL, NULL, NULL, 'Statut d\'une dépense initiée', 'statut_depense'),
	(10, NULL, NULL, NULL, NULL, 'Type de plat', 'type_plat'),
	(11, NULL, NULL, NULL, NULL, 'Type de paramètre enregistré', 'type_parametre'),
	(12, NULL, NULL, NULL, NULL, 'Type de plan tarifaire des chambres', 'plan_tarifaire');
/*!40000 ALTER TABLE `code` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. code_value
CREATE TABLE IF NOT EXISTS `code_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `valeur` varchar(255) NOT NULL,
  `code` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfkb5k0d55r02m1x8n2b1x0vwm` (`code`),
  CONSTRAINT `FKfkb5k0d55r02m1x8n2b1x0vwm` FOREIGN KEY (`code`) REFERENCES `code` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.code_value : ~50 rows (environ)
/*!40000 ALTER TABLE `code_value` DISABLE KEYS */;
INSERT INTO `code_value` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `description`, `valeur`, `code`) VALUES
	(1, NULL, NULL, NULL, NULL, 'Administration', 'administration', 1),
	(2, NULL, NULL, NULL, NULL, 'Hebergement', 'hebergement', 1),
	(3, NULL, NULL, NULL, NULL, 'Comptabilité', 'comptabilite', 1),
	(13, NULL, NULL, NULL, NULL, 'Kilogramme', 'kg', 3),
	(14, NULL, NULL, NULL, NULL, 'Litre', 'litre', 3),
	(15, NULL, NULL, NULL, NULL, 'Mètre', 'metre', 3),
	(16, NULL, NULL, NULL, NULL, 'Morceau', 'morceau', 3),
	(17, NULL, NULL, NULL, NULL, 'Sac', 'sac', 3),
	(18, NULL, NULL, NULL, NULL, 'Palette', 'palette', 3),
	(19, NULL, NULL, NULL, NULL, 'Carton', 'carton', 3),
	(20, NULL, NULL, NULL, NULL, 'Bouteille', 'bouteille', 3),
	(21, NULL, NULL, NULL, NULL, 'Paquet', 'paquet', 3),
	(22, NULL, NULL, NULL, NULL, 'Casier', 'casier', 3),
	(23, NULL, NULL, NULL, NULL, 'Paquet', 'paquet', 3),
	(24, NULL, NULL, NULL, NULL, 'Feuille', 'feuille', 3),
	(25, NULL, NULL, NULL, NULL, 'Ballot', 'ballot', 3),
	(26, NULL, NULL, NULL, NULL, 'Boule', 'boule', 3),
	(32, NULL, NULL, NULL, NULL, 'Moderne', 'moderne', 5),
	(33, NULL, NULL, NULL, NULL, 'Classique', 'classique', 5),
	(34, NULL, NULL, NULL, NULL, 'Autre', 'autre', 5),
	(35, NULL, NULL, NULL, NULL, 'Propre', 'propre', 6),
	(37, NULL, NULL, NULL, NULL, 'Carte d\'identité nationale', 'cni', 7),
	(38, NULL, NULL, NULL, NULL, 'Passeport', 'passeport', 7),
	(39, NULL, NULL, NULL, NULL, 'Carte de séjour', 'carte_sejour', 7),
	(40, NULL, NULL, NULL, NULL, 'Courante', 'courante', 8),
	(41, NULL, NULL, NULL, NULL, 'En attente de validation', 'attente', 9),
	(42, NULL, NULL, NULL, NULL, 'Validée', 'valide', 9),
	(43, NULL, NULL, NULL, NULL, 'Annulée', 'annule', 9),
	(44, NULL, NULL, NULL, NULL, 'Entrée', 'entree', 10),
	(46, NULL, NULL, NULL, NULL, 'Sortie', 'sortie', 10),
	(47, NULL, NULL, NULL, NULL, 'Entier', 'entier', 11),
	(48, NULL, NULL, NULL, NULL, 'Double', 'double', 11),
	(49, NULL, NULL, NULL, NULL, 'Chaine', 'chaine', 11),
	(50, NULL, NULL, NULL, NULL, 'Booléen', 'booleen', 11),
	(51, NULL, NULL, NULL, NULL, 'Caractère', 'caractere', 11),
	(53, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis AM', 'AM', 4),
	(54, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis A1', 'A1', 4),
	(55, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis A2', 'A2', 4),
	(56, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis A', 'A', 4),
	(57, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis B1', 'B1', 4),
	(58, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis B', 'B', 4),
	(59, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis BE', 'BE', 4),
	(61, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis C1', 'C1', 4),
	(62, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis C1E', 'C1E', 4),
	(63, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis C', 'C', 4),
	(64, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis CE', 'CE', 4),
	(65, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis D1', 'D1', 4),
	(66, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis D1E', 'D1E', 4),
	(67, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis D', 'D', 4),
	(68, '2019-05-29 00:00:00', NULL, NULL, '2019-05-29 00:00:00', 'Permis DE', 'DE', 4);
/*!40000 ALTER TABLE `code_value` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. conge
CREATE TABLE IF NOT EXISTS `conge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duree` bigint(20) DEFAULT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  `type_conge` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2tgukh6ecf4k8ycijrctkm21u` (`personnel`),
  KEY `FKs1g8djyq0wlwoj6qk1o82eg4f` (`type_conge`),
  CONSTRAINT `FK2tgukh6ecf4k8ycijrctkm21u` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`),
  CONSTRAINT `FKs1g8djyq0wlwoj6qk1o82eg4f` FOREIGN KEY (`type_conge`) REFERENCES `type_conge` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.conge : ~0 rows (environ)
/*!40000 ALTER TABLE `conge` DISABLE KEYS */;
/*!40000 ALTER TABLE `conge` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. contrat
CREATE TABLE IF NOT EXISTS `contrat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `base_paie` varchar(255) DEFAULT NULL,
  `date_embauche` date DEFAULT NULL,
  `frequence_paie` varchar(255) DEFAULT NULL,
  `mode_reglement` varchar(255) DEFAULT NULL,
  `salaire_base` float NOT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  `profession` bigint(20) DEFAULT NULL,
  `type_contrat` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmbm6hpo2nu1qvv9j68ndwfn3p` (`personnel`),
  KEY `FKlwsbvc7sugona8idi4v59tw17` (`profession`),
  KEY `FKr0i49dba5trus2nx17kr98xq` (`type_contrat`),
  CONSTRAINT `FKlwsbvc7sugona8idi4v59tw17` FOREIGN KEY (`profession`) REFERENCES `profession` (`id`),
  CONSTRAINT `FKmbm6hpo2nu1qvv9j68ndwfn3p` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`),
  CONSTRAINT `FKr0i49dba5trus2nx17kr98xq` FOREIGN KEY (`type_contrat`) REFERENCES `type_contrat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.contrat : ~0 rows (environ)
/*!40000 ALTER TABLE `contrat` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrat` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. cours
CREATE TABLE IF NOT EXISTS `cours` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `id_formation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2pnsgeshc2twsxdxeiky6jxih` (`id_formation`),
  CONSTRAINT `FK2pnsgeshc2twsxdxeiky6jxih` FOREIGN KEY (`id_formation`) REFERENCES `session_formation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.cours : ~2 rows (environ)
/*!40000 ALTER TABLE `cours` DISABLE KEYS */;
INSERT INTO `cours` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `libelle`, `module`, `type`, `id_formation`) VALUES
	(1, '2019-07-16 08:53:55', '$2a$10$nmd2yT3.YMC7ZcFc.AviF.JUH4thV.I4btVQ.wn.tvUkd7KqeMuHW', b'0', NULL, 'debut en conduite', 'Conduite', 'theorique', 1),
	(2, '2019-07-16 08:54:21', '$2a$10$hn9y1pe0xCHeu7rlN.swzeKbt/K7Gwj6vSLj/vWlFAfwsb.SuyBEe', b'0', NULL, 'initiation au panneux de signalisation', 'Les Panneaux', 'theorique', 1),
	(3, '2019-07-16 08:54:49', '$2a$10$khz0XUanO5ud8hjX8hsrkejM6p98lcLZGusd6DFes/cW.SBNn8nbu', b'0', NULL, 'debut en conduite sur le terrain', 'Premier Pas sur la route', 'pratique', 1);
/*!40000 ALTER TABLE `cours` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. cours_chapitres
CREATE TABLE IF NOT EXISTS `cours_chapitres` (
  `cours_id` bigint(20) NOT NULL,
  `chapitres` bigint(20) NOT NULL,
  UNIQUE KEY `UK_643hxv7ufmttd0f9rw9r0k9d` (`chapitres`),
  KEY `FK9nlvmucbqihurgsn96u4v94k0` (`cours_id`),
  CONSTRAINT `FK1hocu9u134y4mjma5cfspp9sg` FOREIGN KEY (`chapitres`) REFERENCES `chapitre` (`id`),
  CONSTRAINT `FK9nlvmucbqihurgsn96u4v94k0` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.cours_chapitres : ~3 rows (environ)
/*!40000 ALTER TABLE `cours_chapitres` DISABLE KEYS */;
INSERT INTO `cours_chapitres` (`cours_id`, `chapitres`) VALUES
	(1, 1),
	(1, 2),
	(1, 4);
/*!40000 ALTER TABLE `cours_chapitres` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. credits
CREATE TABLE IF NOT EXISTS `credits` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `retenue` bigint(20) DEFAULT NULL,
  `taux_interet` bigint(20) DEFAULT NULL,
  `valeur` bigint(20) DEFAULT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnxw6k22eiv0r0524xn3md2lqy` (`personnel`),
  CONSTRAINT `FKnxw6k22eiv0r0524xn3md2lqy` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.credits : ~0 rows (environ)
/*!40000 ALTER TABLE `credits` DISABLE KEYS */;
/*!40000 ALTER TABLE `credits` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. detail
CREATE TABLE IF NOT EXISTS `detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `id_entite` bigint(20) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nom_entite` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `valeur` varchar(255) DEFAULT NULL,
  `detail_parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6wp1tw65ecusrxx8p14fxr8ts` (`detail_parent`),
  CONSTRAINT `FK6wp1tw65ecusrxx8p14fxr8ts` FOREIGN KEY (`detail_parent`) REFERENCES `detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.detail : ~0 rows (environ)
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. diplome
CREATE TABLE IF NOT EXISTS `diplome` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date_delivrance` date DEFAULT NULL,
  `statut` bit(1) NOT NULL,
  `categorie_permis` bigint(20) DEFAULT NULL,
  `inscrit` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg31k81ip3syv7xy21codcmunv` (`categorie_permis`),
  KEY `FK1m91uqnsti767n7rf4rg6h9hc` (`inscrit`),
  CONSTRAINT `FK1m91uqnsti767n7rf4rg6h9hc` FOREIGN KEY (`inscrit`) REFERENCES `inscription` (`id`),
  CONSTRAINT `FKg31k81ip3syv7xy21codcmunv` FOREIGN KEY (`categorie_permis`) REFERENCES `code_value` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.diplome : ~4 rows (environ)
/*!40000 ALTER TABLE `diplome` DISABLE KEYS */;
INSERT INTO `diplome` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `date_delivrance`, `statut`, `categorie_permis`, `inscrit`) VALUES
	(1, '2019-07-16 08:45:50', '$2a$10$3X.Rcs9YhKA/t/AzFqnN.uxy0chnTEF/9d1B1jG/WeeE2C2DisAem', b'0', NULL, NULL, b'0', 63, 1),
	(2, '2019-07-16 08:46:01', '$2a$10$R7NCJyKtZ2a8NZ32.oIEieoMrdBfv3xs8WjPit4NZ7UVSzwS8HAqW', b'0', NULL, NULL, b'0', 56, 2),
	(3, '2019-07-16 08:46:19', '$2a$10$02NOCnxwsMDz.iJRDeoEY.PDIibmdfTeA7i9/BIjRsYcedJmGemMm', b'0', NULL, NULL, b'0', 56, 3),
	(4, '2019-07-16 12:15:20', '$2a$10$mbDewMyMbrn48OK4Z8zFqOHtqF9RKGYp58MVa43PgZmxZ7mbF0fgq', b'0', NULL, NULL, b'0', 57, 4);
/*!40000 ALTER TABLE `diplome` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. entreprise
CREATE TABLE IF NOT EXISTS `entreprise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKavsoj8yr991v6yaen14ai4bt7` (`personnel`),
  CONSTRAINT `FKavsoj8yr991v6yaen14ai4bt7` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.entreprise : ~0 rows (environ)
/*!40000 ALTER TABLE `entreprise` DISABLE KEYS */;
/*!40000 ALTER TABLE `entreprise` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. evaluation
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `note` float NOT NULL,
  `type_evaluation` varchar(255) DEFAULT NULL,
  `id_chapitre` bigint(20) DEFAULT NULL,
  `id_inscription` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtpf6ld1h2yja2xnsg0x3vlfj6` (`id_chapitre`),
  KEY `FKobfuc2t4g26dan8wxfc8ldwii` (`id_inscription`),
  CONSTRAINT `FKobfuc2t4g26dan8wxfc8ldwii` FOREIGN KEY (`id_inscription`) REFERENCES `inscription` (`id`),
  CONSTRAINT `FKtpf6ld1h2yja2xnsg0x3vlfj6` FOREIGN KEY (`id_chapitre`) REFERENCES `chapitre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.evaluation : ~4 rows (environ)
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `date`, `note`, `type_evaluation`, `id_chapitre`, `id_inscription`) VALUES
	(1, '2019-07-16 12:03:31', '$2a$10$c2/5ynmcJ8AkxSxjKaq9h.J9YTi72Fnpnn0kiXJjvjkZ13.b915fK', b'0', NULL, NULL, 16, 'Pratique', 1, 1),
	(2, '2019-07-16 12:03:47', '$2a$10$3lV1oNrqLNdNhP.iRtfRZ.OX71xRvI7heU0DYJVUa/EoYWWQZEyYW', b'0', NULL, NULL, 12, 'Theorique', 2, 2),
	(3, '2019-07-16 12:04:13', '$2a$10$2wZa5OAOJ4ZEDq8WNcpPse7VKTxPTDGp1G4mEq8UZKiGR8veR/GIO', b'0', NULL, NULL, 28, 'Pratique', 4, 2),
	(4, '2019-07-16 12:05:04', '$2a$10$IgWzGo7hsFDiPtobgbOBaOQKVPtmHm97P0tmlPzPdgsyN7wxZd9Ha', b'0', NULL, NULL, 30, 'Pratique', 2, 2);
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. fournitures
CREATE TABLE IF NOT EXISTS `fournitures` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.fournitures : ~0 rows (environ)
/*!40000 ALTER TABLE `fournitures` DISABLE KEYS */;
/*!40000 ALTER TABLE `fournitures` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. fournitures_offres
CREATE TABLE IF NOT EXISTS `fournitures_offres` (
  `fournitures_id` bigint(20) NOT NULL,
  `offres` bigint(20) NOT NULL,
  UNIQUE KEY `UK_dv6vhcgjt61b1x1dgq5257sd9` (`offres`),
  KEY `FKthec4mm0edmon83p6i6gmsbow` (`fournitures_id`),
  CONSTRAINT `FKmdnxe23dy9mnskjw5rkm07lqk` FOREIGN KEY (`offres`) REFERENCES `offre` (`id`),
  CONSTRAINT `FKthec4mm0edmon83p6i6gmsbow` FOREIGN KEY (`fournitures_id`) REFERENCES `fournitures` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.fournitures_offres : ~0 rows (environ)
/*!40000 ALTER TABLE `fournitures_offres` DISABLE KEYS */;
/*!40000 ALTER TABLE `fournitures_offres` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. historique
CREATE TABLE IF NOT EXISTS `historique` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `date_action` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbwgrs8ybbjye0qvefhtibju3` (`user`),
  CONSTRAINT `FKbwgrs8ybbjye0qvefhtibju3` FOREIGN KEY (`user`) REFERENCES `users` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.historique : ~0 rows (environ)
/*!40000 ALTER TABLE `historique` DISABLE KEYS */;
/*!40000 ALTER TABLE `historique` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. inscription
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `paiement` float NOT NULL,
  `id_apprenant` bigint(20) DEFAULT NULL,
  `diplome` bigint(20) DEFAULT NULL,
  `id_formation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4cqphd68ap47rk21hglcum8t7` (`id_apprenant`),
  KEY `FKayjvh5cbtc3866io59gps6u13` (`diplome`),
  KEY `FK6cyssyn195enslhj2u442pw7u` (`id_formation`),
  CONSTRAINT `FK4cqphd68ap47rk21hglcum8t7` FOREIGN KEY (`id_apprenant`) REFERENCES `apprenant` (`id`),
  CONSTRAINT `FK6cyssyn195enslhj2u442pw7u` FOREIGN KEY (`id_formation`) REFERENCES `session_formation` (`id`),
  CONSTRAINT `FKayjvh5cbtc3866io59gps6u13` FOREIGN KEY (`diplome`) REFERENCES `diplome` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.inscription : ~2 rows (environ)
/*!40000 ALTER TABLE `inscription` DISABLE KEYS */;
INSERT INTO `inscription` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `date`, `paiement`, `id_apprenant`, `diplome`, `id_formation`) VALUES
	(1, '2019-07-16 08:45:50', '$2a$10$bzzJnMX9Ip7T8aG4rfd9AuNvsUDk8AkwhdgA3InbxAbuE/EeNAHdi', b'0', NULL, NULL, 10000, 1, 1, 1),
	(2, '2019-07-16 08:46:01', '$2a$10$o44kvq.aKRxNJ4M6.D.KPOIUuyb3/nLAbkyJNuGCXaFYApIJOKJ1W', b'0', NULL, NULL, 12000, 2, 2, 1),
	(3, '2019-07-16 08:46:19', '$2a$10$Q4ywD7GXWTq4nCXDGIJururxREBLZEvcDT8wxixkiqm9G3687facS', b'0', NULL, NULL, 12000, 2, 3, 1),
	(4, '2019-07-16 12:15:20', '$2a$10$39Ut69sNxH3Yfb7asn7Y4.UF1DJP/lmwRxR0UIMOrXMCOCOk4kh5S', b'0', NULL, NULL, 16000, 1, 4, 1);
/*!40000 ALTER TABLE `inscription` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. inscription_evaluations
CREATE TABLE IF NOT EXISTS `inscription_evaluations` (
  `inscription_id` bigint(20) NOT NULL,
  `evaluations` bigint(20) NOT NULL,
  UNIQUE KEY `UK_9o31yo4isd5i4c7kv7mou1oxg` (`evaluations`),
  KEY `FKisa7aqg2vjivqa5j6d0lwa52o` (`inscription_id`),
  CONSTRAINT `FKaijo0rx4sxinpjoow7dsafjgc` FOREIGN KEY (`evaluations`) REFERENCES `evaluation` (`id`),
  CONSTRAINT `FKisa7aqg2vjivqa5j6d0lwa52o` FOREIGN KEY (`inscription_id`) REFERENCES `inscription` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.inscription_evaluations : ~4 rows (environ)
/*!40000 ALTER TABLE `inscription_evaluations` DISABLE KEYS */;
INSERT INTO `inscription_evaluations` (`inscription_id`, `evaluations`) VALUES
	(1, 1),
	(2, 2),
	(2, 3),
	(2, 4);
/*!40000 ALTER TABLE `inscription_evaluations` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. inscription_suivres
CREATE TABLE IF NOT EXISTS `inscription_suivres` (
  `inscription_id` bigint(20) NOT NULL,
  `suivres` bigint(20) NOT NULL,
  UNIQUE KEY `UK_asryc1kbm1g0mhnujyc8n0r75` (`suivres`),
  KEY `FK75mkql2ne7t1binod6lide4i2` (`inscription_id`),
  CONSTRAINT `FK1i3ykqfyuh1wf3ueaet685jr9` FOREIGN KEY (`suivres`) REFERENCES `suivre` (`id`),
  CONSTRAINT `FK75mkql2ne7t1binod6lide4i2` FOREIGN KEY (`inscription_id`) REFERENCES `inscription` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.inscription_suivres : ~4 rows (environ)
/*!40000 ALTER TABLE `inscription_suivres` DISABLE KEYS */;
INSERT INTO `inscription_suivres` (`inscription_id`, `suivres`) VALUES
	(1, 6),
	(1, 7),
	(1, 9),
	(2, 8),
	(2, 10);
/*!40000 ALTER TABLE `inscription_suivres` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. live
CREATE TABLE IF NOT EXISTS `live` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `boite_postale` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `localisation` varchar(255) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `numero_rc` varchar(255) DEFAULT NULL,
  `site_web` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `telephone_1` varchar(255) DEFAULT NULL,
  `telephone_2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.live : ~0 rows (environ)
/*!40000 ALTER TABLE `live` DISABLE KEYS */;
/*!40000 ALTER TABLE `live` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. live_details
CREATE TABLE IF NOT EXISTS `live_details` (
  `live_id` bigint(20) NOT NULL,
  `details` bigint(20) NOT NULL,
  UNIQUE KEY `UK_8dxbclrohvyav99ucwjvhoavv` (`details`),
  KEY `FK2won4gkr4cxl03libby47wd5a` (`live_id`),
  CONSTRAINT `FK2won4gkr4cxl03libby47wd5a` FOREIGN KEY (`live_id`) REFERENCES `live` (`id`),
  CONSTRAINT `FKh76uxe5q61fpe40p3oelks9un` FOREIGN KEY (`details`) REFERENCES `detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.live_details : ~0 rows (environ)
/*!40000 ALTER TABLE `live_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `live_details` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. livre_paie
CREATE TABLE IF NOT EXISTS `livre_paie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date_saisie` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.livre_paie : ~0 rows (environ)
/*!40000 ALTER TABLE `livre_paie` DISABLE KEYS */;
/*!40000 ALTER TABLE `livre_paie` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. mutuelle
CREATE TABLE IF NOT EXISTS `mutuelle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `taux_patronal` float NOT NULL,
  `taux_salarial` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.mutuelle : ~0 rows (environ)
/*!40000 ALTER TABLE `mutuelle` DISABLE KEYS */;
/*!40000 ALTER TABLE `mutuelle` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. offre
CREATE TABLE IF NOT EXISTS `offre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `montant` float NOT NULL,
  `quantite` bigint(20) DEFAULT NULL,
  `id_fourniture` bigint(20) DEFAULT NULL,
  `id_partenaire` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrbe3d18uoyqka41qyj7bgj1dm` (`id_fourniture`),
  KEY `FK18m2tn84j05q6jtum43ai77ds` (`id_partenaire`),
  CONSTRAINT `FK18m2tn84j05q6jtum43ai77ds` FOREIGN KEY (`id_partenaire`) REFERENCES `partenaire` (`id`),
  CONSTRAINT `FKrbe3d18uoyqka41qyj7bgj1dm` FOREIGN KEY (`id_fourniture`) REFERENCES `fournitures` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.offre : ~0 rows (environ)
/*!40000 ALTER TABLE `offre` DISABLE KEYS */;
/*!40000 ALTER TABLE `offre` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. parametre
CREATE TABLE IF NOT EXISTS `parametre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `cle` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `valeur` varchar(255) DEFAULT NULL,
  `type_parametre` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7bxev73h60qglrg7opy6en1v0` (`type_parametre`),
  CONSTRAINT `FK7bxev73h60qglrg7opy6en1v0` FOREIGN KEY (`type_parametre`) REFERENCES `code_value` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.parametre : ~0 rows (environ)
/*!40000 ALTER TABLE `parametre` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametre` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. parametre_entreprise
CREATE TABLE IF NOT EXISTS `parametre_entreprise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `nbre_heure_jour` float NOT NULL,
  `nbre_jour_mois` float NOT NULL,
  `taux_travail_minimum` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.parametre_entreprise : ~0 rows (environ)
/*!40000 ALTER TABLE `parametre_entreprise` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametre_entreprise` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. partenaire
CREATE TABLE IF NOT EXISTS `partenaire` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lieu_naissance` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `numero_cni` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` char(1) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.partenaire : ~0 rows (environ)
/*!40000 ALTER TABLE `partenaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `partenaire` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. partenaire_contrats
CREATE TABLE IF NOT EXISTS `partenaire_contrats` (
  `partenaire_id` bigint(20) NOT NULL,
  `contrats` bigint(20) NOT NULL,
  UNIQUE KEY `UK_nqnj9smqfofhhr93hbnv611i4` (`contrats`),
  KEY `FK3n91949xpsuj4v49lsu386saj` (`partenaire_id`),
  CONSTRAINT `FK3n91949xpsuj4v49lsu386saj` FOREIGN KEY (`partenaire_id`) REFERENCES `partenaire` (`id`),
  CONSTRAINT `FKovceuih5xh0al3f0oojjb0frq` FOREIGN KEY (`contrats`) REFERENCES `contrat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.partenaire_contrats : ~0 rows (environ)
/*!40000 ALTER TABLE `partenaire_contrats` DISABLE KEYS */;
/*!40000 ALTER TABLE `partenaire_contrats` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. partenaire_offres
CREATE TABLE IF NOT EXISTS `partenaire_offres` (
  `partenaire_id` bigint(20) NOT NULL,
  `offres` bigint(20) NOT NULL,
  UNIQUE KEY `UK_c5h5nyyhg8ttaox5ghtdgynq7` (`offres`),
  KEY `FKdtbx451ea8e88017dyq1fj3w` (`partenaire_id`),
  CONSTRAINT `FKdtbx451ea8e88017dyq1fj3w` FOREIGN KEY (`partenaire_id`) REFERENCES `partenaire` (`id`),
  CONSTRAINT `FKtmbyvv8odo0h99oao9fx14uoh` FOREIGN KEY (`offres`) REFERENCES `offre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.partenaire_offres : ~0 rows (environ)
/*!40000 ALTER TABLE `partenaire_offres` DISABLE KEYS */;
/*!40000 ALTER TABLE `partenaire_offres` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. partenaire_previsions
CREATE TABLE IF NOT EXISTS `partenaire_previsions` (
  `partenaire_id` bigint(20) NOT NULL,
  `previsions` bigint(20) NOT NULL,
  UNIQUE KEY `UK_g059d12p2i7y10718kt89rv30` (`previsions`),
  KEY `FK6ckxs6sxtq5taay1cy4atqkdb` (`partenaire_id`),
  CONSTRAINT `FK67xwiwqu4rkwde7ba10h27jx6` FOREIGN KEY (`previsions`) REFERENCES `prevision` (`id`),
  CONSTRAINT `FK6ckxs6sxtq5taay1cy4atqkdb` FOREIGN KEY (`partenaire_id`) REFERENCES `partenaire` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.partenaire_previsions : ~0 rows (environ)
/*!40000 ALTER TABLE `partenaire_previsions` DISABLE KEYS */;
/*!40000 ALTER TABLE `partenaire_previsions` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. partenaire_rendez_vous
CREATE TABLE IF NOT EXISTS `partenaire_rendez_vous` (
  `partenaire_id` bigint(20) NOT NULL,
  `rendez_vous` bigint(20) NOT NULL,
  UNIQUE KEY `UK_r19gtwssb4jhg4ucuoe166jib` (`rendez_vous`),
  KEY `FKs9actogbog4ql9jx7my7o3j9q` (`partenaire_id`),
  CONSTRAINT `FK3067bkx1ilngrgvfjnqkcnwri` FOREIGN KEY (`rendez_vous`) REFERENCES `rendez_vous` (`id`),
  CONSTRAINT `FKs9actogbog4ql9jx7my7o3j9q` FOREIGN KEY (`partenaire_id`) REFERENCES `partenaire` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.partenaire_rendez_vous : ~0 rows (environ)
/*!40000 ALTER TABLE `partenaire_rendez_vous` DISABLE KEYS */;
/*!40000 ALTER TABLE `partenaire_rendez_vous` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel
CREATE TABLE IF NOT EXISTS `personnel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `compte_bancaire` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fonction` varchar(255) DEFAULT NULL,
  `lieu_naissance` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nombre_enfant` int(11) NOT NULL,
  `numerocnps` varchar(255) DEFAULT NULL,
  `numero_cni` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` char(1) DEFAULT NULL,
  `situation_familiale` varchar(255) DEFAULT NULL,
  `telephone_1` varchar(255) DEFAULT NULL,
  `telephone_2` varchar(255) DEFAULT NULL,
  `banque` bigint(20) DEFAULT NULL,
  `entreprise` bigint(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcpaqcd1rf0okcuvje2vcimh0f` (`banque`),
  KEY `FKist460a827tbm04v20r5mg72l` (`entreprise`),
  KEY `FKn2lylx5w2019hf9r3vhaq9th4` (`user`),
  CONSTRAINT `FKcpaqcd1rf0okcuvje2vcimh0f` FOREIGN KEY (`banque`) REFERENCES `banque` (`id`),
  CONSTRAINT `FKist460a827tbm04v20r5mg72l` FOREIGN KEY (`entreprise`) REFERENCES `entreprise` (`id`),
  CONSTRAINT `FKn2lylx5w2019hf9r3vhaq9th4` FOREIGN KEY (`user`) REFERENCES `users` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel : ~2 rows (environ)
/*!40000 ALTER TABLE `personnel` DISABLE KEYS */;
INSERT INTO `personnel` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `adresse`, `compte_bancaire`, `date_naissance`, `email`, `fonction`, `lieu_naissance`, `matricule`, `nom`, `nombre_enfant`, `numerocnps`, `numero_cni`, `photo`, `prenom`, `sexe`, `situation_familiale`, `telephone_1`, `telephone_2`, `banque`, `entreprise`, `user`) VALUES
	(1, '2019-07-16 08:30:00', '$2a$10$2U3r8.sjDMUdsDcDjyPGPeT/nasQ2yJZGHC8tbnxjo0uUlrefUYrG', b'0', NULL, 'bafoussam -  kilombo', '778877665', '1998-02-09', 'mogo.ivan@gmail.com', 'Secretaire', 'bamenda', '6666Y66', 'mogo', 0, '76876', '009982', 'logo.png', 'ivan', 'M', 'M', '677887766', '677665544', 1, NULL, NULL),
	(2, '2019-07-16 08:31:28', '$2a$10$fR7wdj5N4TMKl3QwyZC5cOq3ipmFNlp9C9oLXGBN1tloNXBDplK9y', b'0', NULL, 'bafoussam - Marcher A', '667700U', '1999-05-07', 'mogo.ivan@yahoo.com', 'Comptable', 'bamougoum', '66YU66', 'Nelson', 0, '76876I', '009983', 'logo.png', 'Blackstar', 'M', 'M', '667766666', '66667766', 2, NULL, NULL);
/*!40000 ALTER TABLE `personnel` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_avances
CREATE TABLE IF NOT EXISTS `personnel_avances` (
  `personnel_id` bigint(20) NOT NULL,
  `avances` bigint(20) NOT NULL,
  UNIQUE KEY `UK_qk4waexb7basdcutbb6nj4way` (`avances`),
  KEY `FK45pcivi6cqjm0qy37ee1logo2` (`personnel_id`),
  CONSTRAINT `FK45pcivi6cqjm0qy37ee1logo2` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`),
  CONSTRAINT `FK5yf0y6xrcgnuvx9jas3yeh7gr` FOREIGN KEY (`avances`) REFERENCES `avances` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_avances : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_avances` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_avances` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_bulletin_paie
CREATE TABLE IF NOT EXISTS `personnel_bulletin_paie` (
  `personnel_id` bigint(20) NOT NULL,
  `bulletin_paie` bigint(20) NOT NULL,
  UNIQUE KEY `UK_di46po9ae05sv4rfmeb2lj66o` (`bulletin_paie`),
  KEY `FKpxs05w7xab5k0b60cs8g6a2l` (`personnel_id`),
  CONSTRAINT `FKcvhjy2ba4kjjobceknh5ao80m` FOREIGN KEY (`bulletin_paie`) REFERENCES `bulletin_paie` (`id`),
  CONSTRAINT `FKpxs05w7xab5k0b60cs8g6a2l` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_bulletin_paie : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_bulletin_paie` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_bulletin_paie` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_conge
CREATE TABLE IF NOT EXISTS `personnel_conge` (
  `personnel_id` bigint(20) NOT NULL,
  `conge` bigint(20) NOT NULL,
  UNIQUE KEY `UK_kukxos8wkec3cwglsj31okw3n` (`conge`),
  KEY `FKoetc9iuxi2w0264a31ifysofl` (`personnel_id`),
  CONSTRAINT `FK4ij8atf1dc2j8gtn1hwtbmk2r` FOREIGN KEY (`conge`) REFERENCES `conge` (`id`),
  CONSTRAINT `FKoetc9iuxi2w0264a31ifysofl` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_conge : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_conge` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_conge` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_contrats
CREATE TABLE IF NOT EXISTS `personnel_contrats` (
  `personnel_id` bigint(20) NOT NULL,
  `contrats` bigint(20) NOT NULL,
  UNIQUE KEY `UK_2mqybi596i4yk3ik21h5t1377` (`contrats`),
  KEY `FK45765citp1kmwgc4rk1maqiod` (`personnel_id`),
  CONSTRAINT `FK45765citp1kmwgc4rk1maqiod` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`),
  CONSTRAINT `FKsrgfe3287ia6fsq8fy14151tu` FOREIGN KEY (`contrats`) REFERENCES `contrat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_contrats : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_contrats` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_contrats` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_credits
CREATE TABLE IF NOT EXISTS `personnel_credits` (
  `personnel_id` bigint(20) NOT NULL,
  `credits` bigint(20) NOT NULL,
  UNIQUE KEY `UK_j5vl44slyuwne9o7q7p6706k` (`credits`),
  KEY `FKd6y4fqrw6nxfkumfarxqkr9yj` (`personnel_id`),
  CONSTRAINT `FK1iwrp06lrb54nsg6ooe27oxl0` FOREIGN KEY (`credits`) REFERENCES `credits` (`id`),
  CONSTRAINT `FKd6y4fqrw6nxfkumfarxqkr9yj` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_credits : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_credits` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_credits` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_details
CREATE TABLE IF NOT EXISTS `personnel_details` (
  `personnel_id` bigint(20) NOT NULL,
  `details` bigint(20) NOT NULL,
  UNIQUE KEY `UK_4pnh74unxgk30k784bo7rjalb` (`details`),
  KEY `FKoqjh4q80lm5ev4d3oqc4c6r3o` (`personnel_id`),
  CONSTRAINT `FKipyamxpvh4xv1teg7qe6crf0` FOREIGN KEY (`details`) REFERENCES `detail` (`id`),
  CONSTRAINT `FKoqjh4q80lm5ev4d3oqc4c6r3o` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_details : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_details` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_prets
CREATE TABLE IF NOT EXISTS `personnel_prets` (
  `personnel_id` bigint(20) NOT NULL,
  `prets` bigint(20) NOT NULL,
  UNIQUE KEY `UK_24pfwb7r73s89qsm7eak4brgx` (`prets`),
  KEY `FKgilyrssq9dj8w2cwd25eh90ke` (`personnel_id`),
  CONSTRAINT `FKgilyrssq9dj8w2cwd25eh90ke` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`),
  CONSTRAINT `FKhpskimqlkjarj5uqaacjey1lp` FOREIGN KEY (`prets`) REFERENCES `prets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_prets : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_prets` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_prets` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_primes_fixes
CREATE TABLE IF NOT EXISTS `personnel_primes_fixes` (
  `personnel_id` bigint(20) NOT NULL,
  `primes_fixes` bigint(20) NOT NULL,
  UNIQUE KEY `UK_37rjuj375yhgp2jthqgceep4i` (`primes_fixes`),
  KEY `FKrv8ls3198u32c2ijop4cuvkco` (`personnel_id`),
  CONSTRAINT `FKafrartm7l30f9jmvsf32s59j2` FOREIGN KEY (`primes_fixes`) REFERENCES `primes_fixes` (`id`),
  CONSTRAINT `FKrv8ls3198u32c2ijop4cuvkco` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_primes_fixes : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_primes_fixes` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_primes_fixes` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. personnel_primes_variables
CREATE TABLE IF NOT EXISTS `personnel_primes_variables` (
  `personnel_id` bigint(20) NOT NULL,
  `primes_variables` bigint(20) NOT NULL,
  UNIQUE KEY `UK_bdb73ju5ydce4dutnm5yb0bwd` (`primes_variables`),
  KEY `FKskqbnjo959cj9an7vfq5x3d31` (`personnel_id`),
  CONSTRAINT `FKkjgdef9v7v44pcegfjc01n1td` FOREIGN KEY (`primes_variables`) REFERENCES `primes_variables` (`id`),
  CONSTRAINT `FKskqbnjo959cj9an7vfq5x3d31` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.personnel_primes_variables : ~0 rows (environ)
/*!40000 ALTER TABLE `personnel_primes_variables` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_primes_variables` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. prets
CREATE TABLE IF NOT EXISTS `prets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `retenue_mensuelle` bigint(20) DEFAULT NULL,
  `valeur` bigint(20) DEFAULT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpci4cit7xoq2xqkm4kam855d5` (`personnel`),
  CONSTRAINT `FKpci4cit7xoq2xqkm4kam855d5` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.prets : ~0 rows (environ)
/*!40000 ALTER TABLE `prets` DISABLE KEYS */;
/*!40000 ALTER TABLE `prets` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. prevision
CREATE TABLE IF NOT EXISTS `prevision` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantite` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.prevision : ~0 rows (environ)
/*!40000 ALTER TABLE `prevision` DISABLE KEYS */;
/*!40000 ALTER TABLE `prevision` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. primes_fixes
CREATE TABLE IF NOT EXISTS `primes_fixes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `exoneree` bit(1) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `valeur` float NOT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8saq0t55rqhjeen2nirqmqtnw` (`personnel`),
  CONSTRAINT `FK8saq0t55rqhjeen2nirqmqtnw` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.primes_fixes : ~0 rows (environ)
/*!40000 ALTER TABLE `primes_fixes` DISABLE KEYS */;
/*!40000 ALTER TABLE `primes_fixes` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. primes_variables
CREATE TABLE IF NOT EXISTS `primes_variables` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `exoneree` bit(1) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `valeur` float NOT NULL,
  `personnel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5tsclfelhyqv0mjkhl0jc15jx` (`personnel`),
  CONSTRAINT `FK5tsclfelhyqv0mjkhl0jc15jx` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.primes_variables : ~0 rows (environ)
/*!40000 ALTER TABLE `primes_variables` DISABLE KEYS */;
/*!40000 ALTER TABLE `primes_variables` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. profession
CREATE TABLE IF NOT EXISTS `profession` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.profession : ~0 rows (environ)
/*!40000 ALTER TABLE `profession` DISABLE KEYS */;
/*!40000 ALTER TABLE `profession` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. profession_contrats
CREATE TABLE IF NOT EXISTS `profession_contrats` (
  `profession_id` bigint(20) NOT NULL,
  `contrats` bigint(20) NOT NULL,
  UNIQUE KEY `UK_7oqkdu3w6g34goaf99lhnsshv` (`contrats`),
  KEY `FK4w8i7eou4uvi5l2hefd0cl2y7` (`profession_id`),
  CONSTRAINT `FK3b01xcnkxduhabdj4fiuvce4c` FOREIGN KEY (`contrats`) REFERENCES `contrat` (`id`),
  CONSTRAINT `FK4w8i7eou4uvi5l2hefd0cl2y7` FOREIGN KEY (`profession_id`) REFERENCES `profession` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.profession_contrats : ~0 rows (environ)
/*!40000 ALTER TABLE `profession_contrats` DISABLE KEYS */;
/*!40000 ALTER TABLE `profession_contrats` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. rendez_vous
CREATE TABLE IF NOT EXISTS `rendez_vous` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.rendez_vous : ~0 rows (environ)
/*!40000 ALTER TABLE `rendez_vous` DISABLE KEYS */;
/*!40000 ALTER TABLE `rendez_vous` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. roles
CREATE TABLE IF NOT EXISTS `roles` (
  `nom_role` varchar(255) NOT NULL,
  PRIMARY KEY (`nom_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.roles : ~3 rows (environ)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`nom_role`) VALUES
	('ADMIN'),
	('APPRENANT'),
	('PERSONNEL'),
	('RH');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. session_formation
CREATE TABLE IF NOT EXISTS `session_formation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `delai_dossiers` varchar(255) DEFAULT NULL,
  `frais_inscription` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.session_formation : ~0 rows (environ)
/*!40000 ALTER TABLE `session_formation` DISABLE KEYS */;
INSERT INTO `session_formation` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `date_debut`, `date_fin`, `delai_dossiers`, `frais_inscription`) VALUES
	(1, '2019-07-16 08:26:37', '$2a$10$ztvHiQLmI2xJ.6NMhZLXtebEFWCZVjkST40p4xu35/Oxomq/a2BBi', b'0', NULL, '2019-06-30', '2019-07-30', '2 Mois apres lancement', 15000);
/*!40000 ALTER TABLE `session_formation` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. session_formation_formation_cours
CREATE TABLE IF NOT EXISTS `session_formation_formation_cours` (
  `session_formation_id` bigint(20) NOT NULL,
  `formation_cours` bigint(20) NOT NULL,
  UNIQUE KEY `UK_bvt0gbbl92v8beh8p1j42n1tu` (`formation_cours`),
  KEY `FKfxywrpna0u09lxwwrkjmxxy17` (`session_formation_id`),
  CONSTRAINT `FKfxywrpna0u09lxwwrkjmxxy17` FOREIGN KEY (`session_formation_id`) REFERENCES `session_formation` (`id`),
  CONSTRAINT `FKp8t60y81o57ujo9qsvlkemidr` FOREIGN KEY (`formation_cours`) REFERENCES `cours` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.session_formation_formation_cours : ~0 rows (environ)
/*!40000 ALTER TABLE `session_formation_formation_cours` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_formation_formation_cours` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. session_formation_inscriptions
CREATE TABLE IF NOT EXISTS `session_formation_inscriptions` (
  `session_formation_id` bigint(20) NOT NULL,
  `inscriptions` bigint(20) NOT NULL,
  PRIMARY KEY (`session_formation_id`,`inscriptions`),
  UNIQUE KEY `UK_nyne9u22wsfpokqgh50i8m0rx` (`inscriptions`),
  CONSTRAINT `FKi2as1em3yrkv7pnlwvu5xpwtk` FOREIGN KEY (`inscriptions`) REFERENCES `inscription` (`id`),
  CONSTRAINT `FKlh91v3pxkl2mf7qdu24d4fvkg` FOREIGN KEY (`session_formation_id`) REFERENCES `session_formation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.session_formation_inscriptions : ~0 rows (environ)
/*!40000 ALTER TABLE `session_formation_inscriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_formation_inscriptions` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. suivre
CREATE TABLE IF NOT EXISTS `suivre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `appreciation` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `id_chapitre` bigint(20) DEFAULT NULL,
  `id_inscription` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb0cqrr8jesl8nr37tl0m5gq89` (`id_chapitre`),
  KEY `FKpbu2p3xdh5l5q7u9xfy56wh7t` (`id_inscription`),
  CONSTRAINT `FKb0cqrr8jesl8nr37tl0m5gq89` FOREIGN KEY (`id_chapitre`) REFERENCES `chapitre` (`id`),
  CONSTRAINT `FKpbu2p3xdh5l5q7u9xfy56wh7t` FOREIGN KEY (`id_inscription`) REFERENCES `inscription` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.suivre : ~4 rows (environ)
/*!40000 ALTER TABLE `suivre` DISABLE KEYS */;
INSERT INTO `suivre` (`id`, `created_at`, `fingerprint`, `isdelete`, `updated_at`, `appreciation`, `date`, `id_chapitre`, `id_inscription`) VALUES
	(6, '2019-07-16 12:01:54', '$2a$10$gkhDsg7RqsHb1TcESfk/BOkNMjzWBSeLpgZQ0w0J4Q/ct9rPOg3Du', b'0', NULL, 'bonne', NULL, 1, 1),
	(7, '2019-07-16 12:02:03', '$2a$10$d5HKFpTEchWM6jW83JZcLuIL2O6LuxSWUPJscgBTYp0MsVV55DJ4y', b'0', NULL, 'mauvais', NULL, 2, 1),
	(8, '2019-07-16 12:02:12', '$2a$10$lSGy8Sx4lfHWkqfhoABX8.SLoDtmqvOtDspK9TY1QZUUFo1/IXPXS', b'0', NULL, 'null', NULL, 4, 2),
	(9, '2019-07-16 12:02:27', '$2a$10$LE7Cjb5JSeRLeA8qkxmWP.AQ6gC1XT64aEUfQXj3UEmKx2M6dEsKG', b'0', NULL, 'bonne', NULL, 4, 1),
	(10, '2019-07-16 12:02:58', '$2a$10$41T1gX4KWdwUR5vK9JAzDuFdqrUOhQJAKV37kMYaRlzhnH2Zr5zkS', b'0', NULL, 'bonne', NULL, 2, 2);
/*!40000 ALTER TABLE `suivre` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. type_conge
CREATE TABLE IF NOT EXISTS `type_conge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.type_conge : ~0 rows (environ)
/*!40000 ALTER TABLE `type_conge` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_conge` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. type_conge_conges
CREATE TABLE IF NOT EXISTS `type_conge_conges` (
  `type_conge_id` bigint(20) NOT NULL,
  `conges` bigint(20) NOT NULL,
  UNIQUE KEY `UK_qft3n13aml8l21mlqu9calbg6` (`conges`),
  KEY `FKhi8itp2p5ft2uhb2kpewnolq` (`type_conge_id`),
  CONSTRAINT `FKb44pt1h26ya7w3y7hyy9kmnma` FOREIGN KEY (`conges`) REFERENCES `conge` (`id`),
  CONSTRAINT `FKhi8itp2p5ft2uhb2kpewnolq` FOREIGN KEY (`type_conge_id`) REFERENCES `type_conge` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.type_conge_conges : ~0 rows (environ)
/*!40000 ALTER TABLE `type_conge_conges` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_conge_conges` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. type_contrat
CREATE TABLE IF NOT EXISTS `type_contrat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fingerprint` varchar(255) DEFAULT NULL,
  `isdelete` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `nom_type` varchar(255) DEFAULT NULL,
  `numero_type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.type_contrat : ~0 rows (environ)
/*!40000 ALTER TABLE `type_contrat` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_contrat` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. type_contrat_contrats
CREATE TABLE IF NOT EXISTS `type_contrat_contrats` (
  `type_contrat_id` bigint(20) NOT NULL,
  `contrats` bigint(20) NOT NULL,
  UNIQUE KEY `UK_9f5bte3xx4gawg69s0932p67n` (`contrats`),
  KEY `FKgbb9ced3l30wwtudwipv192b8` (`type_contrat_id`),
  CONSTRAINT `FK8ua5q4bojjmykjk2ig81y1omg` FOREIGN KEY (`contrats`) REFERENCES `contrat` (`id`),
  CONSTRAINT `FKgbb9ced3l30wwtudwipv192b8` FOREIGN KEY (`type_contrat_id`) REFERENCES `type_contrat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.type_contrat_contrats : ~0 rows (environ)
/*!40000 ALTER TABLE `type_contrat_contrats` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_contrat_contrats` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. users
CREATE TABLE IF NOT EXISTS `users` (
  `login` varchar(255) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.users : ~0 rows (environ)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`login`, `active`, `created_at`, `password`, `updated_at`, `username`) VALUES
	('blackstar', b'1', '2019-07-16 09:20:07', '$2a$10$teVCfKwHHBk8e18.CFX/ZenVHFnGNZD1u5bjfRstWZv1fGMju7vY6', NULL, 'blackstar'),
	('nelson', b'1', '2019-07-16 08:33:47', '$2a$10$meLZFCeoKrZ14Jh5Lwkcxuit0YSVj8vysSaFkBWByaUjVSxrFDxUy', NULL, 'Nelson');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Listage de la structure de la table live_manager_db. users_roles
CREATE TABLE IF NOT EXISTS `users_roles` (
  `login` varchar(255) NOT NULL,
  `id_role` varchar(255) NOT NULL,
  KEY `FK3avenccqsoqwrfur1hb8mpbrw` (`id_role`),
  KEY `FKc68nyrih92b7bgtq113ntftww` (`login`),
  CONSTRAINT `FK3avenccqsoqwrfur1hb8mpbrw` FOREIGN KEY (`id_role`) REFERENCES `roles` (`nom_role`),
  CONSTRAINT `FKc68nyrih92b7bgtq113ntftww` FOREIGN KEY (`login`) REFERENCES `users` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table live_manager_db.users_roles : ~8 rows (environ)
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` (`login`, `id_role`) VALUES
	('nelson', 'ADMIN'),
	('nelson', 'APPRENANT'),
	('nelson', 'PERSONNEL'),
	('nelson', 'RH'),
	('blackstar', 'ADMIN'),
	('blackstar', 'APPRENANT'),
	('blackstar', 'PERSONNEL'),
	('blackstar', 'RH');
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
