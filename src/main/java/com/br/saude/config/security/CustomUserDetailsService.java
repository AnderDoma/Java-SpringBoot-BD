package com.br.saude.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.saude.entity.User;
import com.br.saude.repository.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User existsUser = userRepository.findByUsernameFechRoles(username);

		if (existsUser == null) {
			throw new Error("Usuário não cadastrado");
		}
		return UserPrincipal.create(existsUser);
	}

}
