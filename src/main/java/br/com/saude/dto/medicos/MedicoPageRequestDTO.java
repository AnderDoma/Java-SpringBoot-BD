package br.com.saude.dto.medicos;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.saude.dto.utils.Paginacao;
import lombok.Data;

@Data
public class MedicoPageRequestDTO {
    
    @JsonProperty("Medico")
    private Paginacao paginacao;
}