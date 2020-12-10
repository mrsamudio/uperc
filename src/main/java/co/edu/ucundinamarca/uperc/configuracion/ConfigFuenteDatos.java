/**
 * 
 */
package co.edu.ucundinamarca.uperc.configuracion;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * @author mrsamudio
 *
 */
@Configuration
public class ConfigFuenteDatos {

	@Profile("Desarrollo")
	@Bean
	public DataSource fuenteDatosEmbebida() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.addScript("classpath:test-data.sql")
				.build();
	}
	
	@Profile("PruebasCalidad")
	@Bean
	public DataSource datos() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://192.168.122.41:5432/PUC");
		ds.setUsername("");
		ds.setPassword("");
		ds.setInitialSize(5);
		ds.setMaxTotal(10);
		return ds;
		
	}
	
	@Profile("Produccion")
	@Bean
	public DataSource fuenteDeDatos() {
		JndiObjectFactoryBean jndiObjectFactoryBean	= new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/UpercDS");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
		return (DataSource) jndiObjectFactoryBean.getObject();
	}
	
	@Bean
	public JdbcTemplate jdbTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	
}
