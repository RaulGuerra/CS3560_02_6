-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for posdb
CREATE DATABASE IF NOT EXISTS `posdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `posdb`;

-- Dumping structure for table posdb.food
CREATE TABLE IF NOT EXISTS `food` (
  `foodID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `calories` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`foodID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table posdb.food: ~6 rows (approximately)
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` (`foodID`, `name`, `price`, `calories`, `description`) VALUES
	(1, 'Bacon Burger', 10.95, 985, 'Brioche bun, mayo, tomato, lettuce, beef, bacon, cheddar cheese'),
	(2, 'Chicken and Waffles', 10.5, 1050, 'Belgian waffle, chicken tenders, syrup'),
	(3, 'Ceasar Salad', 9.95, 650, 'Romain lettuce, ceasar dressing, croutons, parmesan cheese, grilled chicken'),
	(4, 'Pork Ramen', 15.65, 1250, 'Braised pork, soy broth, seaweed, chilli paste, seasoned egg, scallions'),
	(5, 'French Toast', 11.55, 950, 'Brioche bun, eggwash, milk, fresh fruit, maple syrup'),
	(6, 'Chicken Cordon Bleu', 16.95, 1500, 'Chicken breast, ham, mozzarella, panko breadcrumbs, wild rice');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;

-- Dumping structure for table posdb.order
CREATE TABLE IF NOT EXISTS `order` (
  `orderID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `foodID` int(11) unsigned NOT NULL,
  `checkID` int(11) unsigned NOT NULL,
  `modification` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`orderID`) USING BTREE,
  KEY `FK_order_food` (`foodID`) USING BTREE,
  KEY `FK_order_receipt` (`checkID`) USING BTREE,
  CONSTRAINT `FK_order_food` FOREIGN KEY (`foodID`) REFERENCES `food` (`foodID`) ON UPDATE NO ACTION,
  CONSTRAINT `FK_order_receipt` FOREIGN KEY (`checkID`) REFERENCES `receipt` (`checkID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table posdb.order: ~6 rows (approximately)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`orderID`, `foodID`, `checkID`, `modification`) VALUES
	(1, 1, 1, NULL),
	(2, 2, 1, NULL),
	(3, 3, 1, NULL),
	(4, 1, 1, NULL),
	(7, 1, 4, NULL),
	(8, 4, 4, NULL);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Dumping structure for view posdb.orderprice
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `orderprice` (
	`orderID` INT(11) UNSIGNED NOT NULL,
	`foodID` INT(11) UNSIGNED NOT NULL,
	`checkID` INT(11) UNSIGNED NOT NULL,
	`name` VARCHAR(30) NULL COLLATE 'latin1_swedish_ci',
	`modification` VARCHAR(500) NULL COLLATE 'latin1_swedish_ci',
	`price` DOUBLE(19,2) NULL
) ENGINE=MyISAM;

-- Dumping structure for view posdb.orderquantity
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `orderquantity` (
	`checkID` INT(11) UNSIGNED NOT NULL,
	`foodID` INT(11) UNSIGNED NOT NULL,
	`Quantity` BIGINT(21) NOT NULL,
	`name` VARCHAR(30) NULL COLLATE 'latin1_swedish_ci',
	`Amount` DOUBLE(19,2) NULL
) ENGINE=MyISAM;

-- Dumping structure for table posdb.receipt
CREATE TABLE IF NOT EXISTS `receipt` (
  `checkID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tableNumber` int(11) unsigned NOT NULL DEFAULT 0,
  `paid` bit(1) NOT NULL DEFAULT b'0',
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`checkID`),
  KEY `FK_receipt_table` (`tableNumber`),
  CONSTRAINT `FK_receipt_table` FOREIGN KEY (`tableNumber`) REFERENCES `table` (`tableNumber`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

-- Dumping data for table posdb.receipt: ~24 rows (approximately)
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` (`checkID`, `tableNumber`, `paid`, `date`) VALUES
	(1, 1, b'1', '2025-05-08 22:56:05'),
	(4, 4, b'1', '2022-04-22 18:38:44'),
	(5, 5, b'0', '2022-04-22 00:00:00'),
	(6, 6, b'0', '2022-04-22 00:00:00'),
	(8, 1, b'0', '2023-04-23 19:28:48'),
	(24, 1, b'0', '2022-04-23 19:28:48'),
	(25, 1, b'0', '2022-04-23 19:28:48'),
	(26, 3, b'0', '2023-04-23 19:28:48'),
	(28, 1, b'0', '2022-04-23 18:54:48'),
	(29, 3, b'0', '2022-04-24 01:28:48'),
	(30, 3, b'0', '2022-04-23 19:28:54'),
	(32, 3, b'0', '2022-04-23 19:28:41'),
	(34, 1, b'0', '2022-04-22 15:28:48'),
	(35, 1, b'0', '2022-04-23 19:37:48'),
	(36, 1, b'0', '2022-04-24 03:22:48'),
	(37, 1, b'1', '2022-05-08 22:43:17'),
	(41, 1, b'0', '2023-04-23 19:28:48'),
	(49, 2, b'1', '2022-05-09 15:19:42'),
	(50, 2, b'0', '2022-05-09 15:21:02'),
	(51, 3, b'0', '2022-05-09 15:28:46'),
	(52, 3, b'0', '2022-05-09 15:29:08'),
	(53, 3, b'0', '2022-05-09 15:29:22'),
	(54, 7, b'1', '2022-05-09 15:32:13'),
	(55, 7, b'0', '2022-05-09 15:32:48');
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;

-- Dumping structure for view posdb.receiptsearch
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `receiptsearch` (
	`checkID` INT(11) UNSIGNED NOT NULL,
	`tableNumber` INT(11) UNSIGNED NOT NULL,
	`total` DOUBLE(19,2) NULL,
	`date` TIMESTAMP NOT NULL,
	`paid` BIT(1) NOT NULL
) ENGINE=MyISAM;

-- Dumping structure for view posdb.receipttotal
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `receipttotal` (
	`checkID` INT(11) UNSIGNED NOT NULL,
	`Total` DOUBLE(19,2) NULL
) ENGINE=MyISAM;

-- Dumping structure for table posdb.table
CREATE TABLE IF NOT EXISTS `table` (
  `tableNumber` int(11) unsigned NOT NULL,
  `seats` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `occupied` bit(1) NOT NULL DEFAULT b'0',
  `xCoord` int(11) NOT NULL,
  `yCoord` int(11) NOT NULL,
  PRIMARY KEY (`tableNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table posdb.table: ~7 rows (approximately)
/*!40000 ALTER TABLE `table` DISABLE KEYS */;
INSERT INTO `table` (`tableNumber`, `seats`, `occupied`, `xCoord`, `yCoord`) VALUES
	(1, 6, b'0', 100, 120),
	(2, 6, b'1', 50, 60),
	(3, 6, b'1', 50, 60),
	(4, 6, b'0', 50, 60),
	(5, 5, b'0', 30, 40),
	(6, 2, b'0', 120, 130),
	(7, 5, b'1', 20, 20);
/*!40000 ALTER TABLE `table` ENABLE KEYS */;

-- Dumping structure for view posdb.orderprice
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `orderprice`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `orderprice` AS SELECT orderID, food.foodID, checkID, name, modification, ROUND(price, 2) AS price FROM food, `order` WHERE Food.foodID = `Order`.foodID ;

-- Dumping structure for view posdb.orderquantity
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `orderquantity`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `orderquantity` AS SELECT orderprice.checkID, foodID, COUNT(*) AS Quantity, name,
ROUND(COUNT(*)*price, 2) AS Amount
FROM orderprice GROUP BY foodID, checkID ;

-- Dumping structure for view posdb.receiptsearch
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `receiptsearch`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `receiptsearch` AS SELECT Receipt.checkID, tableNumber, total, date, paid
FROM Receipt, ReceiptTotal
WHERE Receipt.checkID = ReceiptTotal.checkID ;

-- Dumping structure for view posdb.receipttotal
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `receipttotal`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `receipttotal` AS SELECT Orderquantity.checkID, SUM(Amount) AS Total
FROM OrderQuantity
GROUP BY checkID
UNION ALL
SELECT Receipt.checkID, 0 AS TOTAL
FROM Receipt
WHERE NOT EXISTS (SELECT 1 FROM Orderquantity WHERE Receipt.checkID = OrderQuantity.checkID) ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
