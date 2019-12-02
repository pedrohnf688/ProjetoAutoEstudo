package com.pedrohnf688.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Disciplina;
import com.pedrohnf688.api.modelo.Grupo;
import com.pedrohnf688.api.repositorio.DisciplinaRepositorio;

@Service
public class DisciplinaServico {

	private static final Logger log = LoggerFactory.getLogger(DisciplinaServico.class);

	@Autowired
	private DisciplinaRepositorio dr;

	public Disciplina inserir(Disciplina d) {
		log.info("Criando disciplina de nome: {}", d.getDisciplina());
		return this.dr.save(d);
	}

	public List<Disciplina> listarTodos() {
		log.info("Listando todos as disciplinas");
		return this.dr.findAll();
	}

	public Optional<Disciplina> listaPorId(Integer id) {
		log.info("Buscando disciplina por id: {}", id);
		return this.dr.findById(id);

	}

	public void deletarPorId(Integer id) {
		log.info("Deletando disciplina por id: {}", id);
		this.dr.deleteById(id);
	}

}
