package com.br.saude.repository.cliente;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saude.dto.cliente.ClienteDTO;
import com.br.saude.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	Optional<Cliente> findById(Integer id); 
	
	List<Cliente> findAllByOrderByIdAsc();
	
	Cliente save(ClienteDTO clienteDto);
 
	@Transactional
	Integer deleteClienteById(Integer id);

}
