package br.com.cerebroti.rh.usuario.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cerebroti.rh.usuario.entity.Role;
import br.com.cerebroti.rh.usuario.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> , JpaSpecificationExecutor<Usuario> {
	
	public Usuario  findByEmailAndPassword(String email, String password);
	public Usuario  findByEmail(String email);
	

	 @Transactional
	 @Modifying(clearAutomatically = true)
	 @Query(value="INSERT INTO usuario_role (id_usuario, id_role) VALUES (:usuario, :role)", nativeQuery=true)
	 int inserirRole(long usuario, long role);
	 

	 
	 @Transactional
	 @Modifying(clearAutomatically = true)
	 @Query(value="DELETE FROM usuario_role WHERE  id_usuario=:usuario AND id_role=:role", nativeQuery=true)
	 int deleteRole(long usuario, long role);

	 
}