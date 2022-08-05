package br.com.cerebroti.rh.trabalhador.resource;

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

import br.com.cerebroti.rh.trabalhador.entity.Trabalhador;
import br.com.cerebroti.rh.trabalhador.model.PageModel;
import br.com.cerebroti.rh.trabalhador.model.PageRequestModel;
import br.com.cerebroti.rh.trabalhador.service.TrabalhadorService;
import lombok.extern.slf4j.Slf4j;
@RefreshScope /*essa anotacao tem que colocar em  todas as classes que acessam config online para que os sistema tualize automaticamente se precisar*/
@Slf4j
@RestController
@RequestMapping(value="trabalhador")
public class TrabalhadorResource {

	
	@Autowired private Environment enviroment;
	@Autowired private TrabalhadorService service;
	//@Value("${test.config}") private String configTeste;
	
		@PostMapping
		public  ResponseEntity<Trabalhador> salvar(@RequestBody Trabalhador DTO){
			Trabalhador criado = service.salvar(DTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(criado);
		}

		@PutMapping("/{id}")
		public ResponseEntity<Trabalhador> atualizar(@PathVariable(name = "id") Long id, @RequestBody  Trabalhador DTO){
			DTO.setId(id);
			Trabalhador atualizaddo= service.atualizar(DTO);
			return ResponseEntity.ok(DTO);
		}

		@GetMapping("/{id}")
		public ResponseEntity<Trabalhador> getById(@PathVariable(name = "id") Long id) throws Exception{
			
			/*
			Mareta para forcar que de uma execao  e ver a tolerancia do microserviço
			int x =1;
			if (x==1) {
				throw new RuntimeException("Aqui deu merda na execucao");
			}
			*/
			
			
			/*Configuracao para fazer o servico dar um timeout de 3 segundos na na marra, o padrão do ribbon de espera é 1 segundo em 3 segundos ele sempre vai cair por timeout
			try {
				log.info("Vai esperar 3 segundos antes de cair");
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			/*para ver qual porta esta rodando eu vou criar um oog e buscar isso de enviroments*/
			log.info("Porta  =="+enviroment.getProperty("local.server.port"));
			
			Trabalhador object = service.getById(id);
			return ResponseEntity.ok(object);
		}
		
		@GetMapping
		public ResponseEntity<PageModel<Trabalhador>> getAll(@RequestParam Map<String, String> params){
			PageRequestModel pageRequest = new PageRequestModel(params);
			PageModel<Trabalhador> pageModel = service.listPaginado(pageRequest);
			return ResponseEntity.ok(pageModel);
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<String> deletar(@PathVariable(name = "id") Long id){
			service.remover(id);
			return ResponseEntity.ok("Deletado");
		}
		
		
		@GetMapping(value="/configs")
		public ResponseEntity<Void> getConfig(){
			log.info("Configuracao coletada do git hub == aaaa");
			return ResponseEntity.noContent().build();
			
		}
		
		
		
		
	
}
