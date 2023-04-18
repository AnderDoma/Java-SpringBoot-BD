package com.br.saude.consulta.controller.medico;

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

import com.br.saude.dto.medicos.MedicoDTO;
import com.br.saude.dto.medicos.MedicoRequestDTO;
import com.br.saude.entity.Medico;
import com.br.saude.repository.medico.MedicosRepository;

@RestController
@RequestMapping("/v1/saude/medico")
public class MedicoController {
	
	@Autowired
	MedicosRepository medicosRepositoy;
	
	@GetMapping()
    public ResponseEntity<List<Medico>> ConsultarMedicos(
    		@PathVariable("id") Integer id) {  
		List<Medico> response = medicosRepositoy.findAllByOrderByIdAsc();
			
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> GetById(
    		@PathVariable("id") Integer id) {  
		Optional<Medico> response = medicosRepositoy.findById(id);
		
		MedicoDTO medico = new MedicoDTO();
		medico.setId(response.get().getId());
		medico.setName(response.get().getName());
		medico.setEmail(response.get().getEmail());
		medico.setDepartmentId(response.get().getDepartamentoId());
		medico.setBirthDate(response.get().getBirthDate());
		medico.setBaseSalary(response.get().getBaseSalary());
		
		
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

	@PostMapping("/registrar")
	public ResponseEntity<?> registrarMedico(@RequestBody(required = true) MedicoRequestDTO medicoRequestDTO) {

		Medico medico = new Medico();
		medico.setBaseSalary(medicoRequestDTO.getBaseSalary());
		medico.setBirthDate(medicoRequestDTO.getBirthDate());
		medico.setDepartamentId(medicoRequestDTO.getDepartmentId());
		medico.setEmail(medicoRequestDTO.getEmail());
		medico.setName(medicoRequestDTO.getName());
	
		Medico response = new Medico();
		try {
			response = medicosRepositoy.save(medico);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
