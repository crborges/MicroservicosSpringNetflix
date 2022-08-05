package br.com.cerebroti.rh.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker /*Habilita o circut breaker do hystrix para que o projeot seja tolerante a falhas de servicos que ele vai precisar*/
@EnableEurekaClient/*habilita como client de discovery do eureka*/
@EnableFeignClients /*habilita o feing que permite uma comunicação akis limpa entre apis distintas*/
@SpringBootApplication
public class RhPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhPagamentoApplication.class, args);
	}

}
