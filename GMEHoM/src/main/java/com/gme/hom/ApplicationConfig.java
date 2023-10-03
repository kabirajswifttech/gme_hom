package com.gme.hom;

import java.util.Properties;

// import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.filter.CommonsRequestLoggingFilter;



@Configuration
class ApplicationConfig {

	// Moved datasource config to application.properties

	/*
	 * @Bean DataSource dataSource() {
	 * 
	 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 * 
	 * dataSource.setDriverClassName(GlobalConfig.DB_DRIVER);
	 * dataSource.setUsername(GlobalConfig.DB_USERNAME);
	 * dataSource.setPassword(GlobalConfig.DB_PASSWORD);
	 * dataSource.setUrl(GlobalConfig.DB_URL);
	 * 
	 * return dataSource; }
	 */

	@Bean
	JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(GlobalConfig.MAIL_SERVER_HOST);
		mailSender.setPort(GlobalConfig.MAIL_SERVER_PORT);
		mailSender.setUsername(GlobalConfig.MAIL_SERVER_USERNAME);
		mailSender.setPassword(GlobalConfig.MAIL_SERVER_PASSWORD);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", GlobalConfig.MAIL_SERVER_PROTOCOL);
		props.put("mail.smtp.auth", GlobalConfig.MAIL_SERVER_AUTH);
		props.put("mail.smtp.starttls.enable", GlobalConfig.MAIL_SERVER_STARTTLS);
		props.put("mail.debug", GlobalConfig.MAIL_DEBUG);

		return mailSender;
	}

	@Bean
	CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setMaxPayloadLength(1000);
		return loggingFilter;
	}

}
