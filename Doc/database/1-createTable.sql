/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     16/8/1 下午3:23:56                             */
/*==============================================================*/
CREATE DATABASE `tlc`  
CHARACTER SET 'utf8'  
COLLATE 'utf8_general_ci'; 


drop table if exists UserMenuRelate;
drop table if exists Menu;

drop table if exists TlcUser;
drop table if exists Role;
drop table if exists AccessToken;
drop table if exists QrcodeImg;

/*==============================================================*/
/* Table: AccessToken                                           */
/*==============================================================*/
create table AccessToken
(
	tokenSeq integer auto_increment,
	accessToken varchar(512) not null,
	invalidTime varchar(5) not null,
	createTime timestamp,
	updateTime timestamp,
	primary key (tokenSeq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table AccessToken comment 'accesstoken 表';

/*==============================================================*/
/* Table: AccessToken                                           */
/*==============================================================*/
create table QrcodeImg(
   qrcodeSeq integer auto_increment,
   appId varchar(20) not null,
   actionName varchar(20) not null,
   sceneId varchar(50) not null,
   ticket varchar(100) not null,
   url varchar(100) not null,
   qrcodeName varchar(50) not null,
   preservation varchar(50) not null,
   state char(1) not null,
   createTime timestamp,
   updateTime timestamp,
   primary key (qrcodeSeq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table QrcodeImg comment '二维码生成记录表';

/*==============================================================*/
/* Table: Menu                                                  */
/*==============================================================*/
create table Menu
(
   MenuId               varchar(20) not null,
   MenuName             varchar(50) not null,
   ParentId             varchar(20) not null,
   OrderId              integer,
   TransId              varchar(20),
   CreateTime           TIMESTAMP not null,
   primary key (MenuId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table Menu comment '菜单表';

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   RoleSeq              INTEGER not null auto_increment,
   RoleName             VARCHAR(30) not null,
   CreateTime           TIMESTAMP not null,
   primary key (RoleSeq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
auto_increment = 600;

alter table Role comment '角色表';

/*==============================================================*/
/* Table: TlcUser                                               */
/*==============================================================*/
create table TlcUser
(
   UserSeq              INTEGER not null auto_increment,
   RoleSeq              INTEGER,
   UserId               VARCHAR(18),
   UserName             VARCHAR(18),
   Password             VARCHAR(50),
   Sex                  char(1),
   Age                  INTEGER(3),
   IdType               char(2),
   IdNo                 char(18),
   MobilePhone          char(11),
   Phone                VARCHAR(24),
   ChannelId            char(4),
   CreateTime           timestamp,
   Addr                 VARCHAR(500),
   primary key (UserSeq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
auto_increment = 8000000;

alter table TlcUser comment '用户表';

/*==============================================================*/
/* Table: UserMenuRelate                                        */
/*==============================================================*/
create table UserMenuRelate
(
   RoleSeq              INTEGER not null,
   MenuId               varchar(20) not null,
   primary key (RoleSeq, MenuId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;;

alter table UserMenuRelate comment '角色菜单关联表';

alter table TlcUser add constraint FK_Reference_1 foreign key (RoleSeq)
      references Role (RoleSeq) on delete restrict on update restrict;

alter table UserMenuRelate add constraint FK_Reference_2 foreign key (RoleSeq)
      references Role (RoleSeq) on delete restrict on update restrict;

alter table UserMenuRelate add constraint FK_Reference_3 foreign key (MenuId)
      references Menu (MenuId) on delete restrict on update restrict;



