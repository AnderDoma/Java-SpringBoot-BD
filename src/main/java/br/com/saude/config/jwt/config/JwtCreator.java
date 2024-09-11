package br.com.saude.config.jwt.config;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.saude.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtCreator implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final long EXPIRATION_TIME = 3600;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	public String gerarToken(User user) {
		
		SecretKeySpec secretKeySpec = (SecretKeySpec) Keys.hmacShaKeyFor(Base64.getEncoder().encode(secretKey.getBytes()));
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.claims(ClaimsBuild.criarClaims(user));
		
		return jwtBuilder.subject(user.getCpf())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME* 1000))
				.signWith(secretKeySpec, Jwts.SIG.HS256)
				.compact();
	}

	public Object validaToken(String token) {	
		
		SecretKeySpec secretKeySpec = (SecretKeySpec) Keys.hmacShaKeyFor(Base64.getEncoder().encode(secretKey.getBytes()));

		JwtParser jwtParser = Jwts.parser()
				.verifyWith(secretKeySpec)
				.build();
		
		return jwtParser.parse(token).getPayload();
	}
}
