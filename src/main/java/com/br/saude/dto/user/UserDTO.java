package com.br.saude.dto.user;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.br.saude.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDTO  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
    private UUID id;
    
    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firstname")
    private String firstName; 
    
    @JsonProperty("lastname")
    private String lastName;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("roles")
	private List<Role> roles;
    
}
