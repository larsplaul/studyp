delete from STUDYPOINT where id > 1;
delete from TASK where id >1;
delete from SEMESTER_PERIOD where id >1;
delete from SP_CLASS_STUDYPOINT_USER where users_id > 1;
delete from STUDYPOINTUSER_ROLES where roleName != 'xxx';
delete from USER_ROLE where roleName != "xx";
delete from STUDYPOINT_USER where id > -1;
delete from SP_CLASS where id != "xxx";

insert into SP_CLASS(id,nameShort,semesterDescription,maxPointForSemester,requiredPoints) values ('XXX-l15dat3q14f','DAT 3sem hold q','Beskrivelse',240,70);

insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (1,'lam','Lars','Mortensen','lam@cphbusiness.dk','12345678','larsx1234','larsx12234');
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) 
values (2,'peter','Peter','Test','peter@test.dk','12345678','peter','peter');

insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) 
values (3,'Kurt','Kurt','Test','Kurt@test.dk','12345678','kurt','kurt');

insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) 
values (4,'test','test','Test','Kurt@test.dk','12345678','test','test');

insert into USER_ROLE values('Admin');
insert into USER_ROLE values('User');

insert into STUDYPOINTUSER_ROLES(roleName, userName) values('Admin','lam');
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('Admin','test');
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','Kurt');
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','Peter');

insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('XXX-l15dat3q14f',2);
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('XXX-l15dat3q14f',3);


insert into SEMESTER_PERIOD(id,periodname,perioddescription,inclass_id) values (100,'Period-1','Period-1 info','XXX-l15dat3q14f');
insert into SEMESTER_PERIOD(id,periodname,perioddescription,inclass_id) values (102,'Period-2','Period-2 info','XXX-l15dat3q14f');
insert into SEMESTER_PERIOD(id,periodname,perioddescription,inclass_id) values (103,'Period-3','Period-3 info','XXX-l15dat3q14f');


insert into TASK(id,maxscore,name,semesterperiod_id) values (200,1,'Day-1',100);
insert into TASK(id,maxscore,name,semesterperiod_id) values (201,1,'Day-2',100);
insert into TASK(id,maxscore,name,semesterperiod_id) values (202,1,'Day-3',100);
insert into TASK(id,maxscore,name,semesterperiod_id) values (203,1,'Day-4',100);
insert into TASK(id,maxscore,name,semesterperiod_id) values (204,5,'SPE-1',100);

insert into TASK(id,maxscore,name,semesterperiod_id) values (205,1,'Day-1',102);
insert into TASK(id,maxscore,name,semesterperiod_id) values (206,1,'Day-2',102);
insert into TASK(id,maxscore,name,semesterperiod_id) values (207,1,'Day-3',102);
insert into TASK(id,maxscore,name,semesterperiod_id) values (208,1,'Day-4',102);
insert into TASK(id,maxscore,name,semesterperiod_id) values (209,5,'SPE-3',102);

insert into TASK(id,maxscore,name,semesterperiod_id) values (210,1,'Day-1',103);
insert into TASK(id,maxscore,name,semesterperiod_id) values (211,1,'Day-2',103);
insert into TASK(id,maxscore,name,semesterperiod_id) values (212,1,'Day-3',103);
insert into TASK(id,maxscore,name,semesterperiod_id) values (213,1,'Day-4',103);
insert into TASK(id,maxscore,name,semesterperiod_id) values (214,5,'SPE-5',103);

--Studypoints for User 2
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(300,0,2,200); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(301,0,2,201); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(302,0,2,202); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(303,0,2,203); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(304,0,2,204); 

insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(305,0,2,205); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(306,0,2,206); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(307,0,2,207); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(308,0,2,208); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(309,0,2,209); 

insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(310,0,2,210); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(311,0,2,211); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(312,0,2,212); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(313,0,2,213); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(314,0,2,214); 



--Studypoints for User 3
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(315,0,3,200); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(316,0,3,201); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(317,0,3,202); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(318,0,3,203); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(319,0,3,204); 

insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(320,0,3,205); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(321,0,3,206); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(322,0,3,207); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(323,0,3,208); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(324,0,3,209); 

insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(325,0,3,210); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(326,0,3,211); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(327,0,3,212); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(328,0,3,213); 
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id) values(329,0,3,214); 


insert into SP_CLASS(id,nameShort,semesterDescription,maxPointForSemester,requiredPoints) values ('Semester3-xx123','sem3-COS','fjlsafjlska', 240,   70);

insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-1','Annette','Lund','a6@b.dk','424324','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-1');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-2','Andreas','Hansen','a@cc.dk','42342','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-2');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-3','Mikkel','Hansen','dd@dd.dk','434324','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-3');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-4','Kasper','Mortensen','q@q.dk','12345678','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-4');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-5','Jan','Jensen','c@s.dk','432142142','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-5');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-6','Ole','Hansen','cph-eagg@ff.dk','534534','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-6');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-7','Joachim','Mortensenjo@mo.dk','64565464','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-7');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-8','Hans','Carlsen','hc@sss.dk','6645654','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-8');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-9','Kasper','Jennsen','cj@aa.dk','53534','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-9');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-10','Christian','Hansen','ch@aa.dk','54345','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-10');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-11','Martin','Olsen','mo@gg.dk','5345345','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-11');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-12','Martin','Jensen','mj@fsdf.dk','4543','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-12');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-13','Jens','Pedersen','jp@ttt.dk','543543','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-13');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-14','Michael','Melsgård','mm@mm.dk','5345','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-14');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-15','Morten','Overgård','maa@fdf.dk','645646','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-15');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'User-16','Nikolai','Østergård','no@no.dk','5345435','xxx','xxx');
SET @user = LAST_INSERT_ID();
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','User-16');
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('Semester3-xx123',@user);
commit;




insert into SEMESTER_PERIOD(id,periodname,perioddescription,inclass_id) values (NULL,'Period-1','Net, threads and Sockets','Semester3-xx123');
SET @PERIODID = LAST_INSERT_ID();
insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','02-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','03-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','04-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','05-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','06-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'5','SPE-1',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','09-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','10-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','11-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','12-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','13-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'5','SPE-2',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'1','16-02',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'5','CA1CON',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'5','CA1DT',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'4','CA1DOC',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

insert into TASK(id,maxscore,name,semesterperiod_id) values (NULL,'5','CAHF',@PERIODID);
SET @TASKID = LAST_INSERT_ID();
insert into STUDYPOINT(id,score,STUDYPOINTUSER_ID,task_id)
select NULL,0,STUDYPOINT_USER.ID,@TASKID from STUDYPOINT_USER
INNER JOIN SP_CLASS_STUDYPOINT_USER
ON SP_CLASS_STUDYPOINT_USER.users_ID = STUDYPOINT_USER.ID
where SP_CLASS_STUDYPOINT_USER.sP_Classes_ID = 'Semester3-xx123';
commit;

