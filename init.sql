CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id_ref` (`author_id`),
  CONSTRAINT `author_id_ref` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `blog_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(20) DEFAULT NULL,
  `blog_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `blog_comment_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `author` (`id`, `author_name`) VALUES ('1', 'xxx');
INSERT INTO `author` (`id`, `author_name`) VALUES ('2', 'ffff');


INSERT INTO `blog` (`id`, `username`, `author_id`, `status`) VALUES ('1', 'jiangjian', '1', '1');
INSERT INTO `blog` (`id`, `username`, `author_id`, `status`) VALUES ('2', 'alice', '1', NULL);

INSERT INTO `blog_comment` (`id`, `content`, `blog_id`) VALUES ('1', 'fdsf', '1');
INSERT INTO `blog_comment` (`id`, `content`, `blog_id`) VALUES ('2', 'ffs', '2');







