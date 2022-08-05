package br.com.cerebroti.rh.usuario.resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cerebroti.rh.usuario.DTO.UsuarioLoginDTO;
import br.com.cerebroti.rh.usuario.entity.Usuario;
import br.com.cerebroti.rh.usuario.model.PageModel;
import br.com.cerebroti.rh.usuario.model.PageRequestModel;
import br.com.cerebroti.rh.usuario.repository.UsuarioRepository;
import br.com.cerebroti.rh.usuario.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RefreshScope
@RestController
@RequestMapping(value="usuario")
public class UsuarioResource {


	@Autowired private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping(value = "/busca")
	public ResponseEntity<Usuario> findByEmail(@RequestParam String email) {
		Usuario obj = repository.findByEmail(email);
		return ResponseEntity.ok(obj);
	}
	
	
	@PostMapping
	public  ResponseEntity<Usuario> salvar(@RequestBody Usuario DTO){
		Usuario criado = service.salvar(DTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(criado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable(name = "id") Long id, @RequestBody  Usuario DTO){
		DTO.setId(id);
		Usuario atualizaddo= service.atualizar(DTO);
		return ResponseEntity.ok(atualizaddo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable(name = "id") Long id) throws Exception{
		Usuario object = service.getById(id);
		return ResponseEntity.ok(object);
	}
		
	
	
	@GetMapping
	public ResponseEntity<PageModel<Usuario>> getAll(@RequestParam Map<String, String> params){
		PageRequestModel pageRequest = new PageRequestModel(params);
		PageModel<Usuario> pageModel = service.listPaginado(pageRequest);
		return ResponseEntity.ok(pageModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable(name = "id") Long id){
		service.remover(id);
		return ResponseEntity.ok("Deletado");
	}
	/*
	
	//login
	@PostMapping("/login")
	public ResponseEntity<Usuario> login (@RequestBody  @Valid UsuarioLoginDTO logavel){//@Valid invoca a validação anotada na classe
		return ResponseEntity.ok(service.logar(logavel));
		
			
		//crio um token para autenticacao do usuario
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(logavel.getEmail(), logavel.getSenha());
			//cria um atenticador e passa para ele o token de autenticacao gerado com user e senha 
			Authentication authentication= authenticationManager.authenticate(token);
			//coloco o autenticador no contexto de seguranca
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//pego um user spring de dentro do autenticador
			User usuarioSpring = (User) authentication.getPrincipal();
			//pega o email do usuario para gerar o token
			String email = usuarioSpring.getUsername();
			//coleta as roles do usuario para gerar o token
			List<String> roles = usuarioSpring
									.getAuthorities()
									.stream()
									.map(authority -> authority.getAuthority())
									.collect(Collectors.toList());
			//pega os dados e retorna o token
			UserLoginResponseDTO response  = jwtManger.criaToken(email, roles);
			return  ResponseEntity.ok(response);
			
		}

	
	
	//login
	@GetMapping(value = "/getByMail")
	public ResponseEntity<Usuario> getByMail (@RequestParam String email){//@Valid invoca a validação anotada na classe
		log.info("get usuario by email, buscando por "+email);
		return ResponseEntity.ok(service.getByEmail("crrborges@gmail.com"));
	}
*/
	
}
