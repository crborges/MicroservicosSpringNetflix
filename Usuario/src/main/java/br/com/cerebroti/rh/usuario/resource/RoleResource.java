package br.com.cerebroti.rh.usuario.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.validation.Valid;

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

import br.com.cerebroti.rh.usuario.dto.RoleDTO;
import br.com.cerebroti.rh.usuario.dto.RoleResultDTO;
import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.model.PageModel;
import br.com.cerebroti.rh.usuario.model.PageRequestModel;
import br.com.cerebroti.rh.usuario.service.RoleService;
import lombok.extern.slf4j.Slf4j;

@RefreshScope
@RestController
@RequestMapping(value="role")
public class RoleResource {

  @Autowired
   private RoleService service;

	   @PostMapping
	   public ResponseEntity<RoleResultDTO> salvar(@RequestBody @Valid RoleDTO DTO) {
	      Role object = DTO.transformeTo();
	      Role criado = this.service.salvar(object);
	      String dtOp = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
	      RoleResultDTO retorno = new RoleResultDTO(criado.getId(), criado.getRoleName(),criado.getAtivo(), dtOp);
	      return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
	   }

	   @PutMapping({"/{id}"})
	   public ResponseEntity<RoleResultDTO> atualizar(@PathVariable(name = "id") Long id, @RequestBody @Valid RoleDTO DTO) {
		  Role object = DTO.transformeTo();
	      object.setId(id);
	      Role atualizaddo = this.service.atualizar(object);
	      String dtOp = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
	      RoleResultDTO retorno = new RoleResultDTO(atualizaddo.getId(), atualizaddo.getRoleName(),atualizaddo.getAtivo(), dtOp);
	      return ResponseEntity.ok(retorno);
	   }

	   @GetMapping({"/{id}"})
	   public ResponseEntity<RoleResultDTO> getById(@PathVariable(name = "id") Long id, @RequestParam Map<String, String> params) {
		  Role object = this.service.getById(id);
	      String dtOp = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
	      RoleResultDTO retorno = new RoleResultDTO(object.getId(), object.getRoleName(),object.getAtivo(), dtOp);
	      return ResponseEntity.ok(retorno);
	   }

	   @GetMapping
	   public ResponseEntity<PageModel<RoleResultDTO>> getAll(@RequestParam Map<String, String> params) {
	      PageRequestModel pageRequest = new PageRequestModel(params);
	      PageModel<RoleResultDTO> pageModel = this.service.listPaginado(pageRequest);
	      return ResponseEntity.ok(pageModel);
	   }

	   @DeleteMapping({"/{id}"})
	   public ResponseEntity<RoleResultDTO> deletar(@PathVariable(name = "id") Long id) {
		  Role object = this.service.removerLogico(id);
	      String dtOp = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
	      RoleResultDTO retorno =new RoleResultDTO(object.getId(), object.getRoleName(),object.getAtivo(), dtOp);
	      return ResponseEntity.ok(retorno);
	   }	
}
