package br.com.cerebroti.rh.usuario.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
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

import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.model.PageModel;
import br.com.cerebroti.rh.usuario.model.PageRequestModel;
import br.com.cerebroti.rh.usuario.service.RoleService;
import lombok.extern.slf4j.Slf4j;

@RefreshScope
@RestController
@RequestMapping(value="role")
public class RoleResource {

	@Autowired private RoleService service;
	
	@PostMapping
	public  ResponseEntity<Role> salvar(@RequestBody Role DTO){
		Role criado = service.salvar(DTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(criado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Role> atualizar(@PathVariable(name = "id") Long id, @RequestBody  Role DTO){
		DTO.setId(id);
		Role atualizaddo= service.atualizar(DTO);
		return ResponseEntity.ok(atualizaddo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Role> getById(@PathVariable(name = "id") Long id) throws Exception{
		Role object = service.getById(id);
		return ResponseEntity.ok(object);
	}
		
	@GetMapping
	public ResponseEntity<PageModel<Role>> getAll(@RequestParam Map<String, String> params){
		PageRequestModel pageRequest = new PageRequestModel(params);
		PageModel<Role> pageModel = service.listPaginado(pageRequest);
		return ResponseEntity.ok(pageModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable(name = "id") Long id){
		service.remover(id);
		return ResponseEntity.ok("Deletado");
	}

}
