package br.com.cerebroti.rh.pagamento.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cerebroti.rh.pagamento.entity.Pagamento;
import br.com.cerebroti.rh.pagamento.entity.Trabalhador;
import br.com.cerebroti.rh.pagamento.feing.TrabalhadorFeingClient;

@Service
public class PagamentoService {

	@Autowired private TrabalhadorFeingClient trabalhadorFeing;

	
	public Pagamento getPagamento (long idTrabalhador, int dias){
		//esse cara ai vai invocar o metodo via rest la da outra classe
		Trabalhador trabalhador =trabalhadorFeing.getById(idTrabalhador).getBody();
		return new Pagamento(trabalhador.getNome(),trabalhador.getSalarioDiario(),dias);
	}
	
	
}
