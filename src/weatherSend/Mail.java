package weatherSend;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {

	String user;
	String password;
	String host;
	
	private Properties properties;
	private Session session;
	private MimeMessage message;
	
	
	Mail(String usr, String psswd){
		this.user = usr;
		this.password = psswd;
		this.host = "smtp."+ usr.substring(usr.lastIndexOf("@")+1);
	}
	
	private void setProperties() {
		properties = System.getProperties();

		properties.put("mail.smtp.user", user);
	   	properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
	}
	
	private void setSession() {
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, password);
						}
				}
		);
	}
	
	private void createMessage(String [] to, String subject, String emailText) {
		try {
		message = new MimeMessage(session);
		message.setFrom(user);
		for(String iter:to) {
			message.addRecipient(Message.RecipientType.TO, new javax.mail.internet.InternetAddress(iter));
		}
		message.setSubject(subject);
		message.setText(emailText);
		}catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(String [] to, String subject, String emailText) {
		setProperties();
		setSession();
		createMessage(to, subject, emailText);		
		Transport transport = null;
		
		try {
		transport = session.getTransport("smtp");
		transport.connect(host, user, password);
		transport.sendMessage(message, message.getAllRecipients());
		System.out.println("Mails succesfully send.");
		}catch(MessagingException e) {
			e.printStackTrace();
		}finally {
			if(transport != null) {
				try {
					transport.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	
	public static void main(String [] args) {
		//	Mail mail = new Mail("yourEmail@email.com", "YourPasswrd");
		//	String [] toArray = {"recipient@email.com"};
		//	mail.sendMail(toArray, "Subject Text", "Email Body");
	}
}
