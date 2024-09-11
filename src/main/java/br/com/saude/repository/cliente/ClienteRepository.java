package br.com.saude.repository.cliente;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.saude.dto.cliente.ClienteDTO;
import br.com.saude.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	Optional<Cliente> findById(Integer id); 
	
	List<Cliente> findAllByOrderByIdAsc();
	
	Cliente save(ClienteDTO clienteDto);
 
	@Transactional
	Integer deleteClienteById(Integer id);

}
