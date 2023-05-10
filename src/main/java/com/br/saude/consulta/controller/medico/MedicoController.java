package com.br.saude.consulta.controller.medico;

import java.util.List;

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

import com.br.saude.consulta.service.medico.MedicoService;
import com.br.saude.dto.medicos.MedicoDTO;
import com.br.saude.dto.medicos.MedicoRequestDTO;
import com.br.saude.entity.Medico;

@RestController
@RequestMapping("/v1/saude/in/medico")
public class MedicoController {
	
	@Autowired
	MedicoService medicoService;
	
	@GetMapping()
    public ResponseEntity<List<Medico>> consultarMedicos() {  
		return medicoService.consultarMedicos();
    }

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		return medicoService.consultaMedicoPorId(id);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrarMedico(@RequestBody(required = true) MedicoRequestDTO medicoRequestDTO) {
		return medicoService.registrarMedico(medicoRequestDTO);		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<?> atualizarMedico(@RequestBody(required = true) MedicoDTO medicoDTO) {
		return medicoService.atualizarMedico(medicoDTO);	
	}
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<?> removerMedico(@PathVariable("id") Integer id) {
		return medicoService.removerMedico(id);
	}

}
