package br.com.cerebroti.rh.usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.model.PageModel;
import br.com.cerebroti.rh.usuario.model.PageRequestModel;
import br.com.cerebroti.rh.usuario.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Component
public class RoleService {
	@Autowired private RoleRepository repository;

	//salvar
	public Role salvar (Role object) {
		Role criado= repository.save(object);
		return criado;
	}
	
	//atualizar
	public Role atualizar(Role object) {
		Role atualizado = repository.save(object);
		return atualizado;
	}
	
	//getbyid
	public Role getById(Long id) {
		Optional<Role> retornado= repository.findById(id);
		return retornado.get();
	}

	//listar paginado
	public PageModel<Role> listPaginado(PageRequestModel pageRequest){
		Pageable paginavel  = pageRequest.toSpringPageRequest();
		Page<Role> pagina= repository.findAll(paginavel);
		List<Role> retorno = new ArrayList<Role>();
		for (Role dado : pagina) {
			Role dto = new Role(dado.getId(),dado.getRoleName());
			retorno.add(dto);
		}
		return new PageModel<Role>((int)pagina.getTotalElements(),pagina.getSize(),(int)pagina.getTotalElements(),retorno);
	}

	//delete logico 
	public void remover (Long id) {
		repository.delete(repository.findById(id).get());
	}
		
}
