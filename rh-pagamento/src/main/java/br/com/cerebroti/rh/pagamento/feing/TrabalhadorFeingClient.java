package br.com.cerebroti.rh.pagamento.feing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cerebroti.rh.pagamento.entity.Trabalhador;



/*interface que trabalha com o feing client e facilita a comunicação entre projetos via rest*/


/*
Configuração de quem esse cliente atende
Nome do projeto la do properties do sprint
url do projeto (pode ser dinamico)
path do recurso que vou acessar
 * */
@FeignClient(name="rh-trabalhador" , path = "/trabalhador") 
@Component /*para injetar onde precisar essa classe*/
public interface TrabalhadorFeingClient {
	//crio o metodo abstrato que vai representar o metodo que vou chamar la do metodo de origem
	@GetMapping("/{id}")
	ResponseEntity<Trabalhador> getById(@PathVariable(name = "id") Long id);
}
