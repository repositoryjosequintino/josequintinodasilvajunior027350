package br.gov.mt.seplag.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private static final Logger log = LoggerFactory.getLogger(EmailService.class);

	private final JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String emailRemetente;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void enviarEmail(String emailDestinatario, String nomeUsuario, String senhaUsuario, String corpoEmail) {
		try {
			
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setFrom(emailRemetente);
				simpleMailMessage.setTo(emailDestinatario);
				simpleMailMessage.setSubject("SEPLAG-MT - Dados de Acesso à Plataforma");
				simpleMailMessage.setText(corpoEmail);
				
				javaMailSender.send(simpleMailMessage);
				
				log.info("[Sucesso] Email enviado com sucesso para o usuário {}", emailDestinatario);

		} catch (Exception exception) {
			log.error("Falha ao tentar encaminhar email para o destinatário {}. Motivo: {}", emailDestinatario, exception.getMessage());
			throw new RuntimeException("[Falha] Erro ao tentar encaminhar e-mail");
		}
	}
	
	public void enviarEmailFormatado(String emailDestinatario, String nomeUsuario, String senhaUsuario, String corpoEmailHtml) {
	    try {

	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		        mimeMessageHelper.setFrom(emailRemetente);
		        mimeMessageHelper.setTo(emailDestinatario);
		        mimeMessageHelper.setSubject("SEPLAG-MT - Dados de Acesso à Plataforma");
		        mimeMessageHelper.setText(corpoEmailHtml, true);

	        javaMailSender.send(mimeMessage);

	        log.info("[Sucesso] Email enviado com sucesso para {}", emailDestinatario);

	    } catch (Exception e) {
	        log.error("Falha ao enviar email para {}. Motivo: {}", emailDestinatario, e.getMessage());
	        throw new RuntimeException("[Falha] Erro ao tentar encaminhar e-mail");
	    }
	}


	public static String corpoEmailSenhaTemporaria(String nomeUsuario, String senhaUsuario) {
		String mensagemEmail = String.format(
				"<html>"
				+ "<body style='color: #333;'>" 
				+ "<p>Olá <b>%s</b>, seja bem-vindo(a)!</p>"
				+ "<p>Seu acesso à plataforma foi criado com sucesso.</p>"
				+ "<p><strong>Senha temporária:</strong> <span style='color:#2c3e50;'><h3>%s</h3></span></p>"
				+ "<p>Por motivos de segurança, essa senha é válida apenas para o primeiro acesso.<br>"
				+ "Ao entrar na plataforma, você deverá alterá-la imediatamente.</p>"
				+ "Qualquer dúvida, nossa equipe está à disposição.</p>"
				+ "<p style='color:#555;'>Equipe SEPLAG-MT</p>" 
				+ "</body>" 
				+ "</html>",
				nomeUsuario, senhaUsuario);
		return mensagemEmail;
	}
	
	public static String corpoEmailBemVindo(String nomeUsuario, String senhaUsuario) {
		return String.format(
				"<html>" 
				+ "<body style='font-family: Arial, sans-serif; color: #333;'>"
				+ "<div style='max-width:600px; margin:auto; padding:20px; border:1px solid #e0e0e0; border-radius:8px;'>"
				+ "<h2 style='color:#183181;'>Olá %s, seja bem-vindo à Plataforma SEPLAG-MT!</h2>"
				+ "<p>Estamos muito felizes em ter você conosco. Seu acesso foi criado com sucesso e já está disponível.</p>"
				+ "<p><strong>Senha temporária:</strong> <span style='color:#183181;'>%s</span></p>"
				+ "<p style='margin-top:15px;'>Por motivos de segurança, essa senha é válida apenas para o primeiro acesso.<br>"
				+ "Ao entrar na plataforma, você deverá alterá-la imediatamente.</p>"
				+ "<p style='margin-top:20px;'>Explore os recursos disponíveis e conte conosco sempre que precisar.<br>"
				+ "Qualquer dúvida, nossa equipe está à disposição.</p>"
				+ "<hr style='margin:30px 0; border:none; border-top:1px solid #ccc;'/>"
				+ "<p style='color:#183181; font-size:14px; font-weight:bold;'>Equipe SEPLAG-MT</p>" 
				+ "</div>"
				+ "</body>" 
				+ "</html>", 
				nomeUsuario, senhaUsuario);
	}

}
