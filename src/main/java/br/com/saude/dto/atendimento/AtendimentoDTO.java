package br.com.saude.dto.atendimento;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AtendimentoDTO {

    @JsonProperty("id_atendimento")
    private Integer id;

    @JsonProperty("data_atendimento")
    private Date dataAtendimento;

    @JsonProperty("tipo_atendimento")
    private String tipoAtendimento;

    @JsonProperty("id_cliente")
    private Integer clienteId; 

    @JsonProperty("id_medico")
    private Integer medicoId;
    
}
