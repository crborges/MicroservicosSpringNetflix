package br.com.cerebroti.rh.usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.cerebroti.rh.usuario.DTO.UsuarioLoginDTO;
import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.entity.Usuario;
import br.com.cerebroti.rh.usuario.model.PageModel;
import br.com.cerebroti.rh.usuario.model.PageRequestModel;
import br.com.cerebroti.rh.usuario.repository.RoleRepository;
import br.com.cerebroti.rh.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Component
public class UsuarioService {

	
	@Autowired private UsuarioRepository repository;

	//salvar
	public Usuario salvar (Usuario object) {
		Usuario criado= repository.save(object);
		return criado;
	}
	
	//atualizar
	public Usuario atualizar(Usuario object) {
		Usuario atualizado = repository.save(object);
		return atualizado;
	}
	
	//getbyid
	public Usuario getById(Long id) {
		Optional<Usuario> retornado= repository.findById(id);
		return retornado.get();
	}

	//listar paginado
	public PageModel<Usuario> listPaginado(PageRequestModel pageRequest){
		Pageable paginavel  = pageRequest.toSpringPageRequest();
		Page<Usuario> pagina= repository.findAll(paginavel);
		List<Usuario> retorno = new ArrayList<Usuario>();
		for (Usuario dado : pagina) {
			Usuario dto = new Usuario(dado.getId(),dado.getNome(),dado.getEmail(),dado.getPassword(),dado.getRoles());
			retorno.add(dto);
		}
		return new PageModel<Usuario>((int)pagina.getTotalElements(),pagina.getSize(),(int)pagina.getTotalElements(),retorno);
	}

	//delete logico 
	public void remover (Long id) {
		repository.delete(repository.findById(id).get());
	}
	
	
	//logar
	public Usuario logar(UsuarioLoginDTO usuario) {
		String senha =usuario.getSenha();//HashUtil.getHashSenha(senha);
		Usuario logado = repository.findByEmailAndPassword(usuario.getEmail(),senha);
		return logado;
	}
		
	//retorna por email
	public Usuario getByEmail(String email) { return repository.findByEmail(email);	}
	
}
