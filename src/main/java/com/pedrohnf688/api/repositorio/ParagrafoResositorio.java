package com.pedrohnf688.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Paragrafo;

@Transactional(readOnly = true)
@Repository
public interface ParagrafoResositorio extends JpaRepository<Paragrafo, Integer> {

	@Query(value = "SELECT f FROM Paragrafo f WHERE f.artigo.id = :id", nativeQuery = true)
	List<Paragrafo> findByListasArtigo(@Param("id") Integer id);

}
