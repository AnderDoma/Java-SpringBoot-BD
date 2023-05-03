package com.br.saude.consulta.service.plano;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.saude.dto.enums.TiposPlanosEnum;
import com.br.saude.dto.plano.PlanoDTO;
import com.br.saude.entity.Plano;
import com.br.saude.repository.plano.PlanoRepository;

@Service
public class PlanoService {
	
	@Autowired
	PlanoRepository planoRepository;
	
	public ResponseEntity<List<Plano>> consultaTodosPlanos() {
		List<Plano> response = planoRepository.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> consultaPlanoPorId(Integer id) {

		Optional<Plano> response = null;
		
		try {
			response = planoRepository.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID não encontrado", HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<?> inserirPlano(PlanoDTO planoDTO) {
		Plano plano = new Plano();
		plano.setId(null);
		plano.setNome(planoDTO.getNome());
		
		TiposPlanosEnum tipo = TiposPlanosEnum.retornaEnum(planoDTO.getTipoPlano());
		plano.setTipo(tipo.name());
	
		Plano response = new Plano();
		try {
			response = planoRepository.save(plano);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	public ResponseEntity<?> atualizarPlano(PlanoDTO planoDTO) {
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
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<?> removerPlano(Integer id) {
		Optional<Plano> responseConsulta = null;
		try {
			responseConsulta = planoRepository.findById(id);
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			planoRepository.deletePlanoById(id);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Plano removido com sucesso.", HttpStatus.OK);
	}
}
