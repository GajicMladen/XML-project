package main.java.com.xws.a1document.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private final String SUBJECT = "eUprava - Rešenje zahteva";
	
	private void SendSimpleMessage(String to, String subject, String text, File file) throws Exception {		
		
		MimeMessage message = mailSender.createMimeMessage();
	    
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    helper.setFrom("rideshare2023@outlook.com");
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);
	    
	    helper.addAttachment("resenje.pdf", new FileSystemResource(file));
	    
	    mailSender.send(message);
	}
	
	public void sendRequestApproved(String email, File file) throws Exception {
		String body = "Poštovani, \n";
		body += "Vaš zahtev za unošenje u evidenciju i deponovanje autorskih dela je odobren.";
		SendSimpleMessage(email, SUBJECT, body, file);
	}
	
	public void sendRequestDenied(String email, String reason, File file) throws Exception {
		String body = "Poštovani, \n";
		body += "Vaš zahtev za unošenje u evidenciju i deponovanje autorskih dela je odbijen. \n";
		body += "Razlog odbijanja: " + reason;
		SendSimpleMessage(email, SUBJECT, body, file);
	}
}
