package br.com.saude.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.saude.dto.role.UserRoleDTO;
import br.com.saude.entity.Role;

@Repository
public interface  UserRoleRepository extends JpaRepository<Role, UUID> { 
	
	Optional<Role> findByName(String name);
	
	Role save(UserRoleDTO user);
}
