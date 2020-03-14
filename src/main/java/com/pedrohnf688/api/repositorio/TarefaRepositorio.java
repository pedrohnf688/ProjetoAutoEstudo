package com.pedrohnf688.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Tarefa;

@Transactional(readOnly = true)
@Repository
public interface TarefaRepositorio extends JpaRepository<Tarefa, Integer> {

	@Query(value = "SELECT * FROM Tarefa t WHERE t.disciplina_id = :id", nativeQuery = true)
	List<Tarefa> findByListaTarefas(Integer id);
	
	@Query(value = "SELECT * FROM Tarefa t WHERE t.finalizada = false", nativeQuery = true)
	List<Tarefa> findByTarefaStatusAtiva();

	@Query(value = "SELECT * FROM Tarefa t WHERE t.finalizada = true", nativeQuery = true)
	List<Tarefa> findByTarefaStatusFeitas();
}
      