package org.jsp.e_commerce.service;

import org.jsp.e_commerce.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static org.jsp.e_commerce.util.Application_Constants.VERIFY_LINK;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class E_CommerceAppEmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public String sendWellcomeMail(Merchant merchant, HttpServletRequest request) {
		String siteUrl = request.getRequestURL().toString();
		String url = siteUrl.replace(request.getServletPath(), "");
		String actualUrl = url + VERIFY_LINK + merchant.getToken();
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);

		try {
			helper.setTo(merchant.getEmail());
			helper.setSubject("Account Verification Mail");
			helper.setText(actualUrl);
			javaMailSender.send(msg);
			return "Verification Mail Has Been Sent";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Cannot Send Verification Email";
		}
	}
}
