package br.com.saude.consulta.service.cliente;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.saude.dto.cliente.ClienteDTO;
import br.com.saude.entity.Cliente;
import br.com.saude.repository.cliente.ClienteRepository;

@Service
public class ClienteService {
	
	Logger logger = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public ResponseEntity<List<Cliente>> consultaTodosClientes() {
		logger.info("Inicio consultar todos os clientes");
		List<Cliente> response = clienteRepository.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> consultaClientePorId(Integer id) {
		logger.info("Inicio clientes por id");
		Optional<Cliente> response = null;
		
		try {
			response = clienteRepository.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID não encontrado", HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<?> inserirCliente(ClienteDTO clienteDTO) {
		logger.info("Inicio inserir clientes");
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
		logger.info("Inicio atualizar clientes");
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
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<?> removerCliente(Integer id) {
		logger.info("Inicio remover clientes");
		Optional<Cliente> responseConsulta = null;
		try {
			responseConsulta = clienteRepository.findById(id);
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			clienteRepository.deleteClienteById(id);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Cliente removido com sucesso.", HttpStatus.OK);
	}
}
