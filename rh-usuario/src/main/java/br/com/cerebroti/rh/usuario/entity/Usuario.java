package br.com.cerebroti.rh.usuario.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor   	
@AllArgsConstructor 	
@Getter 				
@Setter 
@Entity
@Table(name="usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	@Column(unique = true)
	private String email;
	private String password;

	//vou criar uma tabela no meio entre usuario e role que vai guardar o id do usuario e os id das roles que ele tem 
	@ManyToMany(fetch = FetchType.EAGER) /*anotado para que ao carregar um usuario o sistema ja traga todas as suas roles*/
	@JoinTable(name="usuario_role", joinColumns = @JoinColumn(name ="id_usuario" ),inverseJoinColumns =  @JoinColumn(name ="id_role" ))	private Set<Role> roles = new HashSet<Role>();/*o mapeamento da tabela do meio e das chaves que essa tabela deve ter*/
	
}
