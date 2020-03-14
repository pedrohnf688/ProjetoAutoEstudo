package com.pedrohnf688.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Conversa;

@Transactional(readOnly = true)
@Repository
public interface ConversaRepositorio extends JpaRepository<Conversa, Integer> {

	@Query(value = "SELECT f FROM Conversa f WHERE f.grupo.id = :id", nativeQuery = true)
	List<Conversa> findByListasGrupo(@Param("id") Integer id);

}
