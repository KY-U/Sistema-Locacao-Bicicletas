package br.ufscar.dc.dsw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailService {

	public void send(InternetAddress from, InternetAddress to, String subject, String body, File file) {

		try {

			Properties prop = new Properties();
			prop.put("mail.smtp.auth", true);
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
			prop.put("mail.smtp.port", "2525");
			prop.put("mail.smtp.ssl.trust", "sandbox.smtp.mailtrap.io");

				final String username = "6106b9066275b1";
				final String password = "4ddbef80160365";

				Session session = Session.getInstance(prop, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

			Message message = new MimeMessage(session);
			message.setFrom(from);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(subject);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(body, "text/plain");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			
			if (file != null) {
				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				attachmentBodyPart.attachFile(file);
				multipart.addBodyPart(attachmentBodyPart);	
			}
			
			message.setContent(multipart);
			Transport.send(message);
			
			System.out.println("Mensagem enviada com sucesso para o email: " + to);
			
		} catch (Exception e) {
			System.out.println("Mensagem não enviada!");
			e.printStackTrace();
		}
	}
	
	public void send(InternetAddress from, InternetAddress to, String subject, String body) {
		send(from, to, subject, body, null);
	}
}