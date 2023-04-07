package com.br.saude.repository.medicos;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saude.entity.Medico;

@Repository
public interface MedicosRepositoy extends JpaRepository<Medico, BigInteger> {

	Optional<Medico> findById(BigInteger id); 
}
