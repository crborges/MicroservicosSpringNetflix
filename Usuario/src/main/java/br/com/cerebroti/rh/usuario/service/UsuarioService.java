package br.com.cerebroti.rh.usuario.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.cerebroti.rh.usuario.dto.UsuarioLoginDTO;
import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.entity.Usuario;
import br.com.cerebroti.rh.usuario.exception.NotFoundException;
import br.com.cerebroti.rh.usuario.model.PageModel;
import br.com.cerebroti.rh.usuario.model.PageRequestModel;
import br.com.cerebroti.rh.usuario.repository.RoleRepository;
import br.com.cerebroti.rh.usuario.repository.UsuarioRepository;
import br.com.cerebroti.rh.usuario.resource.util.HashUtil;
import br.com.cerebroti.rh.usuario.specification.UsuarioSpecification;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Component
public class UsuarioService {

	
	@Autowired private UsuarioRepository repository;

	

	
	//retorna por email para login externo 
	public Usuario getByEmail(String email) { log.info("logando sistema"); return repository.findByEmail(email);}
	
	
	
	
		
	  public Usuario salvarUsuario(Usuario usuario) {
		  log.info("Salvando um usuario");
	      String novaSenha = HashUtil.getHashSenha(usuario.getPassword());
	      usuario.setPassword(novaSenha);
	      usuario.setAtivo("S");
	      usuario.setDataCriacao(new Date());
	      usuario.setTipoAlteracao("INSERT");
	      Usuario criado = (Usuario)this.repository.save(usuario);
	      return criado;
	   }

	  
	  
	   public Usuario atualizarUsuario(Usuario usuario) {
		  log.info("atualizando um usuario");
	      String novaSenha = HashUtil.getHashSenha(usuario.getPassword());
	      usuario.setPassword(novaSenha);
	      usuario.setAtivo("S");
	      usuario.setDataAlteracao(new Date());
	      usuario.setTipoAlteracao("UPDATE");
	      Usuario atualizado = this.repository.save(usuario);
	      return atualizado;
	   }

	   public Usuario getUsuarioById(Long id) {
		   log.info("usuario by id");
		   Optional<Usuario> retornado = this.repository.findById(id);
	      return (Usuario)retornado.orElseThrow(() -> {
	         return new NotFoundException("Não existe um Usuário com o id " + id);
	      });
	   }


	   public List<Usuario> getAllUsuario() {return this.repository.findAll();}

	   public PageModel<Usuario> listPaginado(PageRequestModel pageRequest) {
	      Pageable paginavel = pageRequest.toSpringPageRequest();
	      Specification<Usuario> specification = UsuarioSpecification.BuscaUsuario(pageRequest.getBusca());
	      Page<Usuario> pagina = this.repository.findAll(specification, paginavel);
	      return new PageModel((int)pagina.getTotalElements(), pagina.getSize(), (int)pagina.getTotalElements(), pagina.getContent());
	   }

	   
	   public Usuario removerLogico(Long id) {
		   Usuario object = this.repository.findById(id).get();
			//object.setAutor((Usuario)this.repositoryUsuario.findByEmail((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get());
			object.setAtivo("S");
			object.setDataAlteracao(new Date());
			object.setTipoAlteracao("DELETE");
			object.setAtivo("N");
				if (this.repository.findById(object.getId()).get() == null) {
			        throw new NoSuchElementException("Não existe o elemento");
			    } else {
			    	Usuario atualizado = this.repository.save(object);
			        return atualizado;
			    }
		}
		
	   
	   
	   
	   /*metodos bem ruisnpara mexer nas roles do usuario que funcionan*/
	   public int criarRole(Long id_usuario, Long id_role) 		{return this.repository.inserirRole(id_usuario, id_role);	}
	   public int deletarRole(Long id_usuario, Long id_role) 	{return this.repository.deleteRole(id_usuario, id_role);	}






	   
	   
//	   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	      Optional<Usuario> resultado = this.usuarioRepository.findByEmail(username);
//	      if (!resultado.isPresent()) {
//	         throw new UsernameNotFoundException("Não existe um usuário com o email " + username);
//	      } else {
//	         Usuario usuario = (Usuario)resultado.get();
//	         List<GrantedAuthority> permissoes = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()));
//	         User usuarioSpring = new User(usuario.getEmail(), usuario.getSenha(), permissoes);
//	         return usuarioSpring;
//	      }
//	   }
	
	
	
	
	
	
	
}
