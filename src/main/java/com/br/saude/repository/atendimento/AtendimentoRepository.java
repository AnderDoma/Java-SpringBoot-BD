package com.br.saude.repository.atendimento;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saude.dto.atendimento.AtendimentoDTO;
import com.br.saude.entity.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {
	
	Optional<Atendimento> findById(Integer id); 
	
	List<Atendimento> findAllByOrderByIdAsc();
	
	Atendimento save(AtendimentoDTO atendimentoDto);
 
	@Transactional
	Integer deleteAtendimentoById(Integer id);

}
