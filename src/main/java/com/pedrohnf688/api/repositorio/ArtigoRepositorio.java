package com.pedrohnf688.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Artigo;

@Transactional(readOnly = true)
@Repository
public interface ArtigoRepositorio extends JpaRepository<Artigo, Integer> {

	@Query(value = "SELECT f FROM Artigo f WHERE f.disciplina.id = :id", nativeQuery = true)
	List<Artigo> findByListasDisciplina(@Param("id") Integer id);

	Artigo findByTitulo(String titulo);

}