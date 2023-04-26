package com.br.saude.consulta.controller.departamento;

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

import com.br.saude.consulta.service.departamento.DepartamentoService;
import com.br.saude.dto.departamento.DepartamentoDTO;
import com.br.saude.dto.departamento.DepartamentoRequestDTO;

@RestController
@RequestMapping("/v1/saude/departamento")
public class DepartamentoController {

	@Autowired
	DepartamentoService departamentoService;
	
	@GetMapping()
    public ResponseEntity<?> consultaTodosDepartamentos() {
		return departamentoService.consultaTodosDepartamentos();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		return departamentoService.getById(id);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> inserirDepartamento(@RequestBody DepartamentoRequestDTO dpto) {
		return departamentoService.inserirDepartamento(dpto);
    }

	@PutMapping("/atualizar")
    public ResponseEntity<?> atualizarDepartamento(
    		@RequestBody DepartamentoDTO dpto) {  	
		return departamentoService.atualizarDepartamento(dpto);
    }
	
	@DeleteMapping("/remover/{id}")
    public ResponseEntity<?> remover(
    		@PathVariable("id") Integer id) { 
		return departamentoService.remover(id);
    }
	
}
