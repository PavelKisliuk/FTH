/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.singupservice;

import com.pavelkisliuk.fth.exception.FthMailException;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The {@code FthMailSender} is class for sending unique key new users for confirm registration.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthSingUp
 * @since 12.0
 */
class FthMailSender {
	private static final String USERNAME = "fthelperservices@gmail.com";
	private static final String PASSWORD = "FTHServicesPavelKisliuk210194OLINKdjoankdjoank";
	private static final String SUBJECT = "Confirmation registration";
	private Properties props;


	FthMailSender() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

	/**
	 * Send unique key new user.
	 * <p>
	 *
	 * @param text    is message for user with unique key.
	 * @param toEmail is user e-mail.
	 * @throws FthMailException if can't sen e-mail.
	 */
	void send(String text, String toEmail) throws FthMailException {
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			//from
			message.setFrom(new InternetAddress(USERNAME));
			//to
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			//Header
			message.setSubject(SUBJECT);
			//Mail
			message.setText(text);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new FthMailException("MessagingException in FthMailSender -> send()", e);
		}
	}
}