package com.br.saude.dto.plano;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PlanoDTO {

    @JsonProperty("id_plano")
    private Integer id;

    @JsonProperty("nome_plano")
    private String nome;

    @JsonProperty("tipo_plano")
    private String tipoPlano;
    
}
