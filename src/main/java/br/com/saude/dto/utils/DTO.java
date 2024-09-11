package br.com.saude.dto.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DTO {
	
    @JsonProperty("data")
    private Object data;

    @JsonProperty("informacao")
    private String mensagem;
    
    @JsonProperty("paginacao")
    private Paginacao paginacao;

}
