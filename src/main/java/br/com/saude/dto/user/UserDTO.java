package br.com.saude.dto.user;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.saude.entity.Role;
import lombok.Data;

@Data
public class UserDTO  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("cpf")
    private String cpf; 
    
    @JsonProperty("roles")
	private List<Role> roles;
    
}
