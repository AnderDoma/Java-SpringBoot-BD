package br.com.saude.consulta.service.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.saude.dto.role.UserRoleDTO;
import br.com.saude.entity.Role;
import br.com.saude.entity.User;
import br.com.saude.repository.user.UserRepository;
import br.com.saude.repository.user.UserRoleRepository;

@Service
public class UserRoleService {
	
	Logger logger = LoggerFactory.getLogger(UserRoleService.class);

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	public ResponseEntity<?> execute(UserRoleDTO userRoleDTO) {
		logger.info("Inicio execute Role");
		
		Optional<User> responseConsulta = null;
		User responseUser = null;
		User responseBd = null;
		List<Role> roles = new ArrayList<>();

		try {
			UUID id = userRoleDTO.getIdUser();
			System.out.println(id);
			responseConsulta = userRepository.findById(id);

			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("Usuário não existe.", HttpStatus.UNAUTHORIZED);
			}
			
			roles = (List<Role>) userRoleDTO.getIdsRoles().stream().map(role -> {
				return new Role(role);}).collect(Collectors.toList());
			
			responseUser = responseConsulta.get();
			
			responseUser.setRoles(roles);
			
			responseBd = userRepository.save(responseUser);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}	
		
		return new ResponseEntity<>(responseBd, HttpStatus.OK);
	}

	public ResponseEntity<?> cadastrarRole(Role role) {
	logger.info("Inicio execute Role");
		
		Optional<Role> responseConsulta = null;
		Role responseBd = null;

		try {
			responseConsulta = userRoleRepository.findByName(role.getName());

			if (responseConsulta.isPresent()) {
				return new ResponseEntity<>("Role já existe.", HttpStatus.UNAUTHORIZED);
			}
			
			role.setId(UUID.randomUUID());
			responseBd = userRoleRepository.save(role);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}	
		
		return new ResponseEntity<>(responseBd, HttpStatus.OK);
	}

	public ResponseEntity<?> consultarRole(Role role) {
		Optional<Role> responseConsulta = null;

		try {
			responseConsulta = userRoleRepository.findByName(role.getName());

			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("Role não existe.", HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}	
		
		return new ResponseEntity<>(responseConsulta.get(), HttpStatus.OK);
	}	
}