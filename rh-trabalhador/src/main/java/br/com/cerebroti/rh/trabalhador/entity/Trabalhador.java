package br.com.cerebroti.rh.trabalhador.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="trabalhador")
public class Trabalhador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 75, nullable = false, unique = true)
	private String nome;
	@Column(length = 75, nullable = false)
	private double salarioDiario;
	
}
