package com.br.saude.repository.plano;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saude.dto.plano.PlanoDTO;
import com.br.saude.entity.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {
	
	Optional<Plano> findById(Integer id); 
	
	List<Plano> findAllByOrderByIdAsc();
	
	Plano save(PlanoDTO atendimentoDto);
 
	@Transactional
	Integer deletePlanoById(Integer id);

}
