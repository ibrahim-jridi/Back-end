package com.login.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}
/*
 We provide the implementation for the EmailService interface in this class.   
 We are using the JavaMailSender library to send e-mails.

Note the @Async annotation on the sendEmail() method.  This instructs Spring 
to run this asynchronously so it will not wait for e-mail delivery to complete 
before continuing with other tasks like returning the page. */
}
