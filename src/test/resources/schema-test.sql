/* Drop Sequences for Autonumber Columns */
 
--DROP DATABASE IF EXISTS UPERCTEST;

--CREATE DATABASE UPERCTEST;

--CONNECT TO UPERCTEST
 

/* Drop Tables */

DROP TABLE IF EXISTS CONFIGURACION CASCADE
;

DROP TABLE IF EXISTS ESPACIOPARQUEO CASCADE
;

DROP TABLE IF EXISTS INFORME CASCADE
;

DROP TABLE IF EXISTS PERFIL_USUARIO CASCADE
;

DROP TABLE IF EXISTS PERMISO CASCADE
;

DROP TABLE IF EXISTS RECURSO CASCADE
;

DROP TABLE IF EXISTS REG_SERVICIO CASCADE
;

DROP TABLE IF EXISTS REGISTRO_IE CASCADE
;

DROP TABLE IF EXISTS RESERVA CASCADE
;

DROP TABLE IF EXISTS ROL CASCADE
;

DROP TABLE IF EXISTS SISTEMA_EXTERNO CASCADE
;

DROP TABLE IF EXISTS SUPERVISION CASCADE
;

DROP TABLE IF EXISTS UBICACION CASCADE
;

DROP TABLE IF EXISTS USUARIO CASCADE
;

DROP TABLE IF EXISTS VEHICULO CASCADE
;

/* Create Tables */

CREATE TABLE CONFIGURACION
(
	ID serial NOT NULL,
	INTENTOS_FALLIDOS integer NOT NULL,
	CADUCIDAD_CONTRASENA integer NOT NULL,    -- MÁXIMO DE DÍAS DE VIDA DE LAS CONTRASEÑAS DE LOS USUARIOS
	MAX_ADMIN integer NOT NULL,    -- NUMERO MÁXIMO DE ADMINISTRADORES DEL SISTEMA
	FECHA_GUARDADO timestamp without time zone NOT NULL,
	USUARIO serial NOT NULL    -- USUARIO QUE GUARDÓ LA CONFIGURACIÓN
)
;

CREATE TABLE ESPACIOPARQUEO
(
	ID serial NOT NULL,
	NOMBRE varchar(50) NULL,
	UBICACION smallserial NOT NULL,    -- Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a a universidad
	OCUPADO boolean NULL    -- TRIGGER
)
;

CREATE TABLE INFORME
(
	ID bigserial NOT NULL,
	USUARIO bigint NULL,    -- El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?
	REG_SERVICIO bigint NULL,    -- El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?
	FECHA_GENERADO timestamp without time zone NOT NULL,
	FECHA_INIICIO date NOT NULL,
	FECHA_FIN date NOT NULL,
	DISPONIBILIDAD numeric NULL,    -- % porcentaje de espacios disponibles en el rango de fecha DIA/SEMANA/MES
	RESERVAS_OK numeric NULL,    -- % RESERVAS EXITOSAS DIA/SEMANA/MES
	RESERVAS_FAIL numeric NULL,    -- % RESERVAS FALLIDO DIA/SEMANA/MES
	RECOG_OK numeric NULL,    -- % RECONOCIMIENTO EXITOSO DIA/SEMANA/MES
	RECOG_FAIL numeric NULL,    -- % RECONOCIMIENTO FALLIDO DIA/SEMANA/MES
	RECOG_TOTAL integer NULL,    -- CANTIDAD TOTAL DE PLACAS RECONOCIDAS  DIA/SEMANA/MES
	INGRESOS_TOTAL integer NULL,    -- CANTIDAD DE ENTRADAS DIA/SEMANA/MES
	EGRESOS_TOTAL integer NULL    -- CANTIDAD DE SALIDAS DIA/SEMANA/MES
)
;

CREATE TABLE PERFIL_USUARIO
(
	ID smallserial NOT NULL,
	NOMBRE varchar(50) NOT NULL,
	DESCRIPCION varchar(50) NULL
)
;

CREATE TABLE PERMISO
(
	ID bigserial NOT NULL,
	USUARIO bigserial NOT NULL
)
;

CREATE TABLE RECURSO
(
	ID serial NOT NULL,
	NOMBRE varchar(50) NOT NULL,
	MARCA varchar(50) NOT NULL,
	TIPO varchar(50) NULL,    -- El tipo de recurso(dispositivo), puede ser un televisor o monitor o cámara
	IP inet NULL,    -- LA DIRECCIÓN ASIGNADA
	PUERTO varchar(5) NULL,    -- MÁXIMO HASTA 65535
	MAC macaddr NOT NULL,    -- MEDIA ACCESS CONTROL
	PROTOCOLO varchar(10) NULL,    -- 	- TCP 	- UDP  A FUTURO PUEDEN SER MÁS PROTOCOLOS
	FECHA_REGISTRO timestamp without time zone NOT NULL,
	URL_FABRICANTE varchar(2000) NULL,
	ESTADO boolean NOT NULL    -- ACTIVO - TRUE INACTIVO - FALSE
)
;

CREATE TABLE REG_SERVICIO
(
	ID bigserial NOT NULL,
	ID_SESSION uuid NOT NULL,
	SISTEMA_EXTERNO smallserial NOT NULL,
	FECHA_SESSION timestamp with time zone NOT NULL
)
;

CREATE TABLE REGISTRO_IE
(
	ID bigserial NOT NULL,
	FECHA_INGRESO timestamp without time zone NOT NULL,
	FECHA_EGRESO timestamp without time zone NULL,
	RECURSO serial NOT NULL,
	VEHICULO serial NOT NULL,
	USUARIO_INGRESO bigserial NOT NULL,
	USUARIO_EGRESO bigint NULL,
	PERMISO bigint NULL,
	TICKET_ID uuid NOT NULL
)
;

CREATE TABLE RESERVA
(
	ID bigserial NOT NULL,
	FECHA_SOLICITUD timestamp without time zone NOT NULL,
	ESTADO boolean NOT NULL,    -- 	- RESERVADA - FALSE 	- COMPLETADA - TRUE
	ESPACIO_PARQUEO serial NOT NULL,
	FECHA_RESERVA timestamp without time zone NOT NULL,
	FECHA_FIN timestamp without time zone NULL,    -- Fecha en que finaliza la reserva, sin importar si fue completada o cancelada
	CANCELADA boolean NOT NULL,    -- 	- CANCELADA - TRUE 	- NO CANCELADA - FALSE
	USUARIO serial NOT NULL
)
;

CREATE TABLE ROL
(
	ID smallserial NOT NULL,
	NOMBRE varchar(50) NULL,
	DESCRIPCION varchar(50) NULL,
	PERFIL smallserial NOT  NULL
)
;

CREATE TABLE SISTEMA_EXTERNO
(
	ID smallserial NOT NULL,
	IP inet NOT NULL,
	NOMBRE varchar(50) NOT NULL,
	CONTRASENA varchar(500) NOT NULL
)
;

CREATE TABLE SUPERVISION
(
	ID bigserial NOT NULL,
	MENSAJE varchar(50) NOT NULL,
	ESTADO boolean NOT NULL,    -- ACTIVO -TRUE INACTIVO - FALSE
	FECHA timestamp without time zone NOT NULL,
	TIPO boolean NOT NULL,    -- 	- ALERTA - TRUE 	- AVISOS - FLASE
	USUARIO serial NOT NULL
)
;

CREATE TABLE UBICACION
(
	ID smallserial NOT NULL,
	NOMBRE varchar(50) NOT NULL,
	DIRECCION varchar(50) NOT NULL,
	COORDENADAS point NOT NULL,
	TELEFONO varchar(50) NOT NULL
)
;

CREATE TABLE USUARIO
(
	ID serial NOT NULL,
	NOMBRES varchar(50) NULL,
	APELLIDOS varchar(50) NULL,
	TIPOID varchar(50) NOT NULL,
	NUMID varchar(50) NOT NULL,
	CONTRASENA varchar(50) NOT NULL,
	CORREO varchar(50) NOT NULL,
	FECHANAC date NULL,
	FECHAREG varchar(50) NULL,
	ESTADO boolean NOT NULL,
	ROL integer NOT NULL
)
;

CREATE TABLE VEHICULO
(
	ID serial NOT NULL,
	PLACA varchar(50) NULL,
	MARCA varchar(50) NULL,
	COLOR varchar(50) NULL,
	MODELO varchar(50) NULL,
	CLASE varchar(50) NULL,    -- Que clase de vehiculo es (Automovil, moticleta, autobus, etc)
	TIPO_SERVICIO varchar(50) NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */
ALTER TABLE CONFIGURACION ADD CONSTRAINT PK_CONFIGURACION
	PRIMARY KEY (ID)
;
ALTER TABLE ESPACIOPARQUEO ADD CONSTRAINT PK_ESPACIOPARQUEO
	PRIMARY KEY (ID)
;
ALTER TABLE INFORME ADD CONSTRAINT PK_INFORME
	PRIMARY KEY (ID)
;
ALTER TABLE PERFIL_USUARIO ADD CONSTRAINT PK_PERFIL_USUARIO
	PRIMARY KEY (ID)
;
ALTER TABLE PERMISO ADD CONSTRAINT PK_PERMISO
	PRIMARY KEY (ID)
;
ALTER TABLE RECURSO ADD CONSTRAINT PK_RECURSO
	PRIMARY KEY (ID)
;
ALTER TABLE REG_SERVICIO ADD CONSTRAINT PK_REG_SERVICIO
	PRIMARY KEY (ID)
;
ALTER TABLE REGISTRO_IE ADD CONSTRAINT PK_INGRESO
	PRIMARY KEY (ID)
;
ALTER TABLE RESERVA ADD CONSTRAINT PK_RESERVA
	PRIMARY KEY (ID)
;
ALTER TABLE ROL ADD CONSTRAINT PK_ROL
	PRIMARY KEY (ID)
;
ALTER TABLE SISTEMA_EXTERNO ADD CONSTRAINT PK_SISTEMA_EXTERNO
	PRIMARY KEY (ID)
;
ALTER TABLE SUPERVISION ADD CONSTRAINT PK_SUPERVISION
	PRIMARY KEY (ID)
;
ALTER TABLE UBICACION ADD CONSTRAINT PK_UBICACION
	PRIMARY KEY (ID)
;
ALTER TABLE USUARIO ADD CONSTRAINT PK_USUARIO
	PRIMARY KEY (ID)
;
ALTER TABLE VEHICULO ADD CONSTRAINT PK_VEHICULO
	PRIMARY KEY (ID)
;
ALTER TABLE REG_SERVICIO 
  ADD CONSTRAINT C_ID_SESION UNIQUE (ID_SESSION)
;
ALTER TABLE REGISTRO_IE 
  ADD CONSTRAINT C_TICKET_ID UNIQUE (TICKET_ID)
;
ALTER TABLE REGISTRO_IE 
  ADD CONSTRAINT C_PERMISO UNIQUE (PERMISO)
;
ALTER TABLE USUARIO 
  ADD CONSTRAINT C_NUMID UNIQUE (NUMID)
;
ALTER TABLE USUARIO 
  ADD CONSTRAINT C_CORREO UNIQUE (CORREO)
;
ALTER TABLE VEHICULO 
  ADD CONSTRAINT C_PLACA UNIQUE (PLACA)
;


CREATE UNIQUE INDEX IXFK_CONFIGURACION_USUARIO ON CONFIGURACION (USUARIO ASC)
;
CREATE INDEX IXFK_ESPACIOPARQUEO_UBICACION ON ESPACIOPARQUEO (UBICACION ASC)
;
CREATE INDEX IXFK_INFORME_REG_SERVICIO ON INFORME (REG_SERVICIO ASC)
;
CREATE INDEX IXFK_INFORME_USUARIO ON INFORME (USUARIO ASC)
;
CREATE INDEX IXFK_PERMISO_USUARIO ON PERMISO (USUARIO ASC)
;
CREATE INDEX IXFK_REG_SERVICIO_SISTEMA_EXTERNO ON REG_SERVICIO (SISTEMA_EXTERNO ASC)
;
CREATE INDEX IXFK_INGRESO_RECURSO ON REGISTRO_IE (RECURSO ASC)
;
CREATE INDEX IXFK_REGISTRO_IE_PERMISO ON REGISTRO_IE (PERMISO ASC)
;
CREATE INDEX IXFK_REGISTRO_IE_USUARIO ON REGISTRO_IE (USUARIO_INGRESO ASC)
;
CREATE INDEX IXFK_REGISTRO_IE_USUARIO_02 ON REGISTRO_IE (USUARIO_EGRESO ASC)
;
CREATE INDEX IXFK_REGISTRO_IE_VEHICULO ON REGISTRO_IE (VEHICULO ASC)
;
CREATE INDEX IXFK_RESERVA_ESPACIOPARQUEO ON RESERVA (ESPACIO_PARQUEO ASC)
;
CREATE INDEX IXFK_RESERVA_USUARIO ON RESERVA (USUARIO ASC)
;
CREATE INDEX IXFK_ROL_PERFIL_USUARIO ON ROL (PERFIL ASC)
;
CREATE INDEX IXFK_SUPERVISION_USUARIO ON SUPERVISION (USUARIO ASC)
;
CREATE INDEX IXFK_USUARIO_ROL ON USUARIO (ROL ASC)
;


/* Create Foreign Key Constraints */

ALTER TABLE CONFIGURACION ADD CONSTRAINT FK_CONFIGURACION_USUARIO
	FOREIGN KEY (USUARIO) REFERENCES USUARIO (ID) ON DELETE Restrict ON UPDATE Cascade
;

ALTER TABLE ESPACIOPARQUEO ADD CONSTRAINT FK_ESPACIOPARQUEO_UBICACION
	FOREIGN KEY (UBICACION) REFERENCES UBICACION (ID) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE INFORME ADD CONSTRAINT FK_INFORME_REG_SERVICIO
	FOREIGN KEY (REG_SERVICIO) REFERENCES REG_SERVICIO (ID) ON DELETE Restrict ON UPDATE Cascade
;

ALTER TABLE INFORME ADD CONSTRAINT FK_INFORME_USUARIO
	FOREIGN KEY (USUARIO) REFERENCES USUARIO (ID) ON DELETE Restrict ON UPDATE Cascade
;

ALTER TABLE PERMISO ADD CONSTRAINT FK_PERMISO_USUARIO
	FOREIGN KEY (USUARIO) REFERENCES USUARIO (ID) ON DELETE Restrict ON UPDATE Cascade
;

ALTER TABLE REG_SERVICIO ADD CONSTRAINT FK_REG_SERVICIO_SISTEMA_EXTERNO
	FOREIGN KEY (SISTEMA_EXTERNO) REFERENCES SISTEMA_EXTERNO (ID) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_INGRESO_RECURSO
	FOREIGN KEY (RECURSO) REFERENCES RECURSO (ID) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_REGISTRO_IE_PERMISO
	FOREIGN KEY (PERMISO) REFERENCES PERMISO (ID) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_REGISTRO_IE_USUARIO_EGRESO
	FOREIGN KEY (USUARIO_EGRESO) REFERENCES USUARIO (ID) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_REGISTRO_IE_USUARIO_INGRESO
	FOREIGN KEY (USUARIO_INGRESO) REFERENCES USUARIO (ID) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_REGISTRO_IE_VEHICULO
	FOREIGN KEY (VEHICULO) REFERENCES VEHICULO (ID) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE RESERVA ADD CONSTRAINT FK_RESERVA_ESPACIOPARQUEO
	FOREIGN KEY (ESPACIO_PARQUEO) REFERENCES ESPACIOPARQUEO (ID) ON DELETE Restrict ON UPDATE Cascade
;

ALTER TABLE RESERVA ADD CONSTRAINT FK_RESERVA_USUARIO
	FOREIGN KEY (USUARIO) REFERENCES USUARIO (ID) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE ROL ADD CONSTRAINT FK_ROL_PERFIL_USUARIO
	FOREIGN KEY (PERFIL) REFERENCES PERFIL_USUARIO (ID) ON DELETE Restrict ON UPDATE Cascade
;

ALTER TABLE SUPERVISION ADD CONSTRAINT FK_SUPERVISION_USUARIO
	FOREIGN KEY (USUARIO) REFERENCES USUARIO (ID) ON DELETE Restrict ON UPDATE No Action
;


/* Create Table Comments, Sequences for Autonumber Columns */

COMMENT ON TABLE CONFIGURACION
	IS 'Guarda las configuraciones realizadas por los usuarios autorizados(Administrador)'
;

COMMENT ON COLUMN CONFIGURACION.CADUCIDAD_CONTRASENA
	IS 'MÁXIMO DE DÍAS DE VIDA DE LAS CONTRASEÑAS DE LOS USUARIOS'
;

COMMENT ON COLUMN CONFIGURACION.MAX_ADMIN
	IS 'NUMERO MÁXIMO DE ADMINISTRADORES DEL SISTEMA'
;

COMMENT ON COLUMN CONFIGURACION.USUARIO
	IS 'USUARIO QUE GUARDÓ LA CONFIGURACIÓN'
;

COMMENT ON TABLE ESPACIOPARQUEO
	IS 'Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a la universidad'
;

COMMENT ON COLUMN ESPACIOPARQUEO.UBICACION
	IS 'Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a la universidad'
;

COMMENT ON COLUMN ESPACIOPARQUEO.OCUPADO
	IS 'TRIGGER'
;

COMMENT ON TABLE INFORME
	IS 'Guarda los datos procesados de los reportes consultados a la base de datos para ser consultados porteriormente por usuarios o sistemas externos autorizados.  Que contiene un (informe/reporte)?  	- Espacios disponibles/mes 	- % planificación de reservas/días/semanas/mes 	- Frecuencia de entrada y salida de vehiculos/mes 	- % reservas exitosas/mes 	- % reservas fallidas/mes 	- % reconocimientos exitoso/mes  (ingresos) 	- % reconocimiento fallido/mes 	- Cantidad de placas reconocidas/mes 	- Cantidad de ingresos /día/semana/mes 	- Cantidad de salidas /día/semana/mes'
;

COMMENT ON COLUMN INFORME.USUARIO
	IS 'El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?'
;

COMMENT ON COLUMN INFORME.REG_SERVICIO
	IS 'El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?'
;

COMMENT ON COLUMN INFORME.DISPONIBILIDAD
	IS '% porcentaje de espacios disponibles en el rango de fecha DÍA/SEMANA/MES'
;

COMMENT ON COLUMN INFORME.RESERVAS_OK
	IS '% RESERVAS EXITOSAS DÍA/SEMANA/MES'
;

COMMENT ON COLUMN INFORME.RESERVAS_FAIL
	IS '% RESERVAS FALLIDO DÍA/SEMANA/MES'
;

COMMENT ON COLUMN INFORME.RECOG_OK
	IS '% RECONOCIMIENTO EXITOSO DÍA/SEMANA/MES'
;

COMMENT ON COLUMN INFORME.RECOG_FAIL
	IS '% RECONOCIMIENTO FALLIDO DÍA/SEMANA/MES'
;

COMMENT ON COLUMN INFORME.RECOG_TOTAL
	IS 'CANTIDAD TOTAL DE PLACAS RECONOCIDAS  DÍA/SEMANA/MES'
;

COMMENT ON COLUMN INFORME.INGRESOS_TOTAL
	IS 'CANTIDAD DE ENTRADAS DÍA/SEMANA/MES'
;

COMMENT ON COLUMN INFORME.EGRESOS_TOTAL
	IS 'CANTIDAD DE SALIDAS DÍA/SEMANA/MES'
;

COMMENT ON TABLE PERFIL_USUARIO
	IS 'Guarda los perfiles de usuario definidos en los requisitos. Es una tabla catálogo.'
;

COMMENT ON TABLE RECURSO
	IS 'Guarda los dispositivos que capturan o muestran la información del ingreso o egreso de los usuarios con sus vehículos.  	- El campo TIPO registra el tipode recurso(dispositivo), puede ser un televisor o monitor o cámara. 	- El campo IP registra la dirección asignada dentro de la red. 	- El campo PUERTO registra el puerto por el que el dispositivo se está comunicando. 	- El campo MAC registra la mac única del dispositivo. 	- El campo PROTOCOLO registra el protocolo por el cuál el recurso se comunica (TCP - UDP A FUTURO PUEDEN SER MÁS PROTOCOLOS). 	- El campo FECHA_REGISTRO registra el momento de tiempo en el que se agregó el recurso al sistema. 	- El campo URL_FABRICANTE registra la dirección web del fabricante del recurso. 	- El campo ESTADO registra si el recurso se encuentra activo o inactivo.'
;

COMMENT ON COLUMN RECURSO.TIPO
	IS 'El tipo de recurso(dispositivo), puede ser un televisor o monitor o cámara'
;

COMMENT ON COLUMN RECURSO.IP
	IS 'LA DIRECCIÓN ASIGNADA'
;

COMMENT ON COLUMN RECURSO.PUERTO
	IS 'MÁXIMO HASTA 65535'
;

COMMENT ON COLUMN RECURSO.MAC
	IS 'MEDIA ACCESS CONTROL'
;

COMMENT ON COLUMN RECURSO.PROTOCOLO
	IS '	- TCP 	- UDP  A FUTURO PUEDEN SER MÁS PROTOCOLOS'
;

COMMENT ON COLUMN RECURSO.ESTADO
	IS 'ACTIVO - TRUE INACTIVO - FALSE'
;

COMMENT ON TABLE REG_SERVICIO
	IS 'Guarda la sesión del sistema externo y la fecha en la que se creó la sessión.  	- El campo ID_SESSION registra un número que identifica la sesión en que el sistema externo realizará consultas a la base de datos 	- El campo FECHA_SESSION  registra el momento en el que el número de sesión fué creado.'
;

COMMENT ON TABLE REGISTRO_IE
	IS 'Guarda el momento en que los recursos(dispositivos de captura) registran el ingreso o egreso al parqueadero de lo usuarios y vehículos.'
;

COMMENT ON TABLE RESERVA
	IS 'Guarda las reservas que han sido solicitadas por los usuarios. 	- El campo FECHA_SOLICITUD registra el momento de tiempo en que se solicitó la reserva. 	- El campo ESTADO registra si la reserva se encuentra activa o inactiva. 	- El campo NUM_ESTACION registra el número del estacionamiento reservado para el vehículo. 	- El campo FECHA_RESERVA registra el momento en el que se espera que el vehículo ingrese al parqueadero de la universidad para ocupar el espacio reservado. 	- El campo FECHA_FIN registra el momento en que finaliza la reserva. 	- El campo CANCELADA registra si la reserva fue cancelada o no.'
;

COMMENT ON COLUMN RESERVA.ESTADO
	IS '	- RESERVADA - FALSE 	- COMPLETADA - TRUE'
;

COMMENT ON COLUMN RESERVA.FECHA_FIN
	IS 'Fecha en que finaliza la reserva, sin importar si fue completada o cancelada'
;

COMMENT ON COLUMN RESERVA.CANCELADA
	IS '	- CANCELADA - TRUE 	- NO CANCELADA - FALSE'
;

COMMENT ON TABLE ROL
	IS 'Guarda los roles asociados a los perfiles de usuario. Es una tabla catálogo.'
;

COMMENT ON TABLE SISTEMA_EXTERNO
	IS 'Guarda los datos de acceso del sistema externo al sistema.'
;

COMMENT ON TABLE SUPERVISION
	IS 'Guarda los mensajes que envían los usuarios que posean el rol de supervisión.  	- El campo ESTADO indica si el mensaje se encuentra activo o inactivo. 	- el campo FECHA indica el momento de tiempo en que el mensaje fué creado o modificado. 	- El campo TIPO indica si el mensaje es una alerta o un aviso.'
;

COMMENT ON COLUMN SUPERVISION.ESTADO
	IS 'ACTIVO -TRUE INACTIVO - FALSE'
;

COMMENT ON COLUMN SUPERVISION.TIPO
	IS '	- ALERTA - TRUE 	- AVISOS - FLASE'
;

COMMENT ON TABLE UBICACION
	IS 'Table catálogo que guarda la ubicacion de la sede, extenciones, seccionales y otros puntos estratégicos que pertenecen a la universidad de Cundinamarca'
;

COMMENT ON TABLE USUARIO
	IS 'Guarda los usuarios registrados en el sistema.  	- El campo NOMBRES registra los nombres del usuario 	- El campo APELLIDOS registra los apellidos del usuario 	- El campo TIPOID registra el tipo de documento del usuario. 	- El campo NUMID registra el número de documento del usuario. 	- El campo FECHAREG guarda la fecha en que se registró el usuario. 	- El campo ESTADO registra si el usuario se encuentra activo o inactivo. 	- El campo ROL registra el rol que tiene asignado el usuario'
;

COMMENT ON TABLE VEHICULO
	IS 'Guarda los datos de los vehículos que han ingresado al parqueadero.';

COMMENT ON COLUMN VEHICULO.CLASE
	IS 'Que clase de vehículo es (Automovil, moticleta, autobus, etc)';

 