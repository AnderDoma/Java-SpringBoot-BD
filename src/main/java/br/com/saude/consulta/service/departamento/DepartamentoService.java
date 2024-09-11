package br.com.saude.consulta.service.departamento;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.saude.consulta.service.cliente.ClienteService;
import br.com.saude.dto.departamento.DepartamentoDTO;
import br.com.saude.dto.departamento.DepartamentoRequestDTO;
import br.com.saude.entity.Departamento;
import br.com.saude.repository.departamento.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	Logger logger = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	DepartamentoRepository departamentoRepository;
	
	public ResponseEntity<?> consultaTodosDepartamentos() {
		logger.info("Inicio consultar todos os departamentos");
		List<Departamento> response = null;
		
		try {
			response = departamentoRepository.findAllByOrderByIdAsc();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getById(Integer id) {
		logger.info("Inicio consultar departamento por id");
		Optional<Departamento> response = null; 
		
		try {
			response = departamentoRepository.findById(id);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID não encontrado", HttpStatus.NO_CONTENT);
    }
	
	public ResponseEntity<?> inserirDepartamento(DepartamentoRequestDTO dpto) {  
		logger.info("Inicio inserir departamento");
		Departamento dptoDto = new Departamento();
		
		dptoDto.setName(dpto.getNome());
		dptoDto.setEspecialidadeId(dpto.getEspecialidadeId());
		
		Departamento response = null;
		
		try {
			response = departamentoRepository.save(dptoDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
				
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<?> atualizarDepartamento(DepartamentoDTO dpto) {  
		logger.info("Inicio atualizar departamento");
		Optional<Departamento> responseConsulta = null;
		Departamento dptoDto = new Departamento();
		Departamento response = new Departamento();
		try {
			responseConsulta = departamentoRepository.findById(dpto.getId());
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			dptoDto.setId(responseConsulta.get().getId());
			dptoDto.setName(dpto.getName());
			dptoDto.setEspecialidadeId(dpto.getEspecialidadeId());
			
			response = departamentoRepository.save(dptoDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<?> remover(Integer id) {
		logger.info("Inicio remover departamento");
		Optional<Departamento> dpto = null;
		
		try {
			dpto = departamentoRepository.findById(id);
			
			if (!dpto.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			Integer response = 0;
			
			response = departamentoRepository.deleteDepartamentoById(id);
			
			if (response == 0) {
				return new ResponseEntity<>("Departamento não foi removido!", HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
				
        return new ResponseEntity<>("Departamento removido com sucesso!", HttpStatus.OK);
    }
}
