--CREATE DATABASE SWP490_CarAutionSystem;

CREATE TABLE IF NOT EXISTS User(
    ID INT auto_increment primary key,
    UserName VARCHAR(255) NULL,
    Password VARCHAR(255) NOT NULL,
    LName VARCHAR(255) NOT NULL,
    FName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Phone INT NOT NULL,
    RoleId INT NOT NULL,
    CreatedDate DATETIME NOT NULL
);

CREATE TABLE IF NOT exists Set_Bids(
    Session_id INT auto_increment PRIMARY KEY,
    Bidder_id INT,
    Bids int NOT NULL
);

CREATE table IF NOT exists Role(
    ID INT auto_increment PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT exists Session(
    ID INT auto_increment PRIMARY KEY,
    Date DATE NOT NULL,
    Start_time TIME NOT NULL,
    End_Time TIME NOT NULL,
    Winner_Id INT NOT NULL,
    Invoice VARCHAR(255) NOT NULL
);

CREATE table IF NOT exists Notification(
    ID INT auto_increment PRIMARY KEY,
    Message VARCHAR(255) NOT NULL
);

CREATE table IF NOT exists User_Notification(
    User_id INT NOT NULL ,
    Notification_id INT NOT NULL
);

CREATE table IF NOT exists Cartypes(
    ID INT auto_increment PRIMARY KEY,
    SeatCapacity INT NOT NULL,
    Name VARCHAR(255) NOT NULL
);

CREATE table IF NOT exists Category_car(
    ID INT auto_increment PRIMARY KEY,
    Name_brand INT NOT NULL
);

CREATE table IF NOT exists Car(
    ID INT UNSIGNED auto_increment PRIMARY KEY,
    Name_car VARCHAR(255) NOT NULL,
    Picture VARCHAR(255) NOT NULL,
    Price DOUBLE(8, 2) NOT NULL,
    Model VARCHAR(255) NOT NULL,
    YearOfMake DATE NOT NULL,
    Color INT NOT NULL,
    Cartype_id INT,
    Category_id INT,
    Session_id INT
);

CREATE table IF NOT exists Session_Participants(
    Session_id INT UNSIGNED NOT NULL auto_increment PRIMARY KEY,
    Bidder_id INT NOT NULL
);

ALTER table `Set_Bids` 
ADD CONSTRAINT `set_bids_bidder__id_foreign` FOREIGN KEY(`Bidder__id`) REFERENCES `User`(`ID`);

ALTER table `User_Notification` 
ADD CONSTRAINT `user_notification_notification_id_foreign` FOREIGN KEY(`Notification_id`) REFERENCES `Notification`(`ID`);

ALTER table `Car` 
ADD CONSTRAINT `car_session_id_foreign` FOREIGN KEY(`Session_id`) REFERENCES `Session`(`ID`);

ALTER TABLE`Car` 
ADD CONSTRAINT `car_category_id_foreign` FOREIGN KEY(`Category_id`) REFERENCES `Category_car`(`ID`);
   
ALTER TABLE `Car` 
ADD CONSTRAINT `car_cartype_id_foreign` FOREIGN KEY(`Cartype_id`) REFERENCES `Cartypes`(`ID`);