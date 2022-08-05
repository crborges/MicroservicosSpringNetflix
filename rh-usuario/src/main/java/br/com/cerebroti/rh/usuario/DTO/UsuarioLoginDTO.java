package br.com.cerebroti.rh.usuario.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioLoginDTO {
	
	@Email(message="Email Inválido")
	@NotBlank(message = "Email é obrigatório")
	public String email;
	
	@NotBlank(message = "Senha é obrigatória")
	public String senha;
}
