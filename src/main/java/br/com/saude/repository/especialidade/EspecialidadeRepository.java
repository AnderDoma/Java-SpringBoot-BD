package br.com.saude.repository.especialidade;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.saude.dto.especialidade.EspecialidadeDTO;
import br.com.saude.entity.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {

	Optional<Especialidade> findById(Integer id); 
	
	List<Especialidade> findAllByOrderByIdAsc();
	
	Especialidade save(EspecialidadeDTO especialidade);
 
	@Transactional
	Integer deleteEspecialidadeById(Integer Id);
}
