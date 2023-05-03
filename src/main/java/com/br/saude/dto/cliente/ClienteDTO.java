package com.br.saude.dto.cliente;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClienteDTO {

    @JsonProperty("id_cliente")
    private Integer id;

    @JsonProperty("nome_cliente")
    private String name;

    @JsonProperty("email_cliente")
    private String email;

    @JsonProperty("data_nascimento")
    private Date BirthDate; 

    @JsonProperty("id_plano")
    private Integer planoId;
    
}
