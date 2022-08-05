package br.com.cerebroti.rh.pagamento.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor   	
@AllArgsConstructor 	
@Getter 				
@Setter 
public class Pagamento implements Serializable{
	

private static final long serialVersionUID = 1L;
	private String nome;
	private double valorDiario;
	private Integer dias;
	public double getTotal() {return dias*valorDiario;}
}
