package com.pedrohnf688.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Grupo;

@Transactional(readOnly = true)
@Repository
public interface GrupoRepositorio extends JpaRepository<Grupo, Integer> {

	@Query(value = "SELECT id_grupo, data_grupo, qtd_estudantes, titulo FROM grupo INNER JOIN grupo_estudante ON grupo_estudante.grupo_id = grupo.id_grupo INNER JOIN estudante ON grupo_estudante.estudante_id = estudante.id WHERE estudante.id = :id", nativeQuery = true)
	List<Grupo> findByListaGrupos(@Param("id") Integer id);

}
