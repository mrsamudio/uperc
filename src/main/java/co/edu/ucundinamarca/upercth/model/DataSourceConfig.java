package co.edu.ucundinamarca.upercth.model;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
	
	private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	/**
	 * 
	 * Configuración relacionada con el motor de base de datos
	 * @return
	 */
	@Bean(name = "dataSource")
	public DataSource fuenteDatos() {

		try {
			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setDriverClassName("org.postgresql.Driver");
			ds.setUrl("jdbc:postgresql://192.168.100.183:5432/upercfinaltest");
			ds.setUsername("msamudio");
			ds.setPassword("msamudio");
//			ds.setSchema("public");
			return ds;
		} catch (Exception e) {
			logger.error("Hubo un error al construir la base de datos de pruebas!", e);
			return null;
		}
	}
	
	/**
	 * Propiedades Hibernate
	 * 
	 * @return
	 */
	public Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		hibernateProp.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
		hibernateProp.put("hibernate.format_sql", true);
		hibernateProp.put("hibernate.jdbc.batch_size", 10);
		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
		hibernateProp.put("hibernate.max_fetch_depth", 3);
		hibernateProp.put("hibernate.show_sql", true);
		hibernateProp.put("hibernate.use_sql_comments", true);
		hibernateProp.put("hibernate.jdbc.time_zone", "America/Bogota");
		return hibernateProp;
	}
	
	
	/**
	 * Factoria de sessión
	 * 
	 * @return
	 * @throws IOException
	 */
	@Bean
	public SessionFactory factoriaSesion() throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(fuenteDatos());
		sessionFactoryBean.setPackagesToScan("co.edu.ucundinamarca.upercth.model");
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}
	
	
	/**
	 * Gestor de transacción
	 * 
	 * @return
	 * @throws IOException
	 */
	@Bean
	public PlatformTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(factoriaSesion());
	}
	
}
