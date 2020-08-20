/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     18/08/2020 23:12:00                          */
/*==============================================================*/


drop table if exists ASIGNAR_PERFIL;

drop table if exists BITACORA;

drop table if exists CONFIG_CLAVE;

drop table if exists OPCION;

drop table if exists OPCION_PERFIL;

drop table if exists PERFIL;

drop table if exists PERSONA;

drop table if exists USUARIO_ACCESO;

/*==============================================================*/
/* Table: ASIGNAR_PERFIL                                        */
/*==============================================================*/
create table ASIGNAR_PERFIL
(
   ID_ASIGNAR_PERFIL    int AUTO_INCREMENT,
   ID_USUARIO_ACCESO    int,
   ID_PERFIL            int,
   ASGINAR_PERFIL_FECHA datetime,
   primary key (ID_ASIGNAR_PERFIL)
);

/*==============================================================*/
/* Table: BITACORA                                              */
/*==============================================================*/
create table BITACORA
(
   ID_BITACORA          int AUTO_INCREMENT,
   ID_USUARIO_ACCESO    int,
   BIT_FECHA            datetime,
   BIT_OPERACION        varchar(128),
   BIT_RECURSO_AFECTADO varchar(128),
   BIT_DESCRIPCION      varchar(512),
   BIT_IP               varchar(64),
   BIT_SO               varchar(128),
   BIT_NAVEGADOR        varchar(128),
   primary key (ID_BITACORA)
);

/*==============================================================*/
/* Table: CONFIG_CLAVE                                          */
/*==============================================================*/
create table CONFIG_CLAVE
(
   ID_CONFIG_CLAVE      int AUTO_INCREMENT,
   CLAVE_CHAR_MAX       int,
   CLAVE_CHAR_MIN       int,
   CLAVE_CHAR_ALFANUMERICO bool,
   CLAVE_CHAR_MAYUS     bool,
   CLAVE_CHAR_MINUS     bool,
   CLAVE_CHAR_ESPECIAL  bool,
   CLAVE_STR_ESPECIAL   varchar(32),
   CLAVE_FECHA          datetime,
   CLAVE_FECHA_MOD      datetime,
   CLAVE_CAMBIOS_DIA    int,
   CLAVE_INTENTOS_FALLIDOS int,
   CLAVE_ULTIMO         varchar(512),
   primary key (ID_CONFIG_CLAVE)
);

/*==============================================================*/
/* Table: OPCION                                                */
/*==============================================================*/
create table OPCION
(
   ID_OPCION            int AUTO_INCREMENT,
   ID_OPCION_PADRE      int,
   REC_NOMBRE           varchar(128),
   REC_DESCRIPCION      varchar(256),
   REC_PAGINA           varchar(128),
   REC_ESTATUS          char(3),
   REC_INDEX            int,
   primary key (ID_OPCION)
);

/*==============================================================*/
/* Table: OPCION_PERFIL                                         */
/*==============================================================*/
create table OPCION_PERFIL
(
   ID_PERMISO           int AUTO_INCREMENT,
   ID_PERFIL            int,
   ID_OPCION            int,
   OPC_PER_ESTATUS      char(3),
   primary key (ID_PERMISO)
);

/*==============================================================*/
/* Table: PERFIL                                                */
/*==============================================================*/
create table PERFIL
(
   ID_PERFIL            int AUTO_INCREMENT,
   PERFIL_NOMBRE        varchar(128),
   PERFIL_DESCRIPCION   varchar(256),
   PERFIL_ESTATUS       char(3),
   PERFIL_ESTATUS2      char(3),
   primary key (ID_PERFIL)
);

/*==============================================================*/
/* Table: PERSONA                                               */
/*==============================================================*/
create table PERSONA
(
   ID_USUARIO           int AUTO_INCREMENT,
   USR_NOMBRES          varchar(128),
   USR_APELLIDOS        varchar(128),
   USR_DIRECCION        varchar(256),
   USR_IDENTIFICACION   char(10),
   USR_FECHA_NACIMIENTO datetime,
   USR_TELEFONO         varchar(10),
   USR_CORREO           varchar(128),
   USR_FOTO             varchar(256),
   USR_FECHA_CREACION   datetime,
   USR_FECHA_MOD        datetime,
   primary key (ID_USUARIO)
);

/*==============================================================*/
/* Table: USUARIO_ACCESO                                        */
/*==============================================================*/
create table USUARIO_ACCESO
(
   ID_USUARIO_ACCESO    int AUTO_INCREMENT,
   ID_USUARIO           int,
   ID_CONFIG_CLAVE      int,
   USR_ACCESO_NOMBRE    varchar(128),
   USR_ACCESO_CLAVE     varchar(512),
   USR_ACCESO_INTENTOSFALLIDOS int,
   USR_ACCESO_ESTATUS   char(3),
   USR_ACCESO_FECHA_MOD datetime,
   primary key (ID_USUARIO_ACCESO)
);

alter table ASIGNAR_PERFIL add constraint FK_ASIGNAR_PERFIL foreign key (ID_PERFIL)
      references PERFIL (ID_PERFIL) on delete restrict on update restrict;

alter table ASIGNAR_PERFIL add constraint FK_USUARIO_PERFIL foreign key (ID_USUARIO_ACCESO)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

alter table BITACORA add constraint FK_BITACORA_USUARIO foreign key (ID_USUARIO_ACCESO)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

alter table OPCION add constraint FK_RECURSO_PADRE foreign key (ID_OPCION_PADRE)
      references OPCION (ID_OPCION) on delete restrict on update restrict;

alter table OPCION_PERFIL add constraint FK_PERFIL_PERMISOS foreign key (ID_PERFIL)
      references PERFIL (ID_PERFIL) on delete restrict on update restrict;

alter table OPCION_PERFIL add constraint FK_RECURSO_PERMISOS foreign key (ID_OPCION)
      references OPCION (ID_OPCION) on delete restrict on update restrict;

alter table USUARIO_ACCESO add constraint FK_USUARIO_ACCESO foreign key (ID_USUARIO)
      references PERSONA (ID_USUARIO) on delete restrict on update restrict;

alter table USUARIO_ACCESO add constraint FK_USUARIO_CLAVE foreign key (ID_CONFIG_CLAVE)
      references CONFIG_CLAVE (ID_CONFIG_CLAVE) on delete restrict on update restrict;

