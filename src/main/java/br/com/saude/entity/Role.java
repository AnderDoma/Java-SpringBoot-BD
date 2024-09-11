package br.com.saude.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

	@Id
    @GeneratedValue
    @Type(type="uuid-char")
	@Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
	
	@Column(name="NAME", nullable = false, unique = true)
	private String name;
	
	public Role(UUID id) {
		this.id = id;
	}

}	
	
	
	
	