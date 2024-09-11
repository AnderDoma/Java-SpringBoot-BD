package br.com.saude.config.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saude.entity.User;

@Service
public class TokenValidation {
	
	
	JwtCreator jwtCreator;
	
	@Autowired
	public TokenValidation(JwtCreator jwtCreator) {
		this.jwtCreator = jwtCreator;
	}
	
	public String gerarToken(User usuario) {
		try {
			return jwtCreator.gerarToken(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object validaToken(String jwtToken) {
		try {
			return jwtCreator.validaToken(jwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
