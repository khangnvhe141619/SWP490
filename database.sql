--CREATE DATABASE SWP490_CarAutionSystem;
use SWP490_CarAutionSystem;
CREATE TABLE `User`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `UserName` VARCHAR(255) NULL,
    `Password` VARCHAR(255) NOT NULL,
    `LName` VARCHAR(255) NOT NULL,
    `FName` VARCHAR(255) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    `Phone` INT NOT NULL,
    `RoleId` INT NOT NULL
);

CREATE TABLE `Set_Bids`(
    `Session_id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Bidder_id` INT UNSIGNED NOT NULL,
    `Bids` DOUBLE(8, 2) NOT NULL
);

CREATE TABLE `Role`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `userId` INT NOT NULL,
    `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `Session`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `date` DATE NOT NULL,
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
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Message` VARCHAR(255) NOT NULL
);

CREATE TABLE `Car`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `Name_car` VARCHAR(255) NOT NULL,
    `Picture` VARCHAR(255) NOT NULL,
    `Price` DOUBLE(8, 2) NOT NULL,
    `model` VARCHAR(255) NOT NULL,
    `yearOfmake` DATE NOT NULL,
    `color` INT NOT NULL,
    `cartype_id` INT UNSIGNED NOT NULL,
    `category_id` INT UNSIGNED NOT NULL,
    `session_id` INT UNSIGNED NOT NULL,
    `Seller_id` INT UNSIGNED NOT NULL
);

CREATE TABLE `cartypes`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `SeatCapacity` INT NOT NULL,
    `Name` VARCHAR(255) NOT NULL
);

CREATE TABLE `Category_car`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `name_Brand` INT NOT NULL
);

CREATE TABLE `Session_Participants`(
    `session_id` INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
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
