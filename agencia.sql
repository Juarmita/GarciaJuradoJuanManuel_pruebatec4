-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 01-05-2024 a las 20:21:49
-- Versión del servidor: 8.2.0
-- Versión de PHP: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agencia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flight`
--

DROP TABLE IF EXISTS `flight`;
CREATE TABLE IF NOT EXISTS `flight` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `class_type` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `departure_date` date DEFAULT NULL,
  `destination` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `origin` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `price` double NOT NULL,
  `return_date` date DEFAULT NULL,
  `airline` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `seats` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `flight`
--

INSERT INTO `flight` (`id`, `class_type`, `departure_date`, `destination`, `origin`, `price`, `return_date`, `airline`, `seats`) VALUES
(2, NULL, '2024-12-01', 'Madrid', 'Paris', 99, '2024-12-15', 'Iberia', 200),
(3, NULL, '2024-05-01', 'Madrid', 'Malaga', 500, '2024-05-07', 'Nombre de la aerolínea', 150),
(4, NULL, '2024-05-01', 'Madrid', 'Malaga', 500, '2024-05-07', 'Nombre de la aerolínea', 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flight_reservation`
--

DROP TABLE IF EXISTS `flight_reservation`;
CREATE TABLE IF NOT EXISTS `flight_reservation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `flight_id` bigint DEFAULT NULL,
  `hosts_id` bigint DEFAULT NULL,
  `class_type` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `departure_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `seats` int DEFAULT NULL,
  `id_flight` bigint DEFAULT NULL,
  `id_host` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6pavlub6ifokiw27tpqu3r14` (`flight_id`),
  KEY `FKo9qqlbao9akretq202nvq1dj` (`hosts_id`),
  KEY `FKaorff68tvbi4ys5ekyppyxpf0` (`id_flight`),
  KEY `FKm6prfhn8qxxna14c1hsd4g1v4` (`id_host`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `flight_reservation`
--

INSERT INTO `flight_reservation` (`id`, `flight_id`, `hosts_id`, `class_type`, `departure_date`, `return_date`, `seats`, `id_flight`, `id_host`) VALUES
(1, NULL, NULL, NULL, '2024-12-01', '2024-12-15', 2, 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hosts`
--

DROP TABLE IF EXISTS `hosts`;
CREATE TABLE IF NOT EXISTS `hosts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `hosts`
--

INSERT INTO `hosts` (`id`, `age`, `name`, `surname`) VALUES
(1, 30, 'John', 'Doe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

DROP TABLE IF EXISTS `hotel`;
CREATE TABLE IF NOT EXISTS `hotel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `its_reserved` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`id`, `city`, `its_reserved`, `name`) VALUES
(3, 'Malaga', b'1', 'Barcelo'),
(4, 'Malaga', b'1', 'Barcelo'),
(5, 'Malaga', b'1', 'Barcelo'),
(6, 'Algeciras', b'0', 'Maria Cristina');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `available_from` date DEFAULT NULL,
  `available_to` date DEFAULT NULL,
  `price_per_night` double NOT NULL,
  `room_type` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `id_hotel` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2sj2g46omnalavd8hkn1bokhi` (`id_hotel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`id`, `available_from`, `available_to`, `price_per_night`, `room_type`, `id_hotel`) VALUES
(1, '2023-01-01', '2023-12-31', 100, 'Doble', NULL),
(2, '2022-01-01', '2022-12-31', 150, 'Suite', 3),
(3, '2022-01-01', '2022-12-31', 150, 'Suite', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room_reservation`
--

DROP TABLE IF EXISTS `room_reservation`;
CREATE TABLE IF NOT EXISTS `room_reservation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `id_host` bigint DEFAULT NULL,
  `id_room` bigint DEFAULT NULL,
  `city` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `room_type` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpdx46adc79c3yk0cgkegq47gu` (`id_host`),
  KEY `FK6jg04hlkqgsks4ixww1s6skij` (`id_room`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `room_reservation`
--

INSERT INTO `room_reservation` (`id`, `check_in`, `check_out`, `id_host`, `id_room`, `city`, `room_type`) VALUES
(4, '2023-05-01', '2023-05-10', 1, 1, 'Algeciras', 'Doble'),
(5, '2023-05-01', '2023-05-10', 1, 2, 'Malaga', 'Suite');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `flight_reservation`
--
ALTER TABLE `flight_reservation`
  ADD CONSTRAINT `FK6pavlub6ifokiw27tpqu3r14` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
  ADD CONSTRAINT `FKaorff68tvbi4ys5ekyppyxpf0` FOREIGN KEY (`id_flight`) REFERENCES `flight` (`id`),
  ADD CONSTRAINT `FKm6prfhn8qxxna14c1hsd4g1v4` FOREIGN KEY (`id_host`) REFERENCES `hosts` (`id`),
  ADD CONSTRAINT `FKo9qqlbao9akretq202nvq1dj` FOREIGN KEY (`hosts_id`) REFERENCES `hosts` (`id`);

--
-- Filtros para la tabla `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `FK2sj2g46omnalavd8hkn1bokhi` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id`);

--
-- Filtros para la tabla `room_reservation`
--
ALTER TABLE `room_reservation`
  ADD CONSTRAINT `FK6jg04hlkqgsks4ixww1s6skij` FOREIGN KEY (`id_room`) REFERENCES `room` (`id`),
  ADD CONSTRAINT `FKpdx46adc79c3yk0cgkegq47gu` FOREIGN KEY (`id_host`) REFERENCES `hosts` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
