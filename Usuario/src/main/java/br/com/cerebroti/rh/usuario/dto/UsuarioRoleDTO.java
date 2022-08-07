package br.com.cerebroti.rh.usuario.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.cerebroti.rh.usuario.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioRoleDTO {

	@NotNull(message = "É obrigatório definir uma função do usuário")
	private Role role;
}
