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


delete from HOME_T
/
delete from TOOL_T
/
delete from HOME_TOOL_T
/


insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'PUBLIC', 0)
/
insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'BL', 0)
/
insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'IN', 0)
/
insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'CO', 0)
/
insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'EA', 0)
/
insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'KO', 0)
/
insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'NW', 0)
/
insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'SB', 0)
/
insert into HOME_T (HOME_ID, HOME_NM, VER_NBR) values (SEQ_HOME_T.NEXTVAL, 'SE', 0)
/

insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'My Classes', 'myclasses', 'Class information; Oncourse  forums, resources, and more!', 'images/service-icons/srvc-myclasses.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Classifieds', 'https://onestart.iu.edu/ccf2-prd/ClassifiedsMb.do', 'Find furniture, books, an apartment, a job, and more.', 'images/service-icons/srvc-classifieds.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Bus Schedules', 'http://iub.doublemap.com/map/mobile', 'Never miss an IU Bloomington campus bus again.', 'images/service-icons/srvc-bus.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Maps', 'maps', 'Get from here to there. Search for buildings by name.', 'images/service-icons/srvc-maps.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Athletics', 'athletics', 'Live scores, rosters, news and schedules for your IU teams.', 'images/service-icons/srvc-athletics.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Social Media', 'socialmedia', 'Stay up to date with the IU social media.', 'images/service-icons/srvc-social.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'People', 'people', 'Find contact information for IU students, faculty, and staff.', 'images/service-icons/srvc-people.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Computer Labs', 'computerlabs', 'See which campus STC labs have an open computer.', 'images/service-icons/srvc-stc.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'IT Notices', 'itnotices', 'Alerts and announcements affecting your technology.', 'images/service-icons/srvc-itnotice.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'News', 'news', 'The latest buzz on IU''s exciting events and achievements.', 'images/service-icons/srvc-news.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Calendar', 'events', 'See what''s happening on campus today or your personal calendar.', 'images/service-icons/srvc-events.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Knowledge Base', 'knowledgebase', 'Find answers to questions about IU information technology.', 'images/service-icons/srvc-kb.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Ask IU', 'askiu', 'Take IU''s popular question & answer service with you on the go.', 'images/service-icons/srvc-askiu.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Dining Services', 'dining', 'Get up to date menus and prices for campus dining services.', 'images/service-icons/srvc-dining.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Emergency Contacts', 'emergencycontacts', 'Police and medical phone numbers.', 'images/service-icons/srvc-emergency.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Campus Alerts', 'alerts', 'See a list of active campus alert messages.', 'images/service-icons/srvc-alerts-green.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Feedback', 'feedback', 'Submit questions and comments about IU Mobile.', 'images/service-icons/srvc-feedback.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Jaguar Athletics', 'http://www.iupuijags.com', 'IUPUI Athletics information.', 'images/service-icons/srvc-jag.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Class Cancellations', 'events/viewEvents?categoryId=nw_cancel&campus=ZZ', 'Class Cancellations', 'images/service-icons/srvc-classcancel.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Library', 'static-services/SB-Library/index.html', 'IU South Bend Library information.', 'images/service-icons/srvc-schurzlibrary.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'RedHawk Shuttle', 'events/viewEvents?categoryId=nw_shuttle&campus=ZZ', 'RedHawk Shuttle', 'images/service-icons/srvc-redhawk.png', 0)
/
insert into TOOL_T (TOOL_ID, TITLE, URL, DESCRIPTION, ICON_URL, VER_NBR) values (SEQ_TOOL_T.NEXTVAL, 'Email', 'email', 'Check your campus iMail or uMail account.', 'images/service-icons/srvc-email.png', 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'PUBLIC'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Bus Schedules'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 5, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'BL'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Jaguar Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'IN'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'CO'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'EA'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'KO'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Class Cancellations'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'RedHawk Shuttle'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'NW'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Library'), 5, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SB'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/

insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'My Classes'), 1, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Email'), 2, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Social Media'), 3, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Classifieds'), 4, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Dining Services'), 5, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Maps'), 6, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Athletics'), 7, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Computer Labs'), 8, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'People'), 9, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'News'), 10, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Calendar'), 11, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'IT Notices'), 12, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Knowledge Base'), 13, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Ask IU'), 14, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Emergency Contacts'), 15, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Campus Alerts'), 16, 0)
/
insert into HOME_TOOL_T (HOME_TOOL_ID, HOME_ID, TOOL_ID, ORDR, VER_NBR) values (SEQ_HOME_TOOL_T.NEXTVAL, (select HOME_ID from HOME_T where HOME_NM = 'SE'), (select TOOL_ID from TOOL_T where TITLE = 'Feedback'), 17, 0)
/



