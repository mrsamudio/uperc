--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.15
-- Dumped by pg_dump version 13.2 (Ubuntu 13.2-1.pgdg18.04+1)

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
    id bigint NOT NULL,
    intentosfallidos integer NOT NULL,
    caducidadcontrasena integer NOT NULL,
    maxadmin integer NOT NULL,
    fechaguardado timestamp without time zone NOT NULL
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
-- Name: espacioparqueo; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.espacioparqueo (
    id integer NOT NULL,
    nombre character varying(50),
    ubicacion smallint NOT NULL,
    ocupado boolean NOT NULL
);


ALTER TABLE public.espacioparqueo OWNER TO msamudio;

--
-- Name: TABLE espacioparqueo; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.espacioparqueo IS 'Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a a universidad';


--
-- Name: COLUMN espacioparqueo.ubicacion; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.espacioparqueo.ubicacion IS 'Tabla catálogo que almacena los espacios de parqueo de las diferentes ubicaciones que pertenecen a a universidad';


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
-- Name: informe; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.informe (
    id bigint NOT NULL,
    usuario bigint,
    regservicio bigint,
    fechagenerado timestamp without time zone NOT NULL,
    fechainicio date NOT NULL,
    fechafin date NOT NULL,
    disponibilidad numeric(7,4),
    reservasok numeric(7,4),
    reservasfail numeric(7,4),
    recogok numeric(7,4),
    recogfail numeric(7,4),
    recogtotal integer,
    ingresostotal integer,
    egresostotal integer
);


ALTER TABLE public.informe OWNER TO msamudio;

--
-- Name: TABLE informe; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.informe IS 'Guarda los datos procesados de los reportes consultados a la base de datos para ser consultados porteriormente por usuarios o sistemas externos autorizados.  Que contiene un (informe/reporte)?  	- Espacios disponibles/mes 	- % planificación de reservas/dias/semanas/mes 	- Frecuencia de entrada y salida de vehiculos/mes 	- % reservas exitosas/mes 	- % reservas fallidas/mes 	- % reconocimientos exitoso/mes  (ingresos) 	- % reconocimiento fallido/mes 	- Cantidad de placas reconocidas/mes 	- Cantidad de ingresos /dia/semana/mes 	- Cantidad de salidas /dia/semana/mes';


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

COMMENT ON COLUMN public.informe.disponibilidad IS '% porcentaje de espacios disponibles en el rango de fecha DIA/SEMANA/MES';


--
-- Name: COLUMN informe.reservasok; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.reservasok IS '% RESERVAS EXITOSAS DIA/SEMANA/MES';


--
-- Name: COLUMN informe.reservasfail; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.reservasfail IS '% RESERVAS FALLIDO DIA/SEMANA/MES';


--
-- Name: COLUMN informe.recogok; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.recogok IS '% RECONOCIMIENTO EXITOSO DIA/SEMANA/MES';


--
-- Name: COLUMN informe.recogfail; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.recogfail IS '% RECONOCIMIENTO FALLIDO DIA/SEMANA/MES';


--
-- Name: COLUMN informe.recogtotal; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.recogtotal IS 'CANTIDAD TOTAL DE PLACAS RECONOCIDAS  DIA/SEMANA/MES';


--
-- Name: COLUMN informe.ingresostotal; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.ingresostotal IS 'CANTIDAD DE ENTRADAS DIA/SEMANA/MES';


--
-- Name: COLUMN informe.egresostotal; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.informe.egresostotal IS 'CANTIDAD DE SALIDAS DIA/SEMANA/MES';


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
-- Name: perfil_usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.perfil_usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.perfil_usuario_id_seq OWNER TO msamudio;

--
-- Name: perfil_usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.perfil_usuario_id_seq OWNED BY public.perfilusuario.id;


--
-- Name: permiso; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.permiso (
    id bigint NOT NULL,
    usuario bigint NOT NULL
);


ALTER TABLE public.permiso OWNER TO msamudio;

--
-- Name: TABLE permiso; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.permiso IS 'Tabla intermedia que registra los permisos dados por usuarios supervisores a los usuarios que salen con un vehiculo diferente del parqueadero';


--
-- Name: recurso; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.recurso (
    id integer NOT NULL,
    nombre character varying(50) NOT NULL,
    marca character varying(50) NOT NULL,
    tipo character varying(50) NOT NULL,
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

COMMENT ON TABLE public.recurso IS 'Guarda los dispositivos que capturan o muestran la información del ingreso o egreso de los usuarios con sus vehículos.  	- El campo TIPO registra el tipode recurso(dispositivo), puede ser un televisor o monitor o cámara. 	- El campo IP registra la dirección asignada dentro de la red. 	- El campo PUERTO registra el puerto por el que el dispositivo se está comunicando. 	- El campo MAC registra la mac única del dispositivo. 	- El campo PROTOCOLO registra el protocolo por el cuál el recurso se comunica (TCP - UDP A FUTURO PUEDEN SER MÁS PROTOCOLOS). 	- El campo FECHA_REGISTRO registra el momento de tiempo en el que se agregó el recurso al sistema. 	- El campo URL_FABRICANTE registra la dirección web del fabricante del recurso. 	- El campo ESTADO registra si el recurso se encuentra activo o inactivo.';


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
-- Name: reg_servicio; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.reg_servicio (
    id bigint NOT NULL,
    idsession uuid NOT NULL,
    sistemaexterno smallint NOT NULL,
    fechasession timestamp with time zone NOT NULL
);


ALTER TABLE public.reg_servicio OWNER TO msamudio;

--
-- Name: TABLE reg_servicio; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.reg_servicio IS 'Guarda la sesión del sistema externo y la fecha en la que se creó la sessión.  	- El campo ID_SESSION registra un número que identifica la sesión en que el sistema externo realizará consultas a la base de datos 	- El campo FECHA_SESSION  registra el momento en el que el número de sesión fué creado.';


--
-- Name: reg_servicio_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.reg_servicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reg_servicio_id_seq OWNER TO msamudio;

--
-- Name: reg_servicio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.reg_servicio_id_seq OWNED BY public.reg_servicio.id;


--
-- Name: registro_ie; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.registro_ie (
    id bigint NOT NULL,
    fechaingreso timestamp without time zone NOT NULL,
    fechaegreso timestamp without time zone,
    recursoingreso integer NOT NULL,
    vehiculo bigint NOT NULL,
    usuarioingreso bigint NOT NULL,
    usuarioegreso bigint,
    ticketid uuid NOT NULL,
    recursoegreso integer
);


ALTER TABLE public.registro_ie OWNER TO msamudio;

--
-- Name: TABLE registro_ie; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.registro_ie IS 'Guarda el momento en que los recursos(dispositivos de captura) registran el ingreso o egreso al parqueadero de lo usuarios y vehículos.';


--
-- Name: COLUMN registro_ie.recursoingreso; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.registro_ie.recursoingreso IS 'Recurso con el que se registra la entrada del vehiculo y el usuario';


--
-- Name: COLUMN registro_ie.recursoegreso; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON COLUMN public.registro_ie.recursoegreso IS 'Recurso con el que se registra la salida del vehiculo y el usuario';


--
-- Name: registro_ie_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.registro_ie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.registro_ie_id_seq OWNER TO msamudio;

--
-- Name: registro_ie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.registro_ie_id_seq OWNED BY public.registro_ie.id;


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
    usuario bigint NOT NULL
);


ALTER TABLE public.reserva OWNER TO msamudio;

--
-- Name: TABLE reserva; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.reserva IS 'Guarda las reservas que han sido solicitadas por los usuarios. 	- El campo FECHA_SOLICITUD registra el momento de tiempo en que se solicitó la reserva. 	- El campo ESTADO registra si la reserva se encuentra activa o inactiva. 	- El campo NUM_ESTACION registra el número del estacionamiento reservado para el vehiculo. 	- El campo FECHA_RESERVA registra el momento en el que se espera que el vehiculo ingrese al parqueadero de la universidad para ocupar el espacio reservado. 	- El campo FECHA_FIN registra el momento en que finaliza la reserva. 	- El campo CANCELADA registra si la reserva fue cancelada o no.';


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
-- Name: sistema_externo; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.sistema_externo (
    id smallint NOT NULL,
    ip inet NOT NULL,
    nombre character varying(50) NOT NULL,
    contrasena character varying(500) NOT NULL
);


ALTER TABLE public.sistema_externo OWNER TO msamudio;

--
-- Name: TABLE sistema_externo; Type: COMMENT; Schema: public; Owner: msamudio
--

COMMENT ON TABLE public.sistema_externo IS 'Guarda los datos de acceso del sistema externo al sistema.';


--
-- Name: sistema_externo_id_seq; Type: SEQUENCE; Schema: public; Owner: msamudio
--

CREATE SEQUENCE public.sistema_externo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sistema_externo_id_seq OWNER TO msamudio;

--
-- Name: sistema_externo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: msamudio
--

ALTER SEQUENCE public.sistema_externo_id_seq OWNED BY public.sistema_externo.id;


--
-- Name: supervision; Type: TABLE; Schema: public; Owner: msamudio
--

CREATE TABLE public.supervision (
    id bigint NOT NULL,
    mensaje character varying(50) NOT NULL,
    estado boolean NOT NULL,
    fecha timestamp without time zone NOT NULL,
    tipo boolean NOT NULL,
    usuario bigint NOT NULL
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

COMMENT ON TABLE public.ubicacion IS 'Table catálogo que guarda la ubicacion de la sede, extenciones, seccionales y otros puntos estratègicos que pertenecen a la universidad de Cundinamarca';


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
    id bigint NOT NULL,
    nombres character varying(50) NOT NULL,
    apellidos character varying(50) NOT NULL,
    tipoid character varying(1) NOT NULL,
    numid character varying(150) NOT NULL,
    contrasena character varying(150) NOT NULL,
    correo character varying(50) NOT NULL,
    fechanac date NOT NULL,
    fechareg timestamp without time zone,
    estado boolean NOT NULL,
    rol smallint NOT NULL
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
    id bigint NOT NULL,
    placa character varying(50) NOT NULL,
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

COMMENT ON COLUMN public.vehiculo.clase IS 'Que clase de vehiculo es (Automovil, moticleta, autobus, etc)';


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
-- Name: espacioparqueo id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.espacioparqueo ALTER COLUMN id SET DEFAULT nextval('public.espacioparqueo_id_seq'::regclass);


--
-- Name: informe id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.informe ALTER COLUMN id SET DEFAULT nextval('public.informe_id_seq'::regclass);


--
-- Name: perfilusuario id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.perfilusuario ALTER COLUMN id SET DEFAULT nextval('public.perfil_usuario_id_seq'::regclass);


--
-- Name: recurso id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.recurso ALTER COLUMN id SET DEFAULT nextval('public.recurso_id_seq'::regclass);


--
-- Name: reg_servicio id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reg_servicio ALTER COLUMN id SET DEFAULT nextval('public.reg_servicio_id_seq'::regclass);


--
-- Name: registro_ie id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registro_ie ALTER COLUMN id SET DEFAULT nextval('public.registro_ie_id_seq'::regclass);


--
-- Name: reserva id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reserva ALTER COLUMN id SET DEFAULT nextval('public.reserva_id_seq'::regclass);


--
-- Name: rol id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.rol ALTER COLUMN id SET DEFAULT nextval('public.rol_id_seq'::regclass);


--
-- Name: sistema_externo id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.sistema_externo ALTER COLUMN id SET DEFAULT nextval('public.sistema_externo_id_seq'::regclass);


--
-- Name: supervision id; Type: DEFAULT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.supervision ALTER COLUMN id SET DEFAULT nextval('public.supervision_id_seq'::regclass);


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

INSERT INTO public.configuracion (id, intentosfallidos, caducidadcontrasena, maxadmin, fechaguardado) VALUES (2, 3, 10, 3, '2021-02-10 00:57:17.870947');


--
-- Data for Name: espacioparqueo; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (1, 'A1', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (2, 'A2', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (3, 'A3', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (4, 'A4', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (5, 'A5', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (6, 'A6', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (7, 'A7', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (8, 'A8', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (9, 'A9', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (10, 'A10', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (11, 'A11', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (12, 'B1', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (13, 'B2', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (14, 'B3', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (15, 'B4', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (16, 'B5', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (17, 'B6', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (18, 'B7', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (19, 'B8', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (20, 'B9', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (21, 'B10', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (22, 'B11', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (23, 'B12', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (24, 'B13', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (25, 'B14', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (26, 'B15', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (27, 'B16', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (28, 'C1', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (29, 'C2', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (30, 'C3', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (31, 'C4', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (32, 'C5', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (33, 'C6', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (34, 'C7', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (35, 'C8', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (36, 'C9', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (37, 'C10', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (38, 'C11', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (39, 'C12', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (40, 'C13', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (41, 'C14', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (42, 'C15', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (43, 'C16', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (44, 'C17', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (45, 'D1', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (46, 'D2', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (47, 'D3', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (48, 'D4', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (49, 'D5', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (50, 'D6', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (51, 'D7', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (52, 'D8', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (53, 'D9', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (54, 'D10', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (55, 'D11', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (56, 'D12', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (57, 'D13', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (58, 'D14', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (59, 'D15', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (60, 'D16', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (61, 'D17', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (62, 'D18', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (63, 'D19', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (64, 'D20', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (65, 'D21', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (66, 'D22', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (67, 'D23', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (68, 'D24', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (69, 'E1', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (70, 'E2', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (71, 'E3', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (72, 'E4', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (73, 'E5', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (74, 'E6', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (75, 'E7', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (76, 'E8', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (77, 'E9', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (78, 'E10', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (79, 'E11', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (80, 'E12', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (81, 'E13', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (82, 'E14', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (83, 'E15', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (84, 'E16', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (85, 'E17', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (86, 'E18', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (87, 'E19', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (88, 'E20', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (89, 'E21', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (90, 'E22', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (91, 'E23', 4, false);
INSERT INTO public.espacioparqueo (id, nombre, ubicacion, ocupado) VALUES (92, 'E24', 4, false);


--
-- Data for Name: informe; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.informe (id, usuario, regservicio, fechagenerado, fechainicio, fechafin, disponibilidad, reservasok, reservasfail, recogok, recogfail, recogtotal, ingresostotal, egresostotal) VALUES (2, NULL, 1, '2021-01-29 00:00:00', '2021-01-10', '2021-01-20', 0.3500, 0.7300, 0.3000, 0.9300, 0.0700, 200, 100, 100);
INSERT INTO public.informe (id, usuario, regservicio, fechagenerado, fechainicio, fechafin, disponibilidad, reservasok, reservasfail, recogok, recogfail, recogtotal, ingresostotal, egresostotal) VALUES (1, 2, NULL, '2021-02-23 00:00:00', '2021-02-22', '2021-02-23', 0.5000, 0.9000, 0.1000, 0.9900, 0.0100, 100, 50, 50);


--
-- Data for Name: perfilusuario; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.perfilusuario (id, nombre, descripcion) VALUES (1, 'Administrador', 'Descripcíon breve');
INSERT INTO public.perfilusuario (id, nombre, descripcion) VALUES (2, 'Supervisor', 'Descripcíon breve');
INSERT INTO public.perfilusuario (id, nombre, descripcion) VALUES (3, 'Usuario general', 'Descripcíon breve');
INSERT INTO public.perfilusuario (id, nombre, descripcion) VALUES (4, 'Visitante', 'Descripcíon breve');


--
-- Data for Name: permiso; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.permiso (id, usuario) VALUES (2, 8);
INSERT INTO public.permiso (id, usuario) VALUES (3, 7);


--
-- Data for Name: recurso; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.recurso (id, nombre, marca, tipo, ip, puerto, mac, protocolo, fecharegistro, urlfabricante, estado) VALUES (1, 'Entrada 1', 'kvision', 'CamaraIP', '192.168.100.132', '64001', '08:00:2b:01:02:03', 'TCP', '2021-02-10 00:48:09.230218', 'http://127.0.0.1/CamaraIP.html', false);


--
-- Data for Name: reg_servicio; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.reg_servicio (id, idsession, sistemaexterno, fechasession) VALUES (1, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 1, '2021-12-03 05:00:00+00');
INSERT INTO public.reg_servicio (id, idsession, sistemaexterno, fechasession) VALUES (2, 'a0eebc99-9c0f-4ff8-bb6d-6bb8bd380a11', 2, '1999-03-04 05:00:00+00');


--
-- Data for Name: registro_ie; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.registro_ie (id, fechaingreso, fechaegreso, recursoingreso, vehiculo, usuarioingreso, usuarioegreso, ticketid, recursoegreso) VALUES (1, '2021-02-27 00:00:00', '2021-02-28 00:00:00', 1, 4, 5, 5, 'a0eebc99-9c0b-4ef8-bb6d-6ff9bd380a19', NULL);
INSERT INTO public.registro_ie (id, fechaingreso, fechaegreso, recursoingreso, vehiculo, usuarioingreso, usuarioegreso, ticketid, recursoegreso) VALUES (2, '2021-02-26 04:30:15', '2021-02-26 05:30:17', 1, 1, 5, 4, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd388e30', NULL);
INSERT INTO public.registro_ie (id, fechaingreso, fechaegreso, recursoingreso, vehiculo, usuarioingreso, usuarioegreso, ticketid, recursoegreso) VALUES (5, '2021-02-28 10:56:30', NULL, 1, 4, 4, NULL, 'a0ccbc99-9c0b-4ef8-bb6d-6bb9ad3904ee', NULL);
INSERT INTO public.registro_ie (id, fechaingreso, fechaegreso, recursoingreso, vehiculo, usuarioingreso, usuarioegreso, ticketid, recursoegreso) VALUES (3, '2020-11-30 20:03:05', '2021-11-30 21:30:07', 1, 2, 10, 4, 'f0dddd77-9c0b-4ef8-bb6d-6bb9bd380b22', 1);
INSERT INTO public.registro_ie (id, fechaingreso, fechaegreso, recursoingreso, vehiculo, usuarioingreso, usuarioegreso, ticketid, recursoegreso) VALUES (4, '2021-02-28 09:10:00', '2021-02-28 10:00:00', 1, 2, 5, 10, 'f0dddd77-9c0b-4ef8-bb6d-6bb0bd380b22', 1);


--
-- Data for Name: reserva; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.reserva (id, fechasolicitud, estado, espacioparqueo, fechareserva, fechafin, cancelada, usuario) VALUES (1, '2021-01-25 00:00:00', false, 10, '2021-02-28 00:00:00', NULL, false, 3);
INSERT INTO public.reserva (id, fechasolicitud, estado, espacioparqueo, fechareserva, fechafin, cancelada, usuario) VALUES (2, '2021-01-30 00:00:00', false, 4, '2021-02-28 00:00:00', NULL, false, 5);
INSERT INTO public.reserva (id, fechasolicitud, estado, espacioparqueo, fechareserva, fechafin, cancelada, usuario) VALUES (3, '2021-02-20 00:00:00', false, 12, '2021-03-01 00:00:00', NULL, false, 10);
INSERT INTO public.reserva (id, fechasolicitud, estado, espacioparqueo, fechareserva, fechafin, cancelada, usuario) VALUES (4, '2021-02-25 00:00:00', false, 3, '2021-03-01 00:00:00', NULL, false, 6);


--
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.rol (id, nombre, descripcion, perfil) VALUES (1, 'Docente', 'Descripcíon breve', 3);
INSERT INTO public.rol (id, nombre, descripcion, perfil) VALUES (2, 'Graduado', 'Descripcíon breve', 3);
INSERT INTO public.rol (id, nombre, descripcion, perfil) VALUES (3, 'Estudiante', 'Descripcíon breve', 3);
INSERT INTO public.rol (id, nombre, descripcion, perfil) VALUES (4, 'Administrativo', 'Descripcíon breve', 1);
INSERT INTO public.rol (id, nombre, descripcion, perfil) VALUES (5, 'Administrador', 'Descripcíon breve', 1);
INSERT INTO public.rol (id, nombre, descripcion, perfil) VALUES (6, 'Supervisión', 'Descripcíon breve', 2);
INSERT INTO public.rol (id, nombre, descripcion, perfil) VALUES (7, 'Visitante', 'Descripcíon breve', 4);


--
-- Data for Name: sistema_externo; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.sistema_externo (id, ip, nombre, contrasena) VALUES (1, '192.168.100.0/24', 'FinancieroSystem', 'f12345');
INSERT INTO public.sistema_externo (id, ip, nombre, contrasena) VALUES (2, '127.0.0.1', 'ProyectoEspecial', 'p12345');
INSERT INTO public.sistema_externo (id, ip, nombre, contrasena) VALUES (7, '192.168.3.0/24', 'test', 'asdñlkj');
INSERT INTO public.sistema_externo (id, ip, nombre, contrasena) VALUES (8, '192.168.4.54', 'test', 'asdñldkj');
INSERT INTO public.sistema_externo (id, ip, nombre, contrasena) VALUES (9, '192.168.54.0/8', 'test', 'asdñldkj');


--
-- Data for Name: supervision; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.supervision (id, mensaje, estado, fecha, tipo, usuario) VALUES (1, 'Este es un mensaje de alerta general', true, '2021-02-27 00:00:00', true, 8);
INSERT INTO public.supervision (id, mensaje, estado, fecha, tipo, usuario) VALUES (3, 'Este es un mensaje de aviso', true, '2021-01-30 00:00:00', false, 7);
INSERT INTO public.supervision (id, mensaje, estado, fecha, tipo, usuario) VALUES (2, 'mensaje de bienvenida por día especial', true, '2021-01-20 00:00:00', false, 8);


--
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (1, 'Fusagasugá', 'Diagonal 18 No. 20-29', '(4.33405240000000003,-74.3692551000000037)', '18281483');
INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (2, 'Girardot', 'Carrera 19 Nº 24 - 209', '(4.30652610000000013,-74.8062448000000018)', '18335071');
INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (3, 'Ubaté', 'Calle 6 Nº 9 - 80', '(5.30876069999999967,-73.8172500000000014)', '18553055');
INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (4, 'Chía', 'Autopista Chía - Cajicá | Sector "El Cuarenta', '(4.87371830000000017,-74.0376925999999997)', '18553055');
INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (5, 'Chocontá', 'Carrera 3 Nº 5 -71', '(5.14391670000000012,-73.6842201000000045)', '18562520');
INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (6, 'Facatativá', 'Calle 14 con Avenida 15', '(4.82912609999999987,-74.354577699999993)', '18920706');
INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (7, 'Soacha', 'Diagonal 9 No. 4B-85', '(4.57841529999999963,-74.2224348999999961)', '17219220');
INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (8, 'Zipaquirá', 'Carrera 7 No. 1-31', '(5.02169270000000001,-74.0056418000000065)', '18515792');
INSERT INTO public.ubicacion (id, nombre, direccion, coordenadas, telefono) VALUES (9, 'Oficinas Bogotá', 'Carrera 20 No. 39-32 Teusaquillo', '(4.62797859999999961,-74.0736311000000001)', '17448180');


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (2, 'Ad', 'Ministro', 'C', '00000001', 'minijtro', 'minijtro@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 5);
INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (3, 'El', 'docente', 'C', '00000002', 'docente', 'docente@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 1);
INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (4, 'El', 'Graduado', 'C', '00000004', 'graduado', 'graduado@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 2);
INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (5, 'La', 'estudiante', 'C', '000000465', 'estudiante', 'estudiante@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 3);
INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (6, 'Carlos', 'PeñarandaAdministrativo', 'C', '00000006', 'cpenaranda', 'cpenaranda@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 4);
INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (7, 'El', 'primer supervisor', 'C', '00000028', 'super2', 'super2@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 6);
INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (8, 'Segundo', 'supervisor', 'C', '00000007', 'super1', 'super1@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 6);
INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (9, 'Estudiante', 'Segundo', 'C', '00000008', 'estudiante2', 'estudiante2@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 3);
INSERT INTO public.usuario (id, nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol) VALUES (10, 'Primer', 'visitante', 'C', '00300009', 'visitante', 'visitante@localhost', '2021-02-16', '2021-02-16 11:14:55.808771', true, 7);


--
-- Data for Name: vehiculo; Type: TABLE DATA; Schema: public; Owner: msamudio
--

INSERT INTO public.vehiculo (id, placa, marca, color, modelo, clase, tiposervicio) VALUES (1, 'AAA-123', 'honda', 'rojo', '2019', 'automovil', 'particular');
INSERT INTO public.vehiculo (id, placa, marca, color, modelo, clase, tiposervicio) VALUES (2, 'AAB-124', 'AKT', 'blanca', '2014', 'motocicleta', 'particular');
INSERT INTO public.vehiculo (id, placa, marca, color, modelo, clase, tiposervicio) VALUES (3, 'ABC-111', 'renault', 'gris', '2013', 'bus', 'especial');
INSERT INTO public.vehiculo (id, placa, marca, color, modelo, clase, tiposervicio) VALUES (4, 'AAB-111', 'suzuki', 'amarillo', '2018', 'automovil', 'publico');


--
-- Name: espacioparqueo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.espacioparqueo_id_seq', 117, true);


--
-- Name: informe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.informe_id_seq', 47, true);


--
-- Name: perfil_usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.perfil_usuario_id_seq', 24, true);


--
-- Name: recurso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.recurso_id_seq', 18, true);


--
-- Name: reg_servicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.reg_servicio_id_seq', 23, true);


--
-- Name: registro_ie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.registro_ie_id_seq', 12, true);


--
-- Name: reserva_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.reserva_id_seq', 18, true);


--
-- Name: rol_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.rol_id_seq', 20, true);


--
-- Name: sistema_externo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.sistema_externo_id_seq', 24, true);


--
-- Name: supervision_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.supervision_id_seq', 20, true);


--
-- Name: ubicacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.ubicacion_id_seq', 27, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.usuario_id_seq', 125, true);


--
-- Name: vehiculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: msamudio
--

SELECT pg_catalog.setval('public.vehiculo_id_seq', 28, true);


--
-- Name: usuario c_correo; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT c_correo UNIQUE (correo);


--
-- Name: reg_servicio c_id_sesion; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reg_servicio
    ADD CONSTRAINT c_id_sesion UNIQUE (idsession);


--
-- Name: usuario c_numid; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT c_numid UNIQUE (numid);


--
-- Name: vehiculo c_placa; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.vehiculo
    ADD CONSTRAINT c_placa UNIQUE (placa);


--
-- Name: registro_ie c_ticket_id; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registro_ie
    ADD CONSTRAINT c_ticket_id UNIQUE (ticketid);


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
-- Name: registro_ie pk_ingreso; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registro_ie
    ADD CONSTRAINT pk_ingreso PRIMARY KEY (id);


--
-- Name: perfilusuario pk_perfil_usuario; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.perfilusuario
    ADD CONSTRAINT pk_perfil_usuario PRIMARY KEY (id);


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
-- Name: reg_servicio pk_reg_servicio; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reg_servicio
    ADD CONSTRAINT pk_reg_servicio PRIMARY KEY (id);


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
-- Name: sistema_externo pk_sistema_externo; Type: CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.sistema_externo
    ADD CONSTRAINT pk_sistema_externo PRIMARY KEY (id);


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

CREATE UNIQUE INDEX ixfk_configuracion_usuario ON public.configuracion USING btree (id);


--
-- Name: ixfk_espacioparqueo_ubicacion; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_espacioparqueo_ubicacion ON public.espacioparqueo USING btree (ubicacion);


--
-- Name: ixfk_informe_reg_servicio; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_informe_reg_servicio ON public.informe USING btree (regservicio);


--
-- Name: ixfk_informe_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_informe_usuario ON public.informe USING btree (usuario);


--
-- Name: ixfk_ingreso_recurso; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_ingreso_recurso ON public.registro_ie USING btree (recursoingreso);


--
-- Name: ixfk_permiso_registro_ie; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_permiso_registro_ie ON public.permiso USING btree (id);


--
-- Name: ixfk_permiso_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_permiso_usuario ON public.permiso USING btree (usuario);


--
-- Name: ixfk_reg_servicio_sistema_externo; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_reg_servicio_sistema_externo ON public.reg_servicio USING btree (sistemaexterno);


--
-- Name: ixfk_registro_ie_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_registro_ie_usuario ON public.registro_ie USING btree (usuarioingreso);


--
-- Name: ixfk_registro_ie_usuario_02; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_registro_ie_usuario_02 ON public.registro_ie USING btree (usuarioegreso);


--
-- Name: ixfk_registro_ie_vehiculo; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_registro_ie_vehiculo ON public.registro_ie USING btree (vehiculo);


--
-- Name: ixfk_reserva_espacioparqueo; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_reserva_espacioparqueo ON public.reserva USING btree (espacioparqueo);


--
-- Name: ixfk_reserva_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_reserva_usuario ON public.reserva USING btree (usuario);


--
-- Name: ixfk_rol_perfil_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_rol_perfil_usuario ON public.rol USING btree (perfil);


--
-- Name: ixfk_supervision_usuario; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_supervision_usuario ON public.supervision USING btree (usuario);


--
-- Name: ixfk_usuario_rol; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX ixfk_usuario_rol ON public.usuario USING btree (rol);


--
-- Name: registro_ie_recursoegreso_idx; Type: INDEX; Schema: public; Owner: msamudio
--

CREATE INDEX registro_ie_recursoegreso_idx ON public.registro_ie USING btree (recursoegreso);


--
-- Name: registro_ie fk_egreso_recurso; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registro_ie
    ADD CONSTRAINT fk_egreso_recurso FOREIGN KEY (recursoegreso) REFERENCES public.recurso(id) ON DELETE RESTRICT;


--
-- Name: espacioparqueo fk_espacioparqueo_ubicacion; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.espacioparqueo
    ADD CONSTRAINT fk_espacioparqueo_ubicacion FOREIGN KEY (ubicacion) REFERENCES public.ubicacion(id);


--
-- Name: informe fk_informe_reg_servicio; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.informe
    ADD CONSTRAINT fk_informe_reg_servicio FOREIGN KEY (regservicio) REFERENCES public.reg_servicio(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: informe fk_informe_usuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.informe
    ADD CONSTRAINT fk_informe_usuario FOREIGN KEY (usuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: registro_ie fk_ingreso_recurso; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registro_ie
    ADD CONSTRAINT fk_ingreso_recurso FOREIGN KEY (recursoingreso) REFERENCES public.recurso(id) ON DELETE RESTRICT;


--
-- Name: permiso fk_permiso_registro_ie; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT fk_permiso_registro_ie FOREIGN KEY (id) REFERENCES public.registro_ie(id) ON DELETE RESTRICT;


--
-- Name: permiso fk_permiso_usuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT fk_permiso_usuario FOREIGN KEY (usuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: reg_servicio fk_reg_servicio_sistema_externo; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.reg_servicio
    ADD CONSTRAINT fk_reg_servicio_sistema_externo FOREIGN KEY (sistemaexterno) REFERENCES public.sistema_externo(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: registro_ie fk_registro_ie_usuario_egreso; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registro_ie
    ADD CONSTRAINT fk_registro_ie_usuario_egreso FOREIGN KEY (usuarioegreso) REFERENCES public.usuario(id) ON DELETE RESTRICT;


--
-- Name: registro_ie fk_registro_ie_usuario_ingreso; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registro_ie
    ADD CONSTRAINT fk_registro_ie_usuario_ingreso FOREIGN KEY (usuarioingreso) REFERENCES public.usuario(id) ON DELETE RESTRICT;


--
-- Name: registro_ie fk_registro_ie_vehiculo; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.registro_ie
    ADD CONSTRAINT fk_registro_ie_vehiculo FOREIGN KEY (vehiculo) REFERENCES public.vehiculo(id);


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
-- Name: rol fk_rol_perfil_usuario; Type: FK CONSTRAINT; Schema: public; Owner: msamudio
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT fk_rol_perfil_usuario FOREIGN KEY (perfil) REFERENCES public.perfilusuario(id) ON UPDATE CASCADE ON DELETE RESTRICT;


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

