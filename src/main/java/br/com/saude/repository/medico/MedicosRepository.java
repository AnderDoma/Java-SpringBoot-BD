package br.com.saude.repository.medico;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.saude.dto.medicos.MedicoDTO;
import br.com.saude.entity.Medico;

@Repository
public interface MedicosRepository extends JpaRepository<Medico, Integer> {

	Optional<Medico> findById(Integer id); 
	
	List<Medico> findAllByOrderByIdAsc();
	
	Medico save(MedicoDTO medicoDTO);
 
	@Transactional
    Integer deleteMedicoById(Integer medicoId);
	
	Page<Medico> findAll(Pageable pageble);
}
