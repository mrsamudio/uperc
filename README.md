# uperc
SISTEMA DE INFORMACIÓN PARA LA GESTIÓN DE PARQUEADEROS DE LA UNIVERSIDAD DE CUNDINAMARCA SEDE CHÍA



Pasos para usar el modelo de entidad relacion en  la base de datos de postgresql

1. Crear usuario
`CREATE USER msamudio WITH CREATEDB PASSWORD 'msamudio';`

2. Crear base de datos antes del script
`CREATE DATABASE "upercfinaltest"
    WITH OWNER "msamudio" ENCODING 'ISO88591'
    TEMPLATE template0;`
    
3.  Conversión del archivo de ejecución de la base de datos sql(Solo para linux)
`iconv -f 'ISO 8859-1' -t utf-8 'Modelo fisico de base de datos.sql' > MFBD.sql`

4. ejecutar script sql
`psql -U msamudio -h 192.168.100.183 -f MFBD.sql upercfinaltest`
