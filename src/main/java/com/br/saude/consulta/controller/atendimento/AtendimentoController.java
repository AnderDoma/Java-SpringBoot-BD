package com.br.saude.consulta.controller.atendimento;

import java.text.ParseException;

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

import com.br.saude.consulta.service.atendimento.AtendimentoService;
import com.br.saude.dto.atendimento.AtendimentoDTO;

@RestController
@RequestMapping("/v1/saude/atendimento")
public class AtendimentoController {

	@Autowired
	AtendimentoService atendimentoService;
	
	@GetMapping()
    public ResponseEntity<?> consultaTodosAtendimentos() {
		return atendimentoService.consultaTodosAtendimentos();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultaAtendimentoPorId(@PathVariable("id") Integer id) {
		return atendimentoService.consultaAtendimentoPorId(id);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> inserirAtendimento(@RequestBody AtendimentoDTO atendimento) throws ParseException {
		return atendimentoService.inserirAtendimento(atendimento);
    }

	@PutMapping("/atualizar")
    public ResponseEntity<?> atualizarAtendimento(
    		@RequestBody AtendimentoDTO cliente) {  	
		return atendimentoService.atualizarAtendimento(cliente);
    }
	
	@DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerAtendimento(
    		@PathVariable("id") Integer id) { 
		return atendimentoService.removerAtendimento(id);
    }
	
}
