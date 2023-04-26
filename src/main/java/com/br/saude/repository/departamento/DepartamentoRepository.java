package com.br.saude.repository.departamento;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saude.dto.departamento.DepartamentoDTO;
import com.br.saude.entity.Departamento;

import jakarta.transaction.Transactional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
	
	Optional<Departamento> findById(Integer id); 
	
	List<Departamento> findAllByOrderByIdAsc();
	
	Departamento save(DepartamentoDTO dptoDto);
 
	@Transactional
	Integer deleteDepartamentoById(Integer id);

}
