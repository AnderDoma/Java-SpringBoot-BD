package com.br.saude.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;
	
	@Column(name = "ESPECIALIDADEID", nullable = false)
	private Integer especialidadeId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEspecialidadeId() {
		return especialidadeId;
	}

	public void setEspecialidadeId(Integer especialidadeId) {
		this.especialidadeId = especialidadeId;
	}

}
