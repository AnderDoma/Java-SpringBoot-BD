package com.br.saude.consulta.medicos.resouce;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.saude.entity.Medico;
import com.br.saude.medicos.dto.MedicoDTO;
import com.br.saude.repository.medicos.MedicosRepositoy;

@RestController
@RequestMapping("/v1/saude/{id}")
public class ConsultaDados {
	
	@Autowired
	MedicosRepositoy medicosRepositoy;
	
	@GetMapping()
    public ResponseEntity<MedicoDTO> GetById(
    		@PathVariable("id") BigInteger id)
    {  
		Optional<Medico> response = medicosRepositoy.findById(id);
		
		MedicoDTO medico = new MedicoDTO();
		medico.setId(response.get().getId());
		medico.setName(response.get().getName());
		medico.setEmail(response.get().getEmail());
		medico.setDepartmentId(response.get().getDepartmentId());
		medico.setBirthDate(response.get().getBirthDate());
		medico.setBaseSalary(response.get().getBaseSalary());
		
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

}
