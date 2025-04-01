package pl.matzysz.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class MailConfig {

    @Value("${ticket-king.mail.host}")
    private String mailHost;

    @Value("${ticket-king.mail.port}")
    private String mailPort;

    @Value("${ticket-king.mail.username}")
    private String mailUsername;

    @Value("${ticket-king.mail.password}")
    private String mailPassword;

    @Value("${ticket-king.mail.protocol}")
    private String mailProtocol;

    @Value("${ticket-king.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${ticket-king.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProtocol);
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        props.put("mail.debug", "true"); // optional: logs email sending

        return mailSender;
    }
}
