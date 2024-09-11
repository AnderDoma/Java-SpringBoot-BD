package br.com.saude.consulta.service.auth;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.saude.config.jwt.config.TokenValidation;
import br.com.saude.dto.login.LoginDTO;
import br.com.saude.dto.user.UserDTO;
import br.com.saude.entity.User;
import br.com.saude.repository.user.UserRepository;

@Service
public class AuthService {
	
	Logger logger = LoggerFactory.getLogger(AuthService.class);

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	UserRepository userRepository;
	
	UserRoleService userRoleService;

	TokenValidation tokenGeneration;
	
	private static String USER_PASSWORD_INVALID = "Usuário ou senha inválidos.";

	@Autowired
	public AuthService(UserRepository userRepository, UserRoleService userRoleService, TokenValidation tokenGeneration,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.userRoleService = userRoleService;
		this.tokenGeneration = tokenGeneration;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public ResponseEntity<?> realizarLogin(UserDTO userDto) {
		logger.info("Inicio consulta usuario por id");
		User responseConsulta = null;
		LoginDTO loginDto = null;
		
		try {
			
			responseConsulta = userRepository.findByCpf(userDto.getCpf());

			if (responseConsulta == null) {
				return new ResponseEntity<>(USER_PASSWORD_INVALID, HttpStatus.BAD_REQUEST);
			}
			
			if(!decodePassword(userDto.getPassword(), responseConsulta.getPassword())) {
				return new ResponseEntity<>(USER_PASSWORD_INVALID, HttpStatus.BAD_REQUEST);
			}

			if(!userDto.getUserName().equals(responseConsulta.getUsername())) {
				return new ResponseEntity<>(USER_PASSWORD_INVALID, HttpStatus.BAD_REQUEST);
			}
			
			loginDto = new LoginDTO();
			loginDto.setAccessToken(tokenGeneration.gerarToken(responseConsulta));		
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(loginDto, HttpStatus.OK);
	}
	
	public ResponseEntity<?> inserirUsuario(UserDTO userDto) {
		logger.info("Inicio inserir usuario");
		
		User responseConsulta = null;
		User responseUser = null;

		try {
			responseConsulta = userRepository.findByCpf(userDto.getCpf());

			if (responseConsulta != null) {
				return new ResponseEntity<>("Usuário já cadastrado", HttpStatus.UNAUTHORIZED);
			}

			User user = new User();
			user.setId(UUID.randomUUID());
			user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			user.setUsername(userDto.getUserName());
			user.setCpf(userDto.getCpf());
			user.setRoles(userDto.getRoles());

			userDto.setPassword(encodePassword(userDto.getPassword()));

			responseUser = userRepository.save(user);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		 
		 return new ResponseEntity<>(responseUser, HttpStatus.OK);
	}
	
	public ResponseEntity<?> atualizarUsuario(UserDTO userDto) {
		logger.info("Inicio atualizar atendimentos");	
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	private String encodePassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
	
	private boolean decodePassword(String password, String passwordBd) {
		return bCryptPasswordEncoder.matches(password, passwordBd);
	}
	
}
