/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     11/08/2020 22:29:54                          */
/*==============================================================*/


drop table if exists AERONAVE;

drop table if exists CLIENTE;

drop table if exists DESCUENTO;

drop table if exists DETALLE_VUELO;

drop table if exists EMPLEADO;

drop table if exists FORMACION_PARACAIDISTA;

drop table if exists PARACAIDISTA;

drop table if exists PEDIDO;

drop table if exists PERFIL;

drop table if exists PERMISO;

drop table if exists PILOTO;

drop table if exists RECURSO;

drop table if exists RESPONSABLE;

drop table if exists SALTO;

drop table if exists SERVICIO_ADICIONAL;

drop table if exists USUARIO;

drop table if exists VUELO;

/*==============================================================*/
/* Table: AERONAVE                                              */
/*==============================================================*/
create table AERONAVE
(
   ID_AERONAVE          int AUTO_INCREMENT,
   NOMBREAER            varchar(100),
   MODELOAER            varchar(14),
   CAPACIDADTOTALAER    int,
   primary key (ID_AERONAVE)
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE
(
   ID_CLIENTE           int AUTO_INCREMENT,
   NOMBRESCLI           varchar(250),
   APELLIDOSCLI         varchar(250),
   CEDULACLI            varchar(10),
   FECHANACIMIENTOCLI   timestamp,
   CORREOCLI            varchar(100),
   TELFCLI              varchar(10),
   primary key (ID_CLIENTE)
);

/*==============================================================*/
/* Table: DESCUENTO                                             */
/*==============================================================*/
create table DESCUENTO
(
   ID_DESCUENTO         int AUTO_INCREMENT,
   NOMBREDES            varchar(100),
   DESCRIPCIONDES       varchar(250),
   VALORDES             numeric(6,2),
   primary key (ID_DESCUENTO)
);

/*==============================================================*/
/* Table: DETALLE_VUELO                                         */
/*==============================================================*/
create table DETALLE_VUELO
(
   ID_DETVUELO          int AUTO_INCREMENT,
   PEDIDO_ID            int,
   VUELO_ID             int,
   COSTOTOTAL           numeric(6,2),
   primary key (ID_DETVUELO)
);

/*==============================================================*/
/* Table: EMPLEADO                                              */
/*==============================================================*/
create table EMPLEADO
(
   ID_EMPLEADO          int AUTO_INCREMENT,
   NOMBRESEMP           varchar(250),
   APELLIDOSEMP         varchar(250),
   CEDULAEMP            varchar(10),
   TELFEMP              varchar(10),
   CORREOEMP            varchar(100),
   CARGOEMP             varchar(50),
   FECHANACIMIENTO      timestamp,
   primary key (ID_EMPLEADO)
);

/*==============================================================*/
/* Table: FORMACION_PARACAIDISTA                                */
/*==============================================================*/
create table FORMACION_PARACAIDISTA
(
   ID_FORPAR            int AUTO_INCREMENT,
   NOMBRECURSO          varchar(100),
   FECHAINICIOCURSO     timestamp,
   FECHAFINCURSO        timestamp,
   LUGARCURSO           varchar(250),
   primary key (ID_FORPAR)
);

/*==============================================================*/
/* Table: PARACAIDISTA                                          */
/*==============================================================*/
create table PARACAIDISTA
(
   ID_PARACAIDISTA      int AUTO_INCREMENT,
   FORPAR_ID            int,
   NOMBRESPAR           varchar(250),
   APELLIDOSPAR         varchar(250),
   TIPOPAR              varchar(32),
   ESCUELAFORMACION     varchar(100),
   NUMEROSALTOS         int,
   FECHAULTIMOSALTO     timestamp,
   LICENCIA             bool,
   FECHACADUCIDADLICENCIA timestamp,
   primary key (ID_PARACAIDISTA)
);

/*==============================================================*/
/* Table: PEDIDO                                                */
/*==============================================================*/
create table PEDIDO
(
   ID_PEDIDO            int AUTO_INCREMENT,
   CLIENTE_ID           int,
   SALTO_ID             int,
   PARACAIDISTA_ID      int,
   DESCUENTO_ID         int,
   SERVICIO_ID          int,
   FECHAPED             timestamp,
   COSTOSALTO           numeric(6,2),
   COSTOTOTALPED        numeric(6,2),
   primary key (ID_PEDIDO)
);

/*==============================================================*/
/* Table: PERFIL                                                */
/*==============================================================*/
create table PERFIL
(
   ID_PERFIL            int AUTO_INCREMENT,
   NOMBREPER            varchar(64),
   ESTADOPER            char(3),
   ESTADOPER2           char(3),
   FECHACREACIONPER     timestamp,
   FECHAMODPER          timestamp,
   primary key (ID_PERFIL)
);

/*==============================================================*/
/* Table: PERMISO                                               */
/*==============================================================*/
create table PERMISO
(
   ID_PERMISO           int AUTO_INCREMENT,
   RECURSO_ID           int,
   PERFIL_ID            int,
   NOMBREPER            varchar(50),
   ESTADOPER            char(3),
   primary key (ID_PERMISO)
);

/*==============================================================*/
/* Table: PILOTO                                                */
/*==============================================================*/
create table PILOTO
(
   ID_PILOTO            int AUTO_INCREMENT,
   NOMBRESPIL           varchar(200),
   CEDULAPIL            varchar(10),
   TELFPIL              varchar(10),
   primary key (ID_PILOTO)
);

/*==============================================================*/
/* Table: RECURSO                                               */
/*==============================================================*/
create table RECURSO
(
   ID_RECURSO           int AUTO_INCREMENT,
   NOMBREREC            varchar(128),
   PAGINAREC            varchar(250),
   ICONOREC             varchar(32),
   INDICEREC            int,
   ESTADOREC            char(3),
   primary key (ID_RECURSO)
);

/*==============================================================*/
/* Table: RESPONSABLE                                           */
/*==============================================================*/
create table RESPONSABLE
(
   ID_RESPONSABLE       int AUTO_INCREMENT,
   NOMBRESRES           varchar(100),
   CEDULARES            varchar(10),
   EMAILRES             varchar(100),
   TELFRES              varchar(10),
   primary key (ID_RESPONSABLE)
);

/*==============================================================*/
/* Table: SALTO                                                 */
/*==============================================================*/
create table SALTO
(
   ID_SALTO             int AUTO_INCREMENT,
   TIPOSALTO            varchar(50),
   primary key (ID_SALTO)
);

/*==============================================================*/
/* Table: SERVICIO_ADICIONAL                                    */
/*==============================================================*/
create table SERVICIO_ADICIONAL
(
   ID_SERVICIO          int AUTO_INCREMENT,
   RESPONSABLE_ID       int,
   NOMBRESER            varchar(100),
   DESCRIPCIONSER       varchar(250),
   COSTOSER             numeric(6,2),
   primary key (ID_SERVICIO)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID_USUARIO           int AUTO_INCREMENT,
   EMPLEADO_ID          int,
   PERFIL_ID            int,
   NOMBREUSUARIO        varchar(100),
   CLAVEUSU             varchar(250),
   primary key (ID_USUARIO)
);

/*==============================================================*/
/* Table: VUELO                                                 */
/*==============================================================*/
create table VUELO
(
   ID_VUELO             int AUTO_INCREMENT,
   AERONAVE_ID          int,
   PILOTO_ID            int,
   HORASALIDA           timestamp,
   HORALLEGADA          timestamp,
   CAPACIDADPERSONAS    int,
   primary key (ID_VUELO)
);

alter table DETALLE_VUELO add constraint FK_LLEVA foreign key (VUELO_ID)
      references VUELO (ID_VUELO) on delete restrict on update restrict;

alter table DETALLE_VUELO add constraint FK_REGISTRA foreign key (PEDIDO_ID)
      references PEDIDO (ID_PEDIDO) on delete restrict on update restrict;

alter table PARACAIDISTA add constraint FK_CURSO foreign key (FORPAR_ID)
      references FORMACION_PARACAIDISTA (ID_FORPAR) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_PUEDE_AYUDAR foreign key (PARACAIDISTA_ID)
      references PARACAIDISTA (ID_PARACAIDISTA) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_PUEDE_TENER foreign key (DESCUENTO_ID)
      references DESCUENTO (ID_DESCUENTO) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_REALIZA foreign key (CLIENTE_ID)
      references CLIENTE (ID_CLIENTE) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_SOLICITA_UN foreign key (SALTO_ID)
      references SALTO (ID_SALTO) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_TIENE foreign key (SERVICIO_ID)
      references SERVICIO_ADICIONAL (ID_SERVICIO) on delete restrict on update restrict;

alter table PERMISO add constraint FK_AUTORIZA foreign key (PERFIL_ID)
      references PERFIL (ID_PERFIL) on delete restrict on update restrict;

alter table PERMISO add constraint FK_PERMISO_RECURSO foreign key (RECURSO_ID)
      references RECURSO (ID_RECURSO) on delete restrict on update restrict;

alter table SERVICIO_ADICIONAL add constraint FK_SE_ENCARGA foreign key (RESPONSABLE_ID)
      references RESPONSABLE (ID_RESPONSABLE) on delete restrict on update restrict;

alter table USUARIO add constraint FK_NECESARIAMENTE foreign key (PERFIL_ID)
      references PERFIL (ID_PERFIL) on delete restrict on update restrict;

alter table USUARIO add constraint FK_PODRA_TENER foreign key (EMPLEADO_ID)
      references EMPLEADO (ID_EMPLEADO) on delete restrict on update restrict;

alter table VUELO add constraint FK_CONDUCE foreign key (PILOTO_ID)
      references PILOTO (ID_PILOTO) on delete restrict on update restrict;

alter table VUELO add constraint FK_REALIZA_UN foreign key (AERONAVE_ID)
      references AERONAVE (ID_AERONAVE) on delete restrict on update restrict;

