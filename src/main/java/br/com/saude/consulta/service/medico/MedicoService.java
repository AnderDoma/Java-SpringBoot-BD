package br.com.saude.consulta.service.medico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.saude.dto.medicos.MedicoDTO;
import br.com.saude.dto.medicos.MedicoRequestDTO;
import br.com.saude.dto.utils.DTO;
import br.com.saude.dto.utils.Paginacao;
import br.com.saude.entity.Medico;
import br.com.saude.repository.medico.MedicosRepository;

@Service
public class MedicoService {
	
	Logger logger = LoggerFactory.getLogger(MedicoService.class);
	
	@Autowired
	MedicosRepository medicosRepository;
	
	public ResponseEntity<List<Medico>> consultarMedicos() {
		logger.info("Inicio consulta todos os médicos");
		List<Medico> response = medicosRepository.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> consultaMedicoPorId(Integer id) {
		logger.info("Inicio consulta médico por id");
		Optional<Medico> response = null;
		
		try {
			response = medicosRepository.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID não encontrado", HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<?> registrarMedico(MedicoRequestDTO medicoRequestDTO) {
		logger.info("Inicio registro médicos");
		Medico medico = new Medico();
		medico.setBaseSalary(medicoRequestDTO.getBaseSalary());
		medico.setBirthDate(medicoRequestDTO.getBirthDate());
		medico.setDepartamentId(medicoRequestDTO.getDepartmentId());
		medico.setEmail(medicoRequestDTO.getEmail());
		medico.setName(medicoRequestDTO.getName());
	
		Medico response = new Medico();
		try {
			response = medicosRepository.save(medico);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	public ResponseEntity<?> atualizarMedico(MedicoDTO medicoDTO) {
		logger.info("Inicio atualizar médicos");
		Optional<Medico> responseConsulta = null;
		
		Medico response = new Medico();
		
		try {
			responseConsulta = medicosRepository.findById(medicoDTO.getId());
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			Medico medico = new Medico();
			medico.setId(responseConsulta.get().getId());
			medico.setBaseSalary(medicoDTO.getBaseSalary());
			medico.setBirthDate(medicoDTO.getBirthDate());
			medico.setDepartamentId(medicoDTO.getDepartmentoId());
			medico.setEmail(medicoDTO.getEmail());
			medico.setName(medicoDTO.getName());
			
			response = medicosRepository.save(medico);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<?> removerMedico(Integer id) {
		logger.info("Inicio remover médicos");
		Optional<Medico> responseConsulta = null;
		try {
			responseConsulta = medicosRepository.findById(id);
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			medicosRepository.deleteMedicoById(id);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Médico removido com sucesso.", HttpStatus.OK);
	}

	public DTO consultarMedicosPaginados(Paginacao page) {

		int pagina = page.getPagina();
		int tamanho = page.getTamanhoPagina();
		
		PageRequest pageRequest = new PageRequest(pagina, tamanho);
		
		Page<Medico> response = null;

		try {
			response = medicosRepository.findAll(pageRequest);
		} catch (Exception e) {
			throw e;
		}
		
		Paginacao pageResponse = new Paginacao();
		pageResponse.setPagina(response.getNumber());
		pageResponse.setTamanhoPagina(response.getTotalPages());
		pageResponse.setTotalElementos(response.getTotalElements());
		pageResponse.setTotalPaginas(response.getSize());
		
		return DTO.builder().data(response.getContent()).paginacao(pageResponse).build();
	}
}
