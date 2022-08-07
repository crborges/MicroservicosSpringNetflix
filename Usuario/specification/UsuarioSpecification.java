package br.com.cerebroti.rh.usuario.specification;


import org.springframework.data.jpa.domain.Specification;

import br.com.cerebroti.rh.usuario.entity.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecification {

	public static Specification<Usuario> BuscaUsuario(String texto){
		return new Specification<Usuario>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//local onde eu vou colocar os criterios de busca
				if(texto== null ||texto.trim().length() <=0) {return null;}//retorna nulo pque não tem criterios de busca
				String like ="%"+texto+"%";
				Predicate predicado = criteriaBuilder.or(
						criteriaBuilder.like(root.get("nome"), like),
						criteriaBuilder.like(root.get("email"), like)
						/*,criteriaBuilder.like(root.get("role").as(String.class), like)*/
						);
				return predicado;
			}
		};
		
	}
}