-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema booksdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `booksdb` ;

-- -----------------------------------------------------
-- Schema booksdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `booksdb` DEFAULT CHARACTER SET utf8 ;
USE `booksdb` ;

-- -----------------------------------------------------
-- Table `book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `book` ;

CREATE TABLE IF NOT EXISTS `book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `release_date` DATETIME NULL,
  `price` DECIMAL NULL,
  `date_started` DATETIME NULL,
  `date_finished` DATETIME NULL,
  `finished` TINYINT NULL,
  `number_of_pages` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `author` ;

CREATE TABLE IF NOT EXISTS `author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `book_author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `book_author` ;

CREATE TABLE IF NOT EXISTS `book_author` (
  `book_id` INT NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`book_id`, `author_id`),
  INDEX `fk_book_has_author_author1_idx` (`author_id` ASC) VISIBLE,
  INDEX `fk_book_has_author_book_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_has_author_book`
    FOREIGN KEY (`book_id`)
    REFERENCES `book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_has_author_author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `author` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS reader@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'reader'@'localhost' IDENTIFIED BY 'reader';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'reader'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `book`
-- -----------------------------------------------------
START TRANSACTION;
USE `booksdb`;
INSERT INTO `book` (`id`, `title`, `release_date`, `price`, `date_started`, `date_finished`, `finished`, `number_of_pages`) VALUES (1, 'Empire of the Vampire', '2021:09:07', 22.00, '2021:09:07', '2021:09:10', 1, 768);
INSERT INTO `book` (`id`, `title`, `release_date`, `price`, `date_started`, `date_finished`, `finished`, `number_of_pages`) VALUES (2, 'Empire of the Damned', '2024:02:29', 23.00, '2024:02:29', '2024:03:03', 1, 736);
INSERT INTO `book` (`id`, `title`, `release_date`, `price`, `date_started`, `date_finished`, `finished`, `number_of_pages`) VALUES (3, 'Aurora Rising', '2019:05:05', 20.00, '2022:03:04', '2022:03:07', 1, 473);
INSERT INTO `book` (`id`, `title`, `release_date`, `price`, `date_started`, `date_finished`, `finished`, `number_of_pages`) VALUES (4, 'Aurora Burning', '2020:04:28', 20.00, '2022:03:07', '2022:03:10', 1, 512);
INSERT INTO `book` (`id`, `title`, `release_date`, `price`, `date_started`, `date_finished`, `finished`, `number_of_pages`) VALUES (5, 'Aurora\'s End', '2021:11:09', 25.00, '2022:03:10', '2022:03:14', 1, 493);
INSERT INTO `book` (`id`, `title`, `release_date`, `price`, `date_started`, `date_finished`, `finished`, `number_of_pages`) VALUES (6, 'Into The Wild', '1996:01:13', 10.00, '2018:01:10', '2018:01:12', 1, 240);
INSERT INTO `book` (`id`, `title`, `release_date`, `price`, `date_started`, `date_finished`, `finished`, `number_of_pages`) VALUES (7, 'Black Site', '2012:01:31', 10.00, '2016:02:22', '2016:02:24', 1, 528);
INSERT INTO `book` (`id`, `title`, `release_date`, `price`, `date_started`, `date_finished`, `finished`, `number_of_pages`) VALUES (8, 'Tier One Wild', '2012:10:16', 10.00, '2016:02:24', '2016:02:26', 1, 528);

COMMIT;


-- -----------------------------------------------------
-- Data for table `author`
-- -----------------------------------------------------
START TRANSACTION;
USE `booksdb`;
INSERT INTO `author` (`id`, `first_name`, `last_name`) VALUES (1, 'Jay', 'Kristoff');
INSERT INTO `author` (`id`, `first_name`, `last_name`) VALUES (2, 'Amie', 'Kaufman');
INSERT INTO `author` (`id`, `first_name`, `last_name`) VALUES (3, 'Dalton', 'Fury');
INSERT INTO `author` (`id`, `first_name`, `last_name`) VALUES (4, 'John', 'Krakauer');

COMMIT;


-- -----------------------------------------------------
-- Data for table `book_author`
-- -----------------------------------------------------
START TRANSACTION;
USE `booksdb`;
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (1, 1);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (2, 1);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (3, 1);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (4, 1);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (5, 1);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (3, 2);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (4, 2);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (5, 2);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (6, 4);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (7, 3);
INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (8, 3);

COMMIT;

