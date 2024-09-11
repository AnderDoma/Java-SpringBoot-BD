package br.com.saude.repository.departamento;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.saude.dto.departamento.DepartamentoDTO;
import br.com.saude.entity.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
	
	Optional<Departamento> findById(Integer id); 
	
	List<Departamento> findAllByOrderByIdAsc();
	
	Departamento save(DepartamentoDTO dptoDto);
 
	@Transactional
	Integer deleteDepartamentoById(Integer id);

}
