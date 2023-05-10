package com.br.saude.consulta.service.user;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.saude.dto.user.UserDTO;
import com.br.saude.entity.User;
import com.br.saude.repository.user.UserRepository;

@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	public ResponseEntity<?> consultaUsuarioPorUserName(UserDTO userDto) {
		logger.info("Inicio consulta usuario por id");
		User responseConsulta = null;
		logger.info("Dados: " + userDto);
		
		try {
			responseConsulta = userRepository.findByUsername(userDto.getUserName());
			
			if (responseConsulta == null) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(responseConsulta, HttpStatus.OK);

	}
	
	public ResponseEntity<?> inserirUsuario(UserDTO userDto) {
		logger.info("Inicio inserir usuario");
		
		User responseConsulta = null;
		User responseUser = null;

		try {
			responseConsulta = userRepository.findByUsername(userDto.getUserName());

			if (responseConsulta != null) {
				return new ResponseEntity<>("Usuário já cadastrado", HttpStatus.UNAUTHORIZED);
			}
			
			 User user = new User();
			 user.setId(UUID.randomUUID());
			 user.setEmail(userDto.getEmail());
			 user.setLastName(userDto.getLastName());
			 user.setFirstName(userDto.getFirstName());
			 user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			 user.setUsername(userDto.getUserName());
			 user.setRoles(userDto.getRoles());

			 userDto.setPassword(encodePassword(userDto.getPassword()));	
			 
			 System.out.println("password encoded: " +  userDto.getPassword());
			 
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
	
	/*private boolean matchesPassword(String passwordRequest, String password) {
		return bCryptPasswordEncoder.matches(passwordRequest, password);
	}*/
}
