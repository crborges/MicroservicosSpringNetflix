package br.com.cerebroti.rh.pagamento.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy /*habilita o gateway do zuul no projeto*/
@EnableEurekaClient /*Transformna num cliente eureka tbem*/
@SpringBootApplication
public class RhGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhGatewayServiceApplication.class, args);
	}

}
