package br.com.cerebroti.rh.usuario.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="role")
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String roleName;
	
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
		   
    

}
