package br.com.saude.consulta.service.plano;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.saude.dto.enums.TiposPlanosEnum;
import br.com.saude.dto.plano.PlanoDTO;
import br.com.saude.entity.Plano;
import br.com.saude.repository.plano.PlanoRepository;

@Service
public class PlanoService {
	
	Logger logger = LoggerFactory.getLogger(PlanoService.class);
	
	@Autowired
	PlanoRepository planoRepository;
	
	public ResponseEntity<List<Plano>> consultaTodosPlanos() {
		logger.info("Inicio consulta todos os planos");
		List<Plano> response = planoRepository.findAllByOrderByIdAsc();
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> consultaPlanoPorId(Integer id) {
		logger.info("Inicio consulta planos por id");
		Optional<Plano> response = null;
		
		try {
			response = planoRepository.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID não encontrado", HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<?> inserirPlano(PlanoDTO planoDTO) {
		logger.info("Inicio inserir planos");
		Plano plano = new Plano();
		plano.setId(null);
		plano.setNome(planoDTO.getNome());
		
		TiposPlanosEnum tipo = TiposPlanosEnum.retornaEnum(planoDTO.getTipoPlano());
		plano.setTipo(tipo.name());
	
		Plano response = new Plano();
		try {
			response = planoRepository.save(plano);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	public ResponseEntity<?> atualizarPlano(PlanoDTO planoDTO) {
		logger.info("Inicio atualizar planos");
		Optional<Plano> responseConsulta = null;
		
		Plano response = new Plano();
		
		try {
			responseConsulta = planoRepository.findById(planoDTO.getId());
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			Plano plano = new Plano();
			plano.setId(planoDTO.getId());
			plano.setNome(planoDTO.getNome());
			
			TiposPlanosEnum tipo = TiposPlanosEnum.retornaEnum(planoDTO.getTipoPlano());
			plano.setTipo(tipo.name());
			
			response = planoRepository.save(plano);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<?> removerPlano(Integer id) {
		logger.info("Inicio remover planos");
		Optional<Plano> responseConsulta = null;
		try {
			responseConsulta = planoRepository.findById(id);
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			planoRepository.deletePlanoById(id);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Plano removido com sucesso.", HttpStatus.OK);
	}
}
