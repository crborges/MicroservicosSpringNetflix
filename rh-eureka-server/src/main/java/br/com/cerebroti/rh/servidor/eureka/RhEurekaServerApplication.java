package br.com.cerebroti.rh.servidor.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer /*habilita o projeto como um servidor eureka*/
@SpringBootApplication
public class RhEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhEurekaServerApplication.class, args);
	}

}
