package com.pedrohnf688.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Disciplina;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplina, Integer> {

	@Query(value = "SELECT id_disciplina, assunto, data_disciplina, disciplina, qtd_estudantes FROM disciplina INNER JOIN disciplina_estudante ON disciplina_estudante.disciplina_id = disciplina.id_disciplina INNER JOIN estudante ON disciplina_estudante.estudante_id = estudante.id WHERE disciplina_estudante.estudante_id = :id", nativeQuery = true)
	List<Disciplina> findByListaDisciplinas(@Param("id") Integer id);

}

