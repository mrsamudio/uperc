select * from informe;

delete from configuracion c 
where c.id= 8;


update usuario
set fechanac = current_timestamp


    2 Ad         Ministro                C      00000001  minijtro    minijtro@localhost    1900-01-01 2021-01-18 05:25:59.0 true     5


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
 
 delete from usuario where id = 77;
 update usuario
 set
 nombres ='Ad', apellidos ='Ministro', tipoid ='C', numid = '00000001', contrasena ='minijtro', correo ='minijtro@localhost',
 fechanac ='2021-02-16'::date, fechareg ='2021-02-16 11:14:55.808771'::timestamp, estado = true, rol = 5
 WHERE id = 2;
 select * from usuario;
 
 