package br.com.saude.consulta.service.atendimento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.saude.dto.atendimento.AtendimentoDTO;
import br.com.saude.dto.enums.AtendimentosEnum;
import br.com.saude.entity.Atendimento;
import br.com.saude.repository.atendimento.AtendimentoRepository;

@Service
public class AtendimentoService {
	
	Logger logger = LoggerFactory.getLogger(AtendimentoService.class);
	
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	public AtendimentoService(AtendimentoRepository atendimentoRepository) {
		this.atendimentoRepository = atendimentoRepository;
	}
	
	public ResponseEntity<List<Atendimento>> consultaTodosAtendimentos() {
		logger.info("Inicio consulta todos os atendimentos");
		List<Atendimento> response = atendimentoRepository.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> consultaAtendimentoPorId(Integer id) {
		logger.info("Inicio consulta atendimentos por id");
		Optional<Atendimento> response = null;
		
		try {
			response = atendimentoRepository.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID não encontrado", HttpStatus.NO_CONTENT);
		
	}
	
	public ResponseEntity<?> inserirAtendimento(AtendimentoDTO atendimentoDTO) throws ParseException {
		logger.info("Inicio inserir atendimentos");
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
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	public ResponseEntity<?> atualizarAtendimento(AtendimentoDTO atendimentoDTO) {
		logger.info("Inicio atualizar atendimentos");
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
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<?> removerAtendimento(Integer id) {
		logger.info("Inicio remover atendimentos");
		Optional<Atendimento> responseConsulta = null;
		try {
			responseConsulta = atendimentoRepository.findById(id);
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			atendimentoRepository.deleteAtendimentoById(id);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Cliente removido com sucesso.", HttpStatus.OK);
	}
}
