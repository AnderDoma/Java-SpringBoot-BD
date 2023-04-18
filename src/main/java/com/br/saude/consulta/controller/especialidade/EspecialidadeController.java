package com.br.saude.consulta.controller.especialidade;

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

import com.br.saude.dto.especialidade.EspecialidadeDTO;
import com.br.saude.entity.Especialidade;
import com.br.saude.repository.especialidade.EspecialidadeRepository;

@RestController
@RequestMapping("/v1/saude/especialidade")
public class EspecialidadeController {
	
	@Autowired
	EspecialidadeRepository especialidadeRepositoy;
	
	@GetMapping("")
    public ResponseEntity<List<Especialidade>> ConsultarTodasEspecialidades()  {  
		List<Especialidade> response = especialidadeRepositoy.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<EspecialidadeDTO> GetById(
    		@PathVariable("id") Integer id)
    {  
		Optional<Especialidade> response = especialidadeRepositoy.findById(id);
		
		EspecialidadeDTO especialidade = new EspecialidadeDTO();
		especialidade.setId(response.get().getId());
		especialidade.setName(response.get().getName());
		
        return new ResponseEntity<>(especialidade, HttpStatus.OK);
    }
	
	@PostMapping("/registrar")
    public ResponseEntity<Especialidade> GetById(
    		@RequestBody EspecialidadeDTO especialidadeDTO) {  
    	
		Especialidade especialidade = new Especialidade();
		especialidade.setName(especialidadeDTO.getName());
	
		Especialidade response = especialidadeRepositoy.save(especialidade);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
