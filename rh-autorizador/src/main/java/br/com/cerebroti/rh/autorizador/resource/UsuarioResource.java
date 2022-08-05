package br.com.cerebroti.rh.autorizador.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hroauth.services.UsuarioService;

import br.com.cerebroti.rh.autorizador.entity.Usuario;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired 	private UsuarioService service;
	
	@GetMapping(value = "/busca")
	public ResponseEntity<Usuario> getByEmail(@RequestParam String email) {
		try {
			Usuario user = service.findByEmail(email);
			return ResponseEntity.ok(user);
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
