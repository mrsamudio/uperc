select * from usuario;

delete from configuracion c 
where c.id= 8;


update usuario
set tipoid = 'C'


    2 Ad         Ministro                C      00000001  minijtro    minijtro@localhost    1900-01-01 2021-01-18 05:25:59.0 true     5


  
  
  
 ALTER TABLE usuario ALTER tipoid TYPE varchar(1);
 ALTER TABLE usuario ALTER numid TYPE varchar(150);
 ALTER TABLE usuario ALTER contrasena TYPE varchar(150);
 ALTER TABLE usuario ALTER COLUMN fechareg TYPE timestamp without time zone USING fechareg::TIMESTAMP;--'YYYY-MM-DD HH24:MI:SS'
	
 ALTER TABLE usuario ALTER tipoid TYPE varchar(1);