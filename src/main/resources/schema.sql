--DROP DATABASE IF EXISTS job_search_db;
--CREATE DATABASE IF NOT EXISTS job_search_db DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

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
    img                 VARCHAR(255) COMMENT '头像',
    create_time         VARCHAR(255) COMMENT '创建时间',
    update_time         VARCHAR(255) COMMENT '更新时间'
);
-- -----------
-- Records of user
-- -----------
INSERT INTO user(id, first_name, last_name, email, password, dob, mobile_no, gender, address, address_state, address_postal_code, address_city, address_country, id_no, role)
values ('1', 'admin', 'admin', 'admin@gmail.com', 'admin', '12-01-1920', '0123456789', 'Male', 'No 5, Jalan 20, Taman Malaysia', 'Kuala Lumpur', '57600', 'KL', 'Malaysia', '950516105361', 'Admin');


-- ----------------------------
-- Table structure for experience
-- ----------------------------
DROP TABLE IF EXISTS experience;
CREATE TABLE experience
(
    id                  VARCHAR(255) NOT NULL PRIMARY KEY COMMENT 'id',
    user_id             VARCHAR(255) NOT NULL COMMENT '用户id',
    position_title      VARCHAR(255) NOT NULL COMMENT '职位名称',
    company_name        VARCHAR(255) NOT NULL COMMENT '公司名称',
    duration_from       VARCHAR(255) NOT NULL COMMENT '开始于',
    duration_to         VARCHAR(255) NOT NULL COMMENT '结束于',
    position_level      VARCHAR(255) NOT NULL COMMENT '职位级别',
    salary              VARCHAR(255) COMMENT '薪水',
    description         VARCHAR(255) COMMENT '岗位描述',
    create_time         VARCHAR(255) COMMENT '创建时间',
    update_time         VARCHAR(255) COMMENT '更新时间',

    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- -----------
-- Records of experience
-- -----------
INSERT INTO experience(id, user_id, position_title, company_name, duration_from, duration_to, position_level, salary, description)
values ('1', '1', 'Software Developer', 'IT Solution', '12-01-2017', '31-01-2020', 'Manager', '8500', 'An experienced Software Manager');

-- ----------------------------
-- Table structure for education
-- ----------------------------
DROP TABLE IF EXISTS education;
CREATE TABLE education
(
    id                  VARCHAR(255) NOT NULL PRIMARY KEY COMMENT 'id',
    user_id             VARCHAR(255) NOT NULL COMMENT '用户id',
    university          VARCHAR(255) NOT NULL COMMENT '毕业大学',
    graduation_date     VARCHAR(255) NOT NULL COMMENT '毕业日期',
    qualification       VARCHAR(255) NOT NULL COMMENT '文凭',
    field_of_study      VARCHAR(255) NOT NULL COMMENT '专业领域',
    grade               VARCHAR(255) NOT NULL COMMENT 'CGPA/ Pass/ Non-gradable',
    description         VARCHAR(255) COMMENT '专业描述',
    create_time         VARCHAR(255) COMMENT '创建时间',
    update_time         VARCHAR(255) COMMENT '更新时间',

    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- -----------
-- Records of education
-- -----------
INSERT INTO education(id, user_id, university, graduation_date, qualification, field_of_study, grade, description)
values ('1', '1', 'Monash University', 'May 2021', 'Bachelor Degree', 'Multimedia Design', 'CGPA 3.4', 'A fresh graduate who passionate in multimedia field');

-- -----------------------
-- Table structure for job
-- -----------------------
DROP TABLE IF EXISTS job;
CREATE TABLE job
(
    id                  VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '用户id',
    position_title      VARCHAR(255) NOT NULL COMMENT '职位名称',
    company_name        VARCHAR(255) NOT NULL COMMENT '公司名称',
    address_state       VARCHAR(255) NOT NULL COMMENT '工作所在州属',
    description         VARCHAR(255) NOT NULL COMMENT '工作内容描述',
    salary              VARCHAR(255) NOT NULL COMMENT '薪水',
    career_level        VARCHAR(255) COMMENT '职业等级',
    qualification       VARCHAR(255) COMMENT '职位资格',
    type                VARCHAR(255) COMMENT '工作类型 (Full-time/ Part-time/ Contract)',
    create_time         VARCHAR(255) COMMENT '创建时间',
    update_time         VARCHAR(255) COMMENT '更新时间'
);
-- --------------
-- Records of job
-- --------------
INSERT INTO job(id, position_title, company_name, address_state, description, salary, career_level, qualification, type)
values ('1', 'Financial Analyst', 'International Bank', 'Selangor', 'Perform financial forecasting, reporting, and operational metrics tracking', '5500', 'Senior', 'Bachelor Degree in Finance or equivalent', 'Full-time');

-- ----------------------------
-- Table structure for applications
-- ----------------------------
DROP TABLE IF EXISTS applications;
CREATE TABLE applications
(
    id                  VARCHAR(255) NOT NULL PRIMARY KEY COMMENT 'id',
    user_id             VARCHAR(255) COMMENT '用户id',
    job_id              VARCHAR(255) COMMENT 'job id',
    position_title      VARCHAR(255) NOT NULL COMMENT '职位名称',
    company_name        VARCHAR(255) NOT NULL COMMENT '公司名称',
    status              VARCHAR(255) NOT NULL COMMENT '1 = 成功，0 = 失败, 2 = In progress',
    create_time         VARCHAR(255) COMMENT '创建时间',
    update_time         VARCHAR(255) COMMENT '更新时间',

    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (job_id) REFERENCES job (id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- -----------------------
-- Records of applications
-- -----------------------
INSERT INTO applications(id, user_id, job_id, position_title, company_name, status)
values ('1', '1', '1', 'Financial Analyst', 'International Bank', '2');


SET FOREIGN_KEY_CHECKS = 1;