INSERT INTO public.perfil_usuario
(nombre, descripcion)
VALUES
('Administrador', 'Descripcíon breve'),
('Supervisor', 'Descripcíon breve'),
('Usuario general', 'Descripcíon breve'),
('Visitante', 'Descripcíon breve');

INSERT INTO public.rol
(nombre, descripcion, perfil)
VALUES
('Docente', 'Descripcíon breve', (SELECT ID FROM perfil_usuario pu WHERE pu.nombre='Usuario general')),
('Graduado', 'Descripcíon breve', (SELECT ID FROM perfil_usuario pu WHERE pu.nombre='Usuario general')),
('Estudiante', 'Descripcíon breve', (SELECT ID FROM perfil_usuario pu WHERE pu.nombre='Usuario general')),

('Administrativo', 'Descripcíon breve', (SELECT ID FROM perfil_usuario pu WHERE pu.nombre='Administrador')),
('Administrador', 'Descripcíon breve', (SELECT ID FROM perfil_usuario pu WHERE pu.nombre='Administrador')),

('Supervisión', 'Descripcíon breve', (SELECT ID FROM perfil_usuario pu WHERE pu.nombre='Supervisor')),

('Visitante', 'Descripcíon breve', (SELECT ID FROM perfil_usuario pu WHERE pu.nombre='Visitante'));

INSERT INTO ubicacion(
            nombre, direccion, coordenadas, telefono)
	VALUES ('Fusagasugá', 'Diagonal 18 No. 20-29', point(4.3340524,-74.3692551), '18281483'),
		('Girardot', 'Carrera 19 Nº 24 - 209', point(4.3065261,-74.8062448), '18335071'),
		('Ubaté', 'Calle 6 Nº 9 - 80', point(5.3087607,-73.81725), '18553055'),
		('Chía', 'Autopista Chía - Cajicá | Sector "El Cuarenta', point(4.8737183,-74.0376926), '18553055'),
		('Chocontá', 'Carrera 3 Nº 5 -71', point(5.1439167,-73.6842201), '18562520'),
		('Facatativá', 'Calle 14 con Avenida 15', point(4.8291261,-74.3545777), '18920706'),
		('Soacha', 'Diagonal 9 No. 4B-85', point(4.5784153,-74.2224349), '17219220'),
		('Zipaquirá', 'Carrera 7 No. 1-31', point(5.0216927,-74.0056418), '18515792'),
		('Oficinas Bogotá', 'Carrera 20 No. 39-32 Teusaquillo', point(4.6279786,-74.0736311), '17448180');

-- ejecucion en psql
do $$
    begin
        for a in 1..11 loop
            insert into ESPACIOPARQUEO
                (NOMBRE, UBICACION, OCUPADO)
                values
                    (CONCAT('A', CAST(a AS TEXT)), (SELECT ID FROM ubicacion U WHERE U.nombre='Chía') , false);
        end loop;
        
        for b in 1..16 loop
            insert into ESPACIOPARQUEO
                (NOMBRE, UBICACION, OCUPADO)
                values
                    (CONCAT('B', CAST(b AS TEXT)), (SELECT ID FROM ubicacion U WHERE U.nombre='Chía') , false);
        end loop;
        
        for c in 1..17 loop
            insert into ESPACIOPARQUEO
                (NOMBRE, UBICACION, OCUPADO)
                values
                    (CONCAT('C', CAST(c AS TEXT)), (SELECT ID FROM ubicacion U WHERE U.nombre='Chía') , false);
        end loop;
        
        for d in 1..24 loop
            insert into ESPACIOPARQUEO
                (NOMBRE, UBICACION, OCUPADO)
                values
                    (CONCAT('D', CAST(d AS TEXT)), (SELECT ID FROM ubicacion U WHERE U.nombre='Chía') , false);
        end loop;
        
        for e in 1..24 loop
            insert into ESPACIOPARQUEO
                (NOMBRE, UBICACION, OCUPADO)
                values
                    (CONCAT('E', CAST(e AS TEXT)), (SELECT ID FROM ubicacion U WHERE U.nombre='Chía') , false);
        end loop;
    end; $$;



INSERT INTO public.sistema_externo
(ip, nombre, contrasena)
VALUES
('192.168.100.0/24', 'FinancieroSystem', 'f12345'),
('127.0.0.1', 'ProyectoEspecial', 'p12345');


INSERT INTO public.recurso
(nombre, marca, tipo, ip, puerto, mac, protocolo, fecharegistro, urlfabricante, estado)
VALUES('Entrada 1', 'kvision', 'CamaraIP', '192.168.100.132', '64001', '08:00:2b:01:02:03' , 'TCP', current_timestamp, 'http://127.0.0.1/CamaraIP.html', false);


INSERT INTO public.usuario
(nombres, apellidos, tipoid, numid, contrasena, correo, fechanac, fechareg, estado, rol)
VALUES
('Ad', 'Ministro', 'CC', '00000001', 'minijtro', 'minijtro@localhost', '01/01/1900', '18/01/2021', true, (SELECT ID FROM rol r WHERE r.nombre='Administrador')),

('El', 'docente', 'CC', '00000002', 'docente', 'docente@localhost', '01/01/1901', '17/01/2021', true, (SELECT ID FROM rol r WHERE r.nombre='Docente')),

('El', 'Graduado', 'CC', '00000004', 'graduado', 'graduado@localhost', '01/01/1902', '16/01/2021', true, (SELECT ID FROM rol r WHERE r.nombre='Graduado')),

('La', 'estudiante', 'CC', '000000465', 'estudiante', 'estudiante@localhost', '01/01/1906', '18/01/2021', true, (SELECT ID FROM rol r WHERE r.nombre='Estudiante')),

('Carlos', 'PeñarandaAdministrativo', 'CC', '00000006', 'cpenaranda', 'cpenaranda@localhost', '01/01/1923', '07/12/2020', true, (SELECT ID FROM rol r WHERE r.nombre='Administrativo')),

('El', 'primer supervisor', 'CC', '00000028', 'super2', 'super2@localhost', '01/01/1900', '18/01/2021', true, (SELECT ID FROM rol r WHERE r.nombre='Supervisión')),

('Segundo', 'supervisor', 'CC', '00000007', 'super1', 'super1@localhost', '01/01/1900', '18/01/2021', true, (SELECT ID FROM rol r WHERE r.nombre='Supervisión')),

('Estudiante', 'Segundo', 'CC', '00000008', 'estudiante2', 'estudiante2@localhost', '01/01/1900', '18/01/2021', true, (SELECT ID FROM rol r WHERE r.nombre='Estudiante')),

('Primer', 'visitante', 'CC', '00300009', 'visitante', 'visitante@localhost', '01/01/1997', '18/01/2021', true, (SELECT ID FROM rol r WHERE r.nombre='Visitante'));

-- Verificar la columna maxadmin
INSERT INTO public.configuracion
(id, intentosfallidos, caducidadcontrasena, maxadmin, fechaguardado)
VALUES
((SELECT id FROM usuario u WHERE u.numid='00000001'), 3, 10, 3, current_timestamp),
((SELECT id FROM usuario u WHERE u.numid='000000465'), 7, 13, 1, current_timestamp),
((SELECT id FROM usuario u WHERE u.numid='00000008'), 29, 49, 1, current_timestamp),
((SELECT id FROM usuario u WHERE u.numid='00300009'), 2, 100, 1, current_timestamp);



--vehiculo
--supervision
--registroIE
--permiso
--reserva
--regservicio

--informe
