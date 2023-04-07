package com.br.saude.medicos.dto;

import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MedicoDTO {

    @JsonProperty("id_medico")
    private BigInteger id;

    @JsonProperty("nome_medico")
    private String name;

    @JsonProperty("email_medico")
    private String email;

    @JsonProperty("data_nascimento")
    private LocalDate BirthDate; 

    @JsonProperty("salario_base")
    private BigInteger BaseSalary;

    @JsonProperty("id_departamento")
    private Long DepartmentId;
}
