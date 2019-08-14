/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.singup;

import com.pavelkisliuk.fth.exception.FthMailException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The {@code MailSender} is class for sending unique key new users for confirm registration.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see SingUpService
 * @since 12.0
 */
class MailSender {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String PROPERTIES_URL = "mail.properties";
	private static final String KEY_USER_NAME = "username";
	private static final String KEY_USER_PASSWORD = "password";
	private static final String KEY_USER_SUBJECT = "subject";

	private static final Properties MAIL_PROPERTIES = new Properties();

	static {
		try (InputStream fileInputStream =
					 MailSender.class.getClassLoader().getResourceAsStream(PROPERTIES_URL)) {
			if (fileInputStream == null) {
				throw new FthMailException(
						"null input stream in MailSender -> MailSender().");
			}
			MAIL_PROPERTIES.load(fileInputStream);
		} catch (IOException e) {
			LOGGER.log(Level.ERROR,
					"IOException in MailSender -> static!!!");
		} catch (FthMailException e) {
			LOGGER.log(Level.ERROR,
					"FthMailException in MailSender -> static!!!");
		}
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
		String userName = MAIL_PROPERTIES.getProperty(KEY_USER_NAME);
		String password = MAIL_PROPERTIES.getProperty(KEY_USER_PASSWORD);
		String subject = MAIL_PROPERTIES.getProperty(KEY_USER_SUBJECT);
		Session session = Session.getInstance(MAIL_PROPERTIES, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			//from
			message.setFrom(new InternetAddress(userName));
			//to
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			//Header
			message.setSubject(subject);
			//Mail
			message.setText(text);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new FthMailException("MessagingException in MailSender -> send()", e);
		}
	}
}