package br.com.cerebroti.rh.usuario.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PageRequestModel {

	private int pagina=0;
	private int tamanho=5;
	private String ativo="S";
	private String sort="";
	private String busca="";
	private String dado="";
	
	public PageRequestModel(Map<String, String> params) {
		if(	params.containsKey("pagina")	) 	{pagina 	= Integer.parseInt(params.get("pagina"));	}
		if(	params.containsKey("tamanho")	) 	{tamanho 	= Integer.parseInt(params.get("tamanho"));	}
		if(	params.containsKey("sort")		) 	{sort	 	= params.get("sort");						}
		if(	params.containsKey("busca")		) 	{busca	 	= params.get("busca");						}
		if(	params.containsKey("ativo")		) 	{ativo 		= params.get("ativo");						}
		if(	params.containsKey("dado")		) 	{dado 		= params.get("dado");						}
	}
	
	public PageRequest toSpringPageRequest() {
		List<Order> ordenacao = new ArrayList<>();
		String[] properties = sort.split(",");
		for (String propriedades :properties) {
			if(propriedades.trim().length()>0) {
				String coluna = propriedades.trim();
				if (coluna.startsWith("-")){
					coluna= coluna.replace("-","").trim();
					ordenacao.add(Order.desc(coluna));
				}
				else {
					ordenacao.add(Order.asc(coluna));
				}
			}
		}
		
		return PageRequest.of(pagina, tamanho, Sort.by(ordenacao));
	}
	
}
