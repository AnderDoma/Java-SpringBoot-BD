package br.com.saude.dto.departamento;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DepartamentoDTO {
	
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nome_departamento")
    private String name;
    
    @JsonProperty("especialidade_id")
    private Integer especialidadeId;

}
