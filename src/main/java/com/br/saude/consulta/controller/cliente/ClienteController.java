package com.br.saude.consulta.controller.cliente;

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

import com.br.saude.consulta.service.cliente.ClienteService;
import com.br.saude.dto.cliente.ClienteDTO;

@RestController
@RequestMapping("/v1/saude/in/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping()
    public ResponseEntity<?> consultaTodosClientes() {
		return clienteService.consultaTodosClientes();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultaClientePorId(@PathVariable("id") Integer id) {
		return clienteService.consultaClientePorId(id);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> inserirCliente(@RequestBody ClienteDTO cliente) {
		return clienteService.inserirCliente(cliente);
    }

	@PutMapping("/atualizar")
    public ResponseEntity<?> atualizarCliente(
    		@RequestBody ClienteDTO cliente) {  	
		return clienteService.atualizarCliente(cliente);
    }
	
	@DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerCliente(
    		@PathVariable("id") Integer id) { 
		return clienteService.removerCliente(id);
    }
	
}
