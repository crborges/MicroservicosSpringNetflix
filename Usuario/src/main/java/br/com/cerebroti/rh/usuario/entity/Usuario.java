package br.com.cerebroti.rh.usuario.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
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
	
	@Column(length = 75, nullable = false)	
	private String nome;
	
	@Column(length = 75,nullable = false,unique = true)
	private String email;
	
	@Column(length = 100,nullable = false)
	private String password;

	
	/*auditoria default*/
	@Column(length = 1,nullable = false)
    private String ativo;
    @ManyToOne
    @JoinColumn(name = "autor", nullable = true)
	private Usuario autor;
    @Column(name = "data_alteracao",nullable = true)
	private Date dataAlteracao;
	@Column(name = "data_criacao",nullable = true, updatable = false)
    private Date dataCriacao;
    @Column(name = "tipo_alteracao", nullable = false)
    private String tipoAlteracao;
	/*auditoria default*/
		   
		   
	  
	//vou criar uma tabela no meio entre usuario e role que vai guardar o id do usuario e os id das roles que ele tem 
	@ManyToMany(fetch = FetchType.EAGER) /*anotado para que ao carregar um usuario o sistema ja traga todas as suas roles*/
	@JoinTable(name="usuario_role", joinColumns = @JoinColumn(name ="id_usuario" ),inverseJoinColumns =  @JoinColumn(name ="id_role" ))
	private Set<Role> roles = new HashSet<Role>();/*o mapeamento da tabela do meio e das chaves que essa tabela deve ter*/


/*
	public void setId(long id) {
		this.id = id;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}



	public void setAutor(Usuario autor) {
		this.autor = autor;
	}



	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}



	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	public void setTipoAlteracao(String tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	*/
	
	
	
	
}
