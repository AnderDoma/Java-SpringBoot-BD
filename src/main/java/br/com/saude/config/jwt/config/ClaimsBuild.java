package br.com.saude.config.jwt.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.saude.dto.token.TokenDTO;
import br.com.saude.entity.Role;
import br.com.saude.entity.User;

public class ClaimsBuild {
	
	
	public static Map<String, ?> criarClaims(User user) {
		
		Map<String, String> claimMap = new HashMap<>();
		claimMap.put("userId", user.getId().toString());
		claimMap.put("userName", user.getUsername());
		
		Role role = user.getRoles().get(0);
		
		claimMap.put("userRole", role.getName());

		return claimMap;
	}
	
	public static TokenDTO gerarClaims(String jwt) throws JsonParseException, JsonMappingException, IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		return gson.fromJson(jwt, TokenDTO.class);
	}

}
