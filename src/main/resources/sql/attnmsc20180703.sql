/*
SQLyog Ultimate v11.22 (64 bit)
MySQL - 5.7.12-log : Database - attnms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`attnms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `attnms`;

/*Table structure for table `tbl_login_records` */

DROP TABLE IF EXISTS `tbl_login_records`;

CREATE TABLE `tbl_login_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `locked` char(1) NOT NULL COMMENT '锁定标志，''1''代表锁定状态 ''0''未锁定状态',
  `times` int(11) NOT NULL COMMENT '登录失败次数',
  `logindate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间，默认为当前时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户登录记录表';

/*Data for the table `tbl_login_records` */

insert  into `tbl_login_records`(`id`,`username`,`locked`,`times`,`logindate`) values (1,'jerry','0',1,'2018-07-03 11:07:02');

/*Table structure for table `tbl_users` */

DROP TABLE IF EXISTS `tbl_users`;

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `pwd` varchar(32) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_users` */

insert  into `tbl_users`(`id`,`username`,`pwd`) values (1,'160571','newcapec');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
