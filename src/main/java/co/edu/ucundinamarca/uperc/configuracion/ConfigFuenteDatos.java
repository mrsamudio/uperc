/**
 * 
 */
package co.edu.ucundinamarca.uperc.configuracion;

import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import co.edu.ucundinamarca.uperc.persistencia.FuenteDatos;

/**
 * @author mrsamudio
 *
 */
@Configuration
//@ComponentScan
@ComponentScan(basePackages = "co.edu.ucundinamarca.uperc.persistencia")
//@EnableJpaRepositories(basePackages = {"co.edu.ucundinamarca.uperc.persistencia"})
@EnableTransactionManagement
public class ConfigFuenteDatos {

	private static Logger logger = LogManager.getLogger(ConfigFuenteDatos.class);
	
	@Bean
	public DataSource fuenteDatos() {

		try {
			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setDriverClassName("org.postgresql.Driver");
			ds.setUrl("jdbc:postgresql://192.168.100.183:5432/uperctest");
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
	 * 
	 * @return
	 */
	public Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		hibernateProp.put("hibernate.format_sql", true);
		hibernateProp.put("hibernate.jdbc.batch_size", 10);
		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
		hibernateProp.put("hibernate.max_fetch_depth", 3);
		hibernateProp.put("hibernate.show_sql", true);
		hibernateProp.put("hibernate.use_sql_comments", true);
		return hibernateProp;
	}
	
	@Bean
	public SessionFactory factoriaSesion() throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(fuenteDatos());
		sessionFactoryBean.setPackagesToScan("co.edu.ucundinamarca.uperc.persistencia");
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(factoriaSesion());
	}
	
}
