package com.userndot.email.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.userndot.exception.UserNDotException;

@Named
public class AmazonSesMailSender extends MailSender {

	// IMPORTANT: To successfully send an email, you must replace the values of
	// the strings below with your own values.
	private String emailFrom = "admin@amcatmail.com";
	private String EMAIL_REPLY_TO = "admin@amcatmail.com";
	private String[] emailTo = { "amit.lamba@aspiringminds.in" };
	private String[] attachmentPaths = null;
	private static final Regions AWS_REGION = Regions.US_EAST_1;

	private static String emailSubject = "Amazon SES email test";
	private static String emailBody = "This MIME email was sent through Amazon SES using SendRawEmail.";

	@Override
	public void setEmailDetails(String from, String[] recepients,
			String subject, String body, String[] attachmentPaths) {
		this.emailFrom = from;
		this.emailTo = recepients;
		this.attachmentPaths = attachmentPaths;
	}

	@Override
	public void sendEmail() throws AddressException, MessagingException,
			IOException {
		Session session = Session.getDefaultInstance(new Properties());
		MimeMessage message = new MimeMessage(session);
		message.setSubject(emailSubject, "UTF-8");

		message.setFrom(new InternetAddress(emailFrom));
		message.setReplyTo(new Address[] { new InternetAddress(EMAIL_REPLY_TO) });
		message.setRecipients(Message.RecipientType.TO,
				this.toInternetAddresses());

		// Cover wrap
		MimeBodyPart wrap = new MimeBodyPart();

		// Alternative TEXT/HTML content
		MimeMultipart cover = new MimeMultipart("alternative");
		MimeBodyPart html = new MimeBodyPart();
		cover.addBodyPart(html);

		wrap.setContent(cover);

		MimeMultipart content = new MimeMultipart("related");
		message.setContent(content);
		content.addBodyPart(wrap);

		String[] attachmentsFiles = attachmentPaths;

		// This is just for testing HTML embedding of different type of
		// attachments.
		StringBuilder sb = new StringBuilder();

		try {
			for (String attachmentFileName : attachmentsFiles) {
				String id = UUID.randomUUID().toString();
				sb.append("<img src=\"cid:");
				sb.append(id);
				sb.append("\" alt=\"ATTACHMENT\"/>\n");

				MimeBodyPart attachment = new MimeBodyPart();

				DataSource fds = new FileDataSource(attachmentFileName);
				attachment.setDataHandler(new DataHandler(fds));
				attachment.setHeader("Content-ID", "<" + id + ">");
				attachment.setFileName(fds.getName());

				content.addBodyPart(attachment);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		html.setContent("<html><body><h1>HTML</h1>\n" + emailBody
				+ "</body></html>", "text/html");

		try {
			System.out
					.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			/*
			 * The ProfileCredentialsProvider will return your [default]
			 * credential profile by reading from the credentials file located
			 * at (~/.aws/credentials).
			 * 
			 * TransferManager manages a pool of threads, so we create a single
			 * instance and share it throughout our application.
			 */
			AWSCredentials credentials = null;
			try {
				credentials = new BasicAWSCredentials("AKIAJLM3NCBWV7DEY5NQ", "Ak3e+gopZrDAjx5l9xWEjNHN3ZXNxMph6S2J91wG3KVD");
			} catch (Exception e) {
				throw new AmazonClientException(
						"Cannot load the credentials from the credential profiles file. "
								+ "Please make sure that your credentials file is at the correct "
								+ "location (~/.aws/credentials), and is in valid format.",
						e);
			}

			// Instantiate an Amazon SES client, which will make the service
			// call with the supplied AWS credentials.
			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(
					credentials);
			Region REGION = Region.getRegion(AWS_REGION);
			client.setRegion(REGION);

			// Print the raw email content on the console
			PrintStream out = System.out;
			message.writeTo(out);

			// Send the email.
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			message.writeTo(outputStream);
			RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream
					.toByteArray()));

			SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(
					rawMessage);
			client.sendRawEmail(rawEmailRequest);
			System.out.println("Email sent!");

		} catch (Exception ex) {
			System.out.println("Email Failed");
			System.err.println("Error message: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	private InternetAddress[] toInternetAddresses() {
		try {
			return InternetAddress.parse(String.join(";", this.emailTo));
		} catch (AddressException e) {
			e.printStackTrace();
			throw new UserNDotException(e.getMessage());
		}
	}

	public static void main(String[] args) throws AddressException,
			MessagingException, IOException {

		// Setup AmazonSimpleEmailServiceClient
		String keyID = "AKIAJLM3NCBWV7DEY5NQ";// <your key id>
		String secretKey = "Ak3e+gopZrDAjx5l9xWEjNHN3ZXNxMph6S2J91wG3KVD";// <your
																			// secret
																			// key>

		BasicAWSCredentials credentials = new BasicAWSCredentials(keyID,
				secretKey);
		AmazonSimpleEmailServiceClient amazonSimpleEmailService = new AmazonSimpleEmailServiceClient(
				credentials);
		Properties props = new Properties();
		// sets SMTP server properties
		props.setProperty("mail.transport.protocol", "aws");
		props.setProperty("mail.aws.user", credentials.getAWSAccessKeyId());
		props.setProperty("mail.aws.password", credentials.getAWSSecretKey());

		// attachment
		String[] attachFiles = new String[7];
		// attachFiles[0] = "d:/jdbc.txt";
		// attachFiles[1] = "d:/collection.txt";
		// attachFiles[2] = "d:/multithreading.txt";
		// attachFiles[3] = "d:/io.txt";
		// attachFiles[4] = "d:/string.txt";
		// attachFiles[5] = "d:/exception.txt";
		// attachFiles[6] = "d:/overloading.txt";

		// Recipient
		String[] to = { "amit.lamba@aspiringminds.in" };

		// creates a new session with an authenticator
		Session mailSession = Session.getInstance(props);

		// Create an email
		MimeMessage msg = new MimeMessage(mailSession);

		// Sender and recipient
		msg.setFrom(new InternetAddress("Amit Lamba <admin@amcatmail.com>"));

		// msg.setRecipient( Message.RecipientType.TO, new
		// InternetAddress("abc  <abc@gmail.com>"));

		// for multipal Recipient
		InternetAddress[] toAddresses = new InternetAddress[to.length];
		for (int i = 0; i < toAddresses.length; i++) {
			toAddresses[i] = new InternetAddress(to[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, toAddresses);

		// Subject
		msg.setSubject("MIME email test");

		// creates message part
		BodyPart part = new MimeBodyPart();
		String myText = "<h1>Hello, this is a test</h1>";
		part.setContent(myText, "text/html");

		// Add a MIME part to the message
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(part);
		BodyPart attachment = null;
		// for (String filename : attachFiles) {
		// attachment = new MimeBodyPart();
		// DataSource source = new FileDataSource(filename);
		// attachment.setDataHandler(new DataHandler(source));
		// attachment.setFileName(source.getName());
		// mp.addBodyPart(attachment);
		// }

		// sets the multi-part as e-mail's content
		msg.setContent(mp);

		// Print the raw email content on the console
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		msg.writeTo(out);

		// Send Mail
		RawMessage rm = new RawMessage();
		rm.setData(ByteBuffer.wrap(out.toString().getBytes()));
		amazonSimpleEmailService.sendRawEmail(new SendRawEmailRequest()
				.withRawMessage(rm));

		System.out.println("mail send .... good luck ");
	}

}