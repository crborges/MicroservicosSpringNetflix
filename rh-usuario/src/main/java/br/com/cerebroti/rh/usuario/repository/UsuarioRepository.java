package br.com.cerebroti.rh.usuario.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario  findByEmailAndPassword(String email, String password);
	public Usuario  findByEmail(String email);
	

}