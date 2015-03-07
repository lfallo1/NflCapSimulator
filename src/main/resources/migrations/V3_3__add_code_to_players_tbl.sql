ALTER TABLE `player` ADD COLUMN `code` VARCHAR(45) NULL AFTER `weight`;

UPDATE player set code = CONCAT(REPLACE(SUBSTRING(name, 1, 5), ' ', ''), id);