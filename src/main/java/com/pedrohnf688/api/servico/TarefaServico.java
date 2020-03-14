package com.pedrohnf688.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.repositorio.TarefaRepositorio;

@Service
public class TarefaServico {

	private static final Logger log = LoggerFactory.getLogger(TarefaServico.class);

	@Autowired
	private TarefaRepositorio tr;
	
	public Tarefa inserir(Tarefa t) {
		log.info("Criando tarefa de titulo: {}", t.getTituloTarefa());
		return this.tr.save(t);
	}

	public List<Tarefa> listarTodos() {
		log.info("Listando todos as tarefas");
		return this.tr.findAll();
	}

	public Optional<Tarefa> listaPorId(Integer id) {
		log.info("Buscando tarefa por id: {}", id);
		return this.tr.findById(id);

	}

	public void deletarPorId(Integer id) {
		log.info("Deletando tarefa por id: {}", id);
		this.tr.deleteById(id);
	}
	
	public List<Tarefa> listarPorDisciplina(Integer id){
		log.info("Listando todos as tarefas da Disciplinas: {}", id);
		return this.tr.findByListaTarefas(id);
	}


	public List<Tarefa> listaTarefaAtiva(){
		log.info("Listando todos as tarefas ativas");
		return this.tr.findByTarefaStatusAtiva();
	}

	public List<Tarefa> listaTarefaFeita(){
		log.info("Listando todos as tarefas concluidas");
		return this.tr.findByTarefaStatusFeitas();
	}

}
