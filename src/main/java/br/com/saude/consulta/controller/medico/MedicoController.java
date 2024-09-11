package br.com.saude.consulta.controller.medico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.saude.consulta.service.medico.MedicoService;
import br.com.saude.dto.medicos.MedicoDTO;
import br.com.saude.dto.medicos.MedicoRequestDTO;
import br.com.saude.dto.utils.DTO;
import br.com.saude.dto.utils.Paginacao;
import br.com.saude.entity.Medico;

@RestController
@RequestMapping("/v1/saude/in/medico")
public class MedicoController {
	
	@Autowired
	MedicoService medicoService;
	
	@PostMapping("/lista")
    public ResponseEntity<DTO> consultarMedicos(@RequestBody Paginacao page) {  
		DTO dto = medicoService.consultarMedicosPaginados(page);
		
		if(dto.getData() == null) {
			dto.setMensagem("Médicos não encontrados");
			return new ResponseEntity<DTO>(dto, HttpStatus.NOT_FOUND);
		}
		dto.setMensagem("Médicos encontrados com sucesso.");
		
		return new ResponseEntity<DTO>(dto, HttpStatus.OK);
    }
	
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
