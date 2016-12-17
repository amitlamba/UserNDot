package com.userndot.email.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.Test;

public class AmazonSesMailSenderTest {

	@Test
	public void testSendEmail() {
		AmazonSesMailSender mailSender = new AmazonSesMailSender();
		mailSender.setEmailDetails("admin@amcatmail.com", new String[]{"amit.lamba@aspiringminds.in"}, "Test", "Test", null);
		try {
			mailSender.sendEmail();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}

}
