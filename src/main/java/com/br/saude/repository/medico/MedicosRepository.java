package com.br.saude.repository.medico;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saude.dto.medicos.MedicoDTO;
import com.br.saude.entity.Medico;

import jakarta.transaction.Transactional;

@Repository
public interface MedicosRepository extends JpaRepository<Medico, Integer> {

	Optional<Medico> findById(Integer id); 
	
	List<Medico> findAllByOrderByIdAsc();
	
	Medico save(MedicoDTO medicoDTO);
 
	@Transactional
    Integer deleteMedicoById(Integer medicoId);
}
