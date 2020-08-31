/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     31/08/2020 17:28:14                          */
/*==============================================================*/


drop table if exists AERONAVE;

drop table if exists ASIGNAR_PERFIL;

drop table if exists BITACORA;

drop table if exists CABECERA_FACTURA;

drop table if exists CONFIG_CLAVE;

drop table if exists CURSOS_FORMACION;

drop table if exists DESCUENTO;

drop table if exists DETALLE_FACTURA;

drop table if exists EMPRESA;

drop table if exists FORMACION_PARACAIDISTA;

drop table if exists FORMA_PAGO;

drop table if exists OPCION;

drop table if exists OPCION_PERFIL;

drop table if exists PARACAIDISTA;

drop table if exists PEDIDO;

drop table if exists PERFIL;

drop table if exists PERSONA;

drop table if exists SALTO;

drop table if exists SERVICIO_ADICIONAL;

drop table if exists USUARIO_ACCESO;

drop table if exists VUELO;

/*==============================================================*/
/* Table: AERONAVE                                              */
/*==============================================================*/
create table AERONAVE
(
   ID_AERONAVE          int AUTO_INCREMENT,
   AER_NOMBRE           varchar(100),
   AER_MODELO           varchar(14),
   AER_CAPACIDAD_TOTAL  int,
   AER_FECHA_CREACION   datetime,
   AER_FECHA_MOD        datetime,
   primary key (ID_AERONAVE)
);

/*==============================================================*/
/* Table: ASIGNAR_PERFIL                                        */
/*==============================================================*/
create table ASIGNAR_PERFIL
(
   ID_ASIGNAR_PERFIL    int AUTO_INCREMENT,
   ID_USUARIO_ACCESO    int,
   ID_PERFIL            int,
   ASI_PER_FECHA_CREACION datetime,
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
   BIT_OPCION_AFECTADO  varchar(128),
   BIT_DESCRIPCION      varchar(512),
   BIT_IP               varchar(64),
   BIT_SO               varchar(128),
   BIT_NAVEGADOR        varchar(128),
   primary key (ID_BITACORA)
);

/*==============================================================*/
/* Table: CABECERA_FACTURA                                      */
/*==============================================================*/
create table CABECERA_FACTURA
(
   ID_FACTURA           int AUTO_INCREMENT,
   ID_EMPRESA           int,
   ID_FORMA_PAGO        int,
   ID_USUARIO_CLIENTE   int,
   ID_USUARIO_GENERADOR_FAC int,
   FAC_NUMERO           varchar(32),
   FAC_SUBTOTAL         numeric(6,2),
   FAC_TOTAL_DESCUENTO  numeric(6,2),
   FAC_PORCENTAJE_IVA   numeric(6,2),
   FAC_VALOR_TOTAL      numeric(6,2),
   FAC_FECHA_EMISION    datetime,
   FAC_FECHA_CADUCIDAD  datetime,
   primary key (ID_FACTURA)
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
   CLAVE_CAMBIOS_DIA    int,
   CLAVE_INTENTOS_FALLIDOS int,
   CLAVE_ULTIMA_CLAVE   varchar(512),
   CLAVE_FECHA_CREACION datetime,
   CLAVE_FECHA_MOD      datetime,
   primary key (ID_CONFIG_CLAVE)
);

/*==============================================================*/
/* Table: CURSOS_FORMACION                                      */
/*==============================================================*/
create table CURSOS_FORMACION
(
   ID_CUR_FORMACION     int AUTO_INCREMENT,
   NOMBRE_CURSO         varchar(100),
   LUGAR_CURSO          varchar(250),
   FECH_INICIO_CURSO    timestamp,
   FECHA_FIN_CURSO      timestamp,
   primary key (ID_CUR_FORMACION)
);

/*==============================================================*/
/* Table: DESCUENTO                                             */
/*==============================================================*/
create table DESCUENTO
(
   ID_DESCUENTO         int AUTO_INCREMENT,
   DES_NOMBRE           varchar(100),
   DES_DESCRIPCION      varchar(250),
   DES_VALOR            numeric(6,2),
   DES_FECHA_CREACION   datetime,
   DES_FECHA_MOD        datetime,
   primary key (ID_DESCUENTO)
);

/*==============================================================*/
/* Table: DETALLE_FACTURA                                       */
/*==============================================================*/
create table DETALLE_FACTURA
(
   ID_DET_VUELO         int AUTO_INCREMENT,
   ID_FACTURA           int,
   ID_PEDIDO            int,
   primary key (ID_DET_VUELO)
);

/*==============================================================*/
/* Table: EMPRESA                                               */
/*==============================================================*/
create table EMPRESA
(
   ID_EMPRESA           int AUTO_INCREMENT,
   EMP_RAZON_SOCIAL     varchar(64),
   EMP_RUC              varchar(13),
   EMP_DIRECCION        varchar(256),
   EMP_TELEFONO         varchar(10),
   EMP_CORREO           varchar(64),
   NUM_RESO_CONTRIBUYENTE_SRI int,
   NUM_AUTORIZACION_SRI varchar(138),
   OBLIGADO_LLEVAR_CONTABILIDAD bool,
   EMP_FECHA_CREACION   datetime,
   EMP_FECHA_MOD        datetime,
   primary key (ID_EMPRESA)
);

/*==============================================================*/
/* Table: FORMACION_PARACAIDISTA                                */
/*==============================================================*/
create table FORMACION_PARACAIDISTA
(
   ID_FOR_PARACAIDISTA  int AUTO_INCREMENT,
   ID_PARACAIDISTA      int,
   ID_CUR_FORMACION     int,
   FOR_PAR_FECHA_CREACION datetime,
   primary key (ID_FOR_PARACAIDISTA)
);

/*==============================================================*/
/* Table: FORMA_PAGO                                            */
/*==============================================================*/
create table FORMA_PAGO
(
   ID_FORMA_PAGO        int AUTO_INCREMENT,
   PAGO_NOMBRE          varchar(64),
   PAGO_DESCRIPCION     varchar(256),
   primary key (ID_FORMA_PAGO)
);

/*==============================================================*/
/* Table: OPCION                                                */
/*==============================================================*/
create table OPCION
(
   ID_OPCION            int AUTO_INCREMENT,
   ID_OPCION_PADRE      int,
   OPC_NOMBRE           varchar(128),
   OPC_DESCRIPCION      varchar(256),
   OPC_PAGINA           varchar(128),
   OPC_ICONO            varchar(32),
   OPC_INDEX            int,
   OPC_ESTATUS          char(3),
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
/* Table: PARACAIDISTA                                          */
/*==============================================================*/
create table PARACAIDISTA
(
   ID_PARACAIDISTA      int AUTO_INCREMENT,
   ID_USUARIO_ACCESO    int,
   TIPO_PARACAIDISTA    varchar(32),
   ESCUELA_FORMACION    varchar(100),
   NUM_SALTOS_COMPLETADOS int,
   FECHA_ULTIMO_SALTO   datetime,
   LICENCIA             bool,
   FECHA_CADUCIDAD_LICENCIA datetime,
   primary key (ID_PARACAIDISTA)
);

/*==============================================================*/
/* Table: PEDIDO                                                */
/*==============================================================*/
create table PEDIDO
(
   ID_PEDIDO            int AUTO_INCREMENT,
   ID_PARACAIDISTA      int,
   ID_DESCUENTO         int,
   ID_SERVICIO_ADICIONAL int,
   ID_SALTO             int,
   ID_VUELO             int,
   COSTO_SALTO          numeric(6,2),
   COSTO_TOTAL_PEDIDO   numeric(6,2),
   FECA_A_REALIZARSE_SALTO datetime,
   ESTATUS_FACTURACION  bool,
   FECHA_PEDIDO         datetime,
   primary key (ID_PEDIDO)
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
   ID_PERSONA           int AUTO_INCREMENT,
   PER_NOMBRES          varchar(128),
   PER_APELLIDOS        varchar(128),
   PER_DIRECCION        varchar(256),
   PER_IDENTIFICACION   char(10),
   PER_FECHA_NACIMIENTO datetime,
   PER_TELEFONO         varchar(10),
   PER_CORREO           varchar(128),
   PER_FOTO             varchar(256),
   PER_FECHA_CREACION   datetime,
   PER_FECHA_MOD        datetime,
   PER_ESTATUS          char(3),
   primary key (ID_PERSONA)
);

/*==============================================================*/
/* Table: SALTO                                                 */
/*==============================================================*/
create table SALTO
(
   ID_SALTO             int AUTO_INCREMENT,
   TIPO_SALTO           varchar(50),
   primary key (ID_SALTO)
);

/*==============================================================*/
/* Table: SERVICIO_ADICIONAL                                    */
/*==============================================================*/
create table SERVICIO_ADICIONAL
(
   ID_SERVICIO_ADICIONAL int AUTO_INCREMENT,
   ID_USUARIO_RESPONSABLE int,
   SER_NOMBRE           varchar(100),
   SER_DESCRIPCION      varchar(250),
   SER_COSTO            numeric(6,2),
   SER_FECHA_CREACION   datetime,
   SER_FECHA_MOD        datetime,
   primary key (ID_SERVICIO_ADICIONAL)
);

/*==============================================================*/
/* Table: USUARIO_ACCESO                                        */
/*==============================================================*/
create table USUARIO_ACCESO
(
   ID_USUARIO_ACCESO    int AUTO_INCREMENT,
   ID_PERSONA           int,
   ID_CONFIG_CLAVE      int,
   USR_ACCESO_NOMBRE    varchar(128),
   USR_ACCESO_CLAVE     varchar(512),
   USR_ACCESO_INTENTOS_FALLIDOS int,
   USR_ACCESO_ESTATUS   char(3),
   USR_ACCESO_FECHA_CREACION datetime,
   USR_ACCESO_FECHA_MOD datetime,
   primary key (ID_USUARIO_ACCESO)
);

/*==============================================================*/
/* Table: VUELO                                                 */
/*==============================================================*/
create table VUELO
(
   ID_VUELO             int AUTO_INCREMENT,
   ID_AERONAVE          int,
   ID_USUARIO_PILOTO    int,
   VUE_HORA_SALIDA      datetime,
   VUE_HORA_LLEGADA     datetime,
   VUE_PERSONAS_A_BORDO int,
   primary key (ID_VUELO)
);

alter table ASIGNAR_PERFIL add constraint FK_ASIGNAR_PERFIL foreign key (ID_PERFIL)
      references PERFIL (ID_PERFIL) on delete restrict on update restrict;

alter table ASIGNAR_PERFIL add constraint FK_USUARIO_PERFIL foreign key (ID_USUARIO_ACCESO)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

alter table BITACORA add constraint FK_BITACORA_USUARIO foreign key (ID_USUARIO_ACCESO)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

alter table CABECERA_FACTURA add constraint FK_RELATIONSHIP_32 foreign key (ID_FORMA_PAGO)
      references FORMA_PAGO (ID_FORMA_PAGO) on delete restrict on update restrict;

alter table CABECERA_FACTURA add constraint FK_RELATIONSHIP_33 foreign key (ID_EMPRESA)
      references EMPRESA (ID_EMPRESA) on delete restrict on update restrict;

alter table CABECERA_FACTURA add constraint FK_RELATIONSHIP_36 foreign key (ID_USUARIO_CLIENTE)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

alter table CABECERA_FACTURA add constraint FK_RELATIONSHIP_37 foreign key (ID_USUARIO_GENERADOR_FAC)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

alter table DETALLE_FACTURA add constraint FK_RELATIONSHIP_28 foreign key (ID_PEDIDO)
      references PEDIDO (ID_PEDIDO) on delete restrict on update restrict;

alter table DETALLE_FACTURA add constraint FK_RELATIONSHIP_31 foreign key (ID_FACTURA)
      references CABECERA_FACTURA (ID_FACTURA) on delete restrict on update restrict;

alter table FORMACION_PARACAIDISTA add constraint FK_RELATIONSHIP_19 foreign key (ID_CUR_FORMACION)
      references CURSOS_FORMACION (ID_CUR_FORMACION) on delete restrict on update restrict;

alter table FORMACION_PARACAIDISTA add constraint FK_RELATIONSHIP_20 foreign key (ID_PARACAIDISTA)
      references PARACAIDISTA (ID_PARACAIDISTA) on delete restrict on update restrict;

alter table OPCION add constraint FK_RECURSO_PADRE foreign key (ID_OPCION_PADRE)
      references OPCION (ID_OPCION) on delete restrict on update restrict;

alter table OPCION_PERFIL add constraint FK_PERFIL_PERMISOS foreign key (ID_PERFIL)
      references PERFIL (ID_PERFIL) on delete restrict on update restrict;

alter table OPCION_PERFIL add constraint FK_RECURSO_PERMISOS foreign key (ID_OPCION)
      references OPCION (ID_OPCION) on delete restrict on update restrict;

alter table PARACAIDISTA add constraint FK_RELATIONSHIP_35 foreign key (ID_USUARIO_ACCESO)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_RELATIONSHIP_23 foreign key (ID_PARACAIDISTA)
      references PARACAIDISTA (ID_PARACAIDISTA) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_RELATIONSHIP_24 foreign key (ID_DESCUENTO)
      references DESCUENTO (ID_DESCUENTO) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_RELATIONSHIP_25 foreign key (ID_SERVICIO_ADICIONAL)
      references SERVICIO_ADICIONAL (ID_SERVICIO_ADICIONAL) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_RELATIONSHIP_26 foreign key (ID_SALTO)
      references SALTO (ID_SALTO) on delete restrict on update restrict;

alter table PEDIDO add constraint FK_RELATIONSHIP_27 foreign key (ID_VUELO)
      references VUELO (ID_VUELO) on delete restrict on update restrict;

alter table SERVICIO_ADICIONAL add constraint FK_RELATIONSHIP_34 foreign key (ID_USUARIO_RESPONSABLE)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

alter table USUARIO_ACCESO add constraint FK_USUARIO_ACCESO foreign key (ID_PERSONA)
      references PERSONA (ID_PERSONA) on delete restrict on update restrict;

alter table USUARIO_ACCESO add constraint FK_USUARIO_CLAVE foreign key (ID_CONFIG_CLAVE)
      references CONFIG_CLAVE (ID_CONFIG_CLAVE) on delete restrict on update restrict;

alter table VUELO add constraint FK_RELATIONSHIP_29 foreign key (ID_AERONAVE)
      references AERONAVE (ID_AERONAVE) on delete restrict on update restrict;

alter table VUELO add constraint FK_RELATIONSHIP_30 foreign key (ID_USUARIO_PILOTO)
      references USUARIO_ACCESO (ID_USUARIO_ACCESO) on delete restrict on update restrict;

