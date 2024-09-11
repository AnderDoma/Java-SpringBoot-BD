package br.com.saude.consulta.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.saude.consulta.service.auth.AuthService;
import br.com.saude.dto.user.UserDTO;

@RestController
@RequestMapping("/v1/saude/auth")
public class AuthController {
	
	AuthService authService;
	
	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> realizarLogin(@RequestBody UserDTO userDto) {
		return authService.realizarLogin(userDto);
    }
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> realizarCadastro(@RequestBody UserDTO userDto) {
		return authService.inserirUsuario(userDto);
    }
	
}
