package br.com.cerebroti.rh.usuario.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cerebroti.rh.usuario.dto.RoleResultDTO;
import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.exception.NotFoundException;
import br.com.cerebroti.rh.usuario.model.PageModel;
import br.com.cerebroti.rh.usuario.model.PageRequestModel;
import br.com.cerebroti.rh.usuario.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Component
public class RoleService {
	@Autowired private RoleRepository repository;
   
	//salvar
	public Role salvar (Role object) {
		log.info("Salvando uma Role");
		//object.setAutor((Usuario)this.repositoryUsuario.findByEmail((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get());
		object.setAtivo("S");
		object.setDataCriacao(new Date());
		object.setTipoAlteracao("INSERT");
		Role criado= repository.save(object);
		return criado;
	}
	
	//atualizar
	public Role atualizar(Role object) {
		log.info("Atualizando uma Role");
		//object.setAutor((Usuario)this.repositoryUsuario.findByEmail((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get());
		object.setAtivo("S");
		object.setDataAlteracao(new Date());
		object.setTipoAlteracao("UPDATE");
	    if (this.repository.findById(object.getId()).get() == null) {
	    	throw new NoSuchElementException("Não existe o elemento");
	    } 
	    else {
	    	  Role atualizado = repository.save(object);
	         return atualizado;
	    }
	}
	
	//getbyid
	public Role getById(Long id) {
		log.info("Recuperando uma role");
	    Optional<Role> retornado = this.repository.findById(id);
	      return (Role)retornado.orElseThrow(() -> {
	         return new NotFoundException("Não existe uma role com o id " + id);
	      });
	      

	}

	public List<Role> getAll() {
		log.info("get all role sem paginacao ok");
		return this.repository.findAll();
	}
	   
	

	   public PageModel<RoleResultDTO> listPaginado(PageRequestModel pageRequest) {
	      Pageable paginavel = pageRequest.toSpringPageRequest();
	      Page<Role> pagina = this.repository.findByAtivoOrderByRoleName(paginavel, pageRequest.getAtivo());
	      List<RoleResultDTO> retorno = new ArrayList();
	      Iterator ret = pagina.iterator();

	      while(ret.hasNext()) {
	    	 Role dado = (Role)ret.next();
	         String dtOp = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
	         RoleResultDTO dto = new RoleResultDTO(dado.getId(), dado.getRoleName(), dado.getAtivo(), dtOp);
	         retorno.add(dto);
	      }

	      return new PageModel((int)pagina.getTotalElements(), pagina.getSize(), (int)pagina.getTotalElements(), retorno);
	   }
	   
	   
/*	   
	   
	//listar paginado
	public PageModel<Role> listPaginado(PageRequestModel pageRequest){
		Pageable paginavel  = pageRequest.toSpringPageRequest();
		Page<Role> pagina= repository.findAll(paginavel);
		List<Role> retorno = new ArrayList<Role>();
		for (Role dado : pagina) {
			Role dto = new Role();//dado.getId(),dado.getRoleName()
			retorno.add(dto);
		}
		return new PageModel<Role>((int)pagina.getTotalElements(),pagina.getSize(),(int)pagina.getTotalElements(),retorno);
	}
*/
	//delete logico 
	public Role removerLogico(Long id) {
		Role object = (Role)this.repository.findById(id).get();
		//object.setAutor((Usuario)this.repositoryUsuario.findByEmail((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get());
		object.setAtivo("S");
		object.setDataAlteracao(new Date());
		object.setTipoAlteracao("DELETE");
		object.setAtivo("N");
			if (this.repository.findById(object.getId()).get() == null) {
		        throw new NoSuchElementException("Não existe o elemento");
		    } else {
		    	Role atualizado = this.repository.save(object);
		        return atualizado;
		    }
	}
		
}
