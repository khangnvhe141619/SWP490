CREATE DATABASE  IF NOT EXISTS `swp490_cab` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `swp490_cab`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: swp490_cab
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brandName` varchar(255) NOT NULL,
  `imgPath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'VINFAST','img'),(2,'MAZDA','img'),(3,'TOYOTA','img'),(4,'MERCEDES-BENZ','img'),(5,'LEXUS','img'),(6,'FORD','img'),(7,'HYUNDAI','img'),(8,'PORSCHE','img'),(9,'HONDA','img'),(10,'BENTLEY','img'),(11,'BMW','img'),(12,'AUDI','img'),(13,'BUGATTI','img'),(14,'FERRARI','img'),(15,'LAMBORGHINI','img'),(16,'ROLLS-ROYCE','img');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carName` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  `upBoundPrice` int NOT NULL,
  `downBoundPrice` int NOT NULL,
  `modelId` int NOT NULL,
  `status` int NOT NULL,
  `createdBy` int NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `car_modelid_foreign` (`modelId`),
  KEY `car_createby_foreign` (`createdBy`),
  CONSTRAINT `car_createby_foreign` FOREIGN KEY (`createdBy`) REFERENCES `user` (`id`),
  CONSTRAINT `car_modelid_foreign` FOREIGN KEY (`modelId`) REFERENCES `model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'VinFast Lux A 2.0','Exterior design : The current VinFast LUX A2.0 model has the design of a high-end sedan, penned by the famous Italian design studio Pininfarina. The overall car is quite luxurious and elegant thanks to the long embossed ribbed design lines in the bonnet and eye-catching steering wheel, the most prominent is the grille shape and characteristic LED lighting clusters.VinFast LUX A2.0 offers Vietnamese users 6 exterior colors including: Red / Blue / Silver / Black / Gray / White. Interior design and comfort: The interior space of the car is spacious, the leather material and glossy metal details make the cabin more luxurious. The 10.5-inch touch screen integrates Android Auto and Apple CarPlay connectivity systems, allowing you to connect to your phone to control features such as receiving / hanging up calls, accessing contacts, playing good music. locate.',850,650,6,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56'),(2,'Mercedes-Benz E 300 AMG','Exterior design: The exterior design of the E300 AMG is in the style of the \"3-pointed star family\" with a luxurious and elegant look that is no less sporty and powerful. The front grille has a \"star field\" design and in the middle is a typical logo symbol that highlights the front of the car. There are quite a few worthwhile upgrades. Glossy black details from the Night Package such as door borders, air intakes, front bumper, rearview mirror. The 19-inch dual 5-spoke wheels and the glass are also covered in black to create a uniform whole. The Night Package has made the car more masculine and attractive. Interior design and comfort: Similar to the exterior, the interior of the Mercedes-Benz E300 AMG also exudes luxury. The car uses a newly designed D-cut steering wheel with aluminum alloy cladding, integrated with 2 touch control buttons on the steering wheel (Touch Control Buttons), helping the driver control the car\'s features Behind the steering wheel is a digital dashboard connected to the entertainment screen, both screens are 12.3 inches in size, \'Metal weave\' interior cladding. In addition, the Mercedes E300 AMG 2022 also has an automatic throttle feature (Cruise Control), speed limit (Speed ​​Limit) which is now also integrated on the steering wheel, more user-friendly.',2000,2500,1,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56'),(3,'BMW 320i Sport Line Plus ','Exterior design: The exterior of the BMW 3 Series 2022 has been completely upgraded to bring a dynamic and sporty appearance. The grille of the BMW 3 Series 2022 is still shaped like a typical \"kidney\" and has a solid chrome rim with automatic opening/closing feature. The front LED light cluster is beautifully and sophisticatedly designed, while the rear of the car is a slender 3D taillight cluster, increasing the neatness. The overall look of the car is towards a sporty style, although in the first two versions, the Sport Line equipment package is a bit luxurious. Meanwhile, the M-Sport exterior package for the most advanced version offers a more aggressive appearance with different details including front bumper, alloy wheels and rear bumper. The interior and exterior rearview mirrors of the car are anti-reflective. auto-dimming, power-folding & adjustable outside rearview mirror. In addition, the front headlights use LED technology with functions of automatic expansion and adjustment of phase / core.. Interior design and comfort: The interior of the BMW 3 Series 2022 is luxurious but still oriented. much more to a stronger style with sporty front seats that hug the back of the occupants. Besides, the interior of the car, in addition to leather, also has some aluminum-clad details. In which, the first two versions use Mesheffect aluminum and the M-Sport version uses more advanced Tetragon aluminum. The interior of the BMW 3 Series 2022 has the appearance of the BMW Live Cockpit Plus system, but the notable points in 2 In the high-end version, the dashboard behind the steering wheel is fully digital with sizes up to 12.3 inches, while the standard Sport Line version is just analog and 5.7-inch LCD. On the M Sport version, the M equipment package also comes with an M sports steering wheel with the M Performance logo. Not only that, the seats in the M Sport version are also covered with Vemasca leather, which is more advanced than Sensatec leather on the two lower versions.',1800,1300,37,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56'),(4,'Toyota Land Cruiser Prado','Exterior design: The latest Toyota Prado is possessing a massive appearance with a more aggressive and sporty style. Seen from the front, the grille with 5 large bars, surrounded by thick chrome borders brings a strong, sturdy but no less luxurious feeling to the car. Next to it are two connected LED light clusters. attached to the radiator grille. LED lights use modern projector technology for strong lighting performance combined with daytime LED strips. On the 2022 version, the Land Cruiser Prado is also added with a waiting light system. The waiting light system will illuminate the road ahead for the owner to enter the house and will automatically turn off after 30 seconds. The side of the car is also equipped with additional lights on the door steps up and down. The rear of the Prado 2022 looks quite muscular and muscular, the taillights are designed in a 3D LED style with 2 stacked Cs. In the center is a large chrome-plated bar with the Land Cruiser Prado logo.. Interior design and comfort: The interior space of the Land Cruiser Prado is very spacious and luxurious. In addition, this SUV model also adds automatic anti-glare features to the rearview mirror, automatic wipers. However, Toyota Prado has the minus point that it still uses the key while many cheaper SUV or MPV models have a convenient button-start feature. The steering wheel on the car is a 4-spoke multifunctional design that can be adjusted in 4 directions. Both front seats of Prado have heating and ventilation functions. The driver\'s seat can be 10-way power adjustable, while the passenger seat is 6-way power adjustable. Prado\'s passenger compartment is quite spacious, even the third row still has large leg room. High ceilings help occupants feel more comfortable on long trips.',8000,6500,13,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56'),(5,'Lexus LX 570','Exterior design: The Lexus LX 570 2022 is considered a comprehensive luxury SUV with a luxurious but equally masculine and powerful appearance design. The full LED light system adds to the modernity and \"value for money\" of the car. The full-bodied steering wheel with a unique large-sized spindle grille, creates a fierce feel that matches the overall design style. body. Located on both sides of the grille is a sharp main light cluster consisting of 3 shiny balls, accompanied by LED daytime running lights that create an inverted \"L\" shape. Lexus LX570 uses 21-inch alloy wheels with a design. multi-spoke is hugged by square wheel arches. Round to the rear of the 2022 Lexus LX570, we will see that this area is somewhat simpler than the \"facade\". The visual details from the opposing philosophy of the Japanese designers are still clearly shown through the dotted chrome moldings on the back. Interior design and comfort: Space inside the Lexus LX570 Very spacious, airy and luxurious. 3-spoke steering wheel wrapped in leather and wood, integrated with buttons and gearshift paddles. Behind is a clock cluster that displays full information with a 4.2-inch color screen. All seats of the LX570 are covered with high-quality leather, the front seats have cooling and heating features, 12-way power adjustment. The rear seats can be folded and reclined and have extra leg rest for passengers.',11000,9000,15,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56'),(6,'Hyundai Santa Fe','Exterior design: Santa Fe 2022 looks more muscular than the old version thanks to the chrome hexagonal grille. Another highlight at the front of the car is the T-shaped Daytime Running Light (DRL) daytime position lamp. Adaptive LED headlights with automatic adaptive LED (AHB-LED) are also put on the new Santa Fe by Hyundai to increase projection capability. In addition, Hyundai has equipped Santa Fe 2022 with a completely new Y-Plattform chassis to pursue the trend of larger, stronger and more luxurious appearance. Overall, Santa Fe 2022 is more angular and stiffer thanks to the embossed ribs at the front of the car and the wheel arch protruding more clearly. At the rear, New Santa Fe is equipped with 3D LED lights with impressive, dimensional visibility. deep. The strip of lights running across the luggage compartment door connecting the taillights makes the car look more stylish than the previous generation..Interior design and comfort: Santa Fe 2022 interior is also strongly upgraded, prominent in the position. The dashboard position is a completely new 10.25-inch screen that integrates a satellite navigation map specifically for the Vietnamese market and supports 360 SVM camera display to help drivers easily observe and operate the vehicle in different locations. difficult conditions. Behind the steering wheel is a 12.35-inch Full Digital dashboard. Drivers can customize the watch according to their preferences and driving modes. The dashboard also integrates BVM blind spot monitoring to enhance the driving experience. Santa Fe 2022 also features information display on the HUD steering wheel to provide maximum support for the driver. New Santa Fe uses high-quality leather materials combined with imitation leather plastic details to bring a high-class finish to the car. furniture. Both the second and third rows of Santa Fe 2022 can be adjusted with buttons. Particularly, the third row of seats also has an independent auxiliary indoor unit to ensure comfort and privacy. The decorative lights on the car using Ambient LED technology can change color according to the preferences and mood of the car owner.',1000,850,20,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56'),(7,'Porsche Taycan Turbo S','Exterior design: Porsche has been very refined in the design of the Porsche Taycan. Although completely electric, the Taycan still carries Porsche\'s \"DNA\" such as a sporty appearance, overall compliance with the same golden ratio as the 911,... The front part of the Porsche Taycan has a short hood design. features and LED daytime running lights of Porsche\'s famous 4-jewel design. The Porsche Taycan\'s light clusters use the most modern Maxtrix LED technology. Two air vents are connected to the two light clusters to create a strong appearance and contribute to improved aerodynamics. The design of the front bumper and radiator cavity makes the viewer think of the mouth of a stingray. In the body, the Porsche Taycan sports car has flexible curves and especially the B-pillar with a leaning design. after. The side of the car also has an extremely stylish fish gill air vent. The car uses a set of 20-inch, 5-spoke dual-spoke wheels that are sporty. If desired, users can also choose 21-inch wheels. The rear of the Porsche Taycan is similar to the Panamera when the LED light cluster is designed to stretch horizontally. The Taycan\'s taillight design is highly appreciated as it helps to bring a modern look and a sense of spaciousness to the vehicle..Interior design and comfort: Step inside the Porsche Taycan, customer You will immediately feel a comfortable, modern interior space with a series of advanced technologies. This model has up to 4 display screens, providing diverse entertainment features and detailed information for the driver and passengers. With a full screen system, the Porsche Taycan has very few physical buttons. All user operations will be performed via a 1-touch touch screen system. The Taycan\'s steering wheel is a 3-spoke multi-function leather-covered type like other Porsche models. The screen behind the steering wheel displays up to 5 circles with different parameters and information. The front seats are designed to hug the body, so when accelerating, driving and the co-driver position will feel like driving a super sports car. With an emphasis on environmental protection, the Porsche Taycan Do not use animal skin materials for the seats as well as the interior. Instead, the interior will use optional premium materials such as wood, carbon fiber or aluminum.',10000,8000,27,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56'),(8,'Ferrari F8 Tributo','Exterior design: The appearance of the Ferrari F8 Tributo is aggressive, the cutting lines are optimized for speed, highly aerodynamic. The most notable is the S-Duct air cavity inspired by Formula 1 cars, similar to the Ferrari 488 Pista model. If the bonnet of the Ferrari 458 Italia is smoother, the Ferrari 488 GTB has two more small air vents, then on the Ferrari F8 Tributo, the Italian supercar maker creates this giant air cavity to increase the car\'s glide. S-Duct air cavity. on the Ferrari F8 Tributo has been redesigned by the Italian supercar company to increase the downforce by 15% compared to the \"elder\" 488 GTB. In addition, the S-Duct air cavity on the Ferrari F8 Tributo also has the option of carbon cladding if the customer chooses. The next highlight on the Ferrari F8 Tributo is a completely new grille, the middle air cavity connects to the cavity. S-Duct wind on Ferrari F8 Tributo to help this model increase downforce on the road. The car\'s LED headlights are also more angular and on the top of this headlight there is a small air cavity to cool the front brake discs. Seen from the side of the car, the Ferrari F8 Tributo is even more impressive thanks to the The cutting lines are very angular and a large air cavity is also to cool the rear brake system. Depending on the customer\'s choice, details such as the front bumper, the splitter bars, the headlight cavity or the S-Duct air cavity will have more carbon or not. Besides the generous and powerful features, the Ferrari F8 Tributo is still shows the softness with the impressive rear design and double taillights. This pair of taillights has more design influences from the high-end model than the Ferrari 812 Superfast, while the two most recent versions of the Ferrari F8 Tributo, the Ferrari 488 GTB and the Ferrari 458 Italia, both use single taillights. ..Interior design and comfort: The door handles of the Ferrari F8 Tributo and Ferrari 488 GTB have a similar design, but inside the cockpit there have been certain changes. First, the door sills and sides of the car door have been covered with carbon to reduce weight, the seats are also refreshed and sportier.\nAnd yet, every detail on the dashboard, door panels and center console of the Ferrari F8 Tributo has been redesigned. The Ferrari F8 Tributo steering wheel is covered with high-quality leather and matte carbon, smaller in size than its predecessor, integrated with new multi-function keys to make it easier for the driver to manipulate and control. Behind the steering wheel are the gearshift paddles and 3 familiar clocks of the \"prancing horses\", but the use has changed. Along the center console there will be a cup holder and key plug, reverse button. vehicle, autopilot and finally Launch Control. Ferrari F8 Tributo supercar is equipped with propeller-shaped air vents that are very personal compared to the previous version.',29000,24000,52,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56'),(9,'Lamborghini Huracan LP610-4','Exterior design: The appearance of the Lamborghini Huracan LP610-4 impresses with a separate 3-section grille, LED headlights with a W-style, the door handle is hidden inside, as soon as the owner unlocks the car with the key. With the key, the door handle will automatically open. The rear design is equally attractive to the front of the car when it has LED taillights and grille, cascading rear bumper, and a pair of evenly divided dual exhaust pipes. pitiful sides. Lamborghini Huracan LP610-4 returns to Vietnam with 2 options of wheels, which are very interesting 5-spoke double-spoke and spokes.. Interior design and comfort: The cockpit of the Lamborghini Huracan LP610-4 is designed with an entertainment screen. large placed on the center console, under which are a series of buttons. The vehicle\'s start button has the same design as the buttons of the fighter aircraft. On the steering wheel of the Huracan LP610-4 are integrated buttons for the turn signal system, wipers, information system control. and red drive mode selection switch. In addition, this supercar has 3 pre-set driving modes including Strada, Sport and Corsa. Press the button through the racing mode, Lamborghini Huracan LP610-4 will become the most aggressive war bull with growling sounds. from the dual exhaust, the suspension works optimally and the center of gravity of the car will be as low as possible. The seats of the Lamborghini Huracan LP610-4 supercar also have the option of Alcantara leather. A Lamborghini Huracan LP610-4 in Vietnam has been heavily re-',17000,14000,55,1,1,'2022-11-02 07:02:56','2022-11-02 07:02:56');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carspecification`
--

DROP TABLE IF EXISTS `carspecification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carspecification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carId` int NOT NULL,
  `manufactering` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `km_driven` int NOT NULL,
  `gear` varchar(255) NOT NULL,
  `fuel` varchar(255) NOT NULL,
  `fuelConsumption` varchar(255) NOT NULL,
  `outerColor` varchar(255) NOT NULL,
  `innerColor` varchar(255) NOT NULL,
  `overallDimensions` varchar(255) NOT NULL,
  `drive` varchar(255) NOT NULL,
  `yearOfMake` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `carspecification_carid_foreign` (`carId`),
  CONSTRAINT `carspecification_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carspecification`
--

LOCK TABLES `carspecification` WRITE;
/*!40000 ALTER TABLE `carspecification` DISABLE KEYS */;
INSERT INTO `carspecification` VALUES (1,1,'VinFast ','Old',15000,'ZF . 8-speed automatic','Gasoline 2.0L, I-4, DOHC, turbocharger, variable valve','11,11/100km','Grey','Brown','4.973 x 1.900 x 1.500','RFD - Rear-wheel drive',2019),(2,2,'Mercedes-Benz','Old',9000,'9G-TRONIC 9-speed automatic','2.0L I4 dual-port turbocharged petrol engine','8.7/100km','Black','Black','4923 x 1852 x 1468 (mm)','RFD - Rear-wheel drive',2019),(3,3,'BMW ','Old',13500,'Steptronic 8-speed automatic','I4 TwinPower Turbo gasoline engine','6.4/100km','Blue','Grey','4.709 x 1.827 x 1.435 mm','RFD - Rear-wheel drive',2019),(4,4,'Toyota','Old',34000,'6AT','2TR-FE, 4 cylinders in line, 16 valves, DOHC, Dual VVT-i Gasoline','11.85/100km','Black','Brown','4.840 x 1.885 x 1.890','AWD - 4 wheelers full time',2018),(5,5,'Lexus','Old',30000,'8AT','5.7L V8 32-valve, dual camshaft, dual VVT-I Gasoline','14/100km','Black','Brown','5.080 x 1.980 x 1.865','4WD',2018),(6,6,'Hyundai ','Old',6000,'6AT','Gasoline, 2.5L GDI, DOHC','11.85/100km','White','Brown','4.785 x 1.900 x 1.730','4WD - 4-wheel drive',2019),(7,7,'Porsche ','Old',8000,'2AT','2 electric motors Turbocharged','Range 413km/100 % battery','Red','Brown','4.963 x 1.966 x 1.381','4WD - 4-wheel drive',2021),(8,8,'Ferrari ','Old',1000,'7-speed dual clutch','V8 Twin - Turbo Gasoline','11/100km','Yellow','Black','4.611 x 1.979 x 1.206','RFD - Rear-wheel drive',2021),(9,9,'Lamborghini ','Old',5000,'7-speed automatic','Gasoline V10 Natural air intake','12.5/100km','Black','Red','4.459 x 1.924 x 1.165','AWD - 4 wheelers full time',2017);
/*!40000 ALTER TABLE `carspecification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `carId` int NOT NULL,
  `createdAt` datetime NOT NULL,
  `number` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `favorite_userid_foreign` (`userId`),
  KEY `favorite_carid_foreign` (`carId`),
  CONSTRAINT `favorite_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `car` (`id`),
  CONSTRAINT `favorite_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gamerecord`
--

DROP TABLE IF EXISTS `gamerecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gamerecord` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roomId` int NOT NULL,
  `userId` int NOT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gamerecord_userid_foreign` (`userId`),
  KEY `gamerecord_roomid_foreign` (`roomId`),
  CONSTRAINT `gamerecord_roomid_foreign` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`),
  CONSTRAINT `gamerecord_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamerecord`
--

LOCK TABLES `gamerecord` WRITE;
/*!40000 ALTER TABLE `gamerecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `gamerecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carId` int NOT NULL,
  `imgPath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brandId` int NOT NULL,
  `modelName` varchar(255) NOT NULL,
  `modelSpecificationId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `model_brandid_foreign` (`brandId`),
  CONSTRAINT `model_brandid_foreign` FOREIGN KEY (`brandId`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,4,'E-Class',1),(2,4,'C-Class',1),(3,4,'S-Class',1),(4,4,'G-Class',4),(5,1,'LUX SA2.0',4),(6,1,'Lux A2.0',1),(7,1,'Fadil',2),(8,2,'Mazda 3',1),(9,2,'Mazda CX-5',4),(10,3,'Camry',1),(11,3,'Corolla Altis',1),(12,3,'Fortuner',4),(13,3,'Land Cruiser Prado',4),(14,3,'Vios ',1),(15,5,'LX',4),(16,5,'LS',1),(17,5,'ES',1),(18,6,'Ford Ranger',12),(19,7,'Accent',1),(20,7,'Santa Fe',4),(21,7,'Tucson',14),(22,7,'Elantra',1),(23,7,'Grand i10',2),(24,8,'718 Cayman',8),(25,8,'911 Carrera',8),(26,8,'911 Turbo',8),(27,8,'Taycan',1),(28,8,'Panamera',1),(29,9,'CR-V',4),(30,9,'Accord',1),(31,9,'City',1),(32,9,'Civic',1),(33,10,'Mulsanne',1),(34,10,'Flying ',1),(35,10,'Continental ',7),(36,10,'Bentayga',14),(37,11,'Series 3',1),(38,11,'Series 5',1),(39,11,'Series 6',1),(40,11,'Series 7',1),(41,11,'X Series',14),(42,11,'M Series',1),(43,11,' i8',8),(44,12,'A class',1),(45,12,'Q class',4),(46,12,'R8',8),(47,12,'S8',1),(48,12,'TT',7),(49,14,'488 Pista',8),(50,14,'SF90 Stradale',8),(51,14,'488GTB',8),(52,14,'F8 Tributo',8),(53,14,'812 Superfast',8),(54,15,'Urus',14),(55,15,'Huracan',8),(56,15,'Aventador',8),(57,15,'Sian',8),(58,16,'Cullinan',4),(59,16,'Ghost',1),(60,16,'Phantom',1);
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelspecification`
--

DROP TABLE IF EXISTS `modelspecification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelspecification` (
  `modelId` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `seatNumber` int NOT NULL,
  PRIMARY KEY (`modelId`),
  CONSTRAINT `modelspecification_modelid_foreign` FOREIGN KEY (`modelId`) REFERENCES `model` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelspecification`
--

LOCK TABLES `modelspecification` WRITE;
/*!40000 ALTER TABLE `modelspecification` DISABLE KEYS */;
INSERT INTO `modelspecification` VALUES (1,'Sedan',5),(2,'Hatchback',5),(3,'Sedan',4),(4,'SUV',7),(6,'CUV',7),(7,'Coupe ',4),(8,'Coupe ',2),(9,'MVP',7),(10,'Convertible ',2),(11,'Convertible ',4),(12,'Pickup ',5),(13,'Limousine',12),(14,'SUV',5);
/*!40000 ALTER TABLE `modelspecification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(2000) NOT NULL,
  `author` varchar(255) NOT NULL,
  `shortDescribe` varchar(2000) NOT NULL,
  `describe` longtext NOT NULL,
  `createAt` datetime NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2023 BMW iX Review: The beauty is on the inside','JOEL STOCKSDALE','Great performance, space and an gorgeous interior make for an excellent flagship SUV','Pros: Exquisite interior design, superb performance, long electric range, comfortable rideCons: Polarizing exterior styling, no third row of seats, desirable driving assists are options. The 2023 BMW iX SUV is leading its brand’s latest wave of electric cars. It makes a statement anywhere it goes with its controversial exterior design, but get past that, and you\'ll discover a beautifully crafted luxury SUV that\'s comfortable to cruise in and surprisingly engaging when driven hard. The interior is where the iX really shines, with modern, airy design that\'s unlike anything on the market, let alone the BMW line-up. It also has the best implementation of the brand\'s current infotainment system, showcasing how good the system can be, when it\'s running well. It\'s also extremely spacious for occupants and cargo, in particular with more carrying space than its closest competitors, and just shy of the Tesla Model X. The ride quality is among the best available from BMW, and extremely good against the competition. It favors the firm side, but that, along with reasonably good steering and a talkative chassis make it fun to drive. The extra power of the iX M60 only adds to the fun. And with ranges exceeding 300 miles on the regular xDrive50 models, you can have fun driving it for long periods of time without having to stop for a charge. Its only major drawbacks are the aforementioned odd styling, a lack of a third-row seat option (although Tesla\'s and Mercedes’ are of questionable use), as well as hiding many features behind options. In particular, simple adaptive cruise control is an extra-cost option, which is somewhat galling when it\'s becoming commonplace on far more affordable cars.But all-in-all, the iX is a luxury electric crossover that feels genuinely special and practical, particularly for the price. If you\'re in the market, it\'s a must-see.','2022-11-02 07:02:56','img'),(2,'2023 BMW M2 gets full range of M Performance parts','RONAN GLON','The catalog includes body kits, wheels, and suspension parts','Unveiled in October 2022, the new 2023 BMW M2 is already eligible to receive a wide selection of factory-developed M Performance parts. The list of add-ons announced by the Munich-based carmaker includes carbon fiber trim, forged wheels, and a lighter exhaust system. BMW M leveraged its five decades of racing experience to give future M2 owners numerous customization options. Starting with the outside, the company designed a carbon fiber body kit that includes a front splitter, inserts for the front air intakes, side skirts, a rear spoiler, and a rear diffuser. M Performance titanium and carbon fiber exhaust tips and a red M-branded tow strap add a finishing touch to the look. Speaking of the exhaust, BMW M created a system that consists of stainless-steel front pipes and a titanium rear section. It weighs over 17 pounds less than the stock setup and includes electronically-controlled flaps. BMW promises it unlocks \"distinct, racing car acoustics.\" Handling is one of BMW M\'s claims to fame, and the M Performance suspension system was designed to make the new M2 even sharper to drive on and off the track. It adds height-adjustable spring cups to the front and rear axles that let drivers lower the ride height by nearly half an inch. These parts can be paired with 20-inch front and 21-inch rear forged wheels available in M Frozen Gold Bronze or M Jet Black. Buyers who want an M-themed interior can add carbon fiber and Alcantara trim, Alcantara-upholstered knee pads, carbon fiber seatback covers for the M Sport seats, and an M Performance steering wheel. Carbon fiber shift paddles and door sill plates are offered as well. None of the M Performance parts increase the engine\'s output, so enthusiasts who want to dial in more power will need to turn to the aftermarket. Stock, the M2 is powered by a twin-turbocharged, 3.0-liter straight-six rated at 453 horsepower and 406 pound-feet of torque. The engine spins the rear wheels via a six-speed manual transmission, though an eight-speed automatic is offered at an extra cost. The aforementioned parts will be available to order when M2 deliveries begin in April 2023. Pricing hasn\'t been announced yet.','2022-11-02 07:02:56','img'),(3,'Lexus recalls 4,200 NX crossovers due to missing welds','RONAN GLON','Fewer than 1% of the recalled units are missing spot-welds near the front shock mounts','Lexus is recalling about 4,200 units of the 2022 NX due to missing spot-welds. Announced in April 2022, the campaign includes the NX250, the NX350, the NX350h, and the NX450h+, and the Japanese company has asked its dealers to stop selling the vehicles in their inventory.Documents published by the National Highway Traffic Safety Administration (NHTSA) explain that some spot-welds might have been missed during the production process. They\'re located around the mounting areas for the front shock absorbers. Leaving out these welds can cause some of the other welds and the surrounding panels to weaken or crack over time, which could in turn make it possible for the front shock absorbers to separate from their mounting area. This would increase the risk of a crash by causing the driver to lose control of the vehicle. The recall includes 4,215 examples of the NX, though Lexus estimates that fewer than 1% of those are missing welds. It will begin notifying owners of potentially affected vehicles by mail on June 6, 2022. They\'ll be asked to take their NX to an authorized dealer so that a mechanic can check for missing welds. Lexus is still developing a solution to the problem. In the meantime, it has asked its dealers to stop selling the NXs potentially affected by the recall that are still in inventories across the nation, though as of writing only 12 units have been identified. Weld-related recalls are mercifully rare but not unheard of. In 2019, Subaru recalled 2,107 new Outback and Legacy models due to faulty welds below the cowl panel, and 293 units of the Ascent were recalled in 2018 because they were missing a series of spot welds on the B-pillar. At the time, the company explained that the 293 crossovers without the proper welds would be destroyed and replaced.','2022-11-02 07:02:56','img'),(4,'2022 Audi RS 7 Exclusive Edition is limited to just 23 cars','ZAC PALMER','You\'ll pay $166,495 for this special version of the RS 7 Sportback','This is the 2022 Audi RS 7 Exclusive Edition, and it sure is exclusive. Audi says it’s only going to make 23 RS 7s in this spec, so if you’re into the rare, this could be the RS 7 to pursue.So, what do you get with the Exclusive Edition? The main draw is a unique Mamba Black pearl paint that presents as black with blue undertones. We suspect that the paint will speak for itself much better in person than these limited photos do. You also get Sepang Blue stitching throughout the interior. Those blue accents can be found on the door armrests, center console trim, upper door panels, upper dashboard, seats and floor mats. Audi says that various controls in the cabin are adorned in Sepang Blue braiding, too.Beyond the black paint and blue interior accents, this RS 7 comes decked out with virtually every performance option you can spec. That means it has the ceramic brake package (with blue-painted calipers), the Dynamic Ride Control suspension and the Sport Exhaust with black-finished exhaust tips. The 4.0-liter twin-turbo V8 is the same, and it continues to output 591 horsepower and 590 pound-feet of torque.Audi makes both its Carbon Optic package and Black Optic package standard for the Exclusive Edition. This means it has blacked-out badges and trim, along with a carbon fiber spoiler, diffuser and mirror caps. It’s also fitted with 22-inch wheels wrapped in 285-section-width summer performance tires. Lastly, the interior gets the Audi Exclusive extended leather package that covers as many surfaces as possible in black leather.The starting price for the Exclusive Edition is $166,495. Again, only 23 will be produced in this spec, and Audi expects them to ship sometime in late summer.','2022-11-02 07:02:56','img'),(5,'Porsche 718 Cayman GT4 runs naked in the snow','REESE COUNTS','This should be the fastest version of the 718 yet','It\'s been awhile since we\'ve seen pictures of the upcoming Porsche 718 Cayman GT4. Previous spy photos showed an rough prototype testing at the Nürburgring. In these new images, a far more polished car appears to be undergoing some winter testing. Most of the Porsche prototypes we see are nearly naked ( just look at this 718 Boxster Spyder), but this GT4 is more exposed than most. Don\'t expect too much to change before production.Like the last Cayman GT4, expect this new car to be the end-all-be-all of the 718 platform. If the wing didn\'t give it away, just look at the aggressive front splitter or at the snow-covered diffuser. This car isn\'t screwing around. Like the last GT4, the front bumper draws influence from the current 911 GT3. The only thing that looks missing is some additional bodywork around the side air intakes.Expect less weight and more power, though what rests at the heart has not yet been determined. Rumors continue to suggest that the GT4 will swap in a flat-six in place of the 718\'s turbo flat-four, but that\'s simply hope and speculation. What\'s almost a given is that the GT4 will retain a manual transmission.The car has been in development for a while, so don\'t expect to wait too much longer before the reveal. The Geneva Motor Show is coming up in just a few weeks, so we might get lucky there.','2022-11-02 07:02:56','img'),(6,'Toyota Land Cruiser 300-series gets life-size Lego build','BEN HSU','It\'s comprised of 440,000 bricks and weighs 4,520 pounds.','Whenever a hot car debuts nowadays, the inevitable life-size Lego build follows soon after. Like Nürburgring lap times for supercars, it\'s practically an expected part of the vehicle launch routine. The latest vehicle to get the treatment is the not-for-America 300-series Toyota Land Cruiser. The build was commissioned by Al-Futtaim Motors, the exclusive Toyota importer for the the United Arab Emirates. According to a short video about the project, it took a team of 12 individuals 2,688 hours to complete the 1:1 scale sculpture. A total of 440,000 Lego bricks were used in the build, and the finished Cruiser weighs approximately 4,520 pounds, or about 1,000 lighter than the actual 5,600-pound truck. Everything, including the windows, tires and little Toyota oval logos found throughout the car, are made from Lego bricks. The headlights and taillights are even illuminated, backlit from some kind of interior light source. Built around a simple steel frame for rigidity, it measures about 195 inches long, 78 inches wide and 76 inches tall. In the shopping center where it\'s on display, a real 300-series Land Cruiser parks beside it. While the Land Cruiser isn\'t sold here, Toyota has promised us a Lexus LX 600 variant with all the off-road prowess the chassis promises. Under the hood will reside a twin-turbo 3.5-liter V6 making 409 horsepower and 479 pound-feet of torque as its only engine option; the Cruiser\'s diesel and 4.0-liter naturally aspirated V6 aren\'t coming. In the rest of the world the 300-series has been well received as a proper evolution of Toyota\'s legendary off-roader, so hopefully that presages good things for the Lexus. The Land Cruiser follows in the fine tradition of other life-size Lego builds. Past unrelated projects have sprouted up all over the globe, and the plastic brick subject matter has included a Formula One car, Lamborghini Sian, Ferrari SF70H, Bugatti Chiron, Toyota Camry, McLaren Senna, Chevy Silverado Trail Boss and Toyota Supra.','2022-11-02 07:02:56','img'),(7,'Next-gen Mercedes C 63 AMG to get plug-in hybrid four-cylinder','JONATHON RAMSEY','Tuned version of the 416-hp four-pot in the A 45 S with hybrid help and AWD','Autocar reports that the next-generation Mercedes-AMG C 63 will give up its membership to the V8 club and become a four-cylinder plug-in hybrid. You read that correctly. The news isn\'t exactly new, it only adds more confirmation to what\'s been whispered thrice before. In April 2018, Mercedes-AMG boss Tobias Möers told CarAdvice that Affalterbach\'s take on Mercedes\' bestselling sedan would go hybrid and all-wheel drive. He foreshadowed the four-cylinder sucker-punch by saying, \"[We] have to be creative, and I\'m chasing performance, and that\'s not strictly linked to the number of cylinders.\" In May this year, a German site specializing in Mercedes rumors predicted the four-cylinder hybrid with 476 horsepower in base trim and a 48-volt electrical architecture. Then, in another Möers interview piece at the Frankfurt Motor Show, Motor Trend mentioned the 416-hp 2.0-liter four-cylinder in the current A 45 S and CLA 45 S, and wrote, \"Expect to see a version powering a future 2021 Mercedes-Benz C-Class AMG model.\" The C-Class AMG got so good that it\'s been showing the segment superstar BMW M3/M4 how a sport sedan and coupe should be done, part of that master class coming via the sound and gravitas conveyed by the 4.0-liter twin-turbo V8. The M177 AMG V8 makes 469 hp and 479 pound-feet of torque in standard trim, 503 hp and 516 lb-ft in S trim. No matter if the W206 C 63 AMG matches those numbers, or, more likely, exceeds them, plenty of AMG fans will be gutted by their champion falling two cylinders behind the next-gen M3 and adopting economy-car displacement specs.  Nevertheless, we\'re told the coming hot C will be the first use-case for the electrified 2.0-liter M139 four-cylinder in the C 63, and eventually the C 43 and all AMG GLC variants; today\'s C 43 employs the M276 3.0-liter twin-turbo V6. They\'ll pull the 48-volt integrated starter motor hooked up to the M256 inline-six cylinder in the CLS 53 AMG that can supply an additional 22 hp and 184 lb-ft. Tuning for the coming C 63 AMG will draw more horsepower from the electric motor, though, and get a larger capacity battery as well as more comprehensive energy recuperation. The nine-speed AMG MCT Speedshift automatic is expected to handle shifting duty. If that German site is correct about a base model with 476 hp, that would add seven horses compared to the current car. Autocar says the top trim \"will match\" the current S trim\'s 503 hp, while upping torque to 553 lb-ft. Even with the same or slightly elevated numbers, dynamic benefits would come from less weight over the front axle and a powertrain with better front-rear balance. All-wheel drive will be a non-negotiable option, but one using the same rear-bias adjustability found in the current E 63 AMG. As for bodystyles, Autocar says the full-fat wagon will retire, leaving just sedan, coupe and convertible styles for the C 63 AMG, the wagon only available in C 43 AMG trim. We\'ve about two years to wait, with the series production redesigned C-Class due next year as a 2021 model, the AMG versions following the year after.','2022-11-02 07:02:56','img'),(8,'2023 Bentley Bentayga EWB Azure First Edition on sale for one year','JONATHON RAMSEY','Diamond illumination, exclusive wheels, and badges come standard','The 2023 Bentley Bentayga EWB has added a third flavor. Above the standard spec, the Azure trim adds distinguishing features like elongated quilting and perforation on the numerous leather surfaces, jeweled caps for the fuel and oil fillers, fancier wheels, and Azure badging. For the first model year of production the craftspeople at Crewe will build an Azure First Edition trim. The First Edition makes normally optional frills like Bentley Diamond Illumination and the Naim for Bentley stereo standard equipment. That stereo pumps 1,720 watts of playlist through 20 speakers. There are also a heated steering wheel, folding picnic tables, and additional driver assistance systems  The cabin is sewn up with exclusive contrast stitching and embroidery, and of course there\'s First Edition badging outside, on the C-pillar, and inside on the seats. Should the customer decide that isn\'t enough advertising, buyers can request handcrafted metal overlays. This version of the SUV shows off brighter grilles in the lower bumper and sits on 22-inch, 10-spoke, directional wheels, an inch larger than the hoops on the standard Bentayga.  The Bentayga EWB marks the return of the Azure name for the first time since 2010, and the Bentayga\'s second First Edition after the 2017 Bentayga W12 which was limited to 608 units. The W12, by the way, is said to be forbidden to the EWB. Pricing hasn\'t been announced, but since the EWB starts at around $230,000 and Bentley\'s already said it expects \"that people will option them beyond $300,000,\" the Azure First Edition can be expected to push that well higher.','2022-11-02 07:02:56','img');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwordresettoken`
--

DROP TABLE IF EXISTS `passwordresettoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passwordresettoken` (
  `id` int NOT NULL,
  `token` varchar(255) NOT NULL,
  `expiryDate` datetime NOT NULL,
  `userId` int NOT NULL,
  KEY `passwordresettoken_userid_foreign` (`userId`),
  CONSTRAINT `passwordresettoken_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwordresettoken`
--

LOCK TABLES `passwordresettoken` WRITE;
/*!40000 ALTER TABLE `passwordresettoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `passwordresettoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Admin Car'),(3,'Admin Room'),(4,'User');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carId` int NOT NULL,
  `typeRoomId` int NOT NULL,
  `roomName` varchar(255) NOT NULL,
  `roomTime` time NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `createdBy` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_carid_foreign` (`carId`),
  KEY `room_typeroomid_foreign` (`typeRoomId`),
  CONSTRAINT `room_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `car` (`id`),
  CONSTRAINT `room_typeroomid_foreign` FOREIGN KEY (`typeRoomId`) REFERENCES `typeroom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomparticipant`
--

DROP TABLE IF EXISTS `roomparticipant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomparticipant` (
  `roomId` int NOT NULL,
  `userId` int NOT NULL,
  KEY `roomparticipant_roomid_foreign` (`roomId`),
  KEY `roomparticipant_userid_foreign` (`userId`),
  CONSTRAINT `roomparticipant_roomid_foreign` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`),
  CONSTRAINT `roomparticipant_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomparticipant`
--

LOCK TABLES `roomparticipant` WRITE;
/*!40000 ALTER TABLE `roomparticipant` DISABLE KEYS */;
/*!40000 ALTER TABLE `roomparticipant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `safetysystem`
--

DROP TABLE IF EXISTS `safetysystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `safetysystem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carId` int NOT NULL,
  `air_bad` varchar(255) NOT NULL,
  `abs_brake` varchar(255) NOT NULL,
  `speedControl` varchar(255) NOT NULL,
  `tirePressure` varchar(255) NOT NULL,
  `otherDescription` varchar(8000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `safetysystem_carid_foreign` (`carId`),
  CONSTRAINT `safetysystem_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `safetysystem`
--

LOCK TABLES `safetysystem` WRITE;
/*!40000 ALTER TABLE `safetysystem` DISABLE KEYS */;
INSERT INTO `safetysystem` VALUES (1,1,'6','Yes','Yes','Yes','VinFast LUX A2.0 possesses many safety features such as:  ABS anti-lock braking system ; Electronic brake force distribution EBD; BA emergency brake assist ; Electronic balancing system ESC; TCS traction control; Support HSA horizontal departure; ESS emergency brake light,reversing camera, 360-degree camera, blind spot warning, parking assist sensor .....'),(2,2,'9','Yes','Yes','No','Active brake assist system Active Brake Assist, Automatic anti-glare rearview mirror, Anti-skid when accelerating ASR, Rain wipers with rain sensor,'),(3,3,'6','Yes','Yes','Yes','Electronic body stabilization system (Dynamic Stability Control - DSC), Comfort access smart key, Parking assistant system with integrated reversing camera; Reversing assistant function, Parking Assistant Plus system with integrated intelligent 360 camera.'),(4,4,'7','Yes','Yes','Yes','ABS anti-lock brake; Electronic brake force distribution EBD; BA emergency brake assist; Electronic balance; Traction control; Automatic throttle; Reverse camera, reverse sensor; 7 airbags. Toyota also adds to the Land Cruiser Prado Safety Sense safety technology including: Pre-collision warning, active cruise control (DRCC), lane departure warning, automatic high beam adjustment. There is also a blind spot warning system, support for cross-traffic alert when reversing, tire pressure warning, 360 camera, 8 parking sensors, ...'),(5,5,'8','Yes','Yes','Yes','Safety features are also the strength of the Lexus LX570 when fully possessing the most advanced technology available today. In addition to basic features such as anti-lock braking system, electronic brake system, ... the car also has Active Traction Control active traction control system, terrain adaptive system Multi-Terrain Select system, Cruise control for rough terrain Crawl Control, support for steep departure, 8 airbags…'),(6,6,'6','Yes','Yes','Yes','In terms of safety, Santa Fe is equipped with a Smart Sense technology package with AHB adaptive headlights, LFA lane-keeping assist system, BVM & BCA blind spot prevention & monitoring system, and prevention system. Pedestrian collision and active parking assist system. Comes with Smart Sense are the same safety technologies as the previous version, including anti-lock braking ABS, electronic brake force distribution EBD, brake assist. BA emergency, TCS traction control, 6 airbags...'),(7,7,'8','Yes','Yes','Yes','Emergency Braking Assist, Electronic Brakeforce Distribution, Blind Spot Warning, Advanced InnoDrive Technology including semi-autonomous driver assist, radar adaptive cruise control, lane keep assist, lane keep assist Night Vision Assit, Seatbelt Reminder System'),(8,8,'4','Yes','Yes','Yes','Ferrari F8 Tributo is also equipped with safety systems including Side Slip Angle Control 6.1 and torque control at the wheel by Ferrari Dynamic Enhancer (FDE+), 360-degree camera, Seat belt reminder system. full'),(9,9,'4','Yes','Yes','Yes','Support emergency brake force, Electronic brake force distribution, Tire pressure warning system, 360-degree camera, Seat belt reminder system.');
/*!40000 ALTER TABLE `safetysystem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `carId` int NOT NULL,
  `transationHash` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `createdAt` datetime NOT NULL,
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_userid_foreign` (`userId`),
  KEY `transaction_carid_foreign` (`carId`),
  CONSTRAINT `transaction_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `car` (`id`),
  CONSTRAINT `transaction_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeroom`
--

DROP TABLE IF EXISTS `typeroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeroom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeroom`
--

LOCK TABLES `typeroom` WRITE;
/*!40000 ALTER TABLE `typeroom` DISABLE KEYS */;
INSERT INTO `typeroom` VALUES (1,'Free'),(2,'Normal');
/*!40000 ALTER TABLE `typeroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `avatar` varchar(255) NOT NULL,
  `enabled` int NOT NULL,
  `createdAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'viethoang1','123','Viet Hoang','0122333444','viethoang1@gmail.com','img',0,'2022-11-02 07:02:56'),(2,'viethoang2','123','Viet Hoang','0166232333','viethoang2@gmail.com','img',0,'2022-11-02 07:02:56'),(3,'viethoang3','123','Viet Hoang','0966812311','viethoang3@gmail.com','img',0,'2022-11-02 07:02:56'),(4,'viethoang','123','Viet Hoang','0234123123','viethoang42gmail.com','img',0,'2022-11-02 07:02:56');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `userId` int NOT NULL,
  `roleId` int NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `user_role_roleid_foreign` (`roleId`),
  CONSTRAINT `user_role_roleid_foreign` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verificationtoken`
--

DROP TABLE IF EXISTS `verificationtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verificationtoken` (
  `id` int NOT NULL,
  `token` varchar(255) NOT NULL,
  `expiryDate` datetime NOT NULL,
  `userId` int NOT NULL,
  KEY `verificationtoken_userid_foreign` (`userId`),
  CONSTRAINT `verificationtoken_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationtoken`
--

LOCK TABLES `verificationtoken` WRITE;
/*!40000 ALTER TABLE `verificationtoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `verificationtoken` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-03 23:09:16
