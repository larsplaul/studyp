
delete from STUDYPOINT where id > 1;
delete from TASK where id >1;
delete from SEMESTER_PERIOD where id >1;
delete from SP_CLASS_STUDYPOINT_USER where users_id > 1;
delete from STUDYPOINTUSER_ROLES where roleName != 'xxx';
delete from USER_ROLE where roleName != "xx";
delete from STUDYPOINT_USER where id > -1;
delete from SP_CLASS where id != "xxx";


insert into SP_CLASS(id,nameShort,semesterDescription,maxPointForSemester,requiredPoints) values ('CPH-l15dat3q14f','DAT 3sem hold q','Beskrivelse',240,70);

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

insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('CPH-l15dat3q14f',2);
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('CPH-l15dat3q14f',3);


insert into SEMESTER_PERIOD(id,periodname,perioddescription,inclass_id) values (100,'Period-1','Period-1 info','CPH-l15dat3q14f');
insert into SEMESTER_PERIOD(id,periodname,perioddescription,inclass_id) values (102,'Period-2','Period-2 info','CPH-l15dat3q14f');
insert into SEMESTER_PERIOD(id,periodname,perioddescription,inclass_id) values (103,'Period-3','Period-3 info','CPH-l15dat3q14f');


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
