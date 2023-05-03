package com.br.saude.consulta.service.atendimento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.saude.dto.atendimento.AtendimentoDTO;
import com.br.saude.dto.enums.AtendimentosEnum;
import com.br.saude.entity.Atendimento;
import com.br.saude.repository.atendimento.AtendimentoRepository;

@Service
public class AtendimentoService {
	
	@Autowired
	AtendimentoRepository atendimentoRepository;
	
	public ResponseEntity<List<Atendimento>> consultaTodosAtendimentos() {
		List<Atendimento> response = atendimentoRepository.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> consultaAtendimentoPorId(Integer id) {

		Optional<Atendimento> response = null;
		
		try {
			response = atendimentoRepository.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID não encontrado", HttpStatus.NO_CONTENT);
		
	}
	
	public ResponseEntity<?> inserirAtendimento(AtendimentoDTO atendimentoDTO) throws ParseException {
		Atendimento atendimento = new Atendimento();
		atendimento.setClienteId(atendimentoDTO.getClienteId());
		atendimento.setDataAtendimento(atendimentoDTO.getDataAtendimento());
		
		AtendimentosEnum atendimentoEnum = AtendimentosEnum.retornaEnum(atendimentoDTO.getTipoAtendimento());
		atendimento.setTipo(atendimentoEnum.name());
		
		atendimento.setId(null);
		atendimento.setMedicoId(atendimentoDTO.getMedicoId());

		String pattern = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(atendimentoDTO.getDataAtendimento());

		atendimento.setDataAtendimento(simpleDateFormat.parse(date));
		
		Atendimento response = new Atendimento();
		try {
			response = atendimentoRepository.save(atendimento);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	public ResponseEntity<?> atualizarAtendimento(AtendimentoDTO atendimentoDTO) {
		Optional<Atendimento> responseConsulta = null;
		
		Atendimento response = new Atendimento();
		
		try {
			responseConsulta = atendimentoRepository.findById(atendimentoDTO.getId());
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			Atendimento atendimento = new Atendimento();
			atendimento.setClienteId(atendimentoDTO.getClienteId());
			atendimento.setDataAtendimento(atendimentoDTO.getDataAtendimento());
			
			AtendimentosEnum atendimentoEnum = AtendimentosEnum.retornaEnum(atendimentoDTO.getTipoAtendimento());
			atendimento.setTipo(atendimentoEnum.name());
			
			atendimento.setId(atendimentoDTO.getId());
			atendimento.setMedicoId(atendimentoDTO.getMedicoId());
			
			response = atendimentoRepository.save(atendimento);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<?> removerAtendimento(Integer id) {
		Optional<Atendimento> responseConsulta = null;
		try {
			responseConsulta = atendimentoRepository.findById(id);
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			atendimentoRepository.deleteAtendimentoById(id);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Cliente removido com sucesso.", HttpStatus.OK);
	}
}
