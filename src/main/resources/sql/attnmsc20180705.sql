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

/*Table structure for table `tbl_departments` */

DROP TABLE IF EXISTS `tbl_departments`;

CREATE TABLE `tbl_departments` (
  `deptno` int(11) NOT NULL COMMENT '部门编号',
  `dname` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `loc` varchar(50) DEFAULT NULL COMMENT '地理位置',
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息表';

/*Data for the table `tbl_departments` */

insert  into `tbl_departments`(`deptno`,`dname`,`loc`) values (10,'研发部','北京'),(20,'项目管理部','上海'),(30,'销售部','广州'),(40,'财务部','武汉'),(50,'售后部','郑州'),(60,'deptA','郑州');

/*Table structure for table `tbl_employees` */

DROP TABLE IF EXISTS `tbl_employees`;

CREATE TABLE `tbl_employees` (
  `empno` int(11) NOT NULL COMMENT '员工编码',
  `ename` varchar(50) DEFAULT NULL COMMENT '员工名字',
  `job` varchar(50) DEFAULT NULL COMMENT '工作岗位',
  `mgr` int(11) DEFAULT NULL COMMENT '部门经理id',
  `hiredate` date DEFAULT NULL COMMENT '入职日期',
  `sal` decimal(7,2) DEFAULT NULL COMMENT '工资',
  `bonus` decimal(7,2) DEFAULT NULL COMMENT '奖金',
  `deptno` int(11) DEFAULT NULL COMMENT '部门编号',
  `deptname` varchar(64) DEFAULT NULL COMMENT '部门名称（冗余字段）',
  PRIMARY KEY (`empno`),
  KEY `fk_emp` (`mgr`),
  KEY `fk_deptno` (`deptno`),
  CONSTRAINT `fk_emp` FOREIGN KEY (`mgr`) REFERENCES `tbl_employees` (`empno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息表';

/*Data for the table `tbl_employees` */

insert  into `tbl_employees`(`empno`,`ename`,`job`,`mgr`,`hiredate`,`sal`,`bonus`,`deptno`,`deptname`) values (1001,'James','文员',1013,'2000-12-17','8000.00',NULL,20,'项目管理部'),(1002,'alice','销售员',1006,'2001-02-20','16000.00','3000.00',30,'销售部'),(1003,'evan','销售员',1006,'2001-02-22','12500.00','5000.00',30,'销售部'),(1004,'susan','经理',1009,'2001-04-02','29750.00',NULL,20,'项目管理部'),(1005,'Philips','销售员',1006,'2001-09-28','12500.00','14000.00',30,'销售部'),(1006,'peter','经理',1009,'2001-05-01','28500.00',NULL,30,'销售部'),(1007,'william','经理',1009,'2001-09-01','24500.00',NULL,10,'研发部'),(1008,'George','分析师',1004,'2007-04-19','30000.00',NULL,20,'项目管理部'),(1009,'jerry','高级工程师',NULL,'2001-11-17','50000.00',NULL,10,'研发部'),(1010,'ady','销售员',1006,'2001-09-08','15000.00','0.00',30,'销售部'),(1011,'Vencen','文员',1008,'2007-05-23','11000.00',NULL,20,'项目管理部'),(1012,'anna','文员',1006,'2001-12-03','9500.00',NULL,30,'销售部'),(1013,'johne','分析师',1004,'2001-12-03','30000.00',NULL,20,'项目管理部'),(1014,'howard','文员',1007,'2002-01-23','13000.00',NULL,10,'研发部'),(1015,'Tom','文员',1007,'2002-01-23','13000.00',NULL,100,NULL);

/*Table structure for table `tbl_login_records` */

DROP TABLE IF EXISTS `tbl_login_records`;

CREATE TABLE `tbl_login_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `locked` char(1) NOT NULL COMMENT '锁定标志，''1''代表锁定状态 ''0''未锁定状态',
  `times` int(11) NOT NULL COMMENT '登录失败次数',
  `logindate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间，默认为当前时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统用户登录记录表';

/*Data for the table `tbl_login_records` */

insert  into `tbl_login_records`(`id`,`username`,`locked`,`times`,`logindate`) values (1,'jerry','0',1,'2018-07-03 11:07:02'),(7,'160571','0',0,'2018-07-05 16:15:11');

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
