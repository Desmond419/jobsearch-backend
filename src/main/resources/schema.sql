DROP DATABASE IF EXISTS job_search_db;
CREATE DATABASE IF NOT EXISTS job_search_db DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use job_search_db;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id                  VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '用户id',
    first_name          VARCHAR(255) NOT NULL COMMENT '姓氏',
    last_name           VARCHAR(255) NOT NULL COMMENT '名字',
    email               VARCHAR(255) NOT NULL COMMENT '邮箱',
    password            VARCHAR(255) NOT NULL COMMENT '密码',
    dob                 VARCHAR(255) COMMENT '生日',
    mobile_no           VARCHAR(255) COMMENT '电话',
    gender              VARCHAR(255) COMMENT '性别',
    address             VARCHAR(255) COMMENT '地址',
    address_state       VARCHAR(255) COMMENT '州属',
    address_postal_code VARCHAR(255) COMMENT '邮编',
    address_city        VARCHAR(255) COMMENT '城市',
    address_country     VARCHAR(255) COMMENT '国家',
    id_no               VARCHAR(255) COMMENT '身份证',
    role                VARCHAR(255) NOT NULL COMMENT '角色/权限',
    create_time         TIMESTAMP                             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time         TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
);
-- -----------
-- Records of user
-- -----------
INSERT INTO user(id, first_name, last_name, email, password, dob, mobile_no, gender, address, address_state, address_postal_code, address_city, address_country, id_no, role)
values ('1', 'admin', 'admin', 'admin@gmail.com', 'admin', '12-01-1920', '0123456789', 'Male', 'No 5, Jalan 20, Taman Malaysia', 'Kuala Lumpur', '57600', 'KL', 'Malaysia', '950516105361', 'Admin');

SET FOREIGN_KEY_CHECKS = 1;