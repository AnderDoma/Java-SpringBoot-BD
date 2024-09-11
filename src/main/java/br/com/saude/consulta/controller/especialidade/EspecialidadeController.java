package br.com.saude.consulta.controller.especialidade;

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

import br.com.saude.consulta.service.especialidade.EspecialidadeService;
import br.com.saude.dto.especialidade.EspecialidadeDTO;

@RestController
@RequestMapping("/v1/saude/in/especialidade")
public class EspecialidadeController {
	
	@Autowired
	EspecialidadeService especialidadeService;
	
	@GetMapping("")
    public ResponseEntity<?> consultarTodasEspecialidades()  {  
		return especialidadeService.consultarTodasEspecialidades();
    }

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		return especialidadeService.getById(id);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarEspecialidade(@RequestBody EspecialidadeDTO especialidadeDTO) {
		return especialidadeService.registrarEspecialidade(especialidadeDTO);
	}

	@PutMapping("/atualizar")
    public ResponseEntity<?> atualizarEspecialidade(
    		@RequestBody EspecialidadeDTO especialidadeDTO) {  
		return especialidadeService.atualizarEspecialidade(especialidadeDTO);
    }

	@DeleteMapping("/remover/{id}")
	public ResponseEntity<?> deletarEspecialidade(@PathVariable("id") Integer id) {
		return especialidadeService.deletarEspecialidade(id);
	}
}
