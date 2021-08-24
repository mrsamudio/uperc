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
        mailSender.setHost("server295.web-hosting.com");
        mailSender.setPort(465);
        mailSender.setUsername("proyectouperc@ingsamudio.co");
        mailSender.setPassword("lPpigH @Vu! ]y ? b1oc}(#");
        
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.auth", "true");
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
        
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		

//        mailSender.setHost("smtp.office365.com");
//		mailSender.setHost("outlook.office365.com");
//		mailSender.setPort(587);

//		mailSender.setUsername("mrsamudio@ucundinamarca.edu.co");


//		props.put("mail.smtp.tls.enable", "true");

//		props.put("mail.smtp.ssl.enable", "true");
//		props.put("smtpd_recipient_restrictions", "permit_sasl_authenticated");
//		props.put("mail.smtp.socketFactory.fallback", "true");
		

		return mailSender;
	}

	@Bean
	public SimpleMailMessage emailTemplate() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("ingsjobs@gmail.com");
		message.setFrom("proyectouperc@ingsamudio.co");
//		message.setFrom("noreply@cv.ingsamudio.co");
		message.setText("FATAL - Application crash. Save your job !!");
		message.setSubject("El asunto mejor pagado del mundo");
		return message;
	}
}
