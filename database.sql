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
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'VINFAST','/hoang/vinfast.jpg',_binary ''),(2,'MAZDA','/hoang/mazda.jpg',_binary ''),(3,'TOYOTA','/hoang/toyota.png',_binary ''),(4,'Mercedes-Benz','/hoang/Mercedes_Benz.jpg',_binary ''),(5,'LEXUS','/hoang/lexus.png',_binary ''),(6,'FORD','/hoang/ford.jpg',_binary ''),(7,'HYUNDAI','/hoang/huyndai.jpg',_binary ''),(8,'PORSCHE','/hoang/porsche.jpg',_binary ''),(9,'HONDA','/hoang/honda.jpg',_binary ''),(10,'BENTLEY','/hoang/bentlay.png',_binary ''),(11,'BMW','/hoang/bmw.png',_binary ''),(12,'AUDI','/hoang/audi.jpg',_binary ''),(13,'BUGATTI','/hoang/bugati.png',_binary ''),(14,'FERRARI','/hoang/client-3.png',_binary ''),(15,'LAMBORGHINI','/hoang/client-1.png',_binary ''),(16,'ROLLS-ROYCE','/hoang/rollroyce.jpg',_binary ''),(17,'MASERATI','/hoang/images.png',_binary ''),(25,'Chevrolet','/hoang/chevrolet.jpg',_binary ''),(72,'Hoang','/hoang/rollroyce.jpg',_binary '\0'),(73,'Hoang','/hoang/bentlay.png',_binary '\0');
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
  KEY `FKt5hnpbk4vw85s1skfua1k72c8` (`createdBy`),
  KEY `FK5vblw4pdpgrbn3xusfabxmyd0` (`modelId`),
  CONSTRAINT `car_createby_foreign` FOREIGN KEY (`createdBy`) REFERENCES `user` (`id`),
  CONSTRAINT `car_modelid_foreign` FOREIGN KEY (`modelId`) REFERENCES `model` (`id`),
  CONSTRAINT `FK5vblw4pdpgrbn3xusfabxmyd0` FOREIGN KEY (`modelId`) REFERENCES `model` (`id`),
  CONSTRAINT `FKt5hnpbk4vw85s1skfua1k72c8` FOREIGN KEY (`createdBy`) REFERENCES `user` (`id`)
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
  `manufacturing` varchar(255) NOT NULL,
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
-- Table structure for table `favoritedto`
--

DROP TABLE IF EXISTS `favoritedto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoritedto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carid` int DEFAULT NULL,
  `carname` varchar(255) DEFAULT NULL,
  `createdat` datetime DEFAULT NULL,
  `imgpath` varchar(255) DEFAULT NULL,
  `number` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritedto`
--

LOCK TABLES `favoritedto` WRITE;
/*!40000 ALTER TABLE `favoritedto` DISABLE KEYS */;
/*!40000 ALTER TABLE `favoritedto` ENABLE KEYS */;
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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imgpath` varchar(255) DEFAULT NULL,
  `carid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3lwukq6yfu75edrx8l035ji3a` (`carid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeln7t5w71rojd0bj3r6q65p5` (`modelSpecificationId`),
  KEY `FKf36eyn60g4tlx4w9ldv2gmipu` (`brandId`),
  CONSTRAINT `FKeln7t5w71rojd0bj3r6q65p5` FOREIGN KEY (`modelSpecificationId`) REFERENCES `modelspecification` (`id`),
  CONSTRAINT `FKf36eyn60g4tlx4w9ldv2gmipu` FOREIGN KEY (`brandId`) REFERENCES `brand` (`id`),
  CONSTRAINT `model_brandid_foreign` FOREIGN KEY (`brandId`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,4,'E-Class',1,_binary ''),(2,4,'C-Class',1,_binary ''),(3,4,'S-Class',1,_binary ''),(4,4,'G-Class',4,_binary ''),(5,1,'LUX SA2.0',4,_binary ''),(6,1,'Lux A2.0',1,_binary ''),(7,1,'Fadil',2,_binary ''),(8,2,'Mazda 3',1,_binary ''),(9,2,'Mazda CX-5',4,_binary ''),(10,3,'Camry',1,_binary ''),(11,3,'Corolla Altis',1,_binary ''),(12,3,'Fortuner',4,_binary ''),(13,3,'Land Cruiser Prado',4,_binary ''),(14,3,'Vios ',1,_binary ''),(15,5,'LX',4,_binary ''),(16,5,'LS',1,_binary ''),(17,5,'ES',1,_binary ''),(18,6,'Ford Ranger',12,_binary ''),(19,7,'Accent',1,_binary ''),(20,7,'Santa Fe',4,_binary ''),(21,7,'Tucson',14,_binary ''),(22,7,'Elantra',1,_binary ''),(23,7,'Grand i10',2,_binary ''),(24,8,'718 Cayman',8,_binary ''),(25,8,'911 Carrera',8,_binary ''),(26,8,'911 Turbo',8,_binary ''),(27,8,'Taycan',1,_binary ''),(28,8,'Panamera',1,_binary ''),(29,9,'CR-V',4,_binary ''),(30,9,'Accord',1,_binary ''),(31,9,'City',1,_binary ''),(32,9,'Civic',1,_binary ''),(33,10,'Mulsanne',1,_binary ''),(34,10,'Flying ',1,_binary ''),(35,10,'Continental ',7,_binary ''),(36,10,'Bentayga',14,_binary ''),(37,11,'Series 3',1,_binary ''),(38,11,'Series 5',1,_binary ''),(39,11,'Series 6',1,_binary ''),(40,11,'Series 7',1,_binary ''),(41,11,'X Series',14,_binary ''),(42,11,'M Series',1,_binary ''),(43,11,' i8',8,_binary ''),(44,12,'A class',1,_binary ''),(45,12,'Q class',4,_binary ''),(46,12,'R8',8,_binary ''),(47,12,'S8',1,_binary ''),(48,12,'TT',7,_binary ''),(49,14,'488 Pista',8,_binary ''),(50,14,'SF90 Stradale',8,_binary ''),(51,14,'488GTB',8,_binary ''),(52,14,'F8 Tributo',8,_binary ''),(53,14,'812 Superfast',8,_binary ''),(54,15,'Urus',14,_binary ''),(55,15,'Huracan',8,_binary ''),(56,15,'Aventador',8,_binary ''),(57,15,'Sian',8,_binary ''),(58,16,'Cullinan',4,_binary ''),(59,16,'Ghost',1,_binary ''),(60,16,'Phantom',1,_binary '');
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelspecification`
--

DROP TABLE IF EXISTS `modelspecification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelspecification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `seatNumber` int NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelspecification`
--

LOCK TABLES `modelspecification` WRITE;
/*!40000 ALTER TABLE `modelspecification` DISABLE KEYS */;
INSERT INTO `modelspecification` VALUES (1,'Sedan',5,_binary ''),(2,'Hatchback',5,_binary ''),(3,'Sedan',4,_binary ''),(4,'SUV',7,_binary ''),(6,'CUV',7,_binary ''),(7,'Coupe ',4,_binary ''),(8,'Coupe ',2,_binary ''),(9,'MVP',7,_binary ''),(10,'Convertible ',2,_binary ''),(11,'Convertible ',4,_binary ''),(12,'Pickup ',5,_binary ''),(13,'Limousine',12,_binary ''),(14,'SUV',5,_binary '');
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
  `img` varchar(255) NOT NULL,
  `describe1` longtext,
  `describe2` longtext,
  `describe3` longtext,
  `describe4` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'Pure-electric iX4 said to be on track','Jonathon Ramsey','Gas-powered BMW X4 might be dead after this generation','BMW Blog said it tapped its insiders for details on the next-generation gas-powered BMW X4, and heard that there might not be one. A longtime insider on Bimmerpost reports the same news, that the model codenamed G46 \"may have been cancelled.\" And checking the Automotive News future product pipeline returns this, \"Production of the U.S.-made model could end in late 2025 with no replacement planned, according to AutoForecast Solutions.\"','2022-11-02 07:02:56','img','Supposedly, the original plan was for the clamshell crossover to mimic the split headed to the 3 Series lineup, with an ICE-powered 3 Series range on an evolution of BMW\'s Cluster Architecture (CLAR), and pure-electric variants of the 3\'er on the coming Neue Klasse architecture developed just for EVs. For the 3 Series, the electric sedan could resurrect the i3 name, the electric crossover would be the iX3.','The iX4 remains on track. BMW Blog suspects the automaker\'s accountants couldn\'t justify the spend on a new version of the gas-powered X4, though. The raw sales data we see isn\'t sufficient to make judgements. X4 sales have been climbing in the states, reaching 10,620 units in the U.S. last year, and 33% more than that in Europe. The X3 sold about 156,000 units in the U.S. and Europe last year compared to the X4\'s roughly 26,000. At the beginning of this year we heard that BMW could eliminate the 4 Series and 8 Series for a reborn 6 Series lineup. So in addition to the massive expense of electrification forcing ruthless decisions, there are clearly other, larger ideas in play.','The first Neue Klasse model is due in 2025, presumed to be the new i3 and iX3. The iX4 is anticipated the following year. They will take advantage of BMW\'s Gen6 batteries with cylindrical cells that are less expensive to produce and create fewer carbon emissions in production, increase vehicle range by 30% compared to BMW\'s current prismatic cells, weigh up to 20% less, and raise charging speeds to 270 kW. ','If the supplier source who spoke to Inside EVs can be believed, we won\'t need to wait for 2025 for more big X4 news. The supplier said it would be providing parts for two new electric crossovers with quad-motor setups. One would be an iX3, the other an iX4, the supplier adding that \"other code designations\" suggest these could be proper M versions of standard electric crossovers. Curioser and curioser.'),(2,'2023 BMW M2 gets full range of M Performance parts','RONAN GLON','The catalog includes body kits, wheels, and suspension parts','Unveiled in October 2022, the new 2023 BMW M2 is already eligible to receive a wide selection of factory-developed M Performance parts. The list of add-ons announced by the Munich-based carmaker includes carbon fiber trim, forged wheels, and a lighter exhaust system.BMW M leveraged its five decades of racing experience to give future M2 owners numerous customization options. Starting with the outside, the company designed a carbon fiber body kit that includes a front splitter, inserts for the front air intakes, side skirts, a rear spoiler, and a rear diffuser. M Performance titanium and carbon fiber exhaust tips and a red M-branded tow strap add a finishing touch to the look.','2022-11-02 07:02:56','img','Speaking of the exhaust, BMW M created a system that consists of stainless-steel front pipes and a titanium rear section. It weighs over 17 pounds less than the stock setup and includes electronically-controlled flaps. BMW promises it unlocks \"distinct, racing car acoustics.\"','Handling is one of BMW M\'s claims to fame, and the M Performance suspension system was designed to make the new M2 even sharper to drive on and off the track. It adds height-adjustable spring cups to the front and rear axles that let drivers lower the ride height by nearly half an inch. These parts can be paired with 20-inch front and 21-inch rear forged wheels available in M Frozen Gold Bronze or M Jet Black.Buyers who want an M-themed interior can add carbon fiber and Alcantara trim, Alcantara-upholstered knee pads, carbon fiber seatback covers for the M Sport seats, and an M Performance steering wheel. Carbon fiber shift paddles and door sill plates are offered as well.','None of the M Performance parts increase the engine\'s output, so enthusiasts who want to dial in more power will need to turn to the aftermarket. Stock, the M2 is powered by a twin-turbocharged, 3.0-liter straight-six rated at 453 horsepower and 406 pound-feet of torque. The engine spins the rear wheels via a six-speed manual transmission, though an eight-speed automatic is offered at an extra cost.','The aforementioned parts will be available to order when M2 deliveries begin in April 2023. Pricing hasn\'t been announced yet.'),(3,'Apple lends prestige to a new Mercedes-Benz music system','Stephen Williams','The high-end package, with 31 loudspeakers, features Apple Music\'s Spatial Audio','Not often does Apple offer its name to products or technologies that emerge from beyond the walls of Fortress Cupertino. Could a partnership with Mercedes-Benz signal the start of something new?','2022-11-02 07:02:56','img','Mercedes’ new audio system unveiled this past weekend centers on the integration of Apple Music’s Spatial Audio with Dolby Atmos surround — a music enhancement generally heard in headphone-listening environments — in selected (and optional) Burmester audio systems. Plans are to offer the components initially in the Mercedes-Maybach S-Class, the S-Class as well as the EQE, EQE SUV, EQS and EQS SUV. No prices for the options were announced.','Also partnering in the project is the Universal Music Group, one of the world’s leading music publishers.According to Mercedes, the Dolby tech “empowers musicians and audio engineers to place discrete audio elements or objects in a three-dimensional sound field, which goes beyond the capabilities of standard stereo productions. The system adapts to any playback environment, meaning fans can listen to music with unparalleled clarity that matches the artist\'s original vision in the studio.”','We would expect no less from 31 speakers in a Maybach, powered by a pair of amps churning out 1,750 watts. Imagine Keith Richards cranking guitar through 31 speakers in an enclosed car cockpit.Almost three out of four music consumers say that they listen mostly in a car, says Mercedes Chairman Ola Källenius. ”Through this exceptional partnership, we are giving our customers the extraordinary in-car audio experience they expect from Mercedes-Benz,” he said. “Both Apple and UMG share our vision and values and, together with Dolby, we will create a seamless and unique experience for our customers.\"','Partnerships between automakers and audio companies have become common in recent years, and there have been mixed results from systems attempting to create a \"surround sound\" experience that goes beyond stereo. Bose put its systems into General Motors cars decades ago, and others followed, including Bowers & Wilkins, JBL and even the legendary McIntosh. Most recently, famed Italian speaker designer Sonon faber found a home in Maserati\'s new Grecale, and British speaker maker KEF is working with Lotus.'),(4,'It\'s powered by a production-based Coyote V8 engine','ZAC PALMER','2024 Ford Mustang dons a racing suit for Australia\'s Supercars series','Ford announced six track-bound variants of the 2024 Mustang developed for a wide range of series, but that\'s not all the Blue Oval has up its sleeve. It unveiled a racing-ready model online that was designed to compete in Australia\'s popular Supercars series starting in 2023.','2022-11-02 07:02:56','img','Introduced at the Mount Panorama track in New South Wales, the first racing version of the seventh-generation Mustang was developed by Ford Performance and homologation team Dick Johnson Racing. It complies with the Gen3 regulations that will come into effect in 2023, so it\'s relatively close to the street-legal model in terms of overall design. It nonetheless gains a full body kit that includes a front splitter, side skirts, a massive rear spoiler and a wide air diffuser, and it rides on 18-inch center-locking wheels wrapped by Dunlop Sport Maxx tires.','The resemblance is only skin-deep. Engineers used carbon fiber extensively to keep weight in check and stripped the production car\'s interior. They then added a full roll cage, a racing seat, a quick-release steering wheel and a digital instrument cluster, among other parts.','Ford hasn\'t released much in the way of technical specifications. It claims that power comes from a \"production-based\" Coyote V8 engine, though a peek under the hood reveals numerous modifications including a carbon fiber air intake system and aluminum radiator. And, the engine exhales through a new exhaust system that features a pair of cool-looking outlets that poke through the driver-side side skirt.','Australian enthusiasts will get the chance to see the Mustang in action for the first time during the 2022 Bathurst 1000. The coupe won\'t participate in the race; it will hit the track for a couple of demonstration laps. Its competition debut is scheduled for 2023. The list of teams that will race it include Dick Johnson Racing, Tickford Racing, Grove Racing, Blanchard Racing Team and Walkinshaw Andretti United.'),(5,'Honda Prologue electric SUV previewed with first official photo','REESE COUNTS','Here\'s the first Honda EV to come from its collaboration with GM','Honda has talked about its EV collaboration with GM for a long while now, but today we finally get our first glimpse of something physical to come from it. And by physical, we mean a computer rendering.This is the first official photo of the Honda Prologue, which Honda intends to launch in 2024. When it comes to seeing Honda products before they are officially launched in prototype or production form, this is a rather early sneak peek — Honda tends to keep its products close to its vest. Unfortunately, the single photo of the Prologue you’re looking at here is the meat of the news today. But even left at that, it allows us a neat little preview of this SUV.','2022-11-02 07:02:56','img','The Prologue is the result of a collaboration between designers in Honda’s Los Angeles design studio and back in Japan. Conceptually, the idea was to make an SUV that looked modern and fresh, but also looks comfortable in a showroom of existing Honda vehicles. Honda says that aerodynamics were a huge focus of the project, because better aero means more range and reduced cabin wind noise. As a result, Honda says it ended up with simple, clean surfaces for its body lines.',' “As the project leader for the exterior design of the Honda Prologue, it was very exciting to work with a young team of designers with new capabilities to create an SUV with clean, simple lines and a strong influence from our global EV models, including the Honda E,” said Jiro Ikeda, exterior design leader. “We balanced that with a neo-rugged look that you see in our current lineup, to ensure Prologue represents a true Honda EV.”','You’ll notice in the single photo that the Prologue has a long wheelbase, short front overhang and meaty-looking tires. There’s an opening for air to pass through the lower portion of the bumper, but there’s no dramatic grille. It has a relatively narrow black band of a shield that runs across the front of the car, and this piece contains both the headlights on the edges and the Honda logo front and center. Lots of black-painted accents all over give this silver car a classy and eye-catching two-tone look, and the oversize wheels are drawing a lot of attention to themselves in this photo. Lastly, we’ll point out that the charging port is prominently placed in the front fender.In Honda’s presentation to media, it said that the first priority for Prologue sales will be Zero Emission Vehicle (ZEV) states, but expect it to roll it out elsewhere soon after. Honda didn’t say exactly where it will produce the Prologue yet, but says it will be able to assemble the vehicle in its own facilities. Following the launch of the Prologue, Honda says the first EVs built on its own in-house Honda e:Architecture will arrive in 2026.','To support the Prologue and future EVs, Honda says it’s going to be installing Level 2 and fast-charging stations in dealerships across the country. Honda also showed us a few photos of a new design language for dealers, below. The white and blue color scheme remains, but the building itself looks far more modern and futuristic than Honda’s current dealers. Before all you eagle-eyed commenters point it out in the pictures, yes, we also see the Euro market Honda HR-V/Japan market Honda Vezel in the photos of a U.S.-intent dealership.'),(6,'Toyota Land Cruiser 300-series gets life-size Lego build','BEN HSU','It\'s comprised of 440,000 bricks and weighs 4,520 pounds.','Whenever a hot car debuts nowadays, the inevitable life-size Lego build follows soon after. Like Nürburgring lap times for supercars, it\'s practically an expected part of the vehicle launch routine. The latest vehicle to get the treatment is the not-for-America 300-series Toyota Land Cruiser. The build was commissioned by Al-Futtaim Motors, the exclusive Toyota importer for the the United Arab Emirates.','2022-11-02 07:02:56','img','According to a short video about the project, it took a team of 12 individuals 2,688 hours to complete the 1:1 scale sculpture. A total of 440,000 Lego bricks were used in the build, and the finished Cruiser weighs approximately 4,520 pounds, or about 1,000 lighter than the actual 5,600-pound truck. Everything, including the windows, tires and little Toyota oval logos found throughout the car, are made from Lego bricks. The headlights and taillights are even illuminated, backlit from some kind of interior light source. Built around a simple steel frame for rigidity, it measures about 195 inches long, 78 inches wide and 76 inches tall.','In the shopping center where it\'s on display, a real 300-series Land Cruiser parks beside it. While the Land Cruiser isn\'t sold here, Toyota has promised us a Lexus LX 600 variant with all the off-road prowess the chassis promises. Under the hood will reside a twin-turbo 3.5-liter V6 making 409 horsepower and 479 pound-feet of torque as its only engine option; the Cruiser\'s diesel and 4.0-liter naturally aspirated V6 aren\'t coming. ','In the rest of the world the 300-series has been well received as a proper evolution of Toyota\'s legendary off-roader, so hopefully that presages good things for the Lexus. The Land Cruiser follows in the fine tradition of other life-size Lego builds. Past unrelated projects have sprouted up all over the globe, and the plastic brick subject matter has included a Formula One car, Lamborghini Sian, Ferrari SF70H, Bugatti Chiron, Toyota Camry, McLaren Senna, Chevy Silverado Trail Boss and Toyota Supra.',NULL),(7,'2024 Mercedes-AMG C 63 S E Performance makes 671 horsepower','JONATHON RAMSEY','Plug-in hybrid has the most powerful four-cylinder ever produced','The V8-powered Mercedes-AMG C 63 is going away, and that\'s definitely sad. But to make up for it, the 2024 Mercedes-AMG C 63 S E Performance is offering a lot more power. The turbocharged, plug-in hybrid four-cylinder now makes a combined output of 671 horsepower and 752 pound-feet of torque. Those are increases of 168 horsepower and 236 pound-feet of torque.','2022-11-02 07:02:56','img','Making this power is a combination of the highest-output M139l engine yet and a rear-mounted electric motor with a two-speed transmission. The engine, while similar to what\'s used in the C 43, gets a bigger version of the electrically-assisted turbo to make 469 horsepower and 402 pound-feet of torque, making it the most potent four-cylinder in the world. That engine sends power through a nine-speed automatic to the rear axle where the electric motor is. The electric motor makes 201 horsepower and has a two-speed transmission itself, which changes gear around 87 mph. The electric motor also features an electronically-controlled limited-slip differential. Power can be sent either to the rear wheels or back up to the front, enabling all-wheel drive in gas, hybrid or electric driving scenarios. The all-wheel drive system does have a drift mode, like other AMG models. Performance is impressive with a claimed 3.3-second time to hit 60 mph, and top speed of 174 mph (standard top speed is electronically limited to 155 mph).','The C 63 S E Performance is a plug-in hybrid, but just barely. It has the same 6.1-kWh capacity as the AMG GT 63 S E Performance 4-Door. In that model, it provides just 7 miles of range. The C 63\'s smaller size might get it a little more range, but likely very little. The battery weighs 196 pounds and features liquid cooling, with coolant running between every single cell. The battery will also power the C 63 to 81 mph without the engine kicking in.Naturally, AMG made chassis upgrades, too. The suspension uses steel springs with electronically adjustable shocks. Four-wheel steering is standard, as are steel brakes with six-piston calipers up front and single-piston units at the rear. Interestingly, the C 63 S is 3.3 inches longer overall compared to a regular C-Class sedan, and its wheelbase is longer by 0.4 inch. Width is also 3 inches greater. Wheels are 19 inches as standard, but 20-inch wheels are available as options.','And of course, the C 63 S E Performance looks meaner, too, with a redesigned front fascia, new hood vent, side skirts, rear bumper with diffuser and a rear spoiler. The interior gets AMG-specific trim and sport seats, with even more aggressive seats available optionally. New display readouts are included, too for the car\'s hybrid functions. Pricing and availability of this hot C-Class have not yet been announced. With a model year of 2024, we would expect it to launch sometime in the coming year.',NULL),(8,'It\'s a sedan inspired by a van inspired by a spaceship','JONATHON RAMSEY','2023 Hyundai Grandeur (Azera) revealed with futuristic makeover for overseas markets','Hyundai\'s American division deep-sixed the Azera after the 2017 model year, but the big sedan lives on in several overseas markets. Known as the Grandeur in some countries, including its home market of Korea, it enters its seventh generation with a futuristic exterior design that will leave no one indifferent.','2022-11-02 07:02:56','img','Up front, the new Azera borrows styling cues from the head-turning Staria introduced in 2021 — a sedan influenced by a van inspired by a spaceship is admittedly a difficult plot to follow, but bear with us. The front end is relatively low and flat, and it\'s characterized by a massive grille flanked by a pair of rectangular headlights. The thin LED strip above the grille accentuates the Azera\'s width and illustrates Hyundai\'s Seamless Horizon design language. Out back, this approach to design brings an elegant curved trunk lid and a second strip of LEDs.','Designers bucked the \"four-door coupe\" trend and gave the Azera a relatively upright roof line that clears up a generous amount of interior space. The sheet metal begins to slope towards the trunk above the rear wheels, and this created space for a third side window. Hyundai notes that this feature is a tribute to the original Grandeur, which made its debut in 1986 as a badge-engineered Mitsubishi Debonair.','The cabin reflects Hyundai\'s ongoing upmarket shift, both in terms of the materials used and in terms of technology. The driver faces a steering wheel inspired by the one fitted to the original Grandeur and a digital instrument cluster connected to the infotainment system\'s touchscreen. Look closely and you\'ll spot some cool design details: South Korea\'s approach to design notably inspired the pattern on the door panels. Real wood trim, aluminum accents, and naturally-dyed leather upholstery add a finishing touch to the upscale look and feel.','Hyundai hasn\'t released technical specifications, though we\'re guessing the engine palette will include four- and six-cylinder units. Front-wheel drive will come standard, and all-wheel drive will likely be offered at an extra cost in some markets. More details about the 2023 Hyundai Azera will emerge in the coming months and deliveries are scheduled to start in the second half of 2023 — but not in the United States. Nothing suggests the Azera will make a comeback here, where crossovers and SUVs represent the bulk of Hyundai\'s sales.');
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
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `passwordresettoken_userid_foreign` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `rolename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `imgpath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `room_typeroomid_foreign` (`typeRoomId`),
  KEY `FKdgcd3aoxm5w6scd8hupsjo1pq` (`carId`),
  CONSTRAINT `FKdgcd3aoxm5w6scd8hupsjo1pq` FOREIGN KEY (`carId`) REFERENCES `car` (`id`),
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
-- Table structure for table `roomdetailplayer`
--

DROP TABLE IF EXISTS `roomdetailplayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomdetailplayer` (
	`id` int NOT NULL AUTO_INCREMENT,
  `roomId` int NOT NULL,
  `userId` int NOT NULL,
  `userBid` int NOT NULL,
  `timeBid` datetime NOT NULL,
  `winner` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roomdetailplayer_roomid_foreign` (`roomId`),
  KEY `roomdetailplayer_userid_foreign` (`userId`),
  CONSTRAINT `roomdetailplayer_roomid_foreign` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`),
  CONSTRAINT `roomdetailplayer_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomparticipant`
--

LOCK TABLES `roomdetailplayer` WRITE;
/*!40000 ALTER TABLE `roomdetailplayer` DISABLE KEYS */;
/*!40000 ALTER TABLE `roomdetailplayer` ENABLE KEYS */;
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
  `air_bag` varchar(255) NOT NULL,
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
INSERT INTO `user` VALUES (1,'viethoang1','$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u','Viet Hoang','0122333444','viethoang1@gmail.com','img',1,'2022-11-02 07:02:56'),(2,'viethoang2','$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u','Viet Hoang','0166232333','viethoang2@gmail.com','img',1,'2022-11-02 07:02:56'),(3,'viethoang3','$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u','Viet Hoang','0966812311','viethoang3@gmail.com','img',1,'2022-11-02 07:02:56'),(4,'viethoang','$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u','Viet Hoang','0234123123','viethoang42gmail.com','img',1,'2022-11-02 07:02:56');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `userid` int NOT NULL,
  `roleid` int NOT NULL,
  KEY `FKbo5ik0bthje7hum554xb17ry6` (`roleid`),
  KEY `FKd0xwi6psywvnj59btfns0alnm` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `verificationtoken_userid_foreign` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationtoken`
--

LOCK TABLES `verificationtoken` WRITE;
/*!40000 ALTER TABLE `verificationtoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `verificationtoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'swp490_cab'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-21 16:55:41
ALTER TABLE `swp490_cab`.`room` 
ADD COLUMN `openDate` DATE NOT NULL AFTER `createdBy`,
ADD COLUMN `startTime` TIME NOT NULL AFTER `openDate`,
ADD COLUMN `endTime` TIME NOT NULL AFTER `startTime`,
ADD COLUMN `ticketNumber` INT NOT NULL AFTER `endTime`,
ADD COLUMN `ticketPrice` INT NOT NULL AFTER `ticketNumber`;
ALTER TABLE `swp490_cab`.`user` 
ADD COLUMN `addressWallet` VARCHAR(255) NULL AFTER `createdAt`;
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('1', '1', '1', 'VinFast Lux A 2.0', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/Vinfast/e1.jpg');
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('2', '2', '2', 'Mercedes-Benz E 300 AMG', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/Mer/m1.jpg');
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('3', '3', '2', 'BMW 320i Sport Line Plus ', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/bmw/b1.jpg');
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('4', '4', '2', 'Toyota Land Cruiser Prado', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/camry/c1.jpg');
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('5', '5', '2', 'Lexus LX 570', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/lexus/l1.jpg');
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('6', '6', '2', 'Hyundai Santa Fe', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/honda/h1.jpg');
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('7', '7', '2', 'Porsche Taycan Turbo S', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/porche/p1.png');
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('8', '8', '2', 'Ferrari F8 Tributo', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/audi/a1.jpg');
INSERT INTO `swp490_cab`.`room` (`id`, `carId`, `typeRoomId`, `roomName`, `roomTime`, `createdAt`, `updatedAt`, `createdBy`, `openDate`, `startTime`, `endTime`, `ticketNumber`, `ticketPrice`, `imgPath`) VALUES ('9', '9', '2', 'Lamborghini Huracan LP610-4', '10:00:00', '2022-11-19 00:00:00', '2022-11-19 00:00:00', '1', '2022-11-19', '10:00:00', '10:30:00', '10', '50', 'assets/img/ranger/r1.jpg');
INSERT INTO `swp490_cab`.`favorite` (`id`, `userId`, `carId`, `createdAt`, `number`) VALUES ('1', '1', '1', '2022-11-19 00:00:00', '1');
INSERT INTO `swp490_cab`.`favorite` (`id`, `userId`, `carId`, `createdAt`, `number`) VALUES ('2', '1', '2', '2022-11-19 00:00:00', '2');
INSERT INTO `swp490_cab`.`favorite` (`id`, `userId`, `carId`, `createdAt`, `number`) VALUES ('3', '2', '1', '2022-11-19 00:00:00', '3');
INSERT INTO `swp490_cab`.`image` (`id`, `carId`, `imgPath`) VALUES ('1', '1', 'assets/img/Vinfast/e1.jpg');
INSERT INTO `swp490_cab`.`image` (`id`, `carId`, `imgPath`) VALUES ('2', '1', 'assets/img/Vinfast/v1.jpg');
INSERT INTO `swp490_cab`.`image` (`id`, `carId`, `imgPath`) VALUES ('3', '2', 'assets/img/Mer/m1.jpg');
