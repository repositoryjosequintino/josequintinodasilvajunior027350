package br.gov.mt.seplag.api.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.mt.seplag.api.entity.UsuarioEntity;
import br.gov.mt.seplag.api.exception.InvalidCredentialException;
import br.gov.mt.seplag.api.exception.InvalidTokenException;
import br.gov.mt.seplag.api.exception.ResourceAlreadyExistsException;
import br.gov.mt.seplag.api.exception.ResourceNotFoundException;
import br.gov.mt.seplag.api.exception.TokenExpiredException;
import br.gov.mt.seplag.api.mapper.UsuarioMapper;
import br.gov.mt.seplag.api.repository.UsuarioRepository;
import br.gov.mt.seplag.api.transfer.ForgotPasswordRequestTransfer;
import br.gov.mt.seplag.api.transfer.MensagemResponseTransfer;
import br.gov.mt.seplag.api.transfer.RefreshTokenRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioAcessarRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioRequestTransfer;
import br.gov.mt.seplag.api.transfer.UsuarioResponseTransfer;
import br.gov.mt.seplag.api.utility.JwtUtility;

@Service
public class AutenticadorService {

	private static final Logger log = LoggerFactory.getLogger(AutenticadorService.class);
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
	
	public UsuarioResponseTransfer acessarConta(UsuarioAcessarRequestTransfer usuarioAcessarRequestTransfer) {
		
		System.out.println(new Date());

		UsuarioEntity usuarioEntity = this.usuarioRepository
				.findByIdentificador(usuarioAcessarRequestTransfer.getIdentificador())
				.orElseThrow(() -> new InvalidCredentialException("Os dados informados não são válidos!"));
		
		if (!passwordEncoder.matches(usuarioAcessarRequestTransfer.getChaveAcesso(), usuarioEntity.getChaveAcesso())) {
			throw new InvalidCredentialException("Os dados informados não são válidos!!");
		}
		
		String accessToken = this.jwtUtility.generateAccessToken(usuarioEntity.getCode(), usuarioEntity.getNome(),
				usuarioEntity.getIdentificador(), usuarioEntity.getPerfil());
		
		String refreshToken = this.jwtUtility.generateRefreshToken(usuarioEntity.getCode(), usuarioEntity.getIdentificador());
		
			usuarioEntity.setAccessToken(accessToken);
			usuarioEntity.setRefreshToken(refreshToken);
		
		this.usuarioRepository.save(usuarioEntity);

		return UsuarioMapper.from(usuarioEntity);
	}
	
	public UsuarioResponseTransfer refreshAccessToken(RefreshTokenRequestTransfer refreshTokenRequestTransfer) {

		String refreshToken = refreshTokenRequestTransfer.getRefreshToken();
		
		String identificador = jwtUtility.extractIdentificadorToken(refreshToken);

		log.info("[AutenticadorService] Acionando a funcionalidade de RefresToken para o ususario: {}", identificador);
		
		if (!jwtUtility.isRefreshToken(refreshToken)) {
			throw new InvalidTokenException("(AutenticadorService) O Token informado é inválido!");
		}
		
		UsuarioEntity usuarioEntity = usuarioRepository.findByRefreshToken(refreshToken)
				.orElseThrow(() -> new InvalidTokenException("(AutenticadorService) O Token informado está expirado ou inválido!!"));
		
		if(!jwtUtility.validateToken(refreshToken, identificador)) {
			throw new TokenExpiredException("(AutenticadorService) O Token informado está expirado ou inválido!!!");
		}
		
		String accessTokenNew = this.jwtUtility.generateAccessToken(usuarioEntity.getCode(), usuarioEntity.getNome(),
				usuarioEntity.getIdentificador(), usuarioEntity.getPerfil());
		
			usuarioEntity.setAccessToken(accessTokenNew);
			usuarioEntity.setRefreshToken(refreshToken);
			usuarioEntity.setUpdatedAt(Instant.now());
			
			this.usuarioRepository.save(usuarioEntity);
		
		return UsuarioMapper.from(usuarioEntity);
		
	}
	
	public MensagemResponseTransfer forgotPassword(ForgotPasswordRequestTransfer forgotPasswordRequestTransfer) {
		
		UsuarioEntity usuarioEntity = usuarioRepository
				.findByIdentificador(forgotPasswordRequestTransfer.getIdentificador())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado para o e-mail: "
						+ forgotPasswordRequestTransfer.getIdentificador()));
		
		String senhaTemporaria = this.gerarSenhaTemporaria();
		
			usuarioEntity.setChaveAcesso(senhaTemporaria);
			usuarioEntity.setUpdatedAt(Instant.now());
			
			this.usuarioRepository.save(usuarioEntity);
			
			emailService.enviarEmailFormatado(
					usuarioEntity.getIdentificador(), usuarioEntity.getNome(), usuarioEntity.getChaveAcesso(), 
					EmailService.corpoEmailSenhaTemporaria(usuarioEntity.getNome(), senhaTemporaria));
		
		return new MensagemResponseTransfer("Uma nova senha temporária foi enviada para seu e-mail!");
		
	}
	
	private String gerarSenhaTemporaria() {
		return UUID.randomUUID().toString().substring(5, 25).replace("-", "");
	}
	
}
