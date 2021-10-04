-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1:3306
-- 產生時間： 2021-01-07 14:46:11
-- 伺服器版本： 5.7.30-log
-- PHP 版本： 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `missa`
--

-- --------------------------------------------------------

--
-- 資料表結構 `insurance`
--

CREATE TABLE `insurance` (
  `insurance_id` int(12) NOT NULL,
  `insurance_name` varchar(50) NOT NULL,
  `duration_period` int(11) NOT NULL,
  `amount_insured` int(11) NOT NULL,
  `details` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_key` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `insurance`
--

INSERT INTO `insurance` (`insurance_id`, `insurance_name`, `duration_period`, `amount_insured`, `details`, `timestamp`, `delete_key`) VALUES
(1, '醫療險123213', 5, 500000, '主要給付項目為住院醫療費用保險金。此方案之保額為五十萬。被保險人在本契約有效期間之5年內因保單條款而住院診療時，本公司按被保險人住院期間內所發生之項目給予給付。', '2021-01-07 13:18:39', 1),
(2, '醫療險', 10, 1000000, '主要給付項目為住院醫療費用保險金。此方案之保額為一百萬。被保險人在本契約有效期間之10年內因保單條款而住院診療時，本公司按被保險人住院期間內所發生之項目給予給付。', '2020-12-25 11:49:23', 0),
(3, '醫療險', 15, 1500000, '主要給付項目為住院醫療費用保險金。此方案之保額為一百五十萬。被保險人在本契約有效期間之15年內因保單條款而住院診療時，本公司按被保險人住院期間內所發生之項目給予給付。', '2020-12-25 11:49:47', 0),
(4, '意外險', 5, 500000, '主要給付項目為交通意外身故保險金或喪葬費用保險金。此方案之保額為五十萬。被保險人於本契約有效期間之5年內遭受保單條款約定之「交通意外事故」，自「交通意外事故」發生之日起一百八十日以內身故者，本公司按保險金額給付「交通意外身故保險金」，本契約效力即行終止。', '2020-12-25 11:49:12', 0),
(5, '意外險', 10, 1000000, '主要給付項目為交通意外身故保險金或喪葬費用保險金。此方案之保額為一百萬。被保險人於本契約有效期間之10年內遭受保單條款約定之「交通意外事故」，自「交通意外事故」發生之日起一百八十日以內身故者，本公司按保險金額給付「交通意外身故保險金」，本契約效力即行終止。', '2020-12-25 11:49:57', 0),
(6, '意外險', 15, 1500000, '主要給付項目為交通意外身故保險金或喪葬費用保險金。此方案之保額為一百五十萬。被保險人於本契約有效期間之15年內遭受保單條款約定之「交通意外事故」，自「交通意外事故」發生之日起一百八十日以內身故者，本公司按保險金額給付「交通意外身故保險金」，本契約效力即行終止。', '2020-12-25 11:50:05', 0),
(7, '壽險', 10, 1000000, '給付項目為身故保險金或喪葬費用保險金。此方案之保額為一百萬。被保險人於本契約有效期間之10年內身故者，本公司按被保險人身故時之保險金額給付身故保險金後，本契約效力即行終止。\r\n', '2020-12-25 11:50:15', 0),
(8, '壽險', 20, 2000000, '給付項目為身故保險金或喪葬費用保險金。此方案之保額為兩百萬。被保險人於本契約有效期間之20年內身故者，本公司按被保險人身故時之保險金額給付身故保險金後，本契約效力即行終止。\r\n', '2020-12-25 11:50:24', 0),
(9, '壽險', 30, 3000000, '給付項目為身故保險金或喪葬費用保險金。此方案之保額為三百萬。被保險人於本契約有效期間之30年內身故者，本公司按被保險人身故時之保險金額給付身故保險金後，本契約效力即行終止。', '2021-01-03 08:28:20', 0),
(10, '12313', 13123, 132131, '123131', '2021-01-03 08:31:41', 1),
(11, '132133', 13213, 13123, '11321', '2021-01-03 08:38:05', 1),
(12, '12313', 1231312, 121, '1231313', '2021-01-03 08:43:35', 1),
(13, '123131', 123, 13213, '1231', '2021-01-03 12:28:35', 1),
(14, '12313131', 1321, 12321, 'jjjjj', '2021-01-03 14:18:27', 1),
(15, '23132', 12, 12, '12', '2021-01-07 12:14:11', 1),
(16, '1231', 123123, 13213, 'afabba', '2021-01-07 12:14:31', 1),
(17, 'af avd', 12, 123, 'avveffafve', '2021-01-07 12:25:14', 1),
(18, '213123', 12, 1224144, '213214124', '2021-01-07 12:39:17', 1),
(19, '1214', 123, 123, '123131234', '2021-01-07 13:18:52', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `insurance_policy`
--

CREATE TABLE `insurance_policy` (
  `insurance_policy_id` int(11) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `insurance_id` int(12) NOT NULL,
  `insurance_premium` int(11) NOT NULL COMMENT '保費',
  `beneficiary_name` varchar(50) NOT NULL,
  `beneficiary_relationship` varchar(50) NOT NULL,
  `beneficiary_phone_number` varchar(50) NOT NULL,
  `beneficiary_address` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_key` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `insurance_policy`
--

INSERT INTO `insurance_policy` (`insurance_policy_id`, `member_id`, `insurance_id`, `insurance_premium`, `beneficiary_name`, `beneficiary_relationship`, `beneficiary_phone_number`, `beneficiary_address`, `create_time`, `modify_time`, `delete_key`) VALUES
(1, 'A123456789', 3, 5000, '阿二abad', '夫妻', '0322112313', '連江縣莒光鄉2號', '2020-12-24 19:09:25', '2021-01-07 12:37:42', 0),
(11, 'A123456789', 3, 5000, '阿二', '夫妻', '0322112313', '連江縣莒光鄉2號', '2020-12-24 19:09:25', '2021-01-02 08:43:27', 0),
(12, 'A123456789', 3, 5000, '阿二', '夫妻', '0322112313', '連江縣莒光鄉2號', '2020-12-24 19:09:25', '2021-01-02 08:43:27', 0),
(13, 'A123456789', 3, 5000, '阿二', '夫妻', '0322112313', '連江縣莒光鄉2號', '2020-12-24 19:09:25', '2021-01-02 08:43:27', 0),
(14, 'A123456789', 3, 5000, '阿二', '夫妻', '0322112313', '連江縣莒光鄉2號', '2020-12-24 19:09:25', '2021-01-02 08:43:27', 0),
(18, 'A123456789', 2, 9600, 'feefe', 'erer', '0991-212-121', '232r', '2021-01-05 11:10:42', '2021-01-05 11:11:59', 1),
(19, 'A123456789', 1, 4800, '142124124', '123124', '0912-123-132', '22441', '2021-01-06 09:48:33', '2021-01-06 09:48:32', 0),
(20, 'A123456789', 2, 9600, '123131', '2313', '0921-123-123', '312412414', '2021-01-06 12:50:13', '2021-01-07 12:38:05', 1),
(21, 'E123456789', 2, 11200, '阿一', '夫妻', '0911-111-111', '央央大學', '2021-01-07 13:16:11', '2021-01-07 13:20:05', 1),
(22, 'E123456789', 9, 16800, 'ffefa', '123123', '0921-123-123', '124124124', '2021-01-07 13:25:29', '2021-01-07 13:26:44', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `member`
--

CREATE TABLE `member` (
  `ID_number` char(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `bank_account` char(16) NOT NULL,
  `birthday` date NOT NULL,
  `gender` bit(1) NOT NULL,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `disease_id` int(11) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `manager` tinyint(1) NOT NULL DEFAULT '0',
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_key` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `member`
--

INSERT INTO `member` (`ID_number`, `name`, `email`, `password`, `bank_account`, `birthday`, `gender`, `height`, `weight`, `disease_id`, `phone_number`, `address`, `manager`, `time_stamp`, `delete_key`) VALUES
('A123456789', '阿一', 'a123@ncu.edu.tw', 'a123456', '1234567890123456', '1999-01-01', b'1', 180, 80, 1, '0987-654-321', '中央大學志希館123213123', 0, '2021-01-07 13:44:41', 0),
('B287654321', '阿管', '87878@yahoo.com', '87878', '9898989898989898', '1988-08-07', b'1', 187, 87, 2, '0987878787', '台北市信義區光復南路87號', 1, '2020-12-25 11:23:25', 0),
('C212345678', '阿二', '222@gmail.com', '2266', '1212121212121212', '2000-02-06', b'0', 172, 52, 3, '032222222', '連江縣莒光鄉2號', 0, '2021-01-07 13:44:11', 0),
('E123456789', '阿五', '555@gmail.com', 'A5555555', '1234123412341234', '2000-01-01', b'0', 180, 100, 0, '0955-555-555', '央央大學', 0, '2021-01-07 13:24:33', 0),
('tony', '阿一', 'a123@ncu.edu.tw', 'vony123tony123', '1234567890123456', '1999-01-01', b'1', 180, 80, 1, '0987654321', '中央大學志希館', 1, '2021-01-07 08:39:47', 0);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`insurance_id`);

--
-- 資料表索引 `insurance_policy`
--
ALTER TABLE `insurance_policy`
  ADD PRIMARY KEY (`insurance_policy_id`),
  ADD KEY `member_id_fk` (`member_id`),
  ADD KEY `insurance_id_fk` (`insurance_id`);

--
-- 資料表索引 `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`ID_number`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `insurance`
--
ALTER TABLE `insurance`
  MODIFY `insurance_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `insurance_policy`
--
ALTER TABLE `insurance_policy`
  MODIFY `insurance_policy_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `insurance_policy`
--
ALTER TABLE `insurance_policy`
  ADD CONSTRAINT `insurance_id_fk` FOREIGN KEY (`insurance_id`) REFERENCES `insurance` (`insurance_id`),
  ADD CONSTRAINT `member_id_fk` FOREIGN KEY (`member_id`) REFERENCES `member` (`ID_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
