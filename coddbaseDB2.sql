-- --------------------------------------------------------
-- 호스트:                          ls-5cbc7d75472a47d0bcbf66bcd11afdcda919400a.cdy4okwq6zsy.ap-northeast-2.rds.amazonaws.com
-- 서버 버전:                        8.0.41 - Source distribution
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- dbCodebase 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `dbCodebase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbCodebase`;

-- 테이블 dbCodebase.cart 구조 내보내기
CREATE TABLE IF NOT EXISTS `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1054 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.cart:~54 rows (대략적) 내보내기
INSERT INTO `cart` (`id`, `member_email`) VALUES
	(200, NULL),
	(300, NULL),
	(999, 'test'),
	(1001, 'cccc'),
	(1002, 'as'),
	(1004, '45123234'),
	(1005, ''),
	(1006, 'hhh'),
	(1008, 'test@123'),
	(1009, 'wjs478wjs@naver.com'),
	(1010, 'rkdgur5381@gmail.com'),
	(1011, 'rkdgur5381@gmail.com'),
	(1012, 'rkdgur5381@gmail.com'),
	(1013, 'rkdgur5381@gmail.com'),
	(1014, 'oosssok1@nate.com'),
	(1015, 'wjs478wjs@gmail.com'),
	(1016, 'register'),
	(1017, 'wjs478wjs'),
	(1018, 'oosssok1@gmail.com'),
	(1019, 'a@a'),
	(1020, 'oosssok1@gmail.com'),
	(1021, 'qwqw'),
	(1022, 'oosssok1@gmail.com'),
	(1023, 'dwdw'),
	(1024, 'dmswl22@222'),
	(1025, 'rkdgur5381@knu.ac.kr'),
	(1026, 'ww'),
	(1027, 'rkdgur5381@naver.com'),
	(1028, 'rkdgur5381@gmail.com'),
	(1029, 'test@test.com'),
	(1030, 'hanjunghyun95@gmail.com'),
	(1031, 'djg02031@naver.com'),
	(1032, 'rkdgur5381@kakao.com'),
	(1033, 'hanjunghyun95@gmail.com'),
	(1034, 'rkdgur5381@gmail.com'),
	(1035, 'rkdgur5381@naver.com'),
	(1036, 'qwer@qwer.com'),
	(1037, 'qwer@qwer.com'),
	(1038, 'wjs478wjs@naver.com'),
	(1039, 'wjs478wjs@naver.com'),
	(1040, 'wjs478wjs@gmail.com'),
	(1041, 'wjs478wjs@gmail.com'),
	(1042, 'wjs478wjs@gmail.com'),
	(1043, 'wjs478wjs@gmail.com'),
	(1044, 'test@test.com'),
	(1045, 'dawnzeze@gmail.com'),
	(1046, 'rkdgur5381@kakao.com'),
	(1047, 'kuji5256@naver.com'),
	(1048, 'kuji5256@naver.com'),
	(1049, 'kuji5256@naver.com'),
	(1050, 'kuji5256@naver.com'),
	(1051, 'kuji5256@naver.com'),
	(1052, 'rkdgur5381@gmail.com'),
	(1053, 'oosssok1@naver.com');

-- 테이블 dbCodebase.cartitem 구조 내보내기
CREATE TABLE IF NOT EXISTS `cartitem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` bigint DEFAULT NULL,
  `project_id` int DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cartitem_ibfk_1` (`cart_id`),
  KEY `FK_cartitem_project` (`project_id`),
  CONSTRAINT `cartitem_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cartitem_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.cartitem:~5 rows (대략적) 내보내기
INSERT INTO `cartitem` (`id`, `title`, `price`, `project_id`, `cart_id`) VALUES
	(187, '대구 중구 가로등 구성 3D 데이터 및 분석 샘플', 5000000, 142, 1031),
	(188, 'test', 1000, 144, 300),
	(192, 'MX클린 세탁기 내장 프로세스', 4500000, 143, 300),
	(193, 'Melody Sense 맞춤형 음악 분석', 25000000, 149, 300),
	(194, '대구 중구 가로등 구성 3D 데이터 및 분석 샘플', 5000000, 142, 300);

-- 테이블 dbCodebase.chatroom 구조 내보내기
CREATE TABLE IF NOT EXISTS `chatroom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NOT NULL COMMENT '채팅방 생성일',
  `title` varchar(50) NOT NULL COMMENT '채팅방 제목',
  `DM` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='채팅방';

-- 테이블 데이터 dbCodebase.chatroom:~29 rows (대략적) 내보내기
INSERT INTO `chatroom` (`id`, `createdDate`, `title`, `DM`) VALUES
	(46, '2024-12-03 00:00:00', 'ㅎㄹㅇㅎㅀㅇㄹ', 0),
	(54, '2024-12-04 00:00:00', '리액트공부방', 0),
	(55, '2024-12-04 00:00:00', '111', 0),
	(56, '2024-12-04 00:00:00', '1111', 0),
	(57, '2024-12-04 00:00:00', '공부방', 0),
	(60, '2024-12-11 00:00:00', 'wjs478wjs@gmail.com, 이강혁카카오', 0),
	(118, '2024-12-12 00:00:00', '전현식, 이강혁카카오', 0),
	(119, '2024-12-13 00:00:00', 'ddd', 0),
	(120, '2024-12-16 00:00:00', '채팅방', 0),
	(121, '2024-12-16 00:00:00', '와앙ㅇ', 0),
	(125, '2024-12-17 00:00:00', 'test, 이강혁카카오', 0),
	(128, '2024-12-17 00:00:00', 'test', 0),
	(129, '2024-12-17 00:00:00', 'test', 1),
	(130, '2024-12-17 00:00:00', '초대방', 0),
	(131, '2024-12-17 00:00:00', 'naver', 0),
	(132, '2024-12-17 00:00:00', 'test', 1),
	(133, '2024-12-17 00:00:00', '이강혁네이, 물복, 전현식', 0),
	(134, '2024-12-23 00:00:00', '월요일', 1),
	(137, '2024-12-24 00:00:00', '이강혁카카오ㅎ', 1),
	(138, '2024-12-24 00:00:00', '이강혁카카오ㅎ', 1),
	(142, '2024-12-24 00:00:00', '123', 0),
	(145, '2024-12-24 15:45:16', '이강혁네이', 1),
	(146, '2024-12-24 20:39:51', '이강혁카카오ㅎ', 1),
	(147, '2024-12-27 14:24:43', 'ㅅㄷㄴㅅㅎㄹㅇㄶ', 0),
	(158, '2024-12-30 20:32:27', '이강혁네이', 1),
	(159, '2024-12-30 20:32:55', 'test', 0),
	(160, '2025-01-14 20:40:29', 'setgfdsgd', 0),
	(161, '2025-01-19 11:30:07', '코딩공부', 0),
	(162, '2025-02-09 23:33:54', 'mx문의', 0);

-- 테이블 dbCodebase.comments 구조 내보내기
CREATE TABLE IF NOT EXISTS `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` bigint NOT NULL DEFAULT '0',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('active','inactive') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'active',
  `ip_address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.comments:~44 rows (대략적) 내보내기
INSERT INTO `comments` (`id`, `post_id`, `content`, `author`, `created_at`, `updated_at`, `status`, `ip_address`) VALUES
	(1, 35, 'ㄹㄹ 수정 ..\n\n', '전현식', '2025-01-06 03:36:42', '2025-01-06 03:47:06', 'active', NULL),
	(2, 34, '222', 'dmswl', '2025-01-06 03:44:34', '2025-01-06 03:44:34', 'active', NULL),
	(3, 34, '333', 'dmswl', '2025-01-06 03:44:37', '2025-01-06 03:44:37', 'active', NULL),
	(4, 35, '111', 'dmswl', '2025-01-06 03:44:46', '2025-01-06 03:44:46', 'active', NULL),
	(7, 35, 'ㅁㄴㅇ', '전현식', '2025-01-06 03:50:54', '2025-01-06 03:50:54', 'active', NULL),
	(9, 35, 'ㄴㅁㅇㅁㄴ', '전현식', '2025-01-06 03:53:53', '2025-01-06 03:53:53', 'active', NULL),
	(10, 35, '테스트', '전현식', '2025-01-06 03:58:04', '2025-01-06 03:58:04', 'active', NULL),
	(17, 32, 'ㄴㄹㅇㄹㅁ', '전현식', '2025-01-06 04:15:20', '2025-01-06 04:16:05', 'active', NULL),
	(28, 38, 'ㅁㄴㅇㅁ1234', '전현식', '2025-01-06 04:53:52', '2025-01-06 04:53:58', 'active', NULL),
	(34, 16, '리액트공부1234', '전현식', '2025-01-06 05:04:16', '2025-01-06 05:04:21', 'active', NULL),
	(35, 16, '리액트', '전현식', '2025-01-06 05:04:28', '2025-01-06 05:04:28', 'active', NULL),
	(36, 40, '캐나다큐브', '전현식', '2025-01-06 05:11:51', '2025-01-06 05:11:51', 'active', NULL),
	(37, 40, '고바슨', '전현식', '2025-01-06 05:11:56', '2025-01-06 05:11:56', 'active', NULL),
	(39, 41, 'ㅇㅇㅁ', '전현식', '2025-01-06 11:20:29', '2025-01-06 11:20:29', 'active', NULL),
	(40, 41, 'ㅁㅁㅁ', 'g테스트', '2025-01-06 11:20:35', '2025-01-06 11:20:35', 'active', NULL),
	(43, 42, 'asd', '전현식', '2025-01-13 05:14:22', '2025-01-13 05:14:22', 'active', NULL),
	(45, 42, 'asdas222', '전현식', '2025-01-13 05:47:50', '2025-01-13 05:47:55', 'active', NULL),
	(105, 51, '12324', '전현식', '2025-01-13 12:22:03', '2025-01-13 12:22:03', 'active', NULL),
	(106, 51, 'ㅁㄴㅇㅁㄴㅇ', '전현식', '2025-01-13 12:25:23', '2025-01-13 12:25:23', 'active', NULL),
	(111, 55, 'ㅁㅁ', '전현식', '2025-01-13 12:35:51', '2025-01-13 12:35:51', 'active', NULL),
	(126, 54, 'ㅁㄴㅇㅁㄴㅇ', 'Anonymous', '2025-01-13 13:27:39', '2025-01-13 13:27:39', 'active', NULL),
	(127, 54, 'ㄴㅁㅇ', '전현식', '2025-01-13 13:28:35', '2025-01-13 13:28:35', 'active', NULL),
	(144, 53, '테스트', '전현식구글', '2025-01-13 13:42:47', '2025-01-13 13:42:47', 'active', NULL),
	(147, 53, 'ㅇㅇ', '전현식', '2025-01-13 13:45:20', '2025-01-13 13:45:20', 'active', NULL),
	(149, 57, 'asds1234', '전현식구글', '2025-01-14 10:49:52', '2025-01-14 10:49:55', 'active', NULL),
	(152, 58, '댓글 새로고침 없이 수정삭제 하는거 실패.,', '전현식', '2025-01-14 11:06:25', '2025-01-14 11:06:58', 'active', NULL),
	(153, 58, 'ㅇㅁㄴㅇ', '전현식', '2025-01-14 11:17:15', '2025-01-14 11:17:15', 'active', NULL),
	(155, 58, 'test', '이강혁카카오', '2025-01-14 11:36:19', '2025-01-14 11:36:19', 'active', NULL),
	(156, 58, 'gsdfg', '이강혁카카오', '2025-01-14 11:36:32', '2025-01-14 11:36:32', 'active', NULL),
	(157, 58, 'gdfg', '이강혁카카오', '2025-01-14 11:37:04', '2025-01-14 11:37:04', 'active', NULL),
	(158, 58, 'gdsfgdfsg', '이강혁카카오', '2025-01-14 11:40:49', '2025-01-14 11:40:49', 'active', NULL),
	(159, 58, 'gdsfgdfgdfg', '이강혁카카오', '2025-01-14 11:42:31', '2025-01-14 11:42:31', 'active', NULL),
	(163, 58, 'tsetgdsfgdfg', '이강혁카카오', '2025-01-14 11:50:48', '2025-01-14 11:50:48', 'active', NULL),
	(165, 58, '2345345345gsdfgdfsgdfgd', '이강혁카카오', '2025-01-14 11:51:03', '2025-01-14 11:51:08', 'active', NULL),
	(167, 58, 'ㅈ', '전현식', '2025-01-14 11:53:48', '2025-01-14 11:53:48', 'active', NULL),
	(169, 58, 'tesgfdsgggdfgdfg1235434254353456', '이강혁카카오', '2025-01-14 11:56:24', '2025-01-14 11:56:29', 'active', NULL),
	(170, 58, '테스트', '전현식구글', '2025-01-19 01:54:50', '2025-01-19 01:54:50', 'active', NULL),
	(171, 59, 'ㄴㅇ123', '전현식구글', '2025-01-19 01:55:08', '2025-01-19 01:55:12', 'active', NULL),
	(172, 59, '123ㅇㅇㄴㅇㅇㅇㅇ', '전현식구글', '2025-01-19 02:05:38', '2025-01-19 02:06:25', 'active', NULL),
	(185, 60, 'ㅇ', '전현식구글', '2025-01-19 02:25:49', '2025-01-19 02:25:49', 'active', NULL),
	(190, 62, '어려워요', '전현식구글', '2025-01-22 11:36:43', '2025-01-22 11:36:43', 'active', NULL),
	(196, 63, 'asdsad', '전현식구글', '2025-02-21 12:30:14', '2025-02-21 12:30:14', 'active', NULL),
	(197, 63, '1', '전현식구글', '2025-02-21 12:30:27', '2025-02-21 12:30:27', 'active', NULL),
	(199, 64, 'ddd', 'dmswl', '2025-03-26 08:18:15', '2025-03-26 08:18:15', 'active', NULL);

-- 테이블 dbCodebase.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '기본키',
  `name` varchar(50) DEFAULT NULL COMMENT '사용자 이름',
  `email` varchar(50) DEFAULT NULL COMMENT '이메일',
  `password` varchar(200) DEFAULT NULL COMMENT '비밀번호',
  `role` tinyint(1) DEFAULT '0',
  `addr` varchar(200) DEFAULT NULL COMMENT '주소',
  `postcode` varchar(50) DEFAULT NULL,
  `createdAt` date DEFAULT NULL COMMENT '가입일',
  `ProjectCount` int DEFAULT '3',
  `tel` varchar(50) DEFAULT NULL COMMENT '전화번호',
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_member_cart` (`cart_id`),
  CONSTRAINT `FK_member_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.member:~27 rows (대략적) 내보내기
INSERT INTO `member` (`id`, `name`, `email`, `password`, `role`, `addr`, `postcode`, `createdAt`, `ProjectCount`, `tel`, `cart_id`) VALUES
	(1006, 'dmswl', 'dmswl@codebase.co', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 1, ' ', '', '2024-11-23', 3, '111', NULL),
	(1011, 'ccccc', 'cccc', '$2a$10$Eo0EG3fIIe3SUN3zzZKRP.zUXKwFSrQtn.wiEEz7SO7UUMeT2X6ie', 0, '부산 해운대구 APEC로 17 (우동, 센텀리더스마크) c', '48060', '2024-11-23', 3, 'cccc', 1001),
	(1015, 'as', 'as', '$2a$10$G20/eLpeFfPlor5Zpj0bDu47uIDdH0ck9.9x7USISbCUMmE4.0giq', 0, ' as', '', '2024-11-24', 3, 'as', 1002),
	(1021, '1231q2451', '45123234', '$2a$10$UeM.QhGRNLU6fLiIekaeb.e6D3LFHo/O6LtN.We26Bkp9a/wD0rD6', 0, ' asdf', '', '2024-11-24', 3, ' 5631sd', 1004),
	(1023, 'hhh', 'hhh', '$2a$10$cNqwk5ABVEE8q9frfBFikexITWzPCTp9pyhDsUzutZI5NnVW.RTVu', 0, ' ', '', '2024-11-25', 3, 'hhh', 1006),
	(1025, 'test123', 'test@123', '$2a$10$CnF4oOimcKZonmFO8b9Ht.32JayhrqvAzGCNq/s7gTIxkKx0ZQTGW', 0, '대구 남구 희망로 55 (이천동, 이천뜨란채2단지 상가동) 1234', '42435', '2024-11-27', 3, '1234', 1008),
	(1036, '테스트', 'a@a', '$2a$10$e7fIymPrJhVh5ZaTz9kJ0eHQ3sthSGjlZF6/PwevXbfUMKrn9XrF.', 0, '대구 동구 아양로9길 14 (신암동) 123', '41198', '2024-12-05', 3, '1234', 1019),
	(1038, 'qwqw', 'qwqw', '$2a$10$OAU3pHHbSbbZf9d9uFHY.OjDiEMfn1OR5hOT/bG.KP7q2kxzb7aua', 0, ' qwqw', '', '2024-12-10', 3, 'qwqw', 1021),
	(1039, '테스트1', 'oosssok1@gmail.com', NULL, 0, '대구 동구 아양로9길 35 (신암동, 동대구 에일린의 뜰) 111', '41195', '2024-12-11', 3, '1234', 1022),
	(1041, '김은지', 'dmswl22@222.co', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 0, ' ', '', '2024-12-13', 3, '', 1024),
	(1043, 'ww', 'ww', '$2a$10$ZQZ7eJ0i1o.ZDOSykSjMROkq.MLRKw5zABP3qp802uvWBgQZk5wjO', 0, ' ', '', '2024-12-16', 3, 'ww', 1026),
	(1048, 'g테스트', 'djg02031@naver.com', '$2a$10$DxU0VdVi1dg1SLj5geWu0Ox9oewpS5Vg2d09fpNdQVvwBOEiVdh4y', 0, 'undefined s', NULL, '2024-12-17', 3, '01000000000', 1031),
	(1050, '한정현', 'hanjunghyun95@gmail.com', '$2a$10$sG2k4uQmMzBkszysiaOKlOXSgJ8C9/lk21sc6Fk5/BArnIbLxjxlS', 0, 'undefined ', NULL, '2024-12-17', 3, '01000000000', 1033),
	(1052, '이강혁네이', 'rkdgur5381@naver.com', '$2a$10$NkzGzzXB2y4Ps7Blwdf.fujz.rCh5j3O7f6eIwcMOvpEMW4GBW0Wu', 1, '대구 남구 희망로 55 (이천동, 이천뜨란채2단지 상가동) 2124434', '42435', '2024-12-17', 3, '12348545153', 1035),
	(1056, '전현식', 'wjs478wjs@naver.com', NULL, 0, '대구 달서구 장기로 145 (본리동, 성당 래미안 e-편한세상) ', '42675', '2024-12-22', 3, '01047564748', 1039),
	(1060, '전현식구글', 'wjs478wjs@gmail.com', '$2a$10$Un5uq1ZKYDjVRtPZMdd21OsXW20XyOj4S6EXBOaB9CYr4ROiZkUT6', 0, '대구 달서구 장기로 145 (본리동, 성당 래미안 e-편한세상) 208동 1303호', '42675', '2024-12-22', 3, '01047564748', 1043),
	(1063, 'ssss2', 'test@test.com', '$2a$10$il99wANMudMCqvxpFmEw6ObWeGpuNuDrEdOWOhC5T9l163bbZupsO', 1, 'undefined ', NULL, '2024-12-23', 3, '1045', 200),
	(1064, '이강혁카카오', 'rkdgur5381@kakao.com', '$2a$10$xX1sSF/HLA8z3qx/xFJy5emPldeABsZoBAr9LYAu9cgkMTI6FY9oC', 0, '대구 남구 희망로 55 (이천동, 이천뜨란채2단지 상가동) ㅎㄹㅇㅎ', '42435', '2024-12-24', 3, '12345678990', 1046),
	(3000, 'user1', 'dw@dw.dw', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 0, ' ', '', '2024-12-26', 3, '111', 300),
	(3002, 'user3', 'lll@ll.ll', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 0, ' ', '', '2024-12-26', 3, '111', 300),
	(3003, 'user4', 'llll@ll.ll', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 0, ' ', '', '2024-12-26', 3, '111', 300),
	(3004, 'user5', 'lllll@ll.ll', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 0, ' ', '', '2024-12-26', 3, '111', 300),
	(3005, 'dmswl3', 'dmswl33@codebase.co', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 0, '대구 중구 경상감영길 2 (서문로1가) 111', '41919', '2025-01-16', 3, '00000000000', 1047),
	(3006, 'dmswl4', 'dmswl44@codebase.co', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 0, '대구 중구 경상감영길 2 (서문로1가) 111', '41919', '2025-01-17', 3, '00000000000', 1048),
	(3009, 'dmswl5', 'dmswl55@codebase.co', '$2a$10$Tk2VuWKuBDn/oSbpO84KVOOMsp4rkTws7QyQ1npvh0QHn5qi2FqGi', 0, '부산 부산진구 가야공원로 1 (가야동) 111', '47326', '2025-01-17', 3, '00000000000', 1051),
	(3010, '이강혁구글', 'rkdgur5381@gmail.com', NULL, 0, NULL, NULL, '2025-01-13', 3, NULL, 1052),
	(3011, 'mmmm', 'oosssok1@naver.com', '$2a$10$K.olGL4JyBEIX5a0eB5lzunuEG1b2RNAJYdNsk1MqVEfDoqJeI/PW', 0, '대구 동구 동대구로 465 (신천동) ', '41260', '2025-02-06', 3, '01072007998', 1053);

-- 테이블 dbCodebase.memberchatroom 구조 내보내기
CREATE TABLE IF NOT EXISTS `memberchatroom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member` int NOT NULL,
  `chatroom` int NOT NULL,
  `lastCheckedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chatroom` (`chatroom`),
  KEY `member` (`member`),
  CONSTRAINT `fk_chatroom` FOREIGN KEY (`chatroom`) REFERENCES `chatroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `member` FOREIGN KEY (`member`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.memberchatroom:~37 rows (대략적) 내보내기
INSERT INTO `memberchatroom` (`id`, `member`, `chatroom`, `lastCheckedAt`) VALUES
	(43, 1025, 46, '2024-12-03 17:58:10'),
	(119, 1041, 119, '2024-12-17 17:17:52'),
	(120, 1006, 119, '2024-12-29 18:39:59'),
	(121, 1043, 120, '2024-12-16 13:19:28'),
	(134, 1006, 128, '2024-12-29 18:40:01'),
	(136, 1052, 129, '2024-12-17 19:51:32'),
	(138, 1050, 131, '2024-12-17 19:50:57'),
	(139, 1052, 132, '2024-12-17 19:52:18'),
	(141, 1052, 133, '2024-12-17 20:24:22'),
	(144, 1056, 134, '2025-01-13 16:46:42'),
	(151, 1015, 137, '2024-12-24 13:41:33'),
	(153, 1041, 138, '2024-12-24 13:41:45'),
	(160, 1060, 142, '2025-01-21 19:57:44'),
	(165, 1052, 145, '2024-12-24 15:45:16'),
	(168, 1038, 146, '2024-12-24 20:39:51'),
	(169, 1064, 147, '2025-02-06 20:22:47'),
	(170, 1006, 147, '2024-12-30 17:55:01'),
	(171, 1038, 147, '2024-12-27 16:59:54'),
	(172, 1041, 147, '2024-12-27 16:59:54'),
	(173, 1043, 147, '2024-12-27 16:59:54'),
	(174, 1056, 147, '2025-01-13 16:46:40'),
	(175, 1060, 147, '2025-01-21 19:57:43'),
	(176, 3000, 147, '2025-02-09 23:34:34'),
	(187, 1052, 158, '2024-12-30 20:32:27'),
	(188, 1064, 158, '2025-01-14 20:19:27'),
	(189, 1064, 159, '2025-02-06 20:29:45'),
	(190, 1006, 159, '2025-01-05 19:03:45'),
	(191, 1038, 159, '2024-12-30 20:33:09'),
	(192, 1041, 159, '2024-12-30 20:33:09'),
	(193, 1043, 159, '2024-12-30 20:33:09'),
	(194, 1056, 159, '2025-01-13 16:46:41'),
	(195, 1060, 159, '2025-01-21 19:57:39'),
	(196, 3000, 159, '2025-02-09 23:34:10'),
	(197, 1064, 160, '2025-02-06 17:57:16'),
	(198, 1060, 161, '2025-02-06 20:24:39'),
	(199, 1052, 161, '2025-01-19 11:30:33'),
	(200, 3002, 162, '2025-02-09 23:33:54');

-- 테이블 dbCodebase.message 구조 내보내기
CREATE TABLE IF NOT EXISTS `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `text` varchar(500) NOT NULL,
  `timestamp` timestamp NOT NULL,
  `room_id` int DEFAULT NULL,
  `senderMail` varchar(50) NOT NULL,
  `sender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_id` (`room_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fk_room_id` FOREIGN KEY (`room_id`) REFERENCES `chatroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `member` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=333 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.message:~57 rows (대략적) 내보내기
INSERT INTO `message` (`id`, `user_id`, `text`, `timestamp`, `room_id`, `senderMail`, `sender`) VALUES
	(134, NULL, '11', '2024-12-04 23:47:24', 56, 'wjs478wjs', '전현식'),
	(135, NULL, '1', '2024-12-04 23:56:20', 57, 'wjs478wjs', '전현식'),
	(210, NULL, 'test12314', '2024-12-12 09:17:38', 60, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(211, NULL, 'gsdfgdf', '2024-12-12 09:17:39', 60, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(212, NULL, 'gdfgdfg', '2024-12-12 09:17:40', 60, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(213, NULL, 'rwrw', '2024-12-12 09:17:43', 60, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(214, NULL, 'gfdg', '2024-12-12 09:17:44', 60, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(215, NULL, 'gdfgdfg', '2024-12-12 09:17:45', 60, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(216, NULL, 'etsf', '2024-12-12 09:17:48', 60, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(217, NULL, 'gfdsgd', '2024-12-12 09:17:49', 60, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(241, NULL, 'testgfds', '2024-12-12 10:20:09', 118, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(242, NULL, 'gsdfgfdg', '2024-12-12 10:20:10', 118, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(273, 1041, 'ddd', '2024-12-13 16:19:04', 119, 'dmswl22@222', '김은지'),
	(274, 1043, '하이\n', '2024-12-16 13:19:50', 120, 'ww', 'ww'),
	(275, 1043, '하이', '2024-12-16 13:19:56', 120, 'ww', 'ww'),
	(276, 1043, 'testㅎㅇ', '2024-12-16 13:20:15', 120, 'ww', 'ww'),
	(278, NULL, '와아아앙', '2024-12-17 10:39:02', 121, 'test@test.com', '물복'),
	(279, NULL, '안 보여 ㅠㅅ ㅠ', '2024-12-17 10:39:18', 121, 'test@test.com', '물복'),
	(281, NULL, 'ㅎㄴㅇㅀ', '2024-12-17 15:10:41', 118, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(282, NULL, 'ㅗㅇㄹ호', '2024-12-17 15:10:45', 118, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(287, NULL, 'ㅎㅇㅎㅇ\n', '2024-12-17 15:43:55', 125, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(288, NULL, 'ㅎㅇㅎㅇ', '2024-12-17 15:44:25', 125, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(289, NULL, 'ㅇㅇㅇㅇ', '2024-12-17 15:44:27', 125, 'test@test.net', 'test'),
	(290, NULL, 'ㅎㄹㅇㄶㅇㅀㄹㅇㅎㄹㅇ', '2024-12-17 15:44:31', 125, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(291, NULL, 'gsfdgdfsg', '2024-12-17 15:44:57', 125, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(292, 1006, 'ddd', '2024-12-17 16:25:16', 119, 'dmswl@codebase.co', 'dmswl'),
	(300, NULL, 'testgdsfgd', '2024-12-17 19:50:11', 129, 'test@test.net', 'test'),
	(301, NULL, 'hgf', '2024-12-17 19:50:14', 129, 'test@test.net', 'test'),
	(302, 1052, 'gdsgfg', '2024-12-17 19:50:17', 129, 'rkdgur5381@naver.com', '이강혁네이'),
	(303, NULL, 'hdfghf', '2024-12-17 19:50:18', 129, 'test@test.net', 'test'),
	(304, NULL, 'gsdfgf', '2024-12-17 19:50:22', 129, 'test@test.net', 'test'),
	(305, NULL, 'dsadas', '2024-12-17 19:50:31', 56, 'wjs478wjs@naver.com', '전현식'),
	(306, NULL, 'asdfasdf', '2024-12-17 19:50:35', 121, 'test@test.com', '물복'),
	(307, NULL, 'ㅇㅇㅇ', '2024-12-17 19:50:52', 118, 'wjs478wjs@naver.com', '전현식'),
	(308, NULL, 'ㅇㅇㅇ', '2024-12-17 19:51:03', 118, 'wjs478wjs@naver.com', '전현식'),
	(309, 1052, 'gsdfgdgdfg', '2024-12-17 19:51:33', 132, 'rkdgur5381@naver.com', '이강혁네이'),
	(310, 1052, 'hfghghfgh', '2024-12-17 19:51:36', 132, 'rkdgur5381@naver.com', '이강혁네이'),
	(311, NULL, 'ㅎㅎㅇ\n\n', '2024-12-17 19:51:40', 132, 'test@test.com', '물복'),
	(312, 1052, 'gdsfgdfgdgdg', '2024-12-17 19:51:53', 133, 'rkdgur5381@naver.com', '이강혁네이'),
	(313, 1052, 'hgfhfghfghfghfgh', '2024-12-17 19:51:57', 133, 'rkdgur5381@naver.com', '이강혁네이'),
	(314, 1052, 'gfdgdsgdsfgd', '2024-12-17 19:51:59', 133, 'rkdgur5381@naver.com', '이강혁네이'),
	(315, NULL, '..', '2024-12-17 19:52:02', 133, 'wjs478wjs@naver.com', '전현식'),
	(316, NULL, 'ㄴㅁㅇㄴㅇㅁㄴ', '2024-12-17 19:52:10', 133, 'wjs478wjs@naver.com', '전현식'),
	(317, 1052, 'gdfsgdfgg', '2024-12-17 19:52:16', 132, 'rkdgur5381@naver.com', '이강혁네이'),
	(318, NULL, '허ㅗㅓ호\n', '2024-12-17 19:52:25', 133, 'wjs478wjs@naver.com', '전현식'),
	(319, NULL, 'ㅠㅅ ㅠ', '2024-12-17 19:52:35', 133, 'test@test.com', '물복'),
	(321, 1052, 'gdsgdfsgdfgdfg', '2024-12-17 20:15:20', 133, 'rkdgur5381@naver.com', '이강혁네이'),
	(322, NULL, 'ㅎㅇㄹㄶㅇㅀ', '2024-12-18 14:06:36', 125, 'rkdgur5381@kakao.com', '이강혁카카오'),
	(323, 1064, 'ts', '2024-12-24 20:11:46', 137, 'rkdgur5381@kakao.com', '이강혁카카오ㅎ'),
	(324, 1064, 'ee', '2024-12-27 17:00:00', 147, 'rkdgur5381@kakao.com', '이강혁카카오ㅎ'),
	(325, 1064, 'tgrret', '2024-12-30 11:04:49', 147, 'rkdgur5381@kakao.com', '이강혁'),
	(326, 1064, 'gdfgfdg', '2024-12-30 11:04:51', 147, 'rkdgur5381@kakao.com', '이강혁'),
	(327, 1064, 'gfdgdfg', '2024-12-30 11:04:52', 147, 'rkdgur5381@kakao.com', '이강혁'),
	(329, 1052, 'test', '2024-12-30 20:32:29', 158, 'rkdgur5381@naver.com', '이강혁네이'),
	(330, 1064, 'estgfdg', '2024-12-30 20:33:15', 159, 'rkdgur5381@kakao.com', '이강혁'),
	(331, 1060, 'ㅇ', '2025-01-19 11:31:04', 161, 'wjs478wjs@gmail.com', '전현식구글'),
	(332, 1060, '..', '2025-01-31 21:53:44', 161, 'wjs478wjs@gmail.com', '전현식구글');

-- 테이블 dbCodebase.notification 구조 내보내기
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(200) DEFAULT NULL,
  `type` enum('MESSAGE','COMMENT','LIKES') NOT NULL,
  `member` int DEFAULT NULL,
  `createdAt` datetime NOT NULL,
  `isRead` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `memberId` (`member`),
  CONSTRAINT `memberId` FOREIGN KEY (`member`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.notification:~5 rows (대략적) 내보내기
INSERT INTO `notification` (`id`, `content`, `type`, `member`, `createdAt`, `isRead`) VALUES
	(3, '채팅방에 초대되었습니다. : 이강혁네이, 이강혁카카오ㅎ', 'MESSAGE', 1064, '2024-12-24 15:45:16', 1),
	(4, '누가 좋아요 눌러줬습니다.', 'LIKES', 1064, '2024-12-24 16:17:42', 1),
	(5, '누가 댓글달아줬습니다.', 'COMMENT', 1064, '2024-12-24 16:18:25', 1),
	(6, '채팅방에 초대되었습니다. : 이강혁카카오ㅎ, qwqw', 'MESSAGE', 1038, '2024-12-24 20:39:51', 0),
	(7, '채팅방에 초대되었습니다. : 이강혁네이, 이강혁', 'MESSAGE', 1064, '2024-12-30 20:32:27', 1);

-- 테이블 dbCodebase.payment 구조 내보내기
CREATE TABLE IF NOT EXISTS `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `paymentId` varchar(255) NOT NULL,
  `buyer_id` int DEFAULT NULL,
  `project_id` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `purchaseTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.payment:~9 rows (대략적) 내보내기
INSERT INTO `payment` (`id`, `paymentId`, `buyer_id`, `project_id`, `price`, `purchaseTime`) VALUES
	(8, '0000', 3000, 113, 0, '2025-01-19 16:51:37'),
	(9, '0000', 3000, 129, 1000, '2025-01-19 17:05:29'),
	(10, '0000', 3000, 130, 1111, '2025-01-19 17:18:47'),
	(11, '0000', 3000, 131, 111100, '2025-01-19 17:48:56'),
	(12, '0000', 3000, 136, 1000, '2025-01-19 19:13:30'),
	(13, 'a79dcb39c12f3295', 3000, 145, 1000, '2025-02-06 17:09:05'),
	(14, '0000', 3000, 144, 1000, '2025-02-06 20:29:04'),
	(15, '0000', 3002, 149, 25000000, '2025-02-09 23:26:58'),
	(16, '0000', 3002, 143, 4500000, '2025-02-09 23:27:06');

-- 테이블 dbCodebase.post 구조 내보내기
CREATE TABLE IF NOT EXISTS `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `likes` int DEFAULT '0',
  `dislikes` int DEFAULT '0',
  `views` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.post:~37 rows (대략적) 내보내기
INSERT INTO `post` (`id`, `title`, `author`, `content`, `topic`, `createDate`, `likes`, `dislikes`, `views`) VALUES
	(16, '리액트공부2', '전현식', '리액트공부', '웹개발 프로젝트', '2024-12-22 12:52:34', 6, 5, 10),
	(19, '코딩 공부 테스트 수정 구글 전현식', '전현식구글', '코딩 공부 테스트', '코딩 공부 테스트', '2024-12-22 12:58:29', 13, 8, 14),
	(20, '마이바티스', '전현식', '마이바티스', '마이바티스', '2024-12-22 13:18:43', 0, 0, 3),
	(21, '문제', '전현식', '문제', '문제', '2024-12-22 13:23:37', 0, 0, 5),
	(23, '실험', '전현식', '실험', '코딩', '2024-12-22 13:38:21', 0, 0, 3),
	(24, 'ㅇㅇ', '전현식', 'ㅇㅇ', 'ㅇㅇ', '2024-12-22 13:40:24', 4, 0, 7),
	(25, '일요일', '전현식', '325325', '234', '2024-12-22 13:46:02', 7, 0, 14),
	(26, '1222', '전현식', '222', '코딩공부', '2024-12-22 13:47:11', 3, 0, 19),
	(27, '수정삭제 테스트', '전현식구글', '수정삭제 테스트', '수정삭제 테스트', '2024-12-22 13:54:32', 14, 4, 39),
	(28, '화요일', '전현식구글', '화요일 테스트', '화요일', '2024-12-24 06:32:27', 6, 2, 27),
	(29, 'gsdfg', '이강혁카카오ㅎ', 'gdsfg', 'tet', '2024-12-27 07:55:12', 6, 0, 10),
	(30, '테스트', '전현식', '테스트', '테스트', '2024-12-29 05:50:40', 3, 0, 9),
	(31, 'ㄴㅁㅇㄴㅁ', '전현식', 'ㄴㅁㅇㄴㅁㅇ', 'ㄴㅇㅁㄴ', '2024-12-29 05:53:09', 7, 5, 36),
	(32, '테스트', '전현식', '테스트\n', '테스트', '2024-12-30 07:38:07', 10, 7, 60),
	(34, 'dd', 'dmswl', 'dd', 'dd', '2025-01-05 10:04:10', 4, 4, 18),
	(35, '댓글 테스트', '전현식', 'ㄴㅁㅇㅁㄴㅇㅁㄴ', 'ㅁㄴㅇㅁㄴ', '2025-01-06 02:42:46', 5, 2, 8),
	(36, '코딩공부', '전현식', '코딩공부', '1월6일 테스트', '2025-01-06 04:20:20', 7, 5, 4),
	(37, 'ㅇ', '전현식', 'ㅇ', 'ㅇㅇ', '2025-01-06 04:24:45', 16, 12, 3),
	(38, '1월6일 테스트', '전현식', '1월6일 테스트', '1월6일 테스트', '2025-01-06 04:26:59', 23, 24, 5),
	(39, '자유게시판 구현 수정', '전현식', '자유게시판 구현', '자유게시판 구현', '2025-01-06 04:58:36', 18, 14, 5),
	(40, '코딩프로젝트회의 25년1월 6일', '전현식', '코딩프로젝트회의 25년1월 6일\n모임장소 ???', '코딩프로젝트회의', '2025-01-06 05:11:38', 5, 3, 5),
	(41, 'ㅎㄴㅇㅎㅇㄴㅀ', '이강혁', '프로필 게시글 테스트', 'ㅅㄷㄴㅅ', '2025-01-06 08:01:33', 65, 15, 12),
	(42, 'asdas', '전현식', 'sadas', 'sd', '2025-01-13 05:14:14', 3, 4, 6),
	(43, '실험', '전현식', '실험', '실험', '2025-01-13 05:51:15', 5, 2, 2),
	(47, 'ㄴㅁㅇㅁㄴ', '전현식', 'ㅁㄴㅇ', 'ㅁㄴㅇdd', '2025-01-13 06:22:17', 3, 0, 4),
	(49, 'ㅁㄴㅇㅁㄴ', '전현식', 'ㅁㄴㅇㄴㅁ', 'ㅇㄴㅁㅇ', '2025-01-13 06:34:07', 0, 0, 2),
	(51, '좋아요 싫어요 테스트', '전현식', '좋아요 싫어요 테스트', '좋아요 싫어요 테스트', '2025-01-13 06:37:04', 13, 13, 15),
	(53, '댓글작성기능 만들기 너무 어렵다..', '전현식', '댓글작성기능 만들기 너무 어렵다..', '댓글작성기능 만들기 너무 어렵다..', '2025-01-13 09:19:46', 1, 0, 11),
	(56, '마이페이지', '이강혁네이', '마이페이지', '마이페이지', '2025-01-14 08:05:06', 0, 0, 0),
	(57, 'gdfsg', '이강혁카카오', 'etgsfdg', 'test', '2025-01-14 08:44:50', 1, 0, 2),
	(58, '250114회의 고바슨', '전현식구글', '250114회의', '250114회의', '2025-01-14 10:51:01', 1, 1, 19),
	(59, '테스트', '전현식구글', '테스트', '테스트', '2025-01-19 01:55:04', 1, 0, 3),
	(60, '댓글기능 테스트', '전현식구글', '성공', '250119 ㄷ', '2025-01-19 02:14:35', 0, 0, 6),
	(61, '일요일1', '전현식구글', '일요일1', '일요일1', '2025-01-19 02:41:39', 0, 0, 2),
	(62, '화요일', '전현식구글', '20250121', '화요일', '2025-01-21 10:56:56', 0, 1, 7),
	(63, '250131', '전현식구글', '250131', '250131', '2025-01-31 13:02:06', 1, 0, 5),
	(64, '250221', '전현식구글', '1', '1', '2025-02-21 12:31:18', 0, 0, 3);

-- 테이블 dbCodebase.posts 구조 내보내기
CREATE TABLE IF NOT EXISTS `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `author` varchar(100) NOT NULL,
  `create_date` datetime DEFAULT (now()),
  `views` int DEFAULT '0',
  `likes` int DEFAULT '0',
  `dislikes` int DEFAULT '0',
  `topic` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.posts:~0 rows (대략적) 내보내기

-- 테이블 dbCodebase.post_tags 구조 내보내기
CREATE TABLE IF NOT EXISTS `post_tags` (
  `post_id` bigint NOT NULL,
  `tag_id` bigint NOT NULL,
  PRIMARY KEY (`post_id`,`tag_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `post_tags_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  CONSTRAINT `post_tags_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.post_tags:~0 rows (대략적) 내보내기

-- 테이블 dbCodebase.project 구조 내보내기
CREATE TABLE IF NOT EXISTS `project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `content` longtext,
  `price` bigint NOT NULL DEFAULT (0),
  `hit` int DEFAULT '0',
  `link` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `types` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `maker_id` int DEFAULT NULL,
  `isdelete` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'n',
  `issoldout` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'n',
  PRIMARY KEY (`id`),
  KEY `project_ibfk_1` (`maker_id`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`maker_id`) REFERENCES `member` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.project:~10 rows (대략적) 내보내기
INSERT INTO `project` (`id`, `title`, `content`, `price`, `hit`, `link`, `img`, `types`, `username`, `maker_id`, `isdelete`, `issoldout`) VALUES
	(140, '대구 가로등 구성 3D 파일 및 분석', '대구 중구 가로등 분포 데이터 입니다.\n최신 업데이트일 24.11.5일 \n기준으로 제작 되어있습니다.\n향후 5년간 해당 최신데이터 \n열람 자격도 주어집니다.', 5000000, 1, '9c0d3b41-32bf-49a6-9b5d-837248034b82제목을-입력해주세요_-001.jpg', '7b5a87af-9dbc-402e-911c-f75bba6449ed화면 캡처 2025-01-19 200658.jpg', NULL, 'user1', 3000, 'y', 'n'),
	(141, '대구 중구 가로등 3D 데이터 파일 및 분석 샘플', '최신 업데이트 24.11.5\n대구 중구 가로등 \n3D 데이터 입니다.\n구매시 향후 5년간\n최신 데이터 열람\n권한이 주어집니다.', 5000000, 4, 'c8efee6d-4f09-40e9-936e-790eed855b6d제목을-입력해주세요_-001.jpg', '61df64b9-42ae-4d3a-8b3d-d0c1fb31bb5f화면 캡처 2025-01-19 200658.jpg', NULL, 'user1', 3000, 'y', 'n'),
	(142, '대구 중구 가로등 구성 3D 데이터 및 분석 샘플', '대구 중구 가로등 3D 데이터 입니다. (최신 업데이트 24.11.5 )', 5000000, 19, 'd1f0b47e-6e42-448d-8bf9-789791035ab7제목을-입력해주세요_-001 - 복사본.jpg', '1f6be968-255e-4310-99bb-2a6886132a9f화면 캡처 2025-01-19 201453.jpg', NULL, 'user1', 3000, 'n', 'n'),
	(143, 'MX클린 세탁기 내장 프로세스', 'MX클린 세탁기 내장 프로세스 입니다. 상업적 사용 허용', 4500000, 24, '087c3f79-6667-40ba-b420-ee5e1ed91f52001.jpg', 'ba3baa1f-b251-40e2-8067-dfe6964da277화면 캡처 2025-01-19 203106.jpg', NULL, 'user1', 3000, 'n', 'n'),
	(144, 'test', 'test', 1000, 9, '6d12d56b-4a13-4c22-9db2-b81b0f24ba95profile.png', '67355d97-26c6-4ced-8062-ee2d5326e33eprofile.png', NULL, 'user1', 3000, 'n', 'y'),
	(145, 'test2', 'test2', 1000, 3, 'fa7f8ec4-8199-42b6-8d97-f5dd126c5d48profile.png', 'baefbd11-9a3f-4e84-bb26-879fe56abed3profile.png', NULL, 'user1', 3000, 'n', 'y'),
	(146, 'test3', 'test3', 1000, 5, '4c527922-001e-457f-ba66-5e65911c648eprofile.png', '1469492f-c10c-42aa-955d-d5209394012eprofile.png', NULL, 'user1', 3000, 'y', 'n'),
	(147, '(대구) 교통 시뮬레이션 데이터 셋', '대구 데이터', 2000000, 4, '95141e03-e55e-49c8-91c5-6b4b22a15732제목을-입력해주세요_-001.png', 'de306f7c-bfd0-4e0a-85dd-35b47339bf19제목을-입력해주세요_-001.png', NULL, 'user1', 3000, 'y', 'n'),
	(148, '교통 시뮬레이션 데이터셋 (대구)', '대구', 2000000, 9, '4d7609a2-691b-4c47-9951-12882d9c3ddc제목을-입력해주세요_-001 (1).png', '993ac7f3-adaf-4bed-9ea9-bd9f8021f562제목을-입력해주세요_-001 (1).png', NULL, 'user1', 3000, 'n', 'n'),
	(149, 'Melody Sense 맞춤형 음악 분석', '맞춤형 음성 취향 분석 솔루션으로, 사용자의 음악 감성을 분석하여 맞춤형 추천을 제공합니다.\n개인의 선호도와 청취 패턴을 학습하여 더 정교한 음악 큐레이션을 지원하며, 음악 스트리밍 서비스, 플레이리스트 생성, 개인화 추천 시스템 등에 활용할 수 있습니다.', 25000000, 14, 'e56e08b4-af69-407f-a5d7-dc43a8f865b3제목을 입력해주세요_-001.png', 'f0681bfa-0538-4066-af37-0b39de509571제목을 입력해주세요_-001.png', NULL, 'user1', 3000, 'n', 'n');

-- 테이블 dbCodebase.projectteam 구조 내보내기
CREATE TABLE IF NOT EXISTS `projectteam` (
  `pjt_id` int NOT NULL AUTO_INCREMENT,
  `pjtname` varchar(255) DEFAULT NULL,
  `pjtowner` varchar(255) DEFAULT NULL,
  `pjtimg` varchar(255) DEFAULT NULL,
  `pjtdescription` text,
  `pjcategory` varchar(255) DEFAULT NULL,
  `create_day` date DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`pjt_id`),
  KEY `projectteam_ibfk_1` (`member_id`),
  CONSTRAINT `projectteam_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.projectteam:~14 rows (대략적) 내보내기
INSERT INTO `projectteam` (`pjt_id`, `pjtname`, `pjtowner`, `pjtimg`, `pjtdescription`, `pjcategory`, `create_day`, `deadline`, `member_id`) VALUES
	(0, 'default', 'default', 'default', 'default', 'default', '2024-12-16', '2024-12-16', 1039),
	(2, '123', '123', '462d83ab-bdf5-4fc4-af9c-5dd47c01ee91_DJI_0096.JPG', '123', 'Java,React,Spring,Python', '2024-12-11', '2024-12-11', NULL),
	(3, '123', '123', 'c2bd9309-1968-41fc-a1d6-ddc5de168dda_DJI_0087.JPG', '123', 'Java,React,Spring', '2024-12-11', '2024-12-11', NULL),
	(4, '1', '1', '1f6cba44-2f72-41d3-bc73-5abd94aa5528_DJI_0088.JPG', '1', 'Java', '2024-12-11', '2024-12-11', NULL),
	(6, '123', '123', 'f1637c0d-955a-41ef-9d9a-61028d116a49_DJI_0095.JPG', '123', 'Java,React,Spring', '2024-12-11', '2024-12-11', NULL),
	(8, '3', '3', '54195aaa-1900-44e9-87f1-32f49f052b6a_DJI_0096.JPG', '3', 'Java', '2024-12-11', '2024-12-11', NULL),
	(19, '테스트', '', '3c780c96-6a92-4b25-b4fa-16f1631cdbe1_Night.jpg', '테스트', 'Spring,Angular', '2024-12-11', '2024-12-11', NULL),
	(21, '테스트 123', '테스트1', 'af9087b8-aa4a-4e85-95d4-6ea6f62642ef_1733115028425.jpg', '테스트용임', 'Java,React', '2024-12-11', '2024-12-27', 1039),
	(26, '팀원 모집합니다', '테스트1', '0aff6d4b-8b7b-47bd-824e-f96f90bd9f71_다운로드.jpg', '테스트', 'Python,Vue.js', '2024-12-17', '2024-12-30', 1039),
	(30, '테슷트', 'g테스트', '8fd624bb-e5c9-4ccb-98cb-83cdc20bf7a5_1592919738590850_1718463065.png', '테슷트', 'Java', '2024-12-24', '2024-12-27', 1048),
	(31, '222', 'g테스트', 'ad104f45-2a97-4521-aaa3-cd3bc9ab9245_media_126f51234e424100112aecb4f61e26b3a2afc74d8.jpeg', 'aaa', 'Python,JavaScript', '2024-12-24', '2024-12-28', 1048),
	(34, '팀 이름1', '테스트1', '9fb99274-13a3-4dc2-a8f0-623afd913a93_스크린샷 2024-12-16 155358.png', '123', 'Java,Node.js', '2024-12-30', '2024-12-31', 1039),
	(35, '프로필 프로젝트', '이강혁', '7f08b4a9-5eb5-4182-af6d-47961db2b453_잔디 기부 캠페인 기부증.png', '프로필프로젝트', '', '2025-01-14', '2025-01-31', 1064),
	(36, 'testgdfsgsg', '이강혁카카오', '92c5877e-cc54-4394-a82c-7c51ab368cee_잔디 기부 캠페인 기부증.png', 'gdsfgdfsg', '', '2025-01-14', '2025-01-23', 1064);

-- 테이블 dbCodebase.project_order 구조 내보내기
CREATE TABLE IF NOT EXISTS `project_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT '',
  `content` longtext,
  `link` longtext,
  `types` varchar(255) DEFAULT '',
  `maker_name` varchar(50) DEFAULT '',
  `project_id` int NOT NULL,
  `maker_id` int DEFAULT NULL,
  `buyer_id` int NOT NULL,
  `payment_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.project_order:~2 rows (대략적) 내보내기
INSERT INTO `project_order` (`id`, `title`, `content`, `link`, `types`, `maker_name`, `project_id`, `maker_id`, `buyer_id`, `payment_id`) VALUES
	(1, NULL, NULL, NULL, NULL, '', 149, 0, 3002, 15),
	(2, NULL, NULL, NULL, NULL, '', 143, 0, 3002, 16);

-- 테이블 dbCodebase.report_details 구조 내보내기
CREATE TABLE IF NOT EXISTS `report_details` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `report_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  `content` text,
  `report_date` date DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  UNIQUE KEY `report_id` (`report_id`,`member_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `report_details_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `report_summary` (`report_id`) ON DELETE CASCADE,
  CONSTRAINT `report_details_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.report_details:~3 rows (대략적) 내보내기
INSERT INTO `report_details` (`detail_id`, `report_id`, `member_id`, `content`, `report_date`) VALUES
	(1, 39, NULL, '마음에 들지 않아요', '2025-01-06'),
	(3, 39, 1006, '마음에 들지 않아요', '2025-01-06'),
	(8, 46, 1006, '마음에 들지 않아요', '2025-03-26');

-- 테이블 dbCodebase.report_summary 구조 내보내기
CREATE TABLE IF NOT EXISTS `report_summary` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `category` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `report_count` int DEFAULT '0',
  `completed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  UNIQUE KEY `category` (`category`,`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.report_summary:~2 rows (대략적) 내보내기
INSERT INTO `report_summary` (`report_id`, `category`, `category_id`, `report_count`, `completed`) VALUES
	(39, 1, 34, 2, 1),
	(46, 2, 199, 1, 0);

-- 테이블 dbCodebase.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `views` int DEFAULT '0',
  `likes` int DEFAULT '0',
  `dislikes` int DEFAULT '0',
  `pjtId` int DEFAULT '0',
  `category` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_review_projectteam` (`pjtId`),
  CONSTRAINT `FK_review_projectteam` FOREIGN KEY (`pjtId`) REFERENCES `projectteam` (`pjt_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.review:~49 rows (대략적) 내보내기
INSERT INTO `review` (`id`, `title`, `content`, `author`, `createdDate`, `views`, `likes`, `dislikes`, `pjtId`, `category`) VALUES
	(1, '테스트', '테스트', 'g테스트', '2024-12-17 04:08:11', 9, 4, 2, 0, NULL),
	(2, 'aaa', 'aaa', 'g테스트', '2024-12-17 04:11:06', 3, 0, 0, 0, NULL),
	(3, 'ㅊㅇ', 'ㅊ', 'g테스트', NULL, 2, 0, 0, 0, NULL),
	(4, 'ㄴㄴ', 'ㄴㄴ', 'g테스트', '2024-12-17 04:19:37', 2, 0, 0, 0, NULL),
	(5, 'ㄴㄴㄴ', 'ㄴㄴㄴ', 'g테스트', NULL, 1, 0, 0, 0, NULL),
	(6, 'ㄷㄷ', 'ㄷㄷ', 'g테스트', NULL, 2, 0, 0, 0, NULL),
	(7, '시간 테스트', '시간 테스트', 'g테스트', '2024-12-17 04:31:53', 2, 0, 0, 0, NULL),
	(8, 'dd', 'dd', 'g테스트', '2024-12-17 04:33:56', 0, 0, 0, 0, NULL),
	(9, 'ㄴㄴㄴㄴ', 'ㄴㄴㄴㄴㄴ', 'g테스트', '2024-12-17 04:37:12', 1, 0, 0, 0, NULL),
	(10, '페이지 테스트', '페이지 테스트', 'g테스트', '2024-12-17 04:42:34', 3, 1, 0, 0, NULL),
	(11, '페이지', '페이지', 'g테스트', '2024-12-17 04:42:42', 2, 0, 0, 0, NULL),
	(12, '제발요', '제발요', 'g테스트', '2024-12-17 04:52:24', 7, 0, 0, 0, NULL),
	(13, 'ㅇㅇㅇㅇ', 'ㅇㅇㅇ', 'g테스트', '2024-12-17 04:59:40', 6, 0, 0, 0, NULL),
	(14, 'ㅁㄴㅇ', 'ㅁㄴㅇ', 'g테스트', '2024-12-17 05:01:42', 32, 0, 0, 0, NULL),
	(15, '신고삭제 버튼 테스트', '신고삭제버튼 테스트', '한정현', '2024-12-17 07:18:44', 21, 0, 0, 0, NULL),
	(16, 'ㅇㅇㅇㅇㅇ', 'ㅇㅇㅇㅇ', NULL, '2024-12-17 11:08:35', 5, 0, 0, 0, NULL),
	(17, 'ddddd', 'ddddddd', '한정현', '2024-12-17 11:23:53', 10, 0, 0, 0, NULL),
	(18, 'ㅇㅇㅇ', 'ㅇㅇㅇ', '한정현', '2024-12-18 04:06:38', 11, 3, 3, 0, NULL),
	(19, '1', '1', '', '2024-12-22 13:49:03', 9, 5, 0, 0, NULL),
	(20, 'aa', 'aa', 'g테스트', '2024-12-23 13:02:39', 8, 0, 0, 0, NULL),
	(23, 'asd', 'asd', '', '2024-12-24 00:56:40', 13, 0, 0, 0, NULL),
	(24, 'aaa', 'aaaa', '', '2024-12-24 00:59:36', 27, 0, 0, 0, NULL),
	(25, 'qwer', 'qwer', 'g테스트', '2024-12-24 01:22:33', 29, 0, 0, 0, NULL),
	(26, '등록', '등록', '한정현', '2024-12-24 01:51:48', 10, 0, 0, 0, NULL),
	(27, 'ㅁㅁㅁ', 'ㅁㅁㅁ', '', '2024-12-24 02:09:19', 8, 0, 0, 0, NULL),
	(28, 'ㅁㄴㅇ', 'ㅁㄴㅇ', 'g테스트', '2024-12-24 02:39:24', 3, 0, 0, 30, NULL),
	(29, 'ㅁㄴㅇㄹ', 'ㅁㄴㅇㄹ', 'g테스트', '2024-12-24 02:39:41', 8, 0, 0, 30, NULL),
	(30, 'ㅁㅁ', 'ㅁㅁ', 'g테스트', '2024-12-24 02:41:01', 5, 0, 0, 30, NULL),
	(31, 'ㅁㄴㅇ', 'ㅁㄴㅇ', 'g테스트', '2024-12-24 02:42:14', 12, 0, 0, 0, NULL),
	(32, '1', '1', 'g테스트', '2024-12-24 02:44:15', 3, 0, 0, 30, NULL),
	(33, 'asd', 'asd', 'g테스트', '2024-12-24 04:05:17', 38, 2, 0, 30, NULL),
	(34, 'aaaa', 'aaaa', 'g테스트', '2024-12-24 11:44:47', 14, 2, 0, 0, NULL),
	(36, 'ㅇㅇㅇ', 'ㅇㅇㅇ', 'g테스트', '2025-01-06 04:34:37', 1, 0, 0, 30, NULL),
	(37, 'ㅇㅇㅇ', 'ㅇㅇㅇ', 'g테스트', '2025-01-06 04:36:33', 1, 0, 0, 30, NULL),
	(38, 'dd', 'dd', '', '2025-01-06 04:39:43', 0, 0, 0, 0, NULL),
	(39, 'd', 'd', 'g테스트', '2025-01-06 04:40:47', 1, 0, 0, 30, NULL),
	(40, 's', 's', 'g테스트', '2025-01-06 04:54:10', 3, 3, 0, 30, NULL),
	(41, 's', 's', 'g테스트', '2025-01-06 05:19:00', 1, 0, 0, 30, '프로젝트'),
	(42, 'ss', 'ss', 'g테스트', '2025-01-06 05:35:40', 0, 0, 0, 30, '프로젝트'),
	(43, 'a', 'a', 'g테스트', '2025-01-06 05:36:52', 16, 3, 0, 30, '프로젝트'),
	(44, '프로젝트 테스트', '프로젝트 테스트', 'g테스트', '2025-01-06 11:12:48', 2, 3, 0, 30, '프로젝트'),
	(46, 'ㅇㅇㅇㅇ', 'ㅇㅇㅇㅇ', 'g테스트', '2025-01-06 11:23:31', 3, 0, 0, 30, '프로젝트'),
	(47, 'ㅇㅇ', 'ㅇ', 'g테스트', '2025-01-06 11:25:14', 15, 104, 3, 31, '팀원'),
	(48, 'asdas', 'asdas', 'g테스트', '2025-01-14 08:33:10', 2, 0, 0, 0, '팀원'),
	(49, 'aaaaa', 'aaaaa', 'g테스트', '2025-01-14 08:33:23', 2, 11, 2, 0, '팀원'),
	(50, 'ㅁ', 'ㅁ', 'g테스트', '2025-01-14 08:36:07', 1, 0, 0, 30, '팀원'),
	(51, '마이페이지', 'akdlvpdlwl', '이강혁카카오', '2025-01-14 08:45:56', 1, 2, 0, 0, '프로젝트'),
	(52, 'a', 'a', 'g테스트', '2025-01-14 09:10:41', 6, 20, 13, 0, ''),
	(53, '버튼테스트', '버튼테스트', 'g테스트', '2025-01-14 11:29:00', 16, 20, 10, 30, '팀원');

-- 테이블 dbCodebase.tags 구조 내보내기
CREATE TABLE IF NOT EXISTS `tags` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.tags:~0 rows (대략적) 내보내기

-- 테이블 dbCodebase.team_application 구조 내보내기
CREATE TABLE IF NOT EXISTS `team_application` (
  `application_id` int unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL,
  `pjt_id` int NOT NULL,
  `tech_stack` enum('FRONTEND','BACKEND','FULLSTACK','DESIGN','PM','PTC') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` enum('PENDING','ACCEPTED','REJECTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'PENDING',
  PRIMARY KEY (`application_id`),
  KEY `fk_application_member` (`member_id`),
  KEY `fk_application_project` (`pjt_id`),
  CONSTRAINT `fk_application_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_application_project` FOREIGN KEY (`pjt_id`) REFERENCES `projectteam` (`pjt_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.team_application:~20 rows (대략적) 내보내기
INSERT INTO `team_application` (`application_id`, `member_id`, `pjt_id`, `tech_stack`, `status`) VALUES
	(6, 1039, 34, 'FULLSTACK', 'PENDING'),
	(7, 1039, 0, 'FRONTEND', 'PENDING'),
	(8, 1039, 0, 'FULLSTACK', 'PENDING'),
	(16, 1039, 3, 'FULLSTACK', 'PENDING'),
	(17, 1039, 2, 'DESIGN', 'PENDING'),
	(18, 1039, 3, 'FULLSTACK', 'PENDING'),
	(19, 1064, 26, 'FRONTEND', 'PENDING'),
	(20, 1052, 35, 'FRONTEND', 'PENDING'),
	(21, 1052, 0, 'BACKEND', 'PENDING'),
	(22, 1064, 34, 'FRONTEND', 'PENDING'),
	(23, 1060, 2, 'FRONTEND', 'PENDING'),
	(24, 3000, 0, 'PM', 'PENDING'),
	(25, 3000, 0, 'FRONTEND', 'PENDING'),
	(26, 1039, 0, 'BACKEND', 'PENDING'),
	(27, 1039, 2, 'FULLSTACK', 'ACCEPTED'),
	(28, 1039, 3, 'FRONTEND', 'PENDING'),
	(29, 1039, 2, 'FRONTEND', 'PENDING'),
	(30, 1039, 2, 'BACKEND', 'PENDING'),
	(31, 1039, 4, 'FULLSTACK', 'PENDING'),
	(32, 1039, 21, 'DESIGN', 'PENDING');

-- 테이블 dbCodebase.team_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `team_member` (
  `tm_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `pjt_id` int NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT 'active',
  PRIMARY KEY (`tm_id`) USING BTREE,
  KEY `fk_team_member_member` (`member_id`),
  KEY `fk_team_member_projectteam` (`pjt_id`),
  CONSTRAINT `fk_team_member_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_team_member_projectteam` FOREIGN KEY (`pjt_id`) REFERENCES `projectteam` (`pjt_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.team_member:~0 rows (대략적) 내보내기

-- 테이블 dbCodebase.user_dislikes 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_dislikes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_dislike` (`post_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.user_dislikes:~7 rows (대략적) 내보내기
INSERT INTO `user_dislikes` (`id`, `post_id`, `user_id`) VALUES
	(1, 43, '전현식'),
	(2, 44, '전현식'),
	(3, 45, '전현식'),
	(42, 48, '전현식'),
	(99, 51, '전현식'),
	(131, 58, '전현식구글'),
	(152, 62, '전현식구글');

-- 테이블 dbCodebase.user_likes 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_likes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_like` (`post_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.user_likes:~11 rows (대략적) 내보내기
INSERT INTO `user_likes` (`id`, `post_id`, `user_id`) VALUES
	(1, 43, '전현식'),
	(2, 44, '전현식'),
	(3, 45, '전현식'),
	(14, 46, '전현식'),
	(56, 50, '전현식'),
	(76, 52, '전현식'),
	(145, 53, '전현식구글'),
	(149, 57, '전현식구글'),
	(178, 58, '전현식'),
	(182, 59, '전현식구글'),
	(208, 63, '전현식구글');

-- 테이블 dbCodebase.visitor 구조 내보내기
CREATE TABLE IF NOT EXISTS `visitor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `visit_ip` varchar(100) NOT NULL,
  `visit_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 dbCodebase.visitor:~87 rows (대략적) 내보내기
INSERT INTO `visitor` (`id`, `visit_ip`, `visit_date`) VALUES
	(1, '0:0:0:0:0:0:0:1', '2024-11-19 13:26:07'),
	(2, '0:0:0:0:0:0:0:1', '2024-11-20 11:33:50'),
	(3, '0:0:0:0:0:0:0:1', '2024-11-21 16:02:54'),
	(8, '0:0:0:0:0:0:0:1', '2024-11-22 22:58:39'),
	(10, '0:0:0:0:0:0:0:1', '2024-11-23 14:35:53'),
	(11, '0:0:0:0:0:0:0:1', '2024-11-24 18:55:00'),
	(12, '0:0:0:0:0:0:0:1', '2024-11-25 12:47:41'),
	(13, '0:0:0:0:0:0:0:1', '2024-12-02 15:17:14'),
	(14, '0:0:0:0:0:0:0:1', '2024-12-03 08:40:17'),
	(15, '127.0.0.1', '2024-12-03 12:24:52'),
	(16, '0:0:0:0:0:0:0:1', '2024-12-04 14:16:19'),
	(17, '0:0:0:0:0:0:0:1', '2024-12-05 09:28:17'),
	(18, '127.0.0.1', '2024-12-05 18:01:45'),
	(19, '0:0:0:0:0:0:0:1', '2024-12-06 09:14:02'),
	(20, '127.0.0.1', '2024-12-06 16:40:53'),
	(21, '0:0:0:0:0:0:0:1', '2024-12-08 22:38:27'),
	(22, '0:0:0:0:0:0:0:1', '2024-12-09 11:39:05'),
	(23, '127.0.0.1', '2024-12-09 13:21:30'),
	(24, '0:0:0:0:0:0:0:1', '2024-12-10 09:16:26'),
	(25, '127.0.0.1', '2024-12-10 09:50:56'),
	(26, '0:0:0:0:0:0:0:1', '2024-12-10 22:17:08'),
	(27, '0:0:0:0:0:0:0:1', '2024-12-11 11:20:28'),
	(28, '127.0.0.1', '2024-12-11 17:30:49'),
	(29, '127.0.0.1', '2024-12-11 17:30:49'),
	(30, '0:0:0:0:0:0:0:1', '2024-12-12 08:47:16'),
	(31, '127.0.0.1', '2024-12-12 09:48:04'),
	(32, '0:0:0:0:0:0:0:1', '2024-12-13 09:30:26'),
	(33, '127.0.0.1', '2024-12-13 09:52:45'),
	(34, '0:0:0:0:0:0:0:1', '2024-12-14 09:37:34'),
	(35, '0:0:0:0:0:0:0:1', '2024-12-15 14:25:21'),
	(36, '0:0:0:0:0:0:0:1', '2024-12-16 10:15:04'),
	(37, '127.0.0.1', '2024-12-16 10:28:06'),
	(38, '0:0:0:0:0:0:0:1', '2024-12-16 23:27:39'),
	(39, '0:0:0:0:0:0:0:1', '2024-12-17 13:08:34'),
	(40, '127.0.0.1', '2024-12-17 15:03:31'),
	(41, '0:0:0:0:0:0:0:1', '2024-12-18 09:26:09'),
	(42, '0:0:0:0:0:0:0:1', '2024-12-19 08:58:04'),
	(43, '0:0:0:0:0:0:0:1', '2024-12-19 22:19:29'),
	(44, '0:0:0:0:0:0:0:1', '2024-12-20 13:29:51'),
	(45, '127.0.0.1', '2024-12-20 14:29:19'),
	(46, '0:0:0:0:0:0:0:1', '2024-12-21 08:16:51'),
	(47, '127.0.0.1', '2024-12-21 08:48:33'),
	(48, '0:0:0:0:0:0:0:1', '2024-12-22 20:12:43'),
	(49, '0:0:0:0:0:0:0:1', '2024-12-23 09:22:41'),
	(50, '127.0.0.1', '2024-12-23 14:43:36'),
	(51, '0:0:0:0:0:0:0:1', '2024-12-23 22:45:52'),
	(52, '127.0.0.1', '2024-12-24 11:33:52'),
	(53, '0:0:0:0:0:0:0:1', '2024-12-24 11:48:15'),
	(54, '0:0:0:0:0:0:0:1', '2024-12-26 11:29:28'),
	(55, '127.0.0.1', '2024-12-26 11:42:33'),
	(56, '0:0:0:0:0:0:0:1', '2024-12-27 09:30:12'),
	(57, '127.0.0.1', '2024-12-27 11:33:47'),
	(58, '0:0:0:0:0:0:0:1', '2024-12-28 14:30:13'),
	(59, '0:0:0:0:0:0:0:1', '2024-12-29 14:00:12'),
	(60, '127.0.0.1', '2024-12-29 16:50:17'),
	(61, '127.0.0.1', '2024-12-30 08:57:45'),
	(62, '0:0:0:0:0:0:0:1', '2024-12-30 08:59:25'),
	(63, '0:0:0:0:0:0:0:1', '2024-12-30 22:36:02'),
	(64, '0:0:0:0:0:0:0:1', '2025-01-01 21:24:43'),
	(65, '0:0:0:0:0:0:0:1', '2025-01-03 15:10:50'),
	(66, '127.0.0.1', '2025-01-03 15:34:57'),
	(67, '0:0:0:0:0:0:0:1', '2025-01-04 15:29:33'),
	(68, '127.0.0.1', '2025-01-04 15:33:47'),
	(69, '0:0:0:0:0:0:0:1', '2025-01-05 16:53:11'),
	(70, '0:0:0:0:0:0:0:1', '2025-01-06 11:14:40'),
	(71, '0:0:0:0:0:0:0:1', '2025-01-07 17:22:10'),
	(72, '0:0:0:0:0:0:0:1', '2025-01-13 13:58:43'),
	(73, '127.0.0.1', '2025-01-13 20:34:35'),
	(74, '0:0:0:0:0:0:0:1', '2025-01-14 13:10:42'),
	(75, '127.0.0.1', '2025-01-14 20:40:14'),
	(76, '0:0:0:0:0:0:0:1', '2025-01-18 16:10:55'),
	(77, '0:0:0:0:0:0:0:1', '2025-01-19 10:54:32'),
	(78, '127.0.0.1', '2025-01-19 17:04:38'),
	(79, '0:0:0:0:0:0:0:1', '2025-01-21 09:43:39'),
	(80, '0:0:0:0:0:0:0:1', '2025-01-22 20:36:27'),
	(81, '0:0:0:0:0:0:0:1', '2025-01-23 11:31:12'),
	(82, '0:0:0:0:0:0:0:1', '2025-01-26 16:26:50'),
	(83, '0:0:0:0:0:0:0:1', '2025-01-31 21:52:19'),
	(84, '0:0:0:0:0:0:0:1', '2025-02-03 17:57:50'),
	(85, '0:0:0:0:0:0:0:1', '2025-02-06 16:27:21'),
	(86, '127.0.0.1', '2025-02-06 20:19:48'),
	(87, '0:0:0:0:0:0:0:1', '2025-02-09 17:35:18'),
	(88, '127.0.0.1', '2025-02-09 22:41:13'),
	(89, '0:0:0:0:0:0:0:1', '2025-02-21 21:06:29'),
	(90, '0:0:0:0:0:0:0:1', '2025-02-27 19:46:48'),
	(91, '0:0:0:0:0:0:0:1', '2025-03-22 01:53:26'),
	(92, '0:0:0:0:0:0:0:1', '2025-03-26 17:14:50');

-- 트리거 dbCodebase.comments_report_delete 구조 내보내기
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `comments_report_delete` AFTER DELETE ON `comments` FOR EACH ROW BEGIN
    DELETE FROM report_summary WHERE category=2 AND category_id=OLD.id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 트리거 dbCodebase.post_report_delete 구조 내보내기
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `post_report_delete` AFTER DELETE ON `post` FOR EACH ROW BEGIN
    DELETE FROM report_summary WHERE category=1 AND category_id=OLD.id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 트리거 dbCodebase.project_report_delete 구조 내보내기
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `project_report_delete` AFTER DELETE ON `project` FOR EACH ROW BEGIN
    DELETE FROM report_summary WHERE category=0 AND category_id=OLD.id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 트리거 dbCodebase.review_report_delete 구조 내보내기
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `review_report_delete` AFTER DELETE ON `review` FOR EACH ROW BEGIN
    DELETE FROM report_summary WHERE category=3 AND category_id=OLD.id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
