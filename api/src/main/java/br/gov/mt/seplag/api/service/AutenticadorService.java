package br.gov.mt.seplag.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.entity.UsuarioEntity;
import br.gov.mt.seplag.api.exception.ResourceAlreadyExistsException;
import br.gov.mt.seplag.api.mapper.UsuarioMapper;
import br.gov.mt.seplag.api.repository.UsuarioRepository;
import br.gov.mt.seplag.api.transfer.MensagemResponseTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioRequestTransfer;
import br.gov.mt.seplag.api.utility.JwtUtility;

@Service
public class AutenticadorService {
	
	private final UsuarioRepository usuarioRepository;
	
	private final JwtUtility jwtUtility;
	
	private final PasswordEncoder passwordEncoder;
	
	private final EmailService emailService;
	
	private final static String[] TYPE_PERFIL = {"ADMINISTRADOR", "USUARIO"};

	public AutenticadorService(UsuarioRepository usuarioRepository, JwtUtility jwtUtility, PasswordEncoder passwordEncoder, EmailService emailService) {
		this.usuarioRepository = usuarioRepository;
		this.jwtUtility = jwtUtility;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
	}

	public MensagemResponseTransfer registrarUsuario(UsuarioRequestTransfer usuarioRequestTransfer) {
		
		Optional<UsuarioEntity> usuarioOptional = this.usuarioRepository.findByIdentificador(usuarioRequestTransfer.getIdentificador());
		
		if (usuarioOptional.isPresent()) {
			throw new ResourceAlreadyExistsException("[Alerta] Já existe uma conta vinculada a esse e-mail!");
		}
		
		String senhaTemporaria = this.gerarSenhaTemporaria();
		
		UsuarioEntity usuarioEntity = UsuarioMapper.from(usuarioRequestTransfer);
			usuarioEntity.setChaveAcesso(passwordEncoder.encode(senhaTemporaria));
			usuarioEntity.setPerfil(usuarioRequestTransfer.getPerfil() != null ? usuarioRequestTransfer.getPerfil() : TYPE_PERFIL[1]);
		
		usuarioRepository.save(usuarioEntity);
		
		emailService.enviarEmailFormatado(
				usuarioRequestTransfer.getIdentificador(), usuarioRequestTransfer.getNome(), usuarioRequestTransfer.getChaveAcesso(), 
				EmailService.corpoEmailSenhaTemporaria(usuarioRequestTransfer.getNome(), senhaTemporaria));
		
		return new MensagemResponseTransfer("Uma senha temporária foi enviada para seu e-mail!");
		
	}
	
	private String gerarSenhaTemporaria() {
		return UUID.randomUUID().toString().substring(5, 25).replace("-", "");
	}
	
}
