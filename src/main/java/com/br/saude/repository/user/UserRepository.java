package com.br.saude.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.saude.dto.user.UserDTO;
import com.br.saude.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	User findByUsername(String username);
	
	Optional<User> findById(UUID id);

	User save(UserDTO user);
	
	@Query("SELECT u from User u JOIN FETCH u.roles where u.username = :username ")
	User findByUsernameFechRoles(@Param("username") String username);

}
