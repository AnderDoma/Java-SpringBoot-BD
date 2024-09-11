package br.com.saude.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.saude.dto.user.UserDTO;
import br.com.saude.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	User findByCpf(String cpf);
	
	Optional<User> findById(UUID id);

	User save(UserDTO user);
	
	@Query("SELECT u from User u JOIN FETCH u.roles where u.username = :username ")
	User findByUsernameFechRoles(@Param("username") String username);

	User findByUsernameAndPassword(String userName, String password);

}
