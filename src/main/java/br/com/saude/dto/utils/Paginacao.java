package br.com.saude.dto.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Paginacao {
	
	public Paginacao() {
	}

	@JsonProperty("pagina")
    private Integer pagina;
	
	@JsonProperty("total_paginas")
    private Integer totalPaginas;
	
	@JsonProperty("total_elementos")
    private Long totalElementos;
	
	@JsonProperty("tamanho_pagina")
    private Integer tamanhoPagina;

}
