package com.br.saude.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saude.dto.role.UserRoleDTO;
import com.br.saude.entity.Role;

@Repository
public interface  UserRoleRepository extends JpaRepository<Role, UUID> { 
	
	Optional<Role> findByName(String name);
	
	Role save(UserRoleDTO user);
}
