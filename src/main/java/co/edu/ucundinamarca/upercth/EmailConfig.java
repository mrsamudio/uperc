package co.edu.ucundinamarca.upercth;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties props = mailSender.getJavaMailProperties();
		mailSender.setHost("your-host.name");
		mailSender.setPort(465);
		mailSender.setUsername("your-username@your-hostname");
		mailSender.setPassword("Your email password ;)");

		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		return mailSender;
	}

	@Bean
	public SimpleMailMessage emailTemplate() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("your-username@your-hostname");
		message.setFrom("your-username@your-hostname");
		message.setText("FATAL - Application crash. Save your job !!");
		message.setSubject("El asunto mejor pagado del mundo");
		return message;
	}
}
