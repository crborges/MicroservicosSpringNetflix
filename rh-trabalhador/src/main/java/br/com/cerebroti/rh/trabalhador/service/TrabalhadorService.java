package br.com.cerebroti.rh.trabalhador.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.cerebroti.rh.trabalhador.entity.Trabalhador;
import br.com.cerebroti.rh.trabalhador.model.PageModel;
import br.com.cerebroti.rh.trabalhador.model.PageRequestModel;
import br.com.cerebroti.rh.trabalhador.repository.TrabalhadorRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Component
public class TrabalhadorService {
	@Autowired private TrabalhadorRepository repository;

	//salvar
	public Trabalhador salvar (Trabalhador object) {
			log.info("Save trabalhador ok");
			Trabalhador criado= repository.save(object);
			return criado;
	}
		
	//atualizar
	public Trabalhador atualizar(Trabalhador object) {
		log.info("Update trabalhador ok");
		Trabalhador atualizado = repository.save(object);
		return atualizado;
	}

	//getbyid
	public Trabalhador getById(Long id) {
		log.info("Get by id  trabalhador ok");
		Optional<Trabalhador> retornado= repository.findById(id);
		return retornado.get();
	}

	//listar paginado
	public PageModel<Trabalhador> listPaginado(PageRequestModel pageRequest){
		log.info("Get paginado trabalhador ok");
		Pageable paginavel  = pageRequest.toSpringPageRequest();
		Page<Trabalhador> pagina= repository.findAll(paginavel);
		List<Trabalhador> retorno = new ArrayList<Trabalhador>();
		for (Trabalhador dado : pagina) {
			Trabalhador dto = new Trabalhador(dado.getId(),dado.getNome(),dado.getSalarioDiario());
			retorno.add(dto);
		}
		return new PageModel<Trabalhador>((int)pagina.getTotalElements(),pagina.getSize(),(int)pagina.getTotalElements(),retorno);
	}

	//delete logico 
	public void remover (Long id) {
		log.info("Remover trabalhador ok");
		repository.delete(repository.findById(id).get());
	}

}
