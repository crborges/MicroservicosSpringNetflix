package br.com.cerebroti.rh.usuario.dto;

import javax.validation.constraints.NotBlank;
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
public class RoleUsuarioDTO {
	
	
	@NotNull(message = "usuario é obrigatório")
	private long usuario;
	
	@NotNull(message = "role é obrigatória")
	private long role;

	
}
