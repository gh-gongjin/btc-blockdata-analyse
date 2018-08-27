/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.17-log : Database - btc-blockdata-analyse
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`btc-blockdata-analyse` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `btc-blockdata-analyse`;

/*Table structure for table `t_account_info` */

CREATE TABLE `t_account_info` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(30) NOT NULL COMMENT '账户名',
  `password` varchar(100) NOT NULL COMMENT '密码(md5加密)',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `lately_login_time` varchar(50) DEFAULT NULL COMMENT '最近登录时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0:有效 -1:失效',
  `creator` bigint(11) DEFAULT NULL COMMENT '创建人',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` bigint(11) DEFAULT NULL COMMENT '更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_login_log` */

CREATE TABLE `t_login_log` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` bigint(11) DEFAULT NULL COMMENT '账户id',
  `login_time` varchar(50) DEFAULT NULL COMMENT '登录时间',
  `login_address` varchar(100) DEFAULT NULL COMMENT '登录地区',
  `creator` bigint(11) DEFAULT NULL COMMENT '创建人',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
