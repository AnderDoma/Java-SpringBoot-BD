package com.br.saude.consulta.controller.plano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.saude.consulta.service.plano.PlanoService;
import com.br.saude.dto.plano.PlanoDTO;

@RestController
@RequestMapping("/v1/saude/plano")
public class PlanoController {

	@Autowired
	PlanoService planoService;
	
	@GetMapping()
    public ResponseEntity<?> consultaTodosPlanos() {
		return planoService.consultaTodosPlanos();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultaPlanoPorId(@PathVariable("id") Integer id) {
		return planoService.consultaPlanoPorId(id);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> inserirPlano(@RequestBody PlanoDTO plano) {
		return planoService.inserirPlano(plano);
    }

	@PutMapping("/atualizar")
    public ResponseEntity<?> atualizarPlano(
    		@RequestBody PlanoDTO plano) {  	
		return planoService.atualizarPlano(plano);
    }
	
	@DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerPlano(
    		@PathVariable("id") Integer id) { 
		return planoService.removerPlano(id);
    }
	
}
