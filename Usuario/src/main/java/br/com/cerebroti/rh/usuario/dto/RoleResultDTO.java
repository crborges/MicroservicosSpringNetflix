package br.com.cerebroti.rh.usuario.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor   	
@AllArgsConstructor 	
@Getter 				
@Setter
public class RoleResultDTO {

private long id;
@NotNull(message = "Role é obrigatória")
private String role;
private String ativo;
private String dataOperacao;




}
