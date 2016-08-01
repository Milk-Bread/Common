--创建角色
insert into role (`RoleSeq`, `RoleName`, `CreateTime`) values (1,'Admin',now());
--初始化用户 密码 a88888888
insert into tlcuser (userId,userName,password,sex,age,addr,mobilePhone,phone,idType,idNo,RoleSeq,channelId,CreateTime)
values('chepeiqing','车培清','m87Ez6gqjrCXl97T2nPBrw==','M',25,'湖南岳阳','15150667366','0730-7165261','00','430621199111258112',1,'PMBS',now());



--创建菜单
--一级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('SystemSettings','系统设置','00000000',1,'',now());
--二级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('RoleManager','角色管理','SystemSettings',1,'RoleManager',now());
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('UserManager','用户管理','SystemSettings',2,'UserManager',now());
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('PasswordManager','密码管理','SystemSettings',3,'PasswordManager',now());

--授权 --Admin
insert into usermenurelate (`RoleSeq`, `MenuId`) values(1,'SystemSettings');
insert into usermenurelate (`RoleSeq`, `MenuId`) values(1,'RoleManager');
insert into usermenurelate (`RoleSeq`, `MenuId`) values(1,'UserManager');
insert into usermenurelate (`RoleSeq`, `MenuId`) values(1,'PasswordManager');

--一级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('MissionCenter','任务中心','00000000',2,'',now());
--二级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('CheckQuery','待复合查询','MissionCenter',1,'CheckQuery',now());
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('CheckResultQuery','复合结果查询','MissionCenter',2,'CheckResultQuery',now());
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('CheckRecordQuery','复合记录查询','MissionCenter',3,'CheckRecordQuery',now());

--授权 --Admin
insert into usermenurelate (`RoleSeq`, `MenuId`) values(1,'MissionCenter');
insert into usermenurelate (`RoleSeq`, `MenuId`) values(1,'CheckQuery');
insert into usermenurelate (`RoleSeq`, `MenuId`) values(1,'CheckResultQuery');
insert into usermenurelate (`RoleSeq`, `MenuId`) values(1,'CheckRecordQuery');
