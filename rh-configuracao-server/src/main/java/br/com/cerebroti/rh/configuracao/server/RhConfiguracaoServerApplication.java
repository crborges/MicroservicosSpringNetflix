package br.com.cerebroti.rh.configuracao.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class RhConfiguracaoServerApplication implements CommandLineRunner{

	@Value("${spring.cloud.config.server.git.username}") 
	private String gitUser;
	@Value("${spring.cloud.config.server.git.password}") 
	private String gitpass;
	
	
	@Value("{$oauth.client.secret}")
	private String appSecret;
	
	
	public static void main(String[] args) {
		SpringApplication.run(RhConfiguracaoServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Usuario do git hub logando "+appSecret);
		
	}

}
