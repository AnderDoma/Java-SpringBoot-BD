package br.com.saude.config.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.saude.dto.token.TokenDTO;


@Component
public class InternalFilter extends OncePerRequestFilter{
	
	TokenValidation jwtToken;
	
	public InternalFilter(TokenValidation jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {


		if (!request.getRequestURI().contains("/v1/saude/auth/login")) {
			String token = request.getHeader("Authorization");

			if (token == null) {
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			
			CustomHttpServelet req = new CustomHttpServelet(request);
			TokenDTO tokenAberto = new TokenDTO();
			
			try {
				String jwt = jwtToken.validaToken(token.replace("Bearer ", "")).toString();
				
				tokenAberto = ClaimsBuild.gerarClaims(jwt);
				
				req.addHeaderValue("userId", tokenAberto.getUserId());
				req.addHeaderValue("userName", tokenAberto.getUserName());
				req.addHeaderValue("cpf", tokenAberto.getSub());	
				req.addHeaderValue("userRole", tokenAberto.getUserRole());	
				
				
			} catch (Exception e) {
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			
			filterChain.doFilter(req, response);
			
		} else {
			filterChain.doFilter(request, response);
		}
	}
	
}
