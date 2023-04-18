package com.br.saude.dto.departamento;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DepartamentoRequestDTO {
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("especialidade_Id")
	private Integer especialidadeId;
	
}
