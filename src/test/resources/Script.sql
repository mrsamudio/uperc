select * from sistema_externo;



delete from configuracion c 
where c.id= 8;


update usuario
set fechanac = current_timestamp




  delete from usuario where id = 24;
  
  
 ALTER TABLE usuario ALTER tipoid TYPE varchar(1);
 ALTER TABLE usuario ALTER numid TYPE varchar(150);
 ALTER TABLE usuario ALTER contrasena TYPE varchar(150);
 ALTER TABLE usuario ALTER COLUMN fechareg TYPE timestamp without time zone USING fechareg::TIMESTAMP;--'YYYY-MM-DD HH24:MI:SS'
	
 ALTER TABLE usuario ALTER tipoid TYPE varchar(1);
 
 
 
 select * from usuario;
 
 
 
 insert into usuario (rol, estado, fechareg, fechanac, correo, contrasena, numid, tipoid, apellidos,nombres)
 values (
 5, false, '2021-02-16 11:14:55.808771'::timestamp, '2021-02-16'::date, 'info@ingsamudio.co', '123456', '987654321', 'C', 'samudio', 'mario');
 
 select * from usuario;
 
 insert into usuario (rol, estado, fechareg, fechanac, correo, contrasena, numid, tipoid, apellidos,nombres)
 values (
 5, false, '2021-02-16 11:14:55.808771'::timestamp, '2021-02-16'::date, 'infso@ingsamudio.co', '123456', '987654321', 'C', 'samudio', 'mario');

 select * from usuario;
 
 
 
 select * from perfilusuario;
 select * from configuracion;
 select * from rol;
 
 delete from usuario where id = 150;
 update usuario
 set
 nombres ='Ad', apellidos ='Ministro', tipoid ='C', numid = '00000001', contrasena ='minijtro', correo ='minijtro@localhost',
 fechanac ='2021-02-16'::date, fechareg ='2021-02-16 11:14:55.808771'::timestamp, estado = true, rol = 5
 WHERE id = 2;
 select * from usuario;
 
   2 Ad         Ministro                C      00000001  minijtro    minijtro@localhost    2021-02-16 2021-02-16 11:14:55.808771 true     5

 
 
 select * from reg_servicio
 insert into sistema_externo (ip, nombre, contrasena)
 values ('192.168.54/8', 'test', 'asdñldkj')
 
 select *  from supervision

 
 
 InformeDAOImplTest, PermisoDAOImplTest, RecursoDAOImplTest, RegServicioDAOImplTest, RegistroIEDAOImplTest
 InformeDAOImplTest, RegServicioDAOImplTest
 
 
 
 
 