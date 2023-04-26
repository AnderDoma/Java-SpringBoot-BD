package com.br.saude.consulta.service.medico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.saude.dto.medicos.MedicoDTO;
import com.br.saude.dto.medicos.MedicoRequestDTO;
import com.br.saude.entity.Medico;
import com.br.saude.repository.medico.MedicosRepository;

@Service
public class MedicoService {
	
	@Autowired
	MedicosRepository medicosRepositoy;
	
	public ResponseEntity<List<Medico>> consultarMedicos() {
		List<Medico> response = medicosRepositoy.findAllByOrderByIdAsc();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> consultaMedicoPorId(Integer id) {

		Optional<Medico> response = null;
		
		try {
			response = medicosRepositoy.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		MedicoDTO medico = new MedicoDTO();
		medico.setId(response.get().getId());
		medico.setName(response.get().getName());
		medico.setEmail(response.get().getEmail());
		medico.setDepartmentId(response.get().getDepartamentoId());
		medico.setBirthDate(response.get().getBirthDate());
		medico.setBaseSalary(response.get().getBaseSalary());

		return new ResponseEntity<>(medico, HttpStatus.OK);
	}
	
	public ResponseEntity<?> registrarMedico(MedicoRequestDTO medicoRequestDTO) {
		Medico medico = new Medico();
		medico.setBaseSalary(medicoRequestDTO.getBaseSalary());
		medico.setBirthDate(medicoRequestDTO.getBirthDate());
		medico.setDepartamentId(medicoRequestDTO.getDepartmentId());
		medico.setEmail(medicoRequestDTO.getEmail());
		medico.setName(medicoRequestDTO.getName());
	
		Medico response = new Medico();
		try {
			response = medicosRepositoy.save(medico);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	public ResponseEntity<?> atualizarMedico(MedicoDTO medicoDTO) {
		Optional<Medico> responseConsulta = null;
		
		Medico response = new Medico();
		
		try {
			responseConsulta = medicosRepositoy.findById(medicoDTO.getId());
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			Medico medico = new Medico();
			medico.setId(responseConsulta.get().getId());
			medico.setBaseSalary(medicoDTO.getBaseSalary());
			medico.setBirthDate(medicoDTO.getBirthDate());
			medico.setDepartamentId(medicoDTO.getDepartmentId());
			medico.setEmail(medicoDTO.getEmail());
			medico.setName(medicoDTO.getName());
			
			response = medicosRepositoy.save(medico);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<?> removerMedico(Integer id) {
		Optional<Medico> responseConsulta = null;
		try {
			responseConsulta = medicosRepositoy.findById(id);
			
			if (!responseConsulta.isPresent()) {
				return new ResponseEntity<>("ID Não encontrado.", HttpStatus.BAD_REQUEST);
			}
			
			medicosRepositoy.deleteMedicoById(id);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Médico removido com sucesso.", HttpStatus.OK);
	}
}
