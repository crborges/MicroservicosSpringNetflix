package com.devsuperior.hroauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cerebroti.rh.autorizador.entity.Usuario;

@Component
//@FeignClient(name = "hr-user", path = "/users")
@FeignClient(name = "rh-usuario", path = "/usuario")
public interface UserFeignClient {

	//@GetMapping(value = "/search")
	@GetMapping(value = "/busca")
	ResponseEntity<Usuario> findByEmail(@RequestParam String email);	
}