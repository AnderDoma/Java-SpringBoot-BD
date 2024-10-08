package br.com.saude.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Type(type="uuid-char")
	@Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
	
	@Column(name="USERNAME", nullable = false, unique = true)
	private String username;
	
	@Column(name="PASSWORD", nullable = false)
	private String password;
	
	@Column(name="CPF", nullable = false)
	private String cpf;
	
	@ManyToMany
	private List<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
