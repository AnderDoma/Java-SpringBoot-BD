package com.br.saude.consulta.service.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.saude.dto.cliente.ClienteDTO;
import com.br.saude.entity.Cliente;
import com.br.saude.repository.cliente.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public ResponseEntity<List<Cliente>> consultaTodosClientes() {
		List<Cliente> response = clienteRepository.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> consultaClientePorId(Integer id) {

		Optional<Cliente> response = null;
		
		try {
			response = clienteRepository.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID não encontrado", HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<?> inserirCliente(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setName(clienteDTO.getName());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setBirthDate(clienteDTO.getBirthDate());
		cliente.setPlanoId(clienteDTO.getPlanoId());
	
		Cliente response = new Cliente();
		try {
			response = clienteRepository.save(cliente);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	public ResponseEntity<?> atualizarCliente(ClienteDTO clienteDTO) {
		Optional<Cliente> responseConsulta = null;
		
		Cliente response = new Cliente();
		
		try {
			responseConsulta = clienteRepository.findById(clienteDTO.getId());
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			Cliente cliente = new Cliente();
			cliente.setId(clienteDTO.getId());
			cliente.setName(clienteDTO.getName());
			cliente.setEmail(clienteDTO.getEmail());
			cliente.setBirthDate(clienteDTO.getBirthDate());
			cliente.setPlanoId(clienteDTO.getPlanoId());
			
			response = clienteRepository.save(cliente);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<?> removerCliente(Integer id) {
		Optional<Cliente> responseConsulta = null;
		try {
			responseConsulta = clienteRepository.findById(id);
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			clienteRepository.deleteClienteById(id);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Cliente removido com sucesso.", HttpStatus.OK);
	}
}
