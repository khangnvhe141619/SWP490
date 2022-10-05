CREATE DATABASE SWP490_CarAutionSystem;
use SWP490_CarAutionSystem;
CREATE TABLE `User`(
    `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `UserName` VARCHAR(255) NULL,
    `Password` VARCHAR(255) NOT NULL,
    `FName` VARCHAR(255) NOT NULL,
    `LName` VARCHAR(255) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    `Phone` INT NOT NULL,
    `RoleId` INT NOT NULL
);

CREATE TABLE `Set_Bids`(
    `Session_id` INT UNSIGNED primary key,
    `Bidder_id` INT UNSIGNED NOT NULL,
    `Bids` DOUBLE(8, 2) NOT NULL
);

CREATE TABLE `Role`(
    `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Name` VARCHAR(255) NOT NULL
);

CREATE TABLE `Session`(
    `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Date` DATE NOT NULL,
    `Start_time` TIME NOT NULL,
    `End_Time` TIME NOT NULL,
    `Winner_Id` INT NOT NULL,
    `Invoice` VARCHAR(255) NOT NULL
);

CREATE TABLE `User_Notification`(
    `User_id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Notification_id` INT UNSIGNED NOT NULL
);

CREATE TABLE `Notification`(
    `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Message` VARCHAR(255) NOT NULL
);

CREATE TABLE `Car`(
    `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Name_car` VARCHAR(255) NOT NULL,
    `Picture` VARCHAR(255) NOT NULL,
    `Price` DOUBLE(8, 2) NOT NULL,
    `Model` VARCHAR(255) NOT NULL,
    `YearOfmake` INT NOT NULL,
    `Color` VARCHAR(255) NOT NULL,
    `Cartype_id` INT UNSIGNED NOT NULL,
    `Category_id` INT UNSIGNED NOT NULL,
    `Session_id` INT UNSIGNED NOT NULL,
    `Seller_id` INT UNSIGNED NOT NULL
);

CREATE TABLE `Cartypes`(
    `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `SeatCapacity` INT NOT NULL,
    `Name` VARCHAR(255) NOT NULL
);

CREATE TABLE `Category_car`(
    `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Name_Brand` VARCHAR(255) NOT NULL
);

CREATE TABLE `Session_Participants`(
    `Session_id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Bidder_id` INT NOT NULL
);

ALTER TABLE
    `Set_Bids` ADD CONSTRAINT `set_bids_bidder_id_foreign` FOREIGN KEY(`Bidder_id`) REFERENCES `User`(`id`);
ALTER TABLE
    `Car` ADD CONSTRAINT `car_seller_id_foreign` FOREIGN KEY(`Seller_id`) REFERENCES `User`(`id`);
ALTER TABLE
    `User_Notification` ADD CONSTRAINT `user_notification_notification_id_foreign` FOREIGN KEY(`Notification_id`) REFERENCES `Notification`(`id`);
ALTER TABLE
    `Car` ADD CONSTRAINT `car_session_id_foreign` FOREIGN KEY(`session_id`) REFERENCES `Session`(`id`);
ALTER TABLE
    `Car` ADD CONSTRAINT `car_category_id_foreign` FOREIGN KEY(`category_id`) REFERENCES `Category_car`(`id`);
ALTER TABLE
    `Car` ADD CONSTRAINT `car_cartype_id_foreign` FOREIGN KEY(`cartype_id`) REFERENCES `cartypes`(`id`);
    

INSERT INTO `swp490_carautionsystem`.`user` ( `UserName`, `Password`, `LName`, `FName`, `Email`, `Phone`, `RoleId`) VALUES ( 'admin', '123', 'Khang', 'Nguyen', 'khang@gmail.com', '0123121', '1');
INSERT INTO `swp490_carautionsystem`.`user` ( `UserName`, `Password`, `LName`, `FName`, `Email`, `Phone`, `RoleId`) VALUES ( 'mra', '123', 'sy', 'nguyen', 'synguyen0002@gmail.com', '01234921', '2');

INSERT INTO `swp490_carautionsystem`.`role` ( `Name`) VALUES ('Admin');
INSERT INTO `swp490_carautionsystem`.`role` ( `Name`) VALUES ('User');

INSERT INTO `swp490_carautionsystem`.`cartypes` (`SeatCapacity`, `Name`) VALUES ( '7', 'SUV');
INSERT INTO `swp490_carautionsystem`.`cartypes` (`SeatCapacity`, `Name`) VALUES ( '4', 'SEDAN');
INSERT INTO `swp490_carautionsystem`.`category_car` (`Name_Brand`) VALUES ('Vinfast');
INSERT INTO `swp490_carautionsystem`.`category_car` (`Name_Brand`) VALUES ('Audi');
INSERT INTO `swp490_carautionsystem`.`session` (`Date`, `Start_time`, `End_Time`, `Winner_Id`, `Invoice`) VALUES ('2022-01-02', '0', '5', '1', '1000');
INSERT INTO `swp490_carautionsystem`.`set_bids` (`Session_id`, `Bidder_id`, `Bids`) VALUES ('1', '1', '1000');

INSERT INTO `swp490_carautionsystem`.`car` (`Name_car`,`Picture`, `Price`, `model`, `yearOfmake`, `color`, `cartype_id`, `category_id`, `session_id`, `Seller_id`) VALUES ('Vinfast lux A 2.0','aaa', '1000', '2020', '2020', 'black', '1', '1', '1', '1');
