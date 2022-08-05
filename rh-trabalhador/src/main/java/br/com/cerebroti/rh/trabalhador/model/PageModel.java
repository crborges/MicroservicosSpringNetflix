package br.com.cerebroti.rh.trabalhador.model;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageModel<T> implements Serializable{


	private static final long serialVersionUID = 1L;
	private int numTotalElementos;
	private int tamanhoPagina;
	private int numeroPaginas;
	private List<T> elementos;
	
}
