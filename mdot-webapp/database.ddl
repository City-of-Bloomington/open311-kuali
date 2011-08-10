DROP TABLE MIU.NOTIFICATION_T CASCADE CONSTRAINTS
/
DROP PUBLIC SYNONYM NOTIFICATION_T
/
DROP SEQUENCE MIU.SEQ_NOTIFICATION_T
/
DROP PUBLIC SYNONYM SEQ_NOTIFICATION_T
/

CREATE SEQUENCE MIU.SEQ_NOTIFICATION_T INCREMENT BY 1 START WITH 1000
/
CREATE PUBLIC SYNONYM SEQ_NOTIFICATION_T FOR MIU.SEQ_NOTIFICATION_T
/
GRANT SELECT, ALTER ON MIU.SEQ_NOTIFICATION_T TO MIU_PROXY
/

CREATE TABLE MIU.NOTIFICATION_T (
   	NOTIFICATION_ID NUMERIC(18,0) NOT NULL,
	START_DT DATE NULL,
	END_DT DATE NULL,
   	TITLE VARCHAR2(2000) NULL,
   	MESSAGE VARCHAR2(2000) NULL,
   	PCAMPUS VARCHAR2(2000) NULL,
   	NOTIFICATION_TYPE NUMERIC(18,0) NULL,   	
   	VER_NBR NUMERIC(18,0) NOT NULL,
   	CONSTRAINT NOTIFICATION_T_PK PRIMARY KEY (NOTIFICATION_ID)
) 
/
CREATE PUBLIC SYNONYM NOTIFICATION_T FOR MIU.NOTIFICATION_T
/
GRANT SELECT, UPDATE, INSERT, DELETE ON MIU.NOTIFICATION_T TO MIU_PROXY
/



DROP TABLE MIU.USER_NOTIFICATION_T CASCADE CONSTRAINTS
/
DROP PUBLIC SYNONYM USER_NOTIFICATION_T
/
DROP SEQUENCE MIU.SEQ_USER_NOTIFICATION_T
/
DROP PUBLIC SYNONYM SEQ_USER_NOTIFICATION_T
/

CREATE SEQUENCE MIU.SEQ_USER_NOTIFICATION_T INCREMENT BY 1 START WITH 1000
/
CREATE PUBLIC SYNONYM SEQ_USER_NOTIFICATION_T FOR MIU.SEQ_USER_NOTIFICATION_T
/
GRANT SELECT, ALTER ON MIU.SEQ_USER_NOTIFICATION_T TO MIU_PROXY
/

CREATE TABLE MIU.USER_NOTIFICATION_T (
   	USER_NOTIFICATION_ID NUMERIC(18,0) NOT NULL,
   	NOTIFICATION_ID NUMERIC(18,0) NULL,
   	PERSON_ID NUMERIC(18,0) NULL,
   	DEVICE_ID VARCHAR2(2000) NULL,
	NOTIFY_DT DATE NULL,
   	VER_NBR NUMERIC(18,0) NOT NULL,
   	CONSTRAINT USER_NOTIFICATION_T_PK PRIMARY KEY (USER_NOTIFICATION_ID)
) 
/
CREATE PUBLIC SYNONYM USER_NOTIFICATION_T FOR MIU.USER_NOTIFICATION_T
/
GRANT SELECT, UPDATE, INSERT, DELETE ON MIU.USER_NOTIFICATION_T TO MIU_PROXY
/


insert into NOTIFICATION_T (NOTIFICATION_ID, START_DT, END_DT, TITLE, MESSAGE, NOTIFICATION_TYPE, VER_NBR) values (1, null, null, 'First Notification', 'Hope this works', 1, 0)
/
insert into NOTIFICATION_T (NOTIFICATION_ID, START_DT, END_DT, TITLE, MESSAGE, NOTIFICATION_TYPE, VER_NBR) values (2, TO_DATE('01.08.2011', 'DD.MM.YYYY'), TO_DATE('11.11.2011', 'DD.MM.YYYY'), 'Second Notification', 'With valid dates', 1, 0)
/
insert into NOTIFICATION_T (NOTIFICATION_ID, START_DT, END_DT, TITLE, MESSAGE, NOTIFICATION_TYPE, VER_NBR) values (3, TO_DATE('01.08.2011', 'DD.MM.YYYY'), TO_DATE('02.08.2011', 'DD.MM.YYYY'), 'Third Notification', 'With invalid dates', 1, 0)
/









DROP TABLE MIU.HOME_T CASCADE CONSTRAINTS
/
DROP PUBLIC SYNONYM HOME_T
/
DROP SEQUENCE MIU.SEQ_HOME_T
/
DROP PUBLIC SYNONYM SEQ_HOME_T
/

CREATE SEQUENCE MIU.SEQ_HOME_T INCREMENT BY 1 START WITH 1000
/
CREATE PUBLIC SYNONYM SEQ_HOME_T FOR MIU.SEQ_HOME_T
/
GRANT SELECT, ALTER ON MIU.SEQ_HOME_T TO MIU_PROXY
/

CREATE TABLE MIU.HOME_T (
   	HOME_ID NUMERIC(18,0) NOT NULL,
   	HOME_NM VARCHAR2(2000) NULL,
   	VER_NBR NUMERIC(18,0) NOT NULL,
   	CONSTRAINT HOME_T_PK PRIMARY KEY (HOME_ID)
) 
/
CREATE PUBLIC SYNONYM HOME_T FOR MIU.HOME_T
/
GRANT SELECT, UPDATE, INSERT, DELETE ON MIU.HOME_T TO MIU_PROXY
/



DROP TABLE MIU.TOOL_T CASCADE CONSTRAINTS
/
DROP PUBLIC SYNONYM TOOL_T
/
DROP SEQUENCE MIU.SEQ_TOOL_T
/
DROP PUBLIC SYNONYM SEQ_TOOL_T
/

CREATE SEQUENCE MIU.SEQ_TOOL_T INCREMENT BY 1 START WITH 1000
/
CREATE PUBLIC SYNONYM SEQ_TOOL_T FOR MIU.SEQ_TOOL_T
/
GRANT SELECT, ALTER ON MIU.SEQ_TOOL_T TO MIU_PROXY
/

CREATE TABLE MIU.TOOL_T (
   	TOOL_ID NUMERIC(18,0) NOT NULL,
   	HOME_NM VARCHAR2(2000) NULL,
   	TITLE VARCHAR2(2000) NULL,
   	URL VARCHAR2(2000) NULL,
   	DESCRIPTION VARCHAR2(2000) NULL,
   	ICON_URL VARCHAR2(2000) NULL,
   	VER_NBR NUMERIC(18,0) NOT NULL,
   	CONSTRAINT TOOL_T_PK PRIMARY KEY (TOOL_ID)
) 
/
CREATE PUBLIC SYNONYM TOOL_T FOR MIU.TOOL_T
/
GRANT SELECT, UPDATE, INSERT, DELETE ON MIU.TOOL_T TO MIU_PROXY
/

DROP TABLE MIU.HOME_TOOL_T CASCADE CONSTRAINTS
/
DROP PUBLIC SYNONYM HOME_TOOL_T
/

CREATE TABLE MIU.HOME_TOOL_T (
   	HOME_ID NUMERIC(18,0) NOT NULL,
   	TOOL_ID NUMERIC(18,0) NOT NULL,
   	CONSTRAINT HOME_TOOL_T_PK PRIMARY KEY (HOME_ID, TOOL_ID)
) 
/
CREATE PUBLIC SYNONYM HOME_TOOL_T FOR MIU.HOME_TOOL_T
/
GRANT SELECT, UPDATE, INSERT, DELETE ON MIU.HOME_TOOL_T TO MIU_PROXY
/



