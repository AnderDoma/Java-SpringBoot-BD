package br.com.saude.consulta.controller.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.saude.consulta.service.auth.UserRoleService;
import br.com.saude.entity.Role;

@RestController
@RequestMapping("/v1/saude/role")
public class RoleController {
	
	@Autowired
	UserRoleService userRoleService;
	
	@PostMapping("/registrar")
	public ResponseEntity<?> role(@RequestBody Role role) {
		return userRoleService.cadastrarRole(role);
    }
	
	@PostMapping("/consultar")
	public ResponseEntity<?> consultarRole(@RequestBody Role role) {
		return userRoleService.consultarRole(role);
    }
	
}
