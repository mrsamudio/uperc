--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.15
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'LATIN1';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- Name: configuracion; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.configuracion (
    id integer NOT NULL,
    intentosfallidos integer NOT NULL,
    caducidadcontrasena integer NOT NULL,
    maxadmin integer NOT NULL,
    fechaguardado timestamp without time zone NOT NULL,
    usuario integer NOT NULL
);


ALTER TABLE public.configuracion OWNER TO msamudio;

--
-- Name: TABLE configuracion; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.configuracion IS 'Guarda las configuraciones realizadas por los usuarios autorizados(Administrador)';


--
-- Name: COLUMN configuracion.caducidadcontrasena; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.configuracion.caducidadcontrasena IS 'MÁXIMO DE DÍAS DE VIDA DE LAS CONTRASEÑAS DE LOS USUARIOS';


--
-- Name: COLUMN configuracion.maxadmin; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.configuracion.maxadmin IS 'NUMERO MÁXIMO DE ADMINISTRADORES DEL SISTEMA';


--
-- Name: COLUMN configuracion.usuario; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.configuracion.usuario IS 'USUARIO QUE GUARDÓ LA CONFIGURACIÓN';


--
-- Name: configuracion_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.configuracion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.configuracion_id_seq OWNER TO msamudio;

--
-- Name: configuracion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.configuracion_id_seq OWNED BY public.configuracion.id;


--
-- Name: configuracion_usuario_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.configuracion_usuario_seq
--     START WITH 1
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;


-- ALTER TABLE public.configuracion_usuario_seq OWNER TO msamudio;

--
-- Name: configuracion_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.configuracion_usuario_seq OWNED BY public.configuracion.usuario;


--
-- Name: espacioparqueo; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.espacioparqueo (
    id integer NOT NULL,
    nombre character varying(50),
    ubicacion smallint NOT NULL,
    ocupado boolean
);


ALTER TABLE public.espacioparqueo OWNER TO msamudio;

--
-- Name: TABLE espacioparqueo; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.espacioparqueo IS 'Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a la universidad';


--
-- Name: COLUMN espacioparqueo.ubicacion; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.espacioparqueo.ubicacion IS 'Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a la universidad';


--
-- Name: COLUMN espacioparqueo.ocupado; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.espacioparqueo.ocupado IS 'TRIGGER';


--
-- Name: espacioparqueo_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.espacioparqueo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.espacioparqueo_id_seq OWNER TO msamudio;

--
-- Name: espacioparqueo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.espacioparqueo_id_seq OWNED BY public.espacioparqueo.id;


--
-- Name: espacioparqueo_ubicacion_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.espacioparqueo_ubicacion_seq
--     START WITH 1
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;


-- ALTER TABLE public.espacioparqueo_ubicacion_seq OWNER TO msamudio;

--
-- Name: espacioparqueo_ubicacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.espacioparqueo_ubicacion_seq OWNED BY public.espacioparqueo.ubicacion;


--
-- Name: informe; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.informe (
    id bigint NOT NULL,
    usuario bigint,
    regservicio bigint,
    fechagenerado timestamp without time zone NOT NULL,
    fechainicio date NOT NULL,
    fechafin date NOT NULL,
    disponibilidad numeric,
    reservasok numeric,
    reservasfail numeric,
    recogok numeric,
    recogfail numeric,
    recogtotal integer,
    ingresostotal integer,
    egresostotal integer
);


ALTER TABLE public.informe OWNER TO msamudio;

--
-- Name: TABLE informe; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.informe IS 'Guarda los datos procesados de los reportes consultados a la base de datos para ser consultados porteriormente por usuarios o sistemas externos autorizados.  Que contiene un (informe/reporte)?  	- Espacios disponibles/mes 	- % planificación de reservas/días/semanas/mes 	- Frecuencia de entrada y salida de vehiculos/mes 	- % reservas exitosas/mes 	- % reservas fallidas/mes 	- % reconocimientos exitoso/mes  (ingresos) 	- % reconocimiento fallido/mes 	- Cantidad de placas reconocidas/mes 	- Cantidad de ingresos /día/semana/mes 	- Cantidad de salidas /día/semana/mes';


--
-- Name: COLUMN informe.usuario; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.usuario IS 'El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?';


--
-- Name: COLUMN informe.regservicio; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.regservicio IS 'El usuario administrador que genera el informe? o el sistema genera automáticamente el informe?';


--
-- Name: COLUMN informe.disponibilidad; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.disponibilidad IS '% porcentaje de espacios disponibles en el rango de fecha DÍA/SEMANA/MES';


--
-- Name: COLUMN informe.reservasok; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.reservasok IS '% RESERVAS EXITOSAS DÍA/SEMANA/MES';


--
-- Name: COLUMN informe.reservasfail; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.reservasfail IS '% RESERVAS FALLIDO DÍA/SEMANA/MES';


--
-- Name: COLUMN informe.recogok; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.recogok IS '% RECONOCIMIENTO EXITOSO DÍA/SEMANA/MES';


--
-- Name: COLUMN informe.recogfail; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.recogfail IS '% RECONOCIMIENTO FALLIDO DÍA/SEMANA/MES';


--
-- Name: COLUMN informe.recogtotal; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.recogtotal IS 'CANTIDAD TOTAL DE PLACAS RECONOCIDAS  DÍA/SEMANA/MES';


--
-- Name: COLUMN informe.ingresostotal; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.ingresostotal IS 'CANTIDAD DE ENTRADAS DÍA/SEMANA/MES';


--
-- Name: COLUMN informe.egresostotal; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.egresostotal IS 'CANTIDAD DE SALIDAS DÍA/SEMANA/MES';


--
-- Name: informe_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.informe_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.informe_id_seq OWNER TO msamudio;

--
-- Name: informe_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.informe_id_seq OWNED BY public.informe.id;


--
-- Name: perfilusuario; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.perfilusuario (
    id smallint NOT NULL,
    nombre character varying(50) NOT NULL,
    descripcion character varying(50)
);


ALTER TABLE public.perfilusuario OWNER TO msamudio;

--
-- Name: TABLE perfilusuario; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.perfilusuario IS 'Guarda los perfiles de usuario definidos en los requisitos. Es una tabla catálogo.';


--
-- Name: perfilusuario_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.perfilusuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.perfilusuario_id_seq OWNER TO msamudio;

--
-- Name: perfilusuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.perfilusuario_id_seq OWNED BY public.perfilusuario.id;


--
-- Name: permiso; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.permiso (
    id bigint NOT NULL,
    usuario bigint NOT NULL,
    registroie bigint NOT NULL
);


ALTER TABLE public.permiso OWNER TO msamudio;

--
-- Name: permiso_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.permiso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permiso_id_seq OWNER TO msamudio;

--
-- Name: permiso_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.permiso_id_seq OWNED BY public.permiso.id;


--
-- Name: permiso_usuario_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.permiso_usuario_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.permiso_usuario_seq OWNER TO msamudio;

--
-- Name: permiso_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.permiso_usuario_seq OWNED BY public.permiso.usuario;


--
-- Name: recurso; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.recurso (
    id integer NOT NULL,
    nombre character varying(50) NOT NULL,
    marca character varying(50) NOT NULL,
    tipo character varying(50),
    ip inet,
    puerto character varying(5),
    mac macaddr NOT NULL,
    protocolo character varying(10),
    fecharegistro timestamp without time zone NOT NULL,
    urlfabricante character varying(2000),
    estado boolean NOT NULL
);


ALTER TABLE public.recurso OWNER TO msamudio;

--
-- Name: TABLE recurso; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.recurso IS 'Guarda los dispositivos que capturan o muestran la información del ingreso o egreso de los usuarios con sus vehículos.  	- El campo TIPO registra el tipode recurso(dispositivo), puede ser un televisor o monitor o cámara. 	- El campo IP registra la dirección asignada dentro de la red. 	- El campo PUERTO registra el puerto por el que el dispositivo se está comunicando. 	- El campo MAC registra la mac única del dispositivo. 	- El campo PROTOCOLO registra el protocolo por el cuál el recurso se comunica (TCP - UDP A FUTURO PUEDEN SER MÁS PROTOCOLOS). 	- El campo fecharegistro registra el momento de tiempo en el que se agregó el recurso al sistema. 	- El campo urlfabricante registra la dirección web del fabricante del recurso. 	- El campo ESTADO registra si el recurso se encuentra activo o inactivo.';


--
-- Name: COLUMN recurso.tipo; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.recurso.tipo IS 'El tipo de recurso(dispositivo), puede ser un televisor o monitor o cámara';


--
-- Name: COLUMN recurso.ip; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.recurso.ip IS 'LA DIRECCIÓN ASIGNADA';


--
-- Name: COLUMN recurso.puerto; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.recurso.puerto IS 'MÁXIMO HASTA 65535';


--
-- Name: COLUMN recurso.mac; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.recurso.mac IS 'MEDIA ACCESS CONTROL';


--
-- Name: COLUMN recurso.protocolo; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.recurso.protocolo IS '	- TCP 	- UDP  A FUTURO PUEDEN SER MÁS PROTOCOLOS';


--
-- Name: COLUMN recurso.estado; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.recurso.estado IS 'ACTIVO - TRUE INACTIVO - FALSE';


--
-- Name: recurso_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.recurso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recurso_id_seq OWNER TO msamudio;

--
-- Name: recurso_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.recurso_id_seq OWNED BY public.recurso.id;


--
-- Name: regservicio; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.regservicio (
    id bigint NOT NULL,
    idsession uuid NOT NULL,
    sistemaexterno smallint NOT NULL,
    fechasession timestamp with time zone NOT NULL
);


ALTER TABLE public.regservicio OWNER TO msamudio;

--
-- Name: TABLE regservicio; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.regservicio IS 'Guarda la sesión del sistema externo y la fecha en la que se creó la sessión.  	- El campo idsession registra un número que identifica la sesión en que el sistema externo realizará consultas a la base de datos 	- El campo fechasession  registra el momento en el que el número de sesión fué creado.';


--
-- Name: regservicio_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.regservicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.regservicio_id_seq OWNER TO msamudio;

--
-- Name: regservicio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.regservicio_id_seq OWNED BY public.regservicio.id;


--
-- Name: regservicio_sistemaexterno_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.regservicio_sistemaexterno_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.regservicio_sistemaexterno_seq OWNER TO msamudio;

--
-- Name: regservicio_sistemaexterno_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.regservicio_sistemaexterno_seq OWNED BY public.regservicio.sistemaexterno;


--
-- Name: registroie; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.registroie (
    id bigint NOT NULL,
    fechaingreso timestamp without time zone NOT NULL,
    fechaegreso timestamp without time zone,
    recurso integer NOT NULL,
    vehiculo integer NOT NULL,
    usuarioingreso bigint NOT NULL,
    usuarioegreso bigint,
    permiso bigint,
    ticketid uuid NOT NULL
);


ALTER TABLE public.registroie OWNER TO msamudio;

--
-- Name: TABLE registroie; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.registroie IS 'Guarda el momento en que los recursos(dispositivos de captura) registran el ingreso o egreso al parqueadero de lo usuarios y vehículos.';


--
-- Name: registroie_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.registroie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.registroie_id_seq OWNER TO msamudio;

--
-- Name: registroie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.registroie_id_seq OWNED BY public.registroie.id;


--
-- Name: registroie_recurso_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.registroie_recurso_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.registroie_recurso_seq OWNER TO msamudio;

--
-- Name: registroie_recurso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.registroie_recurso_seq OWNED BY public.registroie.recurso;


--
-- Name: registroie_usuarioingreso_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.registroie_usuarioingreso_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.registroie_usuarioingreso_seq OWNER TO msamudio;

--
-- Name: registroie_usuarioingreso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.registroie_usuarioingreso_seq OWNED BY public.registroie.usuarioingreso;


--
-- Name: registroie_vehiculo_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.registroie_vehiculo_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.registroie_vehiculo_seq OWNER TO msamudio;

--
-- Name: registroie_vehiculo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

--  ALTER SEQUENCE public.registroie_vehiculo_seq OWNED BY public.registroie.vehiculo;


--
-- Name: reserva; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.reserva (
    id bigint NOT NULL,
    fechasolicitud timestamp without time zone NOT NULL,
    estado boolean NOT NULL,
    espacioparqueo integer NOT NULL,
    fechareserva timestamp without time zone NOT NULL,
    fechafin timestamp without time zone,
    cancelada boolean NOT NULL,
    usuario integer NOT NULL
);


ALTER TABLE public.reserva OWNER TO msamudio;

--
-- Name: TABLE reserva; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.reserva IS 'Guarda las reservas que han sido solicitadas por los usuarios. 	- El campo fechasolicitud registra el momento de tiempo en que se solicitó la reserva. 	- El campo ESTADO registra si la reserva se encuentra activa o inactiva. 	- El campo NUM_ESTACION registra el número del estacionamiento reservado para el vehículo. 	- El campo fechareserva registra el momento en el que se espera que el vehículo ingrese al parqueadero de la universidad para ocupar el espacio reservado. 	- El campo fechafin registra el momento en que finaliza la reserva. 	- El campo CANCELADA registra si la reserva fue cancelada o no.';


--
-- Name: COLUMN reserva.estado; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.reserva.estado IS '	- RESERVADA - FALSE 	- COMPLETADA - TRUE';


--
-- Name: COLUMN reserva.fechafin; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.reserva.fechafin IS 'Fecha en que finaliza la reserva, sin importar si fue completada o cancelada';


--
-- Name: COLUMN reserva.cancelada; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.reserva.cancelada IS '	- CANCELADA - TRUE 	- NO CANCELADA - FALSE';


--
-- Name: reserva_espacioparqueo_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.reserva_espacioparqueo_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.reserva_espacioparqueo_seq OWNER TO msamudio;

--
-- Name: reserva_espacioparqueo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.reserva_espacioparqueo_seq OWNED BY public.reserva.espacioparqueo;


--
-- Name: reserva_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.reserva_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reserva_id_seq OWNER TO msamudio;

--
-- Name: reserva_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.reserva_id_seq OWNED BY public.reserva.id;


--
-- Name: reserva_usuario_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.reserva_usuario_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.reserva_usuario_seq OWNER TO msamudio;

--
-- Name: reserva_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.reserva_usuario_seq OWNED BY public.reserva.usuario;


--
-- Name: rol; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.rol (
    id smallint NOT NULL,
    nombre character varying(50),
    descripcion character varying(50),
    perfil smallint NOT NULL
);


ALTER TABLE public.rol OWNER TO msamudio;

--
-- Name: TABLE rol; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.rol IS 'Guarda los roles asociados a los perfiles de usuario. Es una tabla catálogo.';


--
-- Name: rol_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.rol_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rol_id_seq OWNER TO msamudio;

--
-- Name: rol_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.rol_id_seq OWNED BY public.rol.id;


--
-- Name: rol_perfil_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--
-- 
-- CREATE SEQUENCE public.rol_perfil_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.rol_perfil_seq OWNER TO msamudio;

--
-- Name: rol_perfil_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.rol_perfil_seq OWNED BY public.rol.perfil;


--
-- Name: sistemaexterno; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.sistemaexterno (
    id smallint NOT NULL,
    ip inet NOT NULL,
    nombre character varying(50) NOT NULL,
    contrasena character varying(500) NOT NULL
);


ALTER TABLE public.sistemaexterno OWNER TO msamudio;

--
-- Name: TABLE sistemaexterno; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.sistemaexterno IS 'Guarda los datos de acceso del sistema externo al sistema.';


--
-- Name: sistemaexterno_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.sistemaexterno_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sistemaexterno_id_seq OWNER TO msamudio;

--
-- Name: sistemaexterno_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.sistemaexterno_id_seq OWNED BY public.sistemaexterno.id;


--
-- Name: supervision; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.supervision (
    id bigint NOT NULL,
    mensaje character varying(50) NOT NULL,
    estado boolean NOT NULL,
    fecha timestamp without time zone NOT NULL,
    tipo boolean NOT NULL,
    usuario integer NOT NULL
);


ALTER TABLE public.supervision OWNER TO msamudio;

--
-- Name: TABLE supervision; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.supervision IS 'Guarda los mensajes que envían los usuarios que posean el rol de supervisión.  	- El campo ESTADO indica si el mensaje se encuentra activo o inactivo. 	- el campo FECHA indica el momento de tiempo en que el mensaje fué creado o modificado. 	- El campo TIPO indica si el mensaje es una alerta o un aviso.';


--
-- Name: COLUMN supervision.estado; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.supervision.estado IS 'ACTIVO -TRUE INACTIVO - FALSE';


--
-- Name: COLUMN supervision.tipo; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.supervision.tipo IS '	- ALERTA - TRUE 	- AVISOS - FLASE';


--
-- Name: supervision_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.supervision_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.supervision_id_seq OWNER TO msamudio;

--
-- Name: supervision_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.supervision_id_seq OWNED BY public.supervision.id;


--
-- Name: supervision_usuario_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

-- CREATE SEQUENCE public.supervision_usuario_seq
    -- START WITH 1
    -- INCREMENT BY 1
    -- NO MINVALUE
    -- NO MAXVALUE
    -- CACHE 1;


-- ALTER TABLE public.supervision_usuario_seq OWNER TO msamudio;

--
-- Name: supervision_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

-- ALTER SEQUENCE public.supervision_usuario_seq OWNED BY public.supervision.usuario;


--
-- Name: ubicacion; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.ubicacion (
    id smallint NOT NULL,
    nombre character varying(50) NOT NULL,
    direccion character varying(50) NOT NULL,
    coordenadas point NOT NULL,
    telefono character varying(50) NOT NULL
);


ALTER TABLE public.ubicacion OWNER TO msamudio;

--
-- Name: TABLE ubicacion; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.ubicacion IS 'Table catálogo que guarda la ubicacion de la sede, extenciones, seccionales y otros puntos estratégicos que pertenecen a la universidad de Cundinamarca';


--
-- Name: ubicacion_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.ubicacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ubicacion_id_seq OWNER TO msamudio;

--
-- Name: ubicacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.ubicacion_id_seq OWNED BY public.ubicacion.id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    nombres character varying(50),
    apellidos character varying(50),
    tipoid character varying(50) NOT NULL,
    numid character varying(50) NOT NULL,
    contrasena character varying(50) NOT NULL,
    correo character varying(50) NOT NULL,
    fechanac date,
    fechareg character varying(50),
    estado boolean NOT NULL,
    rol integer NOT NULL
);


ALTER TABLE public.usuario OWNER TO msamudio;

--
-- Name: TABLE usuario; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.usuario IS 'Guarda los usuarios registrados en el sistema.  	- El campo NOMBRES registra los nombres del usuario 	- El campo APELLIDOS registra los apellidos del usuario 	- El campo TIPOID registra el tipo de documento del usuario. 	- El campo NUMID registra el número de documento del usuario. 	- El campo FECHAREG guarda la fecha en que se registró el usuario. 	- El campo ESTADO registra si el usuario se encuentra activo o inactivo. 	- El campo ROL registra el rol que tiene asignado el usuario';


--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO msamudio;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: vehiculo; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.vehiculo (
    id integer NOT NULL,
    placa character varying(50),
    marca character varying(50),
    color character varying(50),
    modelo character varying(50),
    clase character varying(50),
    tiposervicio character varying(50)
);


ALTER TABLE public.vehiculo OWNER TO msamudio;

--
-- Name: TABLE vehiculo; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.vehiculo IS 'Guarda los datos de los vehículos que han ingresado al parqueadero.';


--
-- Name: COLUMN vehiculo.clase; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.vehiculo.clase IS 'Que clase de vehículo es (Automovil, moticleta, autobus, etc)';


--
-- Name: vehiculo_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.vehiculo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vehiculo_id_seq OWNER TO msamudio;

--
-- Name: vehiculo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.vehiculo_id_seq OWNED BY public.vehiculo.id;


--
-- Name: configuracion id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.configuracion ALTER COLUMN id SET DEFAULT nextval('public.configuracion_id_seq'::regclass);


--
-- Name: configuracion usuario; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.configuracion ALTER COLUMN usuario SET DEFAULT nextval('public.configuracion_usuario_seq'::regclass);


--
-- Name: espacioparqueo id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.espacioparqueo ALTER COLUMN id SET DEFAULT nextval('public.espacioparqueo_id_seq'::regclass);


--
-- Name: espacioparqueo ubicacion; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.espacioparqueo ALTER COLUMN ubicacion SET DEFAULT nextval('public.espacioparqueo_ubicacion_seq'::regclass);


--
-- Name: informe id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.informe ALTER COLUMN id SET DEFAULT nextval('public.informe_id_seq'::regclass);


--
-- Name: perfilusuario id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.perfilusuario ALTER COLUMN id SET DEFAULT nextval('public.perfilusuario_id_seq'::regclass);


--
-- Name: permiso id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.permiso ALTER COLUMN id SET DEFAULT nextval('public.permiso_id_seq'::regclass);


--
-- Name: permiso usuario; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.permiso ALTER COLUMN usuario SET DEFAULT nextval('public.permiso_usuario_seq'::regclass);


--
-- Name: recurso id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.recurso ALTER COLUMN id SET DEFAULT nextval('public.recurso_id_seq'::regclass);


--
-- Name: regservicio id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.regservicio ALTER COLUMN id SET DEFAULT nextval('public.regservicio_id_seq'::regclass);


--
-- Name: regservicio sistemaexterno; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.regservicio ALTER COLUMN sistemaexterno SET DEFAULT nextval('public.regservicio_sistemaexterno_seq'::regclass);


--
-- Name: registroie id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie ALTER COLUMN id SET DEFAULT nextval('public.registroie_id_seq'::regclass);


--
-- Name: registroie recurso; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.registroie ALTER COLUMN recurso SET DEFAULT nextval('public.registroie_recurso_seq'::regclass);


--
-- Name: registroie vehiculo; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.registroie ALTER COLUMN vehiculo SET DEFAULT nextval('public.registroie_vehiculo_seq'::regclass);


--
-- Name: registroie usuarioingreso; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.registroie ALTER COLUMN usuarioingreso SET DEFAULT nextval('public.registroie_usuarioingreso_seq'::regclass);


--
-- Name: reserva id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reserva ALTER COLUMN id SET DEFAULT nextval('public.reserva_id_seq'::regclass);


--
-- Name: reserva espacioparqueo; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.reserva ALTER COLUMN espacioparqueo SET DEFAULT nextval('public.reserva_espacioparqueo_seq'::regclass);


--
-- Name: reserva usuario; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.reserva ALTER COLUMN usuario SET DEFAULT nextval('public.reserva_usuario_seq'::regclass);


--
-- Name: rol id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.rol ALTER COLUMN id SET DEFAULT nextval('public.rol_id_seq'::regclass);


--
-- Name: rol perfil; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.rol ALTER COLUMN perfil SET DEFAULT nextval('public.rol_perfil_seq'::regclass);


--
-- Name: sistemaexterno id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.sistemaexterno ALTER COLUMN id SET DEFAULT nextval('public.sistemaexterno_id_seq'::regclass);


--
-- Name: supervision id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.supervision ALTER COLUMN id SET DEFAULT nextval('public.supervision_id_seq'::regclass);


--
-- Name: supervision usuario; Type: DEFAULT; Schema: public; Owner: msamudio
--

-- ALTER TABLE ONLY public.supervision ALTER COLUMN usuario SET DEFAULT nextval('public.supervision_usuario_seq'::regclass);


--
-- Name: ubicacion id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.ubicacion ALTER COLUMN id SET DEFAULT nextval('public.ubicacion_id_seq'::regclass);


--
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Name: vehiculo id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.vehiculo ALTER COLUMN id SET DEFAULT nextval('public.vehiculo_id_seq'::regclass);


--
-- Data for Name: configuracion; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.configuracion (id, intentosfallidos, caducidadcontrasena, maxadmin, fechaguardado, usuario) FROM stdin;
1	3	28	1	2021-01-19 23:08:28.347458	1
\.


--
-- Data for Name: espacioparqueo; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.espacioparqueo (id, nombre, ubicacion, ocupado) FROM stdin;
2	A2	4	f
3	A3	4	f
4	A4	4	f
5	A5	4	f
6	A6	4	f
7	A7	4	f
8	A8	4	f
9	A9	4	f
10	A10	4	f
11	A11	4	f
12	B1	4	f
13	B2	4	f
14	B3	4	f
15	B4	4	f
16	B5	4	f
17	B6	4	f
18	B7	4	f
19	B8	4	f
20	B9	4	f
21	B10	4	f
22	B11	4	f
23	B12	4	f
24	B13	4	f
25	B14	4	f
26	B15	4	f
27	B16	4	f
28	C1	4	f
29	C2	4	f
30	C3	4	f
31	C4	4	f
32	C5	4	f
33	C6	4	f
34	C7	4	f
35	C8	4	f
36	C9	4	f
37	C10	4	f
38	C11	4	f
39	C12	4	f
40	C13	4	f
41	C14	4	f
42	C15	4	f
43	C16	4	f
44	C17	4	f
45	D1	4	f
46	D2	4	f
47	D3	4	f
48	D4	4	f
49	D5	4	f
50	D6	4	f
51	D7	4	f
52	D8	4	f
53	D9	4	f
54	D10	4	f
55	D11	4	f
56	D12	4	f
57	D13	4	f
58	D14	4	f
59	D15	4	f
60	D16	4	f
61	D17	4	f
62	D18	4	f
63	D19	4	f
64	D20	4	f
65	D21	4	f
66	D22	4	f
67	D23	4	f
68	D24	4	f
69	E1	4	f
70	E2	4	f
71	E3	4	f
72	E4	4	f
73	E5	4	f
74	E6	4	f
75	E7	4	f
76	E8	4	f
77	E9	4	f
78	E10	4	f
79	E11	4	f
80	E12	4	f
81	E13	4	f
82	E14	4	f
83	E15	4	f
84	E16	4	f
85	E17	4	f
86	E18	4	f
87	E19	4	f
88	E20	4	f
89	E21	4	f
90	E22	4	f
91	E23	4	f
92	E24	4	f
1	A1	4	f
\.


--
-- Data for Name: informe; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.informe (id, usuario, regservicio, fechagenerado, fechainicio, fechafin, disponibilidad, reservasok, reservasfail, recogok, recogfail, recogtotal, ingresostotal, egresostotal) FROM stdin;
\.


--
-- Data for Name: perfilusuario; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.perfilusuario (id, nombre, descripcion) FROM stdin;
1	Administrador	Descripcíon breve
2	Supervisor	Descripcíon breve
3	Usuario general	Descripcíon breve
4	Visitante	Descripcíon breve
\.


--
-- Data for Name: permiso; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.permiso (id, usuario) FROM stdin;
\.


--
-- Data for Name: recurso; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.recurso (id, nombre, marca, tipo, ip, puerto, mac, protocolo, fecharegistro, urlfabricante, estado) FROM stdin;
1	Entrada 1	kvision	CamaraIP	192.168.100.132	64001	08:00:2b:01:02:03	TCP	2021-01-19 23:17:10.825593	http://127.0.0.1/CamaraIP.html	f
\.


--
-- Data for Name: regservicio; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.regservicio (id, idsession, sistemaexterno, fechasession) FROM stdin;
\.


--
-- Data for Name: registroie; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.registroie (id, fechaingreso, fechaegreso, recurso, vehiculo, usuarioingreso, usuarioegreso, permiso, ticketid) FROM stdin;
\.


--
-- Data for Name: reserva; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.reserva (id, fechasolicitud, estado, espacioparqueo, fechareserva, fechafin, cancelada, usuario) FROM stdin;
\.


--
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.rol (id, nombre, descripcion, perfil) FROM stdin;
1	Docente	Descripcíon breve	3
2	Graduado	Descripcíon breve	3
3	Estudiante	Descripcíon breve	3
4	Administrativo	Descripcíon breve	1
5	Administrador	Descripcíon breve	1
6	Supervisión	Descripcíon breve	2
7	Visitante	Descripcíon breve	4
\.


--
-- Data for Name: sistemaexterno; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.sistemaexterno (id, ip, nombre, contrasena) FROM stdin;
1	192.168.100.0/24	FinancieroSystem	f12345
2	127.0.0.1	ProyectoEspecial	p12345
\.


--
-- Data for Name: supervision; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.supervision (id, mensaje, estado, fecha, tipo, usuario) FROM stdin;
\.


--
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.ubicacion (id, nombre, direccion, coordenadas, telefono) FROM stdin;
1	Fusagasugá	Diagonal 18 No. 20-29	(4.33405240000000003,-74.3692551000000037)	18281483
2	Girardot	Carrera 19 Nº 24 - 209	(4.30652610000000013,-74.8062448000000018)	18335071
3	Ubaté	Calle 6 Nº 9 - 80	(5.30876069999999967,-73.8172500000000014)	18553055
4	Chía	Autopista Chía - Cajicá | Sector "El Cuarenta	(4.87371830000000017,-74.0376925999999997)	18553055
5	Chocontá	Carrera 3 Nº 5 -71	(5.14391670000000012,-73.6842201000000045)	18562520
6	Facatativá	Calle 14 con Avenida 15	(4.82912609999999987,-74.354577699999993)	18920706
7	Soacha	Diagonal 9 No. 4B-85	(4.57841529999999963,-74.2224348999999961)	17219220
8	Zipaquirá	Carrera 7 No. 1-31	(5.02169270000000001,-74.0056418000000065)	18515792
9	Oficinas Bogotá	Carrera 20 No. 39-32 Teusaquillo	(4.62797859999999961,-74.0736311000000001)	17448180
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) FROM stdin;
1	Ad	Ministro	CC	00000001	minijtro	minijtro@localhost	1900-01-01	18/01/2021	t	5
15	El	docente	CC	00000002	docente	docente@localhost	1901-01-01	17/01/2021	t	1
16	El	Graduado	CC	00000004	graduado	graduado@localhost	1902-01-01	16/01/2021	t	2
17	La	estudiante	CC	000000465	estudiante	estudiante@localhost	1906-01-01	18/01/2021	t	3
18	Carlos	PeñarandaAdministrativo	CC	00000006	cpenaranda	cpenaranda@localhost	1923-01-01	07/12/2020	t	4
19	El	primer supervisor	CC	00000028	super2	super2@localhost	1900-01-01	18/01/2021	t	6
20	Segundo	supervisor	CC	00000007	super1	super1@localhost	1900-01-01	18/01/2021	t	6
21	Estudiante	Segundo	CC	00000008	estudiante2	estudiante2@localhost	1900-01-01	18/01/2021	t	3
22	Primer	visitante	CC	00300009	visitante	visitante@localhost	1997-01-01	18/01/2021	t	7
\.


--
-- Data for Name: vehiculo; Type: TABLE DATA; Schema: public; Owner: msamudio
--

COPY public.vehiculo (id, placa, marca, color, modelo, clase, tiposervicio) FROM stdin;
\.


--
-- Name: configuracion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.configuracion_id_seq', 1, true);


--
-- Name: configuracion_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.configuracion_usuario_seq', 1, true);


--
-- Name: espacioparqueo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.espacioparqueo_id_seq', 92, true);


--
-- Name: espacioparqueo_ubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.espacioparqueo_ubicacion_seq', 1, false);


--
-- Name: informe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.informe_id_seq', 1, false);


--
-- Name: perfilusuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.perfilusuario_id_seq', 4, true);


--
-- Name: permiso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.permiso_id_seq', 1, false);


--
-- Name: permiso_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.permiso_usuario_seq', 1, false);


--
-- Name: recurso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.recurso_id_seq', 1, true);


--
-- Name: regservicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.regservicio_id_seq', 1, false);


--
-- Name: regservicio_sistemaexterno_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.regservicio_sistemaexterno_seq', 1, false);


--
-- Name: registroie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.registroie_id_seq', 1, false);


--
-- Name: registroie_recurso_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.registroie_recurso_seq', 1, false);


--
-- Name: registroie_usuarioingreso_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.registroie_usuarioingreso_seq', 1, false);


--
-- Name: registroie_vehiculo_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.registroie_vehiculo_seq', 1, false);


--
-- Name: reserva_espacioparqueo_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.reserva_espacioparqueo_seq', 1, false);


--
-- Name: reserva_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.reserva_id_seq', 1, false);


--
-- Name: reserva_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.reserva_usuario_seq', 1, false);


--
-- Name: rol_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.rol_id_seq', 7, true);


--
-- Name: rol_perfil_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.rol_perfil_seq', 1, false);


--
-- Name: sistemaexterno_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.sistemaexterno_id_seq', 2, true);


--
-- Name: supervision_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.supervision_id_seq', 1, false);


--
-- Name: supervision_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

-- SELECT pg_catalog.setval('public.supervision_usuario_seq', 1, false);


--
-- Name: ubicacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.ubicacion_id_seq', 9, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.usuario_id_seq', 22, true);


--
-- Name: vehiculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.vehiculo_id_seq', 1, false);


--
-- Name: usuario c_correo; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT c_correo UNIQUE (correo);


--
-- Name: regservicio c_id_sesion; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.regservicio
    ADD CONSTRAINT c_id_sesion UNIQUE (idsession);


--
-- Name: ubicacion c_nombre; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.ubicacion
    ADD CONSTRAINT c_nombre UNIQUE (nombre);


--
-- Name: usuario c_numid; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT c_numid UNIQUE (numid);


--
-- Name: registroie c_permiso; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie
    ADD CONSTRAINT c_permiso UNIQUE (permiso);


--
-- Name: vehiculo c_placa; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.vehiculo
    ADD CONSTRAINT c_placa UNIQUE (placa);


--
-- Name: registroie c_ticketid; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie
    ADD CONSTRAINT c_ticketid UNIQUE (ticketid);


--
-- Name: configuracion pk_configuracion; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.configuracion
    ADD CONSTRAINT pk_configuracion PRIMARY KEY (id);


--
-- Name: espacioparqueo pk_espacioparqueo; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.espacioparqueo
    ADD CONSTRAINT pk_espacioparqueo PRIMARY KEY (id);


--
-- Name: informe pk_informe; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.informe
    ADD CONSTRAINT pk_informe PRIMARY KEY (id);


--
-- Name: registroie pk_ingreso; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie
    ADD CONSTRAINT pk_ingreso PRIMARY KEY (id);


--
-- Name: perfilusuario pk_perfilusuario; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.perfilusuario
    ADD CONSTRAINT pk_perfilusuario PRIMARY KEY (id);


--
-- Name: permiso pk_permiso; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT pk_permiso PRIMARY KEY (id);


--
-- Name: recurso pk_recurso; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.recurso
    ADD CONSTRAINT pk_recurso PRIMARY KEY (id);


--
-- Name: regservicio pk_regservicio; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.regservicio
    ADD CONSTRAINT pk_regservicio PRIMARY KEY (id);


--
-- Name: reserva pk_reserva; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reserva
    ADD CONSTRAINT pk_reserva PRIMARY KEY (id);


--
-- Name: rol pk_rol; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT pk_rol PRIMARY KEY (id);


--
-- Name: sistemaexterno pk_sistemaexterno; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.sistemaexterno
    ADD CONSTRAINT pk_sistemaexterno PRIMARY KEY (id);


--
-- Name: supervision pk_supervision; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.supervision
    ADD CONSTRAINT pk_supervision PRIMARY KEY (id);


--
-- Name: ubicacion pk_ubicacion; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.ubicacion
    ADD CONSTRAINT pk_ubicacion PRIMARY KEY (id);


--
-- Name: usuario pk_usuario; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (id);


--
-- Name: vehiculo pk_vehiculo; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.vehiculo
    ADD CONSTRAINT pk_vehiculo PRIMARY KEY (id);


--
-- Name: ixfk_configuracion_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE UNIQUE INDEX ixfk_configuracion_usuario ON public.configuracion USING btree (usuario);


--
-- Name: ixfk_espacioparqueo_ubicacion; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_espacioparqueo_ubicacion ON public.espacioparqueo USING btree (ubicacion);


--
-- Name: ixfk_informe_regservicio; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_informe_regservicio ON public.informe USING btree (regservicio);


--
-- Name: ixfk_informe_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_informe_usuario ON public.informe USING btree (usuario);


--
-- Name: ixfk_ingreso_recurso; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_ingreso_recurso ON public.registroie USING btree (recurso);


--
-- Name: ixfk_permiso_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_permiso_usuario ON public.permiso USING btree (usuario);


--
-- Name: ixfk_regservicio_sistemaexterno; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_regservicio_sistemaexterno ON public.regservicio USING btree (sistemaexterno);


--
-- Name: ixfk_registroie_permiso; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_registroie_permiso ON public.registroie USING btree (permiso);


--
-- Name: ixfk_registroie_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_registroie_usuario ON public.registroie USING btree (usuarioingreso);


--
-- Name: ixfk_registroie_usuario_02; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_registroie_usuario_02 ON public.registroie USING btree (usuarioegreso);


--
-- Name: ixfk_registroie_vehiculo; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_registroie_vehiculo ON public.registroie USING btree (vehiculo);


--
-- Name: ixfk_reserva_espacioparqueo; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_reserva_espacioparqueo ON public.reserva USING btree (espacioparqueo);


--
-- Name: ixfk_reserva_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_reserva_usuario ON public.reserva USING btree (usuario);


--
-- Name: ixfk_rol_perfilusuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_rol_perfilusuario ON public.rol USING btree (perfil);


--
-- Name: ixfk_supervision_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_supervision_usuario ON public.supervision USING btree (usuario);


--
-- Name: ixfk_usuario_rol; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_usuario_rol ON public.usuario USING btree (rol);


--
-- Name: configuracion fk_configuracion_usuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.configuracion
    ADD CONSTRAINT fk_configuracion_usuario FOREIGN KEY (usuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: espacioparqueo fk_espacioparqueo_ubicacion; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.espacioparqueo
    ADD CONSTRAINT fk_espacioparqueo_ubicacion FOREIGN KEY (ubicacion) REFERENCES public.ubicacion(id);


--
-- Name: informe fk_informe_regservicio; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.informe
    ADD CONSTRAINT fk_informe_regservicio FOREIGN KEY (regservicio) REFERENCES public.regservicio(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: informe fk_informe_usuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.informe
    ADD CONSTRAINT fk_informe_usuario FOREIGN KEY (usuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: registroie fk_ingreso_recurso; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie
    ADD CONSTRAINT fk_ingreso_recurso FOREIGN KEY (recurso) REFERENCES public.recurso(id);


--
-- Name: permiso fk_permiso_usuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT fk_permiso_usuario FOREIGN KEY (usuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: regservicio fk_regservicio_sistemaexterno; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.regservicio
    ADD CONSTRAINT fk_regservicio_sistemaexterno FOREIGN KEY (sistemaexterno) REFERENCES public.sistemaexterno(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: registroie fk_registroie_permiso; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie
    ADD CONSTRAINT fk_registroie_permiso FOREIGN KEY (permiso) REFERENCES public.permiso(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: registroie fk_registroie_usuarioegreso; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie
    ADD CONSTRAINT fk_registroie_usuarioegreso FOREIGN KEY (usuarioegreso) REFERENCES public.usuario(id) ON DELETE RESTRICT;


--
-- Name: registroie fk_registroie_usuarioingreso; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie
    ADD CONSTRAINT fk_registroie_usuarioingreso FOREIGN KEY (usuarioingreso) REFERENCES public.usuario(id) ON DELETE RESTRICT;


--
-- Name: registroie fk_registroie_vehiculo; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registroie
    ADD CONSTRAINT fk_registroie_vehiculo FOREIGN KEY (vehiculo) REFERENCES public.vehiculo(id);


--
-- Name: reserva fk_reserva_espacioparqueo; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reserva
    ADD CONSTRAINT fk_reserva_espacioparqueo FOREIGN KEY (espacioparqueo) REFERENCES public.espacioparqueo(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: reserva fk_reserva_usuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reserva
    ADD CONSTRAINT fk_reserva_usuario FOREIGN KEY (usuario) REFERENCES public.usuario(id) ON DELETE RESTRICT;


--
-- Name: rol fk_rol_perfilusuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT fk_rol_perfilusuario FOREIGN KEY (perfil) REFERENCES public.perfilusuario(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: supervision fk_supervision_usuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.supervision
    ADD CONSTRAINT fk_supervision_usuario FOREIGN KEY (usuario) REFERENCES public.usuario(id) ON DELETE RESTRICT;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
