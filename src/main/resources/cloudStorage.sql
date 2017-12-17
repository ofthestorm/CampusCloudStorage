CREATE SCHEMA `cloud_storage` ;

use cloud_storage;

CREATE TABLE `cloud_storage`.`user` (
  `u_id` INT NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`u_id`));

CREATE TABLE `cloud_storage`.`file` (
  `f_id` INT NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `path` VARCHAR(128) NOT NULL,
  `size` INT NOT NULL,
  PRIMARY KEY (`f_id`));

CREATE TABLE `cloud_storage`.`dir` (
  `d_id` INT NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `path` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`d_id`));