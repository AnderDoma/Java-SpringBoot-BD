package com.br.saude.consulta.service.especialidade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.saude.dto.especialidade.EspecialidadeDTO;
import com.br.saude.entity.Especialidade;
import com.br.saude.repository.especialidade.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	@Autowired
	EspecialidadeRepository especialidadeRepositoy;

	public ResponseEntity<?> consultarTodasEspecialidades() {
		List<Especialidade> response = null;

		try {
			response = especialidadeRepositoy.findAllByOrderByIdAsc();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getById(Integer id) {
		Optional<Especialidade> response = null;
		EspecialidadeDTO especialidade = new EspecialidadeDTO();

		try {
			response = especialidadeRepositoy.findById(id);

			especialidade.setId(response.get().getId());
			especialidade.setName(response.get().getName());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(especialidade, HttpStatus.OK);
	}

	public ResponseEntity<?> registrarEspecialidade(EspecialidadeDTO especialidadeDTO) {
		Especialidade especialidade = new Especialidade();
		especialidade.setName(especialidadeDTO.getName());

		Especialidade response = new Especialidade();

		try {
			response = especialidadeRepositoy.save(especialidade);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> atualizarEspecialidade(EspecialidadeDTO especialidadeDTO) {
		Optional<Especialidade> responseConsulta = null;
		Especialidade response = new Especialidade();

		try {
			responseConsulta = especialidadeRepositoy.findById(especialidadeDTO.getId());

			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}

			Especialidade especialidade = new Especialidade();
			especialidade.setId(especialidadeDTO.getId());
			especialidade.setName(especialidadeDTO.getName());

			response = especialidadeRepositoy.save(especialidade);
		} catch (Exception e) {
			return new ResponseEntity<>("ERRO AO REALIZAR A OPERAÇÃO", HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> deletarEspecialidade(Integer id) {
		Optional<Especialidade> especialidade = null;

		try {
			especialidade = especialidadeRepositoy.findById(id);

			if (!especialidade.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}

			Integer response = especialidadeRepositoy.deleteEspecialidadeById(id);

			if (response == 0) {
				return new ResponseEntity<>("Especialidade não foi removida!", HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>("Especialidade removida com sucesso!", HttpStatus.OK);
	}

}
