USE web_resume
;

SET FOREIGN_KEY_CHECKS=0;



DROP TABLE IF EXISTS CERTIFICATIONS CASCADE
;
DROP TABLE IF EXISTS COUNTRIES CASCADE
;
DROP TABLE IF EXISTS EDUCATIONS CASCADE
;
DROP TABLE IF EXISTS EXP_ACTIVITIES CASCADE
;
DROP TABLE IF EXISTS EXPERIENCES CASCADE
;
DROP TABLE IF EXISTS GENERAL_DATA CASCADE
;
DROP TABLE IF EXISTS PERSON_COUNTRIES CASCADE
;
DROP TABLE IF EXISTS PERSON_LABELS CASCADE
;
DROP TABLE IF EXISTS QUALIFICATIONS CASCADE
;

CREATE TABLE CERTIFICATIONS
(
	CERT_ID INTEGER NOT NULL AUTO_INCREMENT,
	GEN_DAT_ID INTEGER NOT NULL,
	CERT_NAME TEXT CHARACTER SET utf8,
	CERT_DATE VARCHAR(50) CHARACTER SET utf8,
	PRIMARY KEY (CERT_ID),
	KEY (GEN_DAT_ID)

) 
;


CREATE TABLE COUNTRIES
(
	COUNTRY_ID INTEGER NOT NULL,
	NAME VARCHAR(50),
	FLAG LONGBLOB,
	PRIMARY KEY (COUNTRY_ID)

) 
;


CREATE TABLE EDUCATIONS
(
	EDUC_ID INTEGER NOT NULL AUTO_INCREMENT,
	GEN_DAT_ID INTEGER NOT NULL,
	DIPLOMA VARCHAR(100) CHARACTER SET utf8,
	EDUC_CENTER VARCHAR(100) CHARACTER SET utf8,
	EDUC_PERIOD VARCHAR(50) CHARACTER SET utf8,
	PRIMARY KEY (EDUC_ID),
	KEY (GEN_DAT_ID)

) 
;


CREATE TABLE EXP_ACTIVITIES
(
	EXP_ACT_ID INTEGER NOT NULL AUTO_INCREMENT,
	EXP_ID INTEGER NOT NULL,
	ACTIVITY TEXT CHARACTER SET utf8,
	PRIMARY KEY (EXP_ACT_ID),
	KEY (EXP_ID)

) 
;


CREATE TABLE EXPERIENCES
(
	EXP_ID INTEGER NOT NULL AUTO_INCREMENT,
	GEN_DAT_ID INTEGER NOT NULL,
	POSITION VARCHAR(50) CHARACTER SET utf8,
	COMPANY VARCHAR(50) CHARACTER SET utf8,
	PERIOD VARCHAR(50) CHARACTER SET utf8,
	PRIMARY KEY (EXP_ID),
	KEY (GEN_DAT_ID)

) 
;


CREATE TABLE GENERAL_DATA
(
	GEN_DAT_ID INTEGER NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(50) CHARACTER SET utf8,
	SURNAME VARCHAR(50) CHARACTER SET utf8,
	CURRENT_POST VARCHAR(50) CHARACTER SET utf8,
	CURRENT_COMPANY VARCHAR(50) CHARACTER SET utf8,
	CURRENT_LOCATION VARCHAR(50) CHARACTER SET utf8,
	CURRENT_BUS_PHONE VARCHAR(50) CHARACTER SET utf8,
	CURRENT_BUSINESS_MAIL VARCHAR(50) CHARACTER SET utf8,
	SN_LINKEDIN VARCHAR(50) CHARACTER SET utf8,
	SN_TWITTER VARCHAR(50) CHARACTER SET utf8,
	PASSWORD VARCHAR(50) CHARACTER SET utf8,
	PRIMARY KEY (GEN_DAT_ID)

) 
;


CREATE TABLE PERSON_COUNTRIES
(
	PER_CTR_ID INTEGER NOT NULL AUTO_INCREMENT,
	GEN_DAT_ID INTEGER NOT NULL,
	COUNTRY_ID INTEGER NOT NULL,
	PRIMARY KEY (PER_CTR_ID),
	KEY (COUNTRY_ID),
	KEY (GEN_DAT_ID)

) 
;


CREATE TABLE PERSON_LABELS
(
	PER_LAB_ID INTEGER NOT NULL AUTO_INCREMENT,
	GEN_DAT_ID INTEGER NOT NULL,
	LABEL LONGBLOB,
	PRIMARY KEY (PER_LAB_ID),
	KEY (GEN_DAT_ID)

) 
;


CREATE TABLE QUALIFICATIONS
(
	QUAL_ID INTEGER NOT NULL AUTO_INCREMENT,
	GEN_DAT_ID INTEGER NOT NULL,
	QUALIFICATION TEXT CHARACTER SET utf8,
	PRIMARY KEY (QUAL_ID),
	KEY (GEN_DAT_ID)

) 
;



SET FOREIGN_KEY_CHECKS=1;


ALTER TABLE CERTIFICATIONS ADD CONSTRAINT FK_SERTIFICATION_GENERAL_DATA 
	FOREIGN KEY (GEN_DAT_ID) REFERENCES GENERAL_DATA (GEN_DAT_ID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE EDUCATIONS ADD CONSTRAINT FK_EDUCATION_GENERAL_DATA 
	FOREIGN KEY (GEN_DAT_ID) REFERENCES GENERAL_DATA (GEN_DAT_ID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE EXP_ACTIVITIES ADD CONSTRAINT FK_EXP_ACTIVITIES_EXPERIENCE 
	FOREIGN KEY (EXP_ID) REFERENCES EXPERIENCES (EXP_ID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE EXPERIENCES ADD CONSTRAINT FK_EXPERIENCE_GENERAL_DATA 
	FOREIGN KEY (GEN_DAT_ID) REFERENCES GENERAL_DATA (GEN_DAT_ID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE PERSON_COUNTRIES ADD CONSTRAINT FK_PERSON_COUNTRIES_COUNTRIES 
	FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRIES (COUNTRY_ID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE PERSON_COUNTRIES ADD CONSTRAINT FK_PERSON_COUNTRIES_GENERAL_DATA 
	FOREIGN KEY (GEN_DAT_ID) REFERENCES GENERAL_DATA (GEN_DAT_ID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE PERSON_LABELS ADD CONSTRAINT FK_PERSON_LABELS_GENERAL_DATA 
	FOREIGN KEY (GEN_DAT_ID) REFERENCES GENERAL_DATA (GEN_DAT_ID)
	ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE QUALIFICATIONS ADD CONSTRAINT FK_QUALIFICATIONS_GENERAL_DATA 
	FOREIGN KEY (GEN_DAT_ID) REFERENCES GENERAL_DATA (GEN_DAT_ID)
	ON DELETE CASCADE ON UPDATE CASCADE
;
