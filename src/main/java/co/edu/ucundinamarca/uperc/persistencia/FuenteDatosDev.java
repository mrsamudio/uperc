/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author mrsamudio
 *
 */
@Component
@Profile("dev")
@Configuration
//@ComponentScan
////@ComponentScan(basePackages = "co.edu.ucundinamarca.uperc")
////@ComponentScan(basePackages = "co.edu.ucundinamarca.uperc.persistencia")
//@EnableTransactionManagement
public class FuenteDatosDev implements FuenteDatos {

	private static Logger logger = LogManager.getLogger(FuenteDatosDev.class);

	@Override
//	@Bean
	public DataSource fuenteDatos() {
		try {
			logger.warn("Fuente de datos para el entorno de DESARROLLO dev");
			
			EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
			return dbBuilder.setType(EmbeddedDatabaseType.H2)
					.addScripts("classpath:sql/dev-schema.sql", "classpath:sql/dev-data.sql").build();
		} catch (Exception e) {
			
			logger.error("Hubo un error al construir la base de datos embebida!", e);
			return null;
		}
	}

	@Override

	public Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProp.put("hibernate.format_sql", true);
//		hibernateProp.put("hibernate.jdbc.batch_size", 10);
//		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
//		hibernateProp.put("hibernate.max_fetch_depth", 3);
		hibernateProp.put("hibernate.show_sql", true);
		hibernateProp.put("hibernate.use_sql_comments", true);
		return hibernateProp;
	}

	@Override
//	@Bean
	public SessionFactory factoriaSesion() throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(fuenteDatos());
		sessionFactoryBean.setPackagesToScan("co.edu.ucundinamarca.uperc");
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Override
//	@Bean
	public PlatformTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(factoriaSesion());
	}

}
