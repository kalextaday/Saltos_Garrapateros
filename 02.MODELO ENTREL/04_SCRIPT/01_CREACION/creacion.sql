/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     03/08/2020 16:42:38                          */
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
   IDAERONAVE           int not null,
   NOMBREAER            varchar(100),
   MODELOAER            varchar(14),
   CAPACIDADTOTALAER    int,
   primary key (IDAERONAVE)
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE
(
   IDCLIENTE            int AUTO_INCREMENT,
   NOMBRESCLI           varchar(250),
   APELLIDOSCLI         varchar(250),
   CEDULACLI            varchar(10),
   FECHANACIMIENTOCLI   timestamp,
   CORREOCLI            varchar(100),
   TELFCLI              varchar(10),
   primary key (IDCLIENTE)
);

/*==============================================================*/
/* Table: DESCUENTO                                             */
/*==============================================================*/
create table DESCUENTO
(
   IDDESCUENTO          int AUTO_INCREMENT,
   NOMBREDES            varchar(100),
   DESCRIPCIONDES       varchar(250),
   VALORDES             numeric(6,2),
   primary key (IDDESCUENTO)
);

/*==============================================================*/
/* Table: DETALLE_VUELO                                         */
/*==============================================================*/
create table DETALLE_VUELO
(
   IDDETVUELO           int AUTO_INCREMENT,
   PEDIDOID             int,
   VUELOID              int,
   COSTOTOTAL           numeric(6,2),
   primary key (IDDETVUELO)
);

/*==============================================================*/
/* Table: EMPLEADO                                              */
/*==============================================================*/
create table EMPLEADO
(
   IDEMPLEADO           int AUTO_INCREMENT,
   NOMBRESEMP           varchar(250),
   APELLIDOSEMP         varchar(250),
   CEDULAEMP            varchar(10),
   TELFEMP              varchar(10),
   CORREOEMP            varchar(100),
   CARGOEMP             varchar(50),
   FECHANACIMIENTO      timestamp,
   primary key (IDEMPLEADO)
);

/*==============================================================*/
/* Table: FORMACION_PARACAIDISTA                                */
/*==============================================================*/
create table FORMACION_PARACAIDISTA
(
   IDFORPAR             int AUTO_INCREMENT,
   NOMBRECURSO          varchar(100),
   FECHAINICIOCURSO     timestamp,
   FECHAFINCURSO        timestamp,
   LUGARCURSO           varchar(250),
   primary key (IDFORPAR)
);

/*==============================================================*/
/* Table: PARACAIDISTA                                          */
/*==============================================================*/
create table PARACAIDISTA
(
   IDPARACAIDISTA       int AUTO_INCREMENT,
   IDFORPAR             int,
   NOMBRESPAR           varchar(250),
   APELLIDOSPAR         varchar(250),
   TIPOPAR              varchar(32),
   ESCUELAFORMACION     varchar(100),
   NUMEROSALTOS         int,
   FECHAULTIMOSALTO     timestamp,
   LICENCIA             bool,
   FECHACADUCIDADLICENCIA timestamp,
   primary key (IDPARACAIDISTA)
);

/*==============================================================*/
/* Table: PEDIDO                                                */
/*==============================================================*/
create table PEDIDO
(
   IDPEDIDO             int AUTO_INCREMENT,
   CLIENTEID            int,
   SALTOID              int,
   PARACAIDISTAID       int,
   DESCUENTOID          int,
   IDSERVICIO           int,
   FECHAPED             timestamp,
   COSTOSALTO           numeric(6,2),
   COSTOTOTALPED        numeric(6,2),
   primary key (IDPEDIDO)
);

/*==============================================================*/
/* Table: PERFIL                                                */
/*==============================================================*/
create table PERFIL
(
   IDPERFIL             int AUTO_INCREMENT,
   NOMBREPER            varchar(64),
   ESTADOPER            char(3),
   ESTADOPER2           char(3),
   FECHACREACIONPER     timestamp,
   FECHAMODPER          timestamp,
   primary key (IDPERFIL)
);

/*==============================================================*/
/* Table: PERMISO                                               */
/*==============================================================*/
create table PERMISO
(
   IDPERMISO            int AUTO_INCREMENT,
   RECURSOID            int,
   PERFI_ID             int,
   NOMBREPER            varchar(50),
   ESTADOPER            char(3),
   primary key (IDPERMISO)
);

/*==============================================================*/
/* Table: PILOTO                                                */
/*==============================================================*/
create table PILOTO
(
   IDPILOTO             int AUTO_INCREMENT,
   NOMBRESPIL           varchar(200),
   CEDULAPIL            varchar(10),
   TELFPIL              varchar(10),
   primary key (IDPILOTO)
);

/*==============================================================*/
/* Table: RECURSO                                               */
/*==============================================================*/
create table RECURSO
(
   IDRECURSO            int AUTO_INCREMENT,
   REC_IDRECURSO        int,
   NOMBREREC            varchar(128),
   PAGINAREC            varchar(250),
   ICONOREC             varchar(32),
   INDICEREC            int,
   ESTADOREC            char(3),
   primary key (IDRECURSO)
);

/*==============================================================*/
/* Table: RESPONSABLE                                           */
/*==============================================================*/
create table RESPONSABLE
(
   IDRESPONSABLE        int AUTO_INCREMENT,
   NOMBRESRES           varchar(100),
   CEDULARES            varchar(10),
   EMAILRES             varchar(100),
   TELFRES              varchar(10),
   primary key (IDRESPONSABLE)
);

/*==============================================================*/
/* Table: SALTO                                                 */
/*==============================================================*/
create table SALTO
(
   IDSALTO              int AUTO_INCREMENT,
   TIPOSALTO            varchar(50),
   primary key (IDSALTO)
);

/*==============================================================*/
/* Table: SERVICIO_ADICIONAL                                    */
/*==============================================================*/
create table SERVICIO_ADICIONAL
(
   IDSERVICIO           int AUTO_INCREMENT,
   RESPONSABLEID        int,
   NOMBRESER            varchar(100),
   DESCRIPCIONSER       varchar(250),
   COSTOSER             numeric(6,2),
   primary key (IDSERVICIO)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   IDUSUARIO            int AUTO_INCREMENT,
   EMPLEADOID           int,
   PERFILID             int,
   NOMBREUSUARIO        varchar(100),
   CLAVEUSU             varchar(250),
   primary key (IDUSUARIO)
);

/*==============================================================*/
/* Table: VUELO                                                 */
/*==============================================================*/
create table VUELO
(
   IDVUELO              int AUTO_INCREMENT,
   AERONAVEID           int,
   PILOTOID             int,
   HORASALIDA           timestamp,
   HORALLEGADA          timestamp,
   CAPACIDADPERSONAS    int,
   primary key (IDVUELO)
);

alter table DETALLE_VUELO add constraint FK_LLEVA foreign key (VUELOID)
      references VUELO (IDVUELO) on delete restrict on update restrict;

alter table DETALLE_VUELO add constraint FK_REGISTRA foreign key (PEDIDOID)
      references PEDIDO (IDPEDIDO) on delete restrict on update restrict;

alter table PARACAIDISTA add constraint FK_CURSO foreign key (IDFORPAR)
      references FORMACION_PARACAIDISTA (IDFORPAR) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_PUEDE_AYUDAR foreign key (PARACAIDISTAID)
      references PARACAIDISTA (IDPARACAIDISTA) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_PUEDE_TENER foreign key (DESCUENTOID)
      references DESCUENTO (IDDESCUENTO) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_REALIZA foreign key (CLIENTEID)
      references CLIENTE (IDCLIENTE) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_SOLICITA_UN foreign key (SALTOID)
      references SALTO (IDSALTO) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_TIENE foreign key (IDSERVICIO)
      references SERVICIO_ADICIONAL (IDSERVICIO) on delete restrict on update restrict;

alter table PERMISO add constraint FK_AUTORIZA foreign key (PERFI_ID)
      references PERFIL (IDPERFIL) on delete restrict on update restrict;

alter table PERMISO add constraint FK_PERMISO_RECURSO foreign key (RECURSOID)
      references RECURSO (IDRECURSO) on delete restrict on update restrict;

alter table RECURSO add constraint FK_CONTIENE foreign key (REC_IDRECURSO)
      references RECURSO (IDRECURSO) on delete restrict on update restrict;

alter table SERVICIO_ADICIONAL add constraint FK_SE_ENCARGA foreign key (RESPONSABLEID)
      references RESPONSABLE (IDRESPONSABLE) on delete restrict on update restrict;

alter table USUARIO add constraint FK_NECESARIAMENTE foreign key (PERFILID)
      references PERFIL (IDPERFIL) on delete restrict on update restrict;

alter table USUARIO add constraint FK_PODRA_TENER foreign key (EMPLEADOID)
      references EMPLEADO (IDEMPLEADO) on delete restrict on update restrict;

alter table VUELO add constraint FK_CONDUCE foreign key (PILOTOID)
      references PILOTO (IDPILOTO) on delete restrict on update restrict;

alter table VUELO add constraint FK_REALIZA_UN foreign key (AERONAVEID)
      references AERONAVE (IDAERONAVE) on delete restrict on update restrict;

