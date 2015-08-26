

insert into SP_CLASS(id,nameShort,semesterDescription,maxPointForSemester,requiredPoints) values ('CPH-l15dat3q14f','DAT 3sem hold q','Beskrivelse',240,70);

insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'lam','Lars','Mortensen','lam@cphbusiness.dk','12345678','larsx1234','larsx12234');
SET @user1 = LAST_INSERT_ID();
commit;
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'peter','Peter','Test','peter@test.dk','12345678','peter','peter');
SET @user2 = LAST_INSERT_ID();
commit
insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'Kurt','Kurt','Test','Kurt@test.dk','12345678','kurt','kurt');
SET @user3 = LAST_INSERT_ID();

insert into STUDYPOINT_USER(id,username,firstname,lastname,email,phone,password,passwordinitial) values (NULL,'test','test','Test','Kurt@test.dk','12345678','test','test');
SET @user4 = LAST_INSERT_ID();

insert into USER_ROLE values('Admin');
insert into USER_ROLE values('User');

insert into STUDYPOINTUSER_ROLES(roleName, userName) values('Admin','lam');
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('Admin','test');
insert into STUDYPOINTUSER_ROLES(roleName, userName) values('User','Kurt');

insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('CPH-l15dat3q14f',@user1 );
insert into SP_CLASS_STUDYPOINT_USER(sP_Classes_ID,users_ID) values('CPH-l15dat3q14f',@user2 );
commit;
