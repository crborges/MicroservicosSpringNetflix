package br.com.cerebroti.rh.usuario.dto;

import javax.validation.constraints.NotNull;

import br.com.cerebroti.rh.usuario.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor   	
@AllArgsConstructor 	
@Getter 				
@Setter
public class RoleDTO {
	@NotNull(message = "Role é obrigatória")
	private String role;

	
	
public Role transformeTo() {
	Role object = new Role();
	object.setRoleName(this.getRole());
	return object;
}
	   
}
