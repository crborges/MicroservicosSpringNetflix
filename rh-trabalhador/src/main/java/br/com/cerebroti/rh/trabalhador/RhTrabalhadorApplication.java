package br.com.cerebroti.rh.trabalhador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient/*transforma esse projeto num client para ser descoberto pelo eureka*/
@SpringBootApplication
public class RhTrabalhadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhTrabalhadorApplication.class, args);
	}

}
