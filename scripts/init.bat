:: IMPDATA 导入文件名 用户名
@ECHO OFF
del imp.sql
echo DROP DATABASE IF EXISTS `%2`;>>imp.sql
echo CREATE DATABASE `%2`CHARACTER SET utf8;>>imp.sql
echo DROP USER '%2'@'%%';>>imp.sql
echo DROP USER '%2'@'localhost';>>imp.sql
echo CREATE USER '%2'@'%%' IDENTIFIED BY '%2';>>imp.sql
echo CREATE USER '%2'@'localhost' IDENTIFIED BY '%2';>>imp.sql
echo GRANT ALL ON `%2`.* TO '%2'@'%%';>>imp.sql
echo GRANT ALL ON `%2`.* TO '%2'@'localhost';>>imp.sql
echo USE %2;>>imp.sql
echo source %1;>>imp.sql
MYSQL -uroot --default-character-set=utf8 --force < imp.sql