package br.com.cerebroti.rh.trabalhador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cerebroti.rh.trabalhador.entity.Trabalhador;

@Repository
public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {}
