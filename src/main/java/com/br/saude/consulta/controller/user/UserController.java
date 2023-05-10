package com.br.saude.consulta.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.saude.consulta.service.user.UserRoleService;
import com.br.saude.consulta.service.user.UserService;
import com.br.saude.dto.role.UserRoleDTO;
import com.br.saude.dto.user.UserDTO;

@RestController
@RequestMapping("/v1/saude/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/consulta")
	public ResponseEntity<?> consultaUsuarioPorId(@RequestBody UserDTO userDto) {
		return userService.consultaUsuarioPorUserName(userDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/registrar")
	public ResponseEntity<?> salvarUsuario(@RequestBody UserDTO userDto) {
		return userService.inserirUsuario(userDto);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/role")
	public ResponseEntity<?> role(@RequestBody UserRoleDTO userRoleDTO) {
		return userRoleService.execute(userRoleDTO);
    }
	
}
