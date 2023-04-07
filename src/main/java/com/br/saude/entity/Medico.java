package com.br.saude.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEDICO")
public class Medico {
	
    @Id
    private BigInteger id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate BirthDate;
    
    @Column(name = "BASESALARY", nullable = false)
    private BigInteger BaseSalary;
    
    @Column(name = "DEPARTAMENTID", nullable = false)
    private Long DepartmentId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		BirthDate = birthDate;
	}

	public BigInteger getBaseSalary() {
		return BaseSalary;
	}

	public void setBaseSalary(BigInteger baseSalary) {
		BaseSalary = baseSalary;
	}

	public Long getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(Long departmentId) {
		DepartmentId = departmentId;
	}
    

}
