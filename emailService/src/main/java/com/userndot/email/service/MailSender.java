package com.userndot.email.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public abstract class MailSender {

	public abstract void setEmailDetails(String from, String[] recepients, String subject, String body, String[] attachmentPaths);
	
	public abstract void sendEmail() throws AddressException, MessagingException, IOException;
}
