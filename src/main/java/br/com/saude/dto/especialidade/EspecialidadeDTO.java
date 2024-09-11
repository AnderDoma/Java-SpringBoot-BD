package br.com.saude.dto.especialidade;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EspecialidadeDTO {
	
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

}
