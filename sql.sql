/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - webmagic
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webmagic` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `webmagic`;

/*Table structure for table `t_img` */

DROP TABLE IF EXISTS `t_img`;

CREATE TABLE `t_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `img_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `img_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `html_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_img` */

/*Table structure for table `t_type` */

DROP TABLE IF EXISTS `t_type`;

CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_type` */

/*Table structure for table `t_video` */

DROP TABLE IF EXISTS `t_video`;

CREATE TABLE `t_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `video_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频名称',
  `video_url` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频链接',
  `video_image_url` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频图片链接',
  `video_source` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频来源',
  `video_introduce` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频介绍',
  `video_type` int(11) DEFAULT NULL COMMENT '视频类型',
  `create_date` datetime DEFAULT NULL COMMENT '视频存入的时期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_video` */

insert  into `t_video`(`id`,`video_name`,`video_url`,`video_image_url`,`video_source`,`video_introduce`,`video_type`,`create_date`) values (21,'瑞克和莫蒂 第四季第01集','http://meijutw.com/player/1587_1.html','http://meijutw.com/pic/1587.jpg','http://meijutw.com/1587','《瑞克和莫蒂 第四季》',NULL,'2019-12-30 21:06:45'),(22,'瑞克和莫蒂 第四季第02集','http://meijutw.com/player/1587_2.html','http://meijutw.com/pic/1587.jpg','http://meijutw.com/1587','《瑞克和莫蒂 第四季》',NULL,'2019-12-30 21:06:47'),(23,'瑞克和莫蒂 第四季第03集','http://meijutw.com/player/1587_3.html','http://meijutw.com/pic/1587.jpg','http://meijutw.com/1587','《瑞克和莫蒂 第四季》',NULL,'2019-12-30 21:06:47'),(24,'瑞克和莫蒂 第四季第04集','http://meijutw.com/player/1587_4.html','http://meijutw.com/pic/1587.jpg','http://meijutw.com/1587','《瑞克和莫蒂 第四季》',NULL,'2019-12-30 21:06:47'),(25,'瑞克和莫蒂 第四季第05集','http://meijutw.com/player/1587_5.html','http://meijutw.com/pic/1587.jpg','http://meijutw.com/1587','《瑞克和莫蒂 第四季》',NULL,'2019-12-30 21:06:47');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
