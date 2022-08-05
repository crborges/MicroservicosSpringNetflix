package br.com.cerebroti.rh.pagamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*

Classe de configuracao 
A classe de pagamento tem que acessar o servico de trabalhadores para pegar os dados dachamada do byid

*/
@Configuration
public class AppConfig {

	/*vamos criar um compomente que vai ter uma instancia de um template rest para chamar um serviço externo
	Vai implementar o apttern singleton para ter uma instancia unica de rest tmplate para injetarem outros serviços
	 */
	@Bean
	public RestTemplate restTemplate() { return new RestTemplate(); 	}
}

