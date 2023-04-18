package com.br.saude.consulta.controller.departamento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.saude.dto.departamento.DepartamentoDTO;
import com.br.saude.dto.departamento.DepartamentoRequestDTO;
import com.br.saude.entity.Departamento;
import com.br.saude.repository.departamento.DepartamentoRepository;

@RestController
@RequestMapping("/v1/saude/departamento")
public class DepartamentoController {

	@Autowired
	DepartamentoRepository departamentoRepository;
	
	@GetMapping()
    public ResponseEntity<List<Departamento>> ConsultaTodosDepartamentos() {
		List<Departamento> response = departamentoRepository.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> GetById(
    		@PathVariable("id") Integer id) {  
		Optional<Departamento> response = departamentoRepository.findById(id);
		
		DepartamentoDTO dptoDto = new DepartamentoDTO();
		dptoDto.setId(response.get().getId());
		dptoDto.setName(response.get().getName());
		
        return new ResponseEntity<>(dptoDto, HttpStatus.OK);
    }
	
	@PostMapping("/registrar")
    public ResponseEntity<Departamento> inserirDepartamento(
    		@RequestBody DepartamentoRequestDTO dpto) {  
		Departamento dptoDto = new Departamento();
		
		dptoDto.setName(dpto.getNome());
		dptoDto.setEspecialidadeId(dpto.getEspecialidadeId());
		
		Departamento response = departamentoRepository.save(dptoDto);
		
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


	
}
