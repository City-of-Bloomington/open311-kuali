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
   	ALIAS VARCHAR2(2000) NULL,
   	TITLE VARCHAR2(2000) NULL,
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
   	ALIAS VARCHAR2(2000) NULL,
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
DROP SEQUENCE MIU.SEQ_HOME_TOOL_T
/
DROP PUBLIC SYNONYM SEQ_HOME_TOOL_T
/

CREATE SEQUENCE MIU.SEQ_HOME_TOOL_T INCREMENT BY 1 START WITH 1000
/
CREATE PUBLIC SYNONYM SEQ_HOME_TOOL_T FOR MIU.SEQ_HOME_TOOL_T
/
GRANT SELECT, ALTER ON MIU.SEQ_HOME_TOOL_T TO MIU_PROXY
/

CREATE TABLE MIU.HOME_TOOL_T (
	HOME_TOOL_ID NUMERIC(18,0) NOT NULL,
   	HOME_ID NUMERIC(18,0) NOT NULL,
   	TOOL_ID NUMERIC(18,0) NOT NULL,
   	ORDR NUMERIC(8) NOT NULL,
   	VER_NBR NUMERIC(18,0) NOT NULL,
   	CONSTRAINT HOME_TOOL_T_PK PRIMARY KEY (HOME_TOOL_ID)
) 
/
CREATE PUBLIC SYNONYM HOME_TOOL_T FOR MIU.HOME_TOOL_T
/
GRANT SELECT, UPDATE, INSERT, DELETE ON MIU.HOME_TOOL_T TO MIU_PROXY
/


DROP TABLE MIU.TOUR_POI_T CASCADE CONSTRAINTS
/
DROP PUBLIC SYNONYM TOUR_POI_T
/
DROP SEQUENCE MIU.SEQ_TOUR_POI_T
/
DROP PUBLIC SYNONYM SEQ_TOUR_POI_T
/

CREATE SEQUENCE MIU.SEQ_TOUR_POI_T INCREMENT BY 1 START WITH 1000
/
CREATE PUBLIC SYNONYM SEQ_TOUR_POI_T FOR MIU.SEQ_TOUR_POI_T
/
GRANT SELECT, ALTER ON MIU.SEQ_TOUR_POI_T TO MIU_PROXY
/

CREATE TABLE MIU.TOUR_POI_T (
   	POI_ID NUMERIC(18,0) NOT NULL,
    NAME VARCHAR2(256) NULL,
   	POI_TYPE VARCHAR2(1) NOT NULL,
    LOCATION_ID VARCHAR2(256) NULL,
   	LAT DOUBLE PRECISION NOT NULL,
   	LNG DOUBLE PRECISION NOT NULL,
   	MEDIA CLOB NULL,
   	DESCRIPTION CLOB NULL,
   	URL VARCHAR2(2000) NULL,
   	TOUR_ID NUMERIC(18,0) NULL,	
   	VER_NBR NUMERIC(18,0) NOT NULL,
   	CONSTRAINT TOUR_POI_T_PK PRIMARY KEY (POI_ID)
) 
/
CREATE PUBLIC SYNONYM TOUR_POI_T FOR MIU.TOUR_POI_T
/
GRANT SELECT, UPDATE, INSERT, DELETE ON MIU.TOUR_POI_T TO MIU_PROXY
/



DROP TABLE MIU.TOUR_T CASCADE CONSTRAINTS
/
DROP PUBLIC SYNONYM TOUR_T
/
DROP SEQUENCE MIU.SEQ_TOUR_T
/
DROP PUBLIC SYNONYM SEQ_TOUR_T
/

CREATE SEQUENCE MIU.SEQ_TOUR_T INCREMENT BY 1 START WITH 1000
/
CREATE PUBLIC SYNONYM SEQ_TOUR_T FOR MIU.SEQ_TOUR_T
/
GRANT SELECT, ALTER ON MIU.SEQ_TOUR_T TO MIU_PROXY
/

CREATE TABLE MIU.TOUR_T (
   	TOUR_ID NUMERIC(18,0) NOT NULL,
    NAME VARCHAR2(100) NOT NULL,
    DESCRIPTION VARCHAR2(2000) NULL,
   	PATH VARCHAR2(2000) NULL,	
   	VER_NBR NUMERIC(18,0) NOT NULL,
   	CONSTRAINT TOUR_T_PK PRIMARY KEY (TOUR_ID)
) 
/
CREATE PUBLIC SYNONYM TOUR_T FOR MIU.TOUR_T
/
GRANT SELECT, UPDATE, INSERT, DELETE ON MIU.TOUR_T TO MIU_PROXY
/




delete from HOME_T
/
delete from TOOL_T
/
delete from HOME_TOOL_T
/


insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'PUBLIC', 'IU Mobile', 0)
/
insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'BL', 'IUB Mobile', 0)
/
insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'IN', 'IUPUI Mobile', 0)
/
insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'CO', 'IUPUC Mobile', 0)
/
insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'EA', 'IUE Mobile', 0)
/
insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'KO', 'IUK Mobile', 0)
/
insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'NW', 'IUN Mobile', 0)
/
insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'SB', 'IUSB Mobile', 0)
/
insert into HOME_T (HOME_ID, ALIAS, TITLE, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'SE', 'IUS Mobile', 0)
/

insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'myclasses', 'My Classes', 'myclasses', 'Class information; Oncourse  forums, resources, and more!', 'images/service-icons/srvc-myclasses.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'classifieds', 'Classifieds', 'https://onestart.iu.edu/ccf2-prd/ClassifiedsMb.do', 'Find furniture, books, an apartment, a job, and more.', 'images/service-icons/srvc-classifieds.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'buses', 'Bus Schedules', 'bus', 'Never miss an IU Bloomington campus bus again.', 'images/service-icons/srvc-bus.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'maps', 'Maps', 'maps', 'Get from here to there. Search for buildings by name.', 'images/service-icons/srvc-maps.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'athletics', 'Athletics', 'athletics', 'Live scores, rosters, news and schedules for your IU teams.', 'images/service-icons/srvc-athletics.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'social', 'Social Media', 'socialmedia', 'Stay up to date with the IU social media.', 'images/service-icons/srvc-social.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'people', 'People', 'people', 'Find contact information for IU students, faculty, and staff.', 'images/service-icons/srvc-people.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'labs', 'Computer Labs', 'computerlabs', 'See which campus STC labs have an open computer.', 'images/service-icons/srvc-stc.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'itnotices', 'IT Notices', 'itnotices', 'Alerts and announcements affecting your technology.', 'images/service-icons/srvc-itnotice.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'news', 'News', 'news', 'The latest buzz on IU''s exciting events and achievements.', 'images/service-icons/srvc-news.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'events', 'Calendar', 'events', 'See what''s happening on campus today or your personal calendar.', 'images/service-icons/srvc-events.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'kb', 'Knowledge Base', 'knowledgebase', 'Find answers to questions about IU information technology.', 'images/service-icons/srvc-kb.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'ask', 'Ask IU', 'askiu', 'Take IU''s popular question & answer service with you on the go.', 'images/service-icons/srvc-askiu.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'ius-dining', 'Dining Services', 'dining', 'Get up to date menus and prices for campus dining services.', 'images/service-icons/srvc-dining.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'emergency', 'Emergency Contacts', 'emergencycontacts', 'Police and medical phone numbers.', 'images/service-icons/srvc-emergency.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'alerts', 'Campus Alerts', 'alerts', 'See a list of active campus alert messages.', 'images/service-icons/srvc-alerts-green.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'feedback', 'Feedback', 'feedback', 'Submit questions and comments about IU Mobile.', 'images/service-icons/srvc-feedback.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'jagathletics', 'Jaguar Athletics', 'http://www.iupuijags.com', 'IUPUI Athletics information.', 'images/service-icons/srvc-jag.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'cancellations', 'Class Cancellations', 'events/viewEvents?categoryId=nw_cancel&campus=ZZ', 'Class Cancellations', 'images/service-icons/srvc-classcancel.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'iusb-library', 'Library', 'static-services/SB-Library/index.html', 'IU South Bend Library information.', 'images/service-icons/srvc-schurzlibrary.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'redhawk', 'RedHawk Shuttle', 'events/viewEvents?categoryId=nw_shuttle&campus=ZZ', 'RedHawk Shuttle', 'images/service-icons/srvc-redhawk.png', 0)
/
insert into TOOL_T (TOOL_ID, ALIAS, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'email', 'Email', 'email', 'Check your campus iMail or uMail account.', 'images/service-icons/srvc-email.png', 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Bus Schedules'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 5, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Jaguar Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Class Cancellations'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'RedHawk Shuttle'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Library'), 5, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Dining Services'), 5, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where ALIAS = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/


insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Backdoor.Group.Name', 'MIU-BACKDOOR-PRD', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Admin.Group.Name', 'MIU-ADMINISTRATORS', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Maps.Foursquare.Client.Id', 'L323STPYRKMYU2RZAQ1RHF4FCGDYWEHZZB4XHDLM5B51JGVQ', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Maps.Foursquare.Client.Secret', 'CZK5HSH4ZZBXMZ5LCZHM0SO320IBXW4REBDNVHM20D1S3FZY', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'NEWS_SAMPLE_COUNT', '2', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Sakai.Url.Base', 'https://oncourse.iu.edu/oauthdirect/', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'SocialMedia.Twitter.TweetCount', '25', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'SocialMedia.Twitter.Feeds', 'IndianaUniv,idsnews,IDS_Opinion,IU_Health,IUBookstore,UITSNEWS,iumedschool,IUTraffic,IUEMC,OurIndiana,IUAA', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.UA', 'admin', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.BL', 'top', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.IN', 'iupui', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.CO', 'iupuc_news', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.EA', 'iue', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.KO', 'iuk', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.NW', 'iun', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.SB', 'iusb', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.SE', 'iuse', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'News.Top.SE', 'iuse', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Alerts.CacheUpdate.Minute', '5', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.01', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/1_Aug_2010.pdf"><h3 class="wrap">Route 1 North - Fee Lane / BHS North</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.02', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/1_Aug_2010.pdf"><h3 class="wrap">Route 1 South - South Walnut / Clear Creek Shopping</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.03', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/2_South_Aug_2011.htm"><h3 class="wrap">Route 2 South - South Rogers / Countryview</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.04', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/2_Aug_2009.pdf"><h3 class="wrap">Route 2 West - 11th Street via Showers Complex</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.05', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/3_Mar_2010.pdf"><h3 class="wrap">Route 3 East - College Mall / Bradford Place</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.06', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/3_Mar_2010.pdf"><h3 class="wrap">Route 3 West – Highland Village / Curry Pike</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.07', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/4_Aug_2009.pdf"><h3 class="wrap">Route 4 South - High Street / Sherwood Oaks</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.08', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/4_Aug_2009.pdf"><h3 class="wrap">Route 4 West - Bloomfield Road / Heatherwood</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.09', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/5_Aug_2009.pdf"><h3 class="wrap">Route 5 - Sare Road</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.10', '<a class="icon-LINK" href="http://www.bloomingtontransit.com/6_Route_Aug_2011.htm"><h3 class="wrap">Route 6 Campus Shuttle and 6 Limited</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.11', '<a class="icon-LINK" href="http://www.bloomingtontransit.com/7_Route_Aug_2009.htm"><h3 class="wrap">Route 7 Henderson/Walnut Express</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.12', '<a class="icon-PDF" href="http://www.bloomingtontransit.com/8_Aug_2009.pdf"><h3 class="wrap">Route 8 - Eastside Local</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.13', '<a class="icon-LINK" href="http://www.bloomingtontransit.com/9_Route_Jan_2010.htm"><h3 class="wrap">Route 9 IU Campus/College Mall Campus Corner</h3></a>', 0)
/
insert into CONFIG_PARAM_MAINT_T (CONFIG_PARAM_ID, NAME, VALUE, VER_NBR) values (SEQ_CONFIG_PARAM_MAINT_T.NEXTVAL, 'Bus.BT.14', '<a class="icon-LINK" href="http://www.bloomingtontransit.com/9_Limited_Route_Aug_2011.htm"><h3 class="wrap">Route 9 Limited</h3></a>', 0)
/










-- Test stuff below this point

insert into NOTIFICATION_T (NOTIFICATION_ID, START_DT, END_DT, TITLE, MESSAGE, NOTIFICATION_TYPE, VER_NBR) values (1, null, null, 'First Notification', 'Hope this works', 1, 0)
/
insert into NOTIFICATION_T (NOTIFICATION_ID, START_DT, END_DT, TITLE, MESSAGE, NOTIFICATION_TYPE, VER_NBR) values (2, TO_DATE('01.08.2011', 'DD.MM.YYYY'), TO_DATE('11.11.2011', 'DD.MM.YYYY'), 'Second Notification', 'With valid dates', 1, 0)
/
insert into NOTIFICATION_T (NOTIFICATION_ID, START_DT, END_DT, TITLE, MESSAGE, NOTIFICATION_TYPE, VER_NBR) values (3, TO_DATE('01.08.2011', 'DD.MM.YYYY'), TO_DATE('02.08.2011', 'DD.MM.YYYY'), 'Third Notification', 'With invalid dates', 1, 0)
/





select name, count(*) from CONFIG_PARAM_MAINT_T group by name having count(*) > 1
/

select * from CONFIG_PARAM_MAINT_T where name = 'News.Top.BL'
/
select * from CONFIG_PARAM_MAINT_T where name = 'Sakai.Url.Base'
/
select * from CONFIG_PARAM_MAINT_T where name = 'Backdoor.Group.Name'
/
select * from CONFIG_PARAM_MAINT_T where name = 'NEWS_SAMPLE_COUNT'
/

delete from CONFIG_PARAM_MAINT_T where CONFIG_PARAM_ID = 1448
/
delete from CONFIG_PARAM_MAINT_T where CONFIG_PARAM_ID = 1444
/
delete from CONFIG_PARAM_MAINT_T where CONFIG_PARAM_ID = 1439
/
delete from CONFIG_PARAM_MAINT_T where CONFIG_PARAM_ID = 1443
/



select name, value  from CONFIG_PARAM_MAINT_T where name = 'Core.ReloadCacheMinutes'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Core.Rss.Server'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Core.CacheRSSXmlRemoteStartupDelayMinutes'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'News.UITSQueryString'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'NEWS_SAMPLE_COUNT'
/

select name, value  from CONFIG_PARAM_MAINT_T where name like 'News.Top.%'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Events.Url.Matcher.Regex'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Events.Url.Matcher.Format'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'RSS.SOCKET.TIMEOUT.SECONDS'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'CAMPUS_STATUS_XML_URL'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'AskIU.Email.Validation.Regex'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'AskIU.Send.EmailAddress'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Food.Url.SE'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Data.XML.Replacement.Tokens'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Events.CCL.Parser.Header'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Events.CCL.Parser.Footer'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Events.Default.Parser.Header'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Events.Default.Parser.Footer'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Feedback.SendEmail.On'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Feedback.SendEmail.EmailAddress'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Core.EmailAddress'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'KB.Url.Document'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'KB.Url.Search'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Maps.Foursquare.Client.Id'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Maps.Foursquare.Client.Secret'
/

select name, value from CONFIG_PARAM_MAINT_T where name = 'PEOPLE_RESULT_LIMIT_ADS'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'Sakai.Url.Base'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'SocialMedia.Twitter.TweetCount'
/

select name, value  from CONFIG_PARAM_MAINT_T where name = 'SocialMedia.Twitter.Feeds'
/



AskIU.Email.Validation.Regex
AskIU.Send.EmailAddress
CAMPUS_STATUS_XML_URL
Core.CacheRSSXmlRemoteStartupDelayMinutes
Core.EmailAddress
Core.ReloadCacheMinutes
Core.Rss.Server
Data.XML.Replacement.Tokens
Events.CCL.Parser.Header
Events.CCL.Parser.Footer
Events.Default.Parser.Header
Events.Default.Parser.Footer
Events.Url.Matcher.Regex
Events.Url.Matcher.Format
Feedback.SendEmail.On
Feedback.SendEmail.EmailAddress
Food.Url.SE
KB.Url.Document
KB.Url.Search
Maps.Foursquare.Client.Id
Maps.Foursquare.Client.Secret
News.UITSQueryString
NEWS_SAMPLE_COUNT
News.Top.%
PEOPLE_RESULT_LIMIT_ADS
RSS.SOCKET.TIMEOUT.SECONDS
Sakai.Url.Base
SocialMedia.Twitter.TweetCount
SocialMedia.Twitter.Feeds



RSS.SOCKET.TIMEOUT.SECONDS
SocialMedia.Twitter.Feeds

