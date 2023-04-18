package com.br.saude.dto.medicos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MedicoRequestDTO {
    
    @JsonProperty("nome_medico")
    private String name;

    @JsonProperty("email_medico")
    private String email;

    @JsonProperty("data_nascimento")
    private Date BirthDate; 

    @JsonProperty("salario_base")
    private Double BaseSalary;

    @JsonProperty("id_departamento")
    private Integer DepartmentId;
}