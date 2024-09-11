package br.com.saude.config.jwt.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class CustomHttpServelet extends HttpServletRequestWrapper {
	
	private Map<String, Object> customHeader;

	public CustomHttpServelet(HttpServletRequest request) {
		super(request);
		customHeader = new HashMap<>();
	}

	public Map<String, Object> getCustomHeader() {
		return customHeader;
	}

	public void setCustomHeader(Map<String, Object> customHeader) {
		this.customHeader = customHeader;
	}
	
	public void addHeaderValue(String parametro, String valor) {
		customHeader.put(parametro, valor);
	}
	
}
