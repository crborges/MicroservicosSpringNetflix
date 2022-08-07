package br.com.cerebroti.rh.usuario.dto;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioSaveDTO {

	
	
   @NotBlank(message = "Informe o nome do usuário")
   @NotNull(message = "Nome é obrigatório")
   private String nome;
   
   @Email(message = "Formato de e-mail inválido")
   @NotBlank(message = "Informe o e-mail do usuário")
   @NotNull(message = "E-mail é obrigatório")
   private String email;
   
   @Size(min = 6,max = 99,message = "Senha tem que ter entre 6 e 99 digitos")
   @NotBlank(message = "Informe a senha do usuário")
   @NotNull(message = "Senha é obrigatória")
   private String senha;
/*   
   @NotNull(message = "Função é obrigatória")
   private Set<Role> roles = new HashSet<Role>();
*/
   public Usuario transformeToUsuario() {
      Usuario usuario = new Usuario();
      usuario.setNome(this.getNome());
      usuario.setEmail(this.getEmail());
      usuario.setPassword(this.getSenha());
     // usuario.setRoles((Set<Role>) this.getRoles());
      return usuario;
   }

  
}
