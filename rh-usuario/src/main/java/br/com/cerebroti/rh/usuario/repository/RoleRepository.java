package br.com.cerebroti.rh.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cerebroti.rh.usuario.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {  }
