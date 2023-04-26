package com.br.saude.consulta.service.departamento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.saude.dto.departamento.DepartamentoDTO;
import com.br.saude.dto.departamento.DepartamentoRequestDTO;
import com.br.saude.entity.Departamento;
import com.br.saude.repository.departamento.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	DepartamentoRepository departamentoRepository;
	
	public ResponseEntity<?> consultaTodosDepartamentos() {
		List<Departamento> response = null;
		
		try {
			response = departamentoRepository.findAllByOrderByIdAsc();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getById(Integer id) {
		Optional<Departamento> response = null; 
		DepartamentoDTO dptoDto = new DepartamentoDTO();
		
		try {
			response = departamentoRepository.findById(id);
			
			dptoDto.setId(response.get().getId());
			dptoDto.setName(response.get().getName());
			dptoDto.setEspecialidadeId(response.get().getEspecialidadeId());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
        return new ResponseEntity<>(dptoDto, HttpStatus.OK);
    }
	
	public ResponseEntity<?> inserirDepartamento(DepartamentoRequestDTO dpto) {  
		Departamento dptoDto = new Departamento();
		
		dptoDto.setName(dpto.getNome());
		dptoDto.setEspecialidadeId(dpto.getEspecialidadeId());
		
		Departamento response = null;
		
		try {
			response = departamentoRepository.save(dptoDto);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
				
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<?> atualizarDepartamento(DepartamentoDTO dpto) {  
		
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
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<?> remover(Integer id) {
		
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
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
				
        return new ResponseEntity<>("Departamento removido com sucesso!", HttpStatus.OK);
    }
}
