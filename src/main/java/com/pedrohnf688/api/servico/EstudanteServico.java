package com.pedrohnf688.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Estudante;
import com.pedrohnf688.api.repositorio.EstudanteRepositorio;

@Service
public class EstudanteServico {

	private static final Logger log = LoggerFactory.getLogger(EstudanteServico.class);

	@Autowired
	private EstudanteRepositorio er;

	public Estudante inserir(Estudante e) {
		log.info("Criando estudante de nome: {}", e.getNome());
		return this.er.save(e);
	}

	public List<Estudante> listarTodos() {
		log.info("Listando todos os estudantes");
		return this.er.findAll();
	}

	public Optional<Estudante> listaPorId(Integer id) {
		log.info("Buscando estudante por id: {}", id);
		return this.er.findById(id);

	}

	public void deletarPorId(Integer id) {
		log.info("Deletando estudante por id: {}", id);
		this.er.deleteById(id);
	}
}
