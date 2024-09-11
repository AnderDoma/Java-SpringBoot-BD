package br.com.saude.config.jwt.config;

import java.util.ArrayList;
import java.util.List;

public class UrlAllowed {
	
	private static List<String> urlAuth = new ArrayList<>();
	
	static {
		urlAuth.add("/v1/saude/auth/login");
		urlAuth.add("/v1/saude/auth/cadastro");
	}
	
	public boolean validarUrl(String url) {	
		for (String s : urlAuth) {
			if (url.contains(s)) {
				return true;
			}
		}
		return false;
	}
}
