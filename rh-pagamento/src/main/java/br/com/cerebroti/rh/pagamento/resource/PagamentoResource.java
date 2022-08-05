package br.com.cerebroti.rh.pagamento.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

import br.com.cerebroti.rh.pagamento.entity.*;
import br.com.cerebroti.rh.pagamento.service.*;

@RestController
@RequestMapping(value="/pagamento")
public class PagamentoResource {

@Autowired private PagamentoService service;


@HystrixCommand(fallbackMethod = "getPagamentoErro") /*comando do serviço de tolerancia a falhas do netflix circut breaker para ser executado quando a chamada deste metodo remoto do outro serviço falhar*/
@GetMapping(value = "/{idTrabalhador}/dias/{dias}")
public ResponseEntity<Pagamento> getPagamento(@PathVariable(name = "idTrabalhador") Long idTrabalhador,@PathVariable(name = "dias") int dias) throws Exception{
	Pagamento pagamento = 	service.getPagamento(idTrabalhador, dias);
	return ResponseEntity.ok(pagamento);
	 
 }


/*esse metodo da classe vai ser chamado em caso de dar erro na chamada original do metodo*/
public ResponseEntity<Pagamento> getPagamentoErro(Long idTrabalhador, int dias){
	Pagamento pagamento = 	new Pagamento("Serviço de trabalhador fora do ar",0,0);
	return ResponseEntity.ok(pagamento);
	 
 }


}
