package br.com.cerebroti.rh.pagamento.gateway.config;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter{

	@Autowired private JwtTokenStore token;
	
	private static final String[] PUBLIC= {"/autorizador/oauth/token"};
	private static final String[] OPERADOR= {"/trabalhador/**"}; //Todo trabalhador liberado para o operador
	private static final String[] ADMIN= {"/pagamento/**","/usuario/**","/actuator/**","rh-trabalhador/actuator/**","rh-autorizador/actuator/**"};//Todo pagamento e usuario esta liberado para o admin
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(token);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()  //publico tudo liberado
		.antMatchers(HttpMethod.GET,OPERADOR).hasAnyRole("OPERADOR","ADMIN") //todos metodos get liberados para operador
		.antMatchers(ADMIN).hasRole("ADMIN")//tudo liberado para os admin que tem o perfil de admin 
		.anyRequest().authenticated();//tudo o que sobrou(no meu caso muita coisa) liberado desde que logado
		
		http.cors().configurationSource(corsConfigurationSource());//configuração de cors 
	}
	
	@Bean
	public CorsConfigurationSource  corsConfigurationSource  () {
		//objeto do cors 
		CorsConfiguration corsConfig = new CorsConfiguration();
		
		//config do que eu permito no cors
		corsConfig.setAllowedOrigins(Arrays.asList("*"));//qualquer cors consegue me acessar
		corsConfig.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","PATCH"));//quais metodos podem acessar
		corsConfig.setAllowCredentials(true);//permite credenciais
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));//cabeçalhos permitidos

		//url de base 		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		
		//return
		return source;
	}

	@Bean 
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> filtro = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
				filtro.setOrder(Ordered.HIGHEST_PRECEDENCE);//executa com a maior precedendia possivel
		return filtro;
	}
	
	
	
}
