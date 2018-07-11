CREATE TABLE `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` text NOT NULL,
  `url` varchar(255) NOT NULL,
  `author_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `labels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `shares` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `share_from` varchar(45) NOT NULL,
  `share_to` varchar(45) NOT NULL,
  `note_id` int(11) NOT NULL,
  `shared_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);