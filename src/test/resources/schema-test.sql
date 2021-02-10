-- PostgreSQL database version 9.4.15
/* Drop Tables */

DROP TABLE IF EXISTS CONFIGURACION CASCADE;
DROP TABLE IF EXISTS ESPACIOPARQUEO CASCADE;
DROP TABLE IF EXISTS INFORME CASCADE;
DROP TABLE IF EXISTS PERFIL_USUARIO CASCADE;
DROP TABLE IF EXISTS PERMISO CASCADE;
DROP TABLE IF EXISTS RECURSO CASCADE;
DROP TABLE IF EXISTS REG_SERVICIO CASCADE;
DROP TABLE IF EXISTS REGISTRO_IE CASCADE;
DROP TABLE IF EXISTS RESERVA CASCADE;
DROP TABLE IF EXISTS ROL CASCADE;
DROP TABLE IF EXISTS SISTEMA_EXTERNO CASCADE;
DROP TABLE IF EXISTS SUPERVISION CASCADE;
DROP TABLE IF EXISTS UBICACION CASCADE;
DROP TABLE IF EXISTS USUARIO CASCADE;
DROP TABLE IF EXISTS VEHICULO CASCADE;

/* Create Tables */

CREATE TABLE CONFIGURACION
(
	id bigint NOT NULL,
	intentosfallidos integer NOT NULL,
	caducidadcontrasena integer NOT NULL,    -- MÁXIMO DE DÍAS DE VIDA DE LAS CONTRASEÑAS DE LOS USUARIOS
	maxadmin integer NOT NULL,    -- NUMERO MÁXIMO DE ADMINISTRADORES DEL SISTEMA
	fechaguardado timestamp without time zone NOT NULL
);

CREATE TABLE ESPACIOPARQUEO
(
	id serial NOT NULL,
	nombre varchar(50) NULL,
	ubicacion smallint NOT NULL,    -- Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a a universidad
	ocupado boolean NOT NULL    -- TRIGGER
);

CREATE TABLE INFORME
(
	id bigserial NOT NULL,
	usuario bigint NULL,    -- El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?
	regservicio bigint NULL,    -- El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?
	fechagenerado timestamp without time zone NOT NULL,
	fechainicio date NOT NULL,
	fechafin date NOT NULL,
	disponibilidad numeric NULL,    -- % porcentaje de espacios disponibles en el rango de fecha DIA/SEMANA/MES
	reservasok numeric NULL,    -- % RESERVAS EXITOSAS DIA/SEMANA/MES
	reservasfail numeric NULL,    -- % RESERVAS FALLIDO DIA/SEMANA/MES
	recogok numeric NULL,    -- % RECONOCIMIENTO EXITOSO DIA/SEMANA/MES
	recogfail numeric NULL,    -- % RECONOCIMIENTO FALLIDO DIA/SEMANA/MES
	recogtotal integer NULL,    -- CANTIDAD TOTAL DE PLACAS RECONOCIDAS  DIA/SEMANA/MES
	ingresostotal integer NULL,    -- CANTIDAD DE ENTRADAS DIA/SEMANA/MES
	egresostotal integer NULL    -- CANTIDAD DE SALIDAS DIA/SEMANA/MES
);

CREATE TABLE PERFIL_USUARIO
(
	id smallserial NOT NULL,
	nombre varchar(50) NOT NULL,
	descripcion varchar(50) NULL
);

CREATE TABLE PERMISO
(
	id bigint NOT NULL,
	usuario bigint NOT NULL
);

CREATE TABLE RECURSO
(
	id serial NOT NULL,
	nombre varchar(50) NOT NULL,
	marca varchar(50) NOT NULL,
	tipo varchar(50) NOT NULL,    -- El tipo de recurso(dispositivo), puede ser un televisor o monitor o cámara
	ip inet NULL,    -- LA DIRECCIÓN ASIGNADA
	puerto varchar(5) NULL,    -- MÁXIMO HASTA 65535
	mac macaddr NOT NULL,    -- MEDIA ACCESS CONTROL
	protocolo varchar(10) NULL,    -- 	- TCP 	- UDP  A FUTURO PUEDEN SER MÁS PROTOCOLOS
	fecharegistro timestamp without time zone NOT NULL,
	urlfabricante varchar(2000) NULL,
	estado boolean NOT NULL    -- ACTIVO - TRUE INACTIVO - FALSE
);

CREATE TABLE REG_SERVICIO
(
	id bigserial NOT NULL,
	idsession uuid NOT NULL,
	sistemaexterno smallint NOT NULL,
	fechasession timestamp with time zone NOT NULL
);

CREATE TABLE REGISTRO_IE
(
	id bigserial NOT NULL,
	fechaingreso timestamp without time zone NOT NULL,
	fechaegreso timestamp without time zone NULL,
	recurso integer NOT NULL,
	vehiculo bigint NOT NULL,
	usuarioingreso bigint NOT NULL,
	usuarioegreso bigint NULL,
	ticketid uuid NOT NULL
);

CREATE TABLE RESERVA
(
	id bigserial NOT NULL,
	fechasolicitud timestamp without time zone NOT NULL,
	estado boolean NOT NULL,    -- 	- RESERVADA - FALSE 	- COMPLETADA - TRUE
	espacioparqueo integer NOT NULL,
	fechareserva timestamp without time zone NOT NULL,
	fechafin timestamp without time zone NULL,    -- Fecha en que finaliza la reserva, sin importar si fue completada o cancelada
	cancelada boolean NOT NULL,    -- 	- CANCELADA - TRUE 	- NO CANCELADA - FALSE
	usuario bigint NOT NULL
);

CREATE TABLE ROL
(
	id smallserial NOT NULL,
	nombre varchar(50) NULL,
	descripcion varchar(50) NULL,
	perfil smallint NOT NULL
);

CREATE TABLE SISTEMA_EXTERNO
(
	id smallserial NOT NULL,
	ip inet NOT NULL,
	nombre varchar(50) NOT NULL,
	contrasena varchar(500) NOT NULL
);

CREATE TABLE SUPERVISION
(
	id bigserial NOT NULL,
	mensaje varchar(50) NOT NULL,
	estado boolean NOT NULL,    -- ACTIVO -TRUE INACTIVO - FALSE
	fecha timestamp without time zone NOT NULL,
	tipo boolean NOT NULL,    -- 	- ALERTA - TRUE 	- AVISOS - FLASE
	usuario bigint NOT NULL
);

CREATE TABLE UBICACION
(
	id smallserial NOT NULL,
	nombre varchar(50) NOT NULL,
	direccion varchar(50) NOT NULL,
	coordenadas point NOT NULL,
	telefono varchar(50) NOT NULL
);

CREATE TABLE USUARIO
(
	id bigserial NOT NULL,
	nombres varchar(50) NOT NULL,
	apellidos varchar(50) NOT NULL,
	tipoid varchar(50) NOT NULL,
	numid varchar(50) NOT NULL,
	contrasena varchar(50) NOT NULL,
	correo varchar(50) NOT NULL,
	fechanac date NOT NULL,
	fechareg varchar(50) NULL,
	estado boolean NOT NULL,
	rol smallint NOT NULL
);

CREATE TABLE VEHICULO
(
	id bigserial NOT NULL,
	placa varchar(50) NOT NULL,
	marca varchar(50) NULL,
	color varchar(50) NULL,
	modelo varchar(50) NULL,
	clase varchar(50) NULL,    -- Que clase de vehiculo es (Automovil, moticleta, autobus, etc)
	tiposervicio varchar(50) NULL
);

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE CONFIGURACION ADD CONSTRAINT PK_CONFIGURACION
	PRIMARY KEY (id);

ALTER TABLE ESPACIOPARQUEO ADD CONSTRAINT PK_ESPACIOPARQUEO
	PRIMARY KEY (id);

ALTER TABLE INFORME ADD CONSTRAINT PK_INFORME
	PRIMARY KEY (id);

ALTER TABLE PERFIL_USUARIO ADD CONSTRAINT PK_PERFIL_USUARIO
	PRIMARY KEY (id);

ALTER TABLE PERMISO ADD CONSTRAINT PK_PERMISO
	PRIMARY KEY (id);

ALTER TABLE RECURSO ADD CONSTRAINT PK_RECURSO
	PRIMARY KEY (id);

ALTER TABLE REG_SERVICIO ADD CONSTRAINT PK_REG_SERVICIO
	PRIMARY KEY (id);

ALTER TABLE REG_SERVICIO 
  ADD CONSTRAINT C_ID_SESION UNIQUE (idsession);

ALTER TABLE REGISTRO_IE ADD CONSTRAINT PK_INGRESO
	PRIMARY KEY (id);

ALTER TABLE REGISTRO_IE 
  ADD CONSTRAINT C_TICKET_ID UNIQUE (ticketid);

ALTER TABLE RESERVA ADD CONSTRAINT PK_RESERVA
	PRIMARY KEY (id);

ALTER TABLE ROL ADD CONSTRAINT PK_ROL
	PRIMARY KEY (id);

ALTER TABLE SISTEMA_EXTERNO ADD CONSTRAINT PK_SISTEMA_EXTERNO
	PRIMARY KEY (id);

ALTER TABLE SUPERVISION ADD CONSTRAINT PK_SUPERVISION
	PRIMARY KEY (id);

ALTER TABLE UBICACION ADD CONSTRAINT PK_UBICACION
	PRIMARY KEY (id);

ALTER TABLE USUARIO ADD CONSTRAINT PK_USUARIO
	PRIMARY KEY (id);

ALTER TABLE USUARIO 
  ADD CONSTRAINT C_NUMID UNIQUE (numid);

ALTER TABLE USUARIO 
  ADD CONSTRAINT C_CORREO UNIQUE (correo);


ALTER TABLE VEHICULO ADD CONSTRAINT PK_VEHICULO
	PRIMARY KEY (id);

ALTER TABLE VEHICULO 
  ADD CONSTRAINT C_PLACA UNIQUE (placa);



CREATE UNIQUE INDEX IXFK_CONFIGURACION_USUARIO ON CONFIGURACION (id ASC);
CREATE INDEX IXFK_ESPACIOPARQUEO_UBICACION ON ESPACIOPARQUEO (ubicacion ASC);
CREATE INDEX IXFK_INFORME_REG_SERVICIO ON INFORME (regservicio ASC);
CREATE INDEX IXFK_INFORME_USUARIO ON INFORME (usuario ASC);
CREATE INDEX IXFK_PERMISO_REGISTRO_IE ON PERMISO (id ASC);
CREATE INDEX IXFK_PERMISO_USUARIO ON PERMISO (usuario ASC);
CREATE INDEX IXFK_REG_SERVICIO_SISTEMA_EXTERNO ON REG_SERVICIO (sistemaexterno ASC);
CREATE INDEX IXFK_INGRESO_RECURSO ON REGISTRO_IE (recurso ASC);
CREATE INDEX IXFK_REGISTRO_IE_USUARIO ON REGISTRO_IE (usuarioingreso ASC);
CREATE INDEX IXFK_REGISTRO_IE_USUARIO_02 ON REGISTRO_IE (usuarioegreso ASC);
CREATE INDEX IXFK_REGISTRO_IE_VEHICULO ON REGISTRO_IE (vehiculo ASC);
CREATE INDEX IXFK_RESERVA_ESPACIOPARQUEO ON RESERVA (espacioparqueo ASC);
CREATE INDEX IXFK_RESERVA_USUARIO ON RESERVA (usuario ASC);
CREATE INDEX IXFK_ROL_PERFIL_USUARIO ON ROL (perfil ASC);
CREATE INDEX IXFK_SUPERVISION_USUARIO ON SUPERVISION (usuario ASC);
CREATE INDEX IXFK_USUARIO_ROL ON USUARIO (rol ASC);

/* Create Foreign Key Constraints */

ALTER TABLE ESPACIOPARQUEO ADD CONSTRAINT FK_ESPACIOPARQUEO_UBICACION
	FOREIGN KEY (ubicacion) REFERENCES UBICACION (id) ON DELETE No Action ON UPDATE No Action;

ALTER TABLE INFORME ADD CONSTRAINT FK_INFORME_REG_SERVICIO
	FOREIGN KEY (regservicio) REFERENCES REG_SERVICIO (id) ON DELETE Restrict ON UPDATE Cascade;

ALTER TABLE INFORME ADD CONSTRAINT FK_INFORME_USUARIO
	FOREIGN KEY (usuario) REFERENCES USUARIO (id) ON DELETE Restrict ON UPDATE Cascade;

ALTER TABLE PERMISO ADD CONSTRAINT FK_PERMISO_REGISTRO_IE
	FOREIGN KEY (id) REFERENCES REGISTRO_IE (id) ON DELETE Restrict ON UPDATE No Action;

ALTER TABLE PERMISO ADD CONSTRAINT FK_PERMISO_USUARIO
	FOREIGN KEY (usuario) REFERENCES USUARIO (id) ON DELETE Restrict ON UPDATE Cascade;

ALTER TABLE REG_SERVICIO ADD CONSTRAINT FK_REG_SERVICIO_SISTEMA_EXTERNO
	FOREIGN KEY (sistemaexterno) REFERENCES SISTEMA_EXTERNO (id) ON DELETE Restrict ON UPDATE Restrict;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_INGRESO_RECURSO
	FOREIGN KEY (recurso) REFERENCES RECURSO (id) ON DELETE No Action ON UPDATE No Action;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_REGISTRO_IE_USUARIO_EGRESO
	FOREIGN KEY (usuarioegreso) REFERENCES USUARIO (id) ON DELETE Restrict ON UPDATE No Action;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_REGISTRO_IE_USUARIO_INGRESO
	FOREIGN KEY (usuarioingreso) REFERENCES USUARIO (id) ON DELETE Restrict ON UPDATE No Action;

ALTER TABLE REGISTRO_IE ADD CONSTRAINT FK_REGISTRO_IE_VEHICULO
	FOREIGN KEY (vehiculo) REFERENCES VEHICULO (id) ON DELETE No Action ON UPDATE No Action;

ALTER TABLE RESERVA ADD CONSTRAINT FK_RESERVA_ESPACIOPARQUEO
	FOREIGN KEY (espacioparqueo) REFERENCES ESPACIOPARQUEO (id) ON DELETE Restrict ON UPDATE Cascade;

ALTER TABLE RESERVA ADD CONSTRAINT FK_RESERVA_USUARIO
	FOREIGN KEY (usuario) REFERENCES USUARIO (id) ON DELETE Restrict ON UPDATE No Action;

ALTER TABLE ROL ADD CONSTRAINT FK_ROL_PERFIL_USUARIO
	FOREIGN KEY (perfil) REFERENCES PERFIL_USUARIO (id) ON DELETE Restrict ON UPDATE Cascade;

ALTER TABLE SUPERVISION ADD CONSTRAINT FK_SUPERVISION_USUARIO
	FOREIGN KEY (usuario) REFERENCES USUARIO (id) ON DELETE Restrict ON UPDATE No Action;

/* Create Table Comments, Sequences for Autonumber Columns */

COMMENT ON TABLE CONFIGURACION
	IS 'Guarda las configuraciones realizadas por los usuarios autorizados(Administrador)';

COMMENT ON COLUMN CONFIGURACION.caducidadcontrasena
	IS 'MÁXIMO DE DÍAS DE VIDA DE LAS CONTRASEÑAS DE LOS USUARIOS';

COMMENT ON COLUMN CONFIGURACION.maxadmin
	IS 'NUMERO MÁXIMO DE ADMINISTRADORES DEL SISTEMA';

COMMENT ON TABLE ESPACIOPARQUEO
	IS 'Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a a universidad';

COMMENT ON COLUMN ESPACIOPARQUEO.ubicacion
	IS 'Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a a universidad';

COMMENT ON COLUMN ESPACIOPARQUEO.ocupado
	IS 'TRIGGER';

 

COMMENT ON TABLE INFORME
	IS 'Guarda los datos procesados de los reportes consultados a la base de datos para ser consultados porteriormente por usuarios o sistemas externos autorizados.  Que contiene un (informe/reporte)?  	- Espacios disponibles/mes 	- % planificación de reservas/dias/semanas/mes 	- Frecuencia de entrada y salida de vehiculos/mes 	- % reservas exitosas/mes 	- % reservas fallidas/mes 	- % reconocimientos exitoso/mes  (ingresos) 	- % reconocimiento fallido/mes 	- Cantidad de placas reconocidas/mes 	- Cantidad de ingresos /dia/semana/mes 	- Cantidad de salidas /dia/semana/mes';

COMMENT ON COLUMN INFORME.usuario
	IS 'El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?';

COMMENT ON COLUMN INFORME.regservicio
	IS 'El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?';

COMMENT ON COLUMN INFORME.disponibilidad
	IS '% porcentaje de espacios disponibles en el rango de fecha DIA/SEMANA/MES';

COMMENT ON COLUMN INFORME.reservasok
	IS '% RESERVAS EXITOSAS DIA/SEMANA/MES';

COMMENT ON COLUMN INFORME.reservasfail
	IS '% RESERVAS FALLIDO DIA/SEMANA/MES';

COMMENT ON COLUMN INFORME.recogok
	IS '% RECONOCIMIENTO EXITOSO DIA/SEMANA/MES';

COMMENT ON COLUMN INFORME.recogfail
	IS '% RECONOCIMIENTO FALLIDO DIA/SEMANA/MES';

COMMENT ON COLUMN INFORME.recogtotal
	IS 'CANTIDAD TOTAL DE PLACAS RECONOCIDAS  DIA/SEMANA/MES';

COMMENT ON COLUMN INFORME.ingresostotal
	IS 'CANTIDAD DE ENTRADAS DIA/SEMANA/MES';

COMMENT ON COLUMN INFORME.egresostotal
	IS 'CANTIDAD DE SALIDAS DIA/SEMANA/MES';

 

COMMENT ON TABLE PERFIL_USUARIO
	IS 'Guarda los perfiles de usuario definidos en los requisitos. Es una tabla catálogo.';

 

COMMENT ON TABLE PERMISO
	IS 'Tabla intermedia que registra los permisos dados por usuarios supervisores a los usuarios que salen con un vehiculo diferente del parqueadero';

COMMENT ON TABLE RECURSO
	IS 'Guarda los dispositivos que capturan o muestran la información del ingreso o egreso de los usuarios con sus vehículos.  	- El campo TIPO registra el tipode recurso(dispositivo), puede ser un televisor o monitor o cámara. 	- El campo IP registra la dirección asignada dentro de la red. 	- El campo PUERTO registra el puerto por el que el dispositivo se está comunicando. 	- El campo MAC registra la mac única del dispositivo. 	- El campo PROTOCOLO registra el protocolo por el cuál el recurso se comunica (TCP - UDP A FUTURO PUEDEN SER MÁS PROTOCOLOS). 	- El campo FECHA_REGISTRO registra el momento de tiempo en el que se agregó el recurso al sistema. 	- El campo URL_FABRICANTE registra la dirección web del fabricante del recurso. 	- El campo ESTADO registra si el recurso se encuentra activo o inactivo.';

COMMENT ON COLUMN RECURSO.tipo
	IS 'El tipo de recurso(dispositivo), puede ser un televisor o monitor o cámara';

COMMENT ON COLUMN RECURSO.ip
	IS 'LA DIRECCIÓN ASIGNADA';

COMMENT ON COLUMN RECURSO.puerto
	IS 'MÁXIMO HASTA 65535';

COMMENT ON COLUMN RECURSO.mac
	IS 'MEDIA ACCESS CONTROL';

COMMENT ON COLUMN RECURSO.protocolo
	IS '	- TCP 	- UDP  A FUTURO PUEDEN SER MÁS PROTOCOLOS';

COMMENT ON COLUMN RECURSO.estado
	IS 'ACTIVO - TRUE INACTIVO - FALSE';

 

COMMENT ON TABLE REG_SERVICIO
	IS 'Guarda la sesión del sistema externo y la fecha en la que se creó la sessión.  	- El campo ID_SESSION registra un número que identifica la sesión en que el sistema externo realizará consultas a la base de datos 	- El campo FECHA_SESSION  registra el momento en el que el número de sesión fué creado.';

 

COMMENT ON TABLE REGISTRO_IE
	IS 'Guarda el momento en que los recursos(dispositivos de captura) registran el ingreso o egreso al parqueadero de lo usuarios y vehículos.';

 

COMMENT ON TABLE RESERVA
	IS 'Guarda las reservas que han sido solicitadas por los usuarios. 	- El campo FECHA_SOLICITUD registra el momento de tiempo en que se solicitó la reserva. 	- El campo ESTADO registra si la reserva se encuentra activa o inactiva. 	- El campo NUM_ESTACION registra el número del estacionamiento reservado para el vehiculo. 	- El campo FECHA_RESERVA registra el momento en el que se espera que el vehiculo ingrese al parqueadero de la universidad para ocupar el espacio reservado. 	- El campo FECHA_FIN registra el momento en que finaliza la reserva. 	- El campo CANCELADA registra si la reserva fue cancelada o no.';

COMMENT ON COLUMN RESERVA.estado
	IS '	- RESERVADA - FALSE 	- COMPLETADA - TRUE';

COMMENT ON COLUMN RESERVA.fechafin
	IS 'Fecha en que finaliza la reserva, sin importar si fue completada o cancelada';

COMMENT ON COLUMN RESERVA.cancelada
	IS '	- CANCELADA - TRUE 	- NO CANCELADA - FALSE';

 

COMMENT ON TABLE ROL
	IS 'Guarda los roles asociados a los perfiles de usuario. Es una tabla catálogo.';

 

COMMENT ON TABLE SISTEMA_EXTERNO
	IS 'Guarda los datos de acceso del sistema externo al sistema.';

 

COMMENT ON TABLE SUPERVISION
	IS 'Guarda los mensajes que envían los usuarios que posean el rol de supervisión.  	- El campo ESTADO indica si el mensaje se encuentra activo o inactivo. 	- el campo FECHA indica el momento de tiempo en que el mensaje fué creado o modificado. 	- El campo TIPO indica si el mensaje es una alerta o un aviso.';

COMMENT ON COLUMN SUPERVISION.estado
	IS 'ACTIVO -TRUE INACTIVO - FALSE';

COMMENT ON COLUMN SUPERVISION.tipo
	IS '	- ALERTA - TRUE 	- AVISOS - FLASE';

 

COMMENT ON TABLE UBICACION
	IS 'Table catálogo que guarda la ubicacion de la sede, extenciones, seccionales y otros puntos estratègicos que pertenecen a la universidad de Cundinamarca';

 

COMMENT ON TABLE USUARIO
	IS 'Guarda los usuarios registrados en el sistema.  	- El campo NOMBRES registra los nombres del usuario 	- El campo APELLIDOS registra los apellidos del usuario 	- El campo TIPOID registra el tipo de documento del usuario. 	- El campo NUMID registra el número de documento del usuario. 	- El campo FECHAREG guarda la fecha en que se registró el usuario. 	- El campo ESTADO registra si el usuario se encuentra activo o inactivo. 	- El campo ROL registra el rol que tiene asignado el usuario';

 

COMMENT ON TABLE VEHICULO
	IS 'Guarda los datos de los vehículos que han ingresado al parqueadero.';

COMMENT ON COLUMN VEHICULO.clase
	IS 'Que clase de vehiculo es (Automovil, moticleta, autobus, etc)';