-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema vineappdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vineappdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vineappdb` DEFAULT CHARACTER SET utf8 ;
USE `vineappdb` ;

-- -----------------------------------------------------
-- Table `vineappdb`.`bolest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`bolest` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  `date` DATETIME NOT NULL,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `approved` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`fenozafa_razvoja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`fenozafa_razvoja` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `time_of_usage` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATETIME NOT NULL,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`bolest_has_faza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`bolest_has_faza` (
  `BOLEST_id` INT(11) NOT NULL,
  `FENOZAFA_RAZVOJA_id` INT(11) NOT NULL,
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL DEFAULT NULL,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `approved` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_BOLEST_has_FENOZAFA_RAZVOJA_BOLEST1` (`BOLEST_id` ASC),
  INDEX `fk_BOLEST_has_FENOZAFA_RAZVOJA_FENOZAFA_RAZVOJA1` (`FENOZAFA_RAZVOJA_id` ASC),
  CONSTRAINT `fk_BOLEST_has_FENOZAFA_RAZVOJA_BOLEST1`
    FOREIGN KEY (`BOLEST_id`)
    REFERENCES `vineappdb`.`bolest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOLEST_has_FENOZAFA_RAZVOJA_FENOZAFA_RAZVOJA1`
    FOREIGN KEY (`FENOZAFA_RAZVOJA_id`)
    REFERENCES `vineappdb`.`fenozafa_razvoja` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`user` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `SURNAME` VARCHAR(100) NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(100) NOT NULL,
  `PASSWORD` VARCHAR(250) NOT NULL,
  `AKTIVAN` INT(11) NULL DEFAULT NULL,
  `LAST_LOGIN` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`refresh_token` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(250) NULL DEFAULT NULL,
  `expiryDate` DATETIME NULL DEFAULT NULL,
  `USER_ID` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_REFRESH_TOKEN_USER1` (`USER_ID` ASC),
  CONSTRAINT `fk_REFRESH_TOKEN_USER1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `vineappdb`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`role` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`tip_zastitnog_sredstva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`tip_zastitnog_sredstva` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `date` DATETIME NOT NULL,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`zastitno_sredstvo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`zastitno_sredstvo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `TIP_ZASTITNOG_SREDSTVA_id` INT(11) NULL DEFAULT NULL,
  `Description` VARCHAR(200) NULL DEFAULT NULL,
  `Composition` VARCHAR(45) NULL DEFAULT NULL,
  `medium_group` VARCHAR(45) NULL DEFAULT NULL,
  `Formulation` VARCHAR(45) NULL DEFAULT NULL,
  `Type_of_action` VARCHAR(45) NULL DEFAULT NULL,
  `medium_usage` VARCHAR(45) NULL DEFAULT NULL,
  `Concentration` DECIMAL(10,0) NULL DEFAULT NULL,
  `Dosage_on_100` DECIMAL(10,0) NULL DEFAULT NULL,
  `approved` INT(11) NOT NULL,
  `waiting` INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`),
  INDEX `fk_ZASTITNO_SREDSTVO_TIP_ZASTITNOG_SREDSTVA1` (`TIP_ZASTITNOG_SREDSTVA_id` ASC),
  CONSTRAINT `fk_ZASTITNO_SREDSTVO_TIP_ZASTITNOG_SREDSTVA1`
    FOREIGN KEY (`TIP_ZASTITNOG_SREDSTVA_id`)
    REFERENCES `vineappdb`.`tip_zastitnog_sredstva` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`sredstvo_has_bolest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`sredstvo_has_bolest` (
  `ZASTITNO_SREDSTVO_id` INT(11) NOT NULL,
  `BOLEST_id` INT(11) NOT NULL,
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `updated_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
  `approved` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ZASTITNO_SREDSTVO_has_BOLEST_ZASTITNO_SREDSTVO1` (`ZASTITNO_SREDSTVO_id` ASC),
  INDEX `fk_ZASTITNO_SREDSTVO_has_BOLEST_BOLEST1` (`BOLEST_id` ASC),
  CONSTRAINT `fk_ZASTITNO_SREDSTVO_has_BOLEST_BOLEST1`
    FOREIGN KEY (`BOLEST_id`)
    REFERENCES `vineappdb`.`bolest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ZASTITNO_SREDSTVO_has_BOLEST_ZASTITNO_SREDSTVO1`
    FOREIGN KEY (`ZASTITNO_SREDSTVO_id`)
    REFERENCES `vineappdb`.`zastitno_sredstvo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`user_role` (
  `USER_ID` INT(11) NOT NULL,
  `ROLE_ID` INT(11) NOT NULL,
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DATETIME` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_USER_has_ROLE_USER1` (`USER_ID` ASC),
  INDEX `fk_USER_has_ROLE_ROLE1` (`ROLE_ID` ASC),
  CONSTRAINT `fk_USER_has_ROLE_ROLE1`
    FOREIGN KEY (`ROLE_ID`)
    REFERENCES `vineappdb`.`role` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_has_ROLE_USER1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `vineappdb`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vineappdb`.`Spricanje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`Spricanje` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `description` VARCHAR(45) NULL,
  `user_ID` INT(11) NULL,
  `water` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Spricanje_user1_idx` (`user_ID` ASC),
  CONSTRAINT `fk_Spricanje_user1`
    FOREIGN KEY (`user_ID`)
    REFERENCES `vineappdb`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vineappdb`.`Spricanje_has_zastitno_sredstvo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`Spricanje_has_zastitno_sredstvo` (
  `Spricanje_id` INT NOT NULL,
  `zastitno_sredstvo_id` INT(11) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `dosage` DECIMAL NULL,
  INDEX `fk_Spricanje_has_zastitno_sredstvo_zastitno_sredstvo1_idx` (`zastitno_sredstvo_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Spricanje_has_zastitno_sredstvo_zastitno_sredstvo1`
    FOREIGN KEY (`zastitno_sredstvo_id`)
    REFERENCES `vineappdb`.`zastitno_sredstvo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vineappdb`.`Vinograd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`Vinograd` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `adress` VARCHAR(45) NOT NULL,
  `opis` VARCHAR(45) NULL,
  `user_ID` INT(11) NOT NULL,
  `date` DATETIME NULL,
  `updated_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`ID`),
  INDEX `fk_Vinograd_user_idx` (`user_ID` ASC),
  CONSTRAINT `fk_Vinograd_user`
    FOREIGN KEY (`user_ID`)
    REFERENCES `vineappdb`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vineappdb`.`Vinograd_has_VinovaLoza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`Vinograd_has_VinovaLoza` (
  `Vinograd_ID` INT NOT NULL,
  `VinovaLoza_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vineappdb`.`VinovaLoza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vineappdb`.`VinovaLoza` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `opis` VARCHAR(5000) NULL,
  `date` DATETIME NOT NULL,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
