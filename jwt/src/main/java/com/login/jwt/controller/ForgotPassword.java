package com.login.jwt.controller;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.login.jwt.entity.User;
import com.login.jwt.service.UserService;
import com.login.jwt.util.Utility;

import net.bytebuddy.utility.RandomString;
@Controller
public class ForgotPassword {
	 @Autowired
	    private JavaMailSender mailSender;
	     
	    @Autowired
	    private UserService userService;
	    
	    
	    @GetMapping("/forgot_password")
	    public String showForgotPasswordForm() {
	    	return "forgot_password_form";
	    }
	 
	    @PostMapping("/forgot_password")
	    public String processForgotPassword(HttpServletRequest request, Model model) {
	    	String email = request.getParameter("email");
	        String token = RandomString.make(30);
	         
	        try {
	            //userService.updateResetPasswordToken(token, email);
	        	
	            //generate password link
	            /* a random String is generated as reset password token 
	             * using the RandomString class from the net.bytebuddy.utility package*/
	            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	            // send email
	            sendEmail(email, resetPasswordLink);
	            model.addAttribute("message", "nous avons envoyé un lien de restauration à votre Email.");
	             
	        } catch (UsernameNotFoundException ex) {
	            model.addAttribute("error", ex.getMessage());
	        } catch (UnsupportedEncodingException | MessagingException e) {
	            model.addAttribute("error", "erreur on envoyant l'email");
	        }
	             
	        return "forgot_password_form";
	    }
	     
	    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException  {
			// TODO Auto-generated method stub
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
				helper.setFrom("cristianoibrahim66@gmail.com", "support team");
				helper.setTo(email);
				String subject ="un lien pour restaurer votre mot de passe";
				String content = "<p>bonjour,</p>"
			            + "<p>ici c'est le lein pour restaurer votre mot de passe.</p>"
			            + "<p>clique sur le lien :</p>"
			            + "<p><a href=\"" + resetPasswordLink + "\">Changé mon mot de passe</a></p>"
			            + "<br>"
			            + "<p>ignorer cette email si vous trouver votre mot de passe, "
			            + "ou vous n'éssaye pas de le restaurer .</p>";
			     
			    helper.setSubject(subject);
			     
			    helper.setText(content, true);
			     
			    mailSender.send(message);
			
		}

		public void sendEmail(){
	 
	    }  
	     
	     
	   /* @GetMapping("/reset_password")
	    
	    	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    	   User user = userService.getByResetPasswordToken(token);
	    	    model.addAttribute("token", token);
	    	     
	    	    if (user == null) {
	    	        model.addAttribute("message", "Invalid Token");
	    	        return "message";
	    	    }
	    	     
	    	    return "reset_password_form";
	    }                     
	    	     
	    @PostMapping("/reset_password")
	   
	    	public String processResetPassword(HttpServletRequest request, Model model) {
	    	    String token = request.getParameter("token");
	    	    String password = request.getParameter("password");
	    	     
	    	    User customer = userService.getByResetPasswordToken(token);
	    	    model.addAttribute("title", "restaurer ton mot de passe");
	    	     
	    	    if (customer == null) {
	    	        model.addAttribute("message", "Invalid Token");
	    	        return "message";
	    	    } else {           
	    	        userService.updatePassword(customer, password);
	    	         
	    	        model.addAttribute("message", "votre mot de passe est changé avec succés.");
	    	    }
	    	     
	    	    return "message";
	    }*/
}
