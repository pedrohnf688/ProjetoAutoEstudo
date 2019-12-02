package com.pedrohnf688.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Grupo;
import com.pedrohnf688.api.repositorio.GrupoRepositorio;

@Service
public class GrupoServico {

	private static final Logger log = LoggerFactory.getLogger(GrupoServico.class);

	@Autowired
	private GrupoRepositorio gr;

	public Grupo inserir(Grupo g) {
		log.info("Criando grupo de titulo: {}", g.getTitulo());
		return this.gr.save(g);
	}

	public List<Grupo> listarTodos() {
		log.info("Listando todos os grupos");
		return this.gr.findAll();
	}

	public Optional<Grupo> listaPorId(Integer id) {
		log.info("Buscando grupo por id: {}", id);
		return this.gr.findById(id);

	}

	public void deletarPorId(Integer id) {
		log.info("Deletando grupo por id: {}", id);
		this.gr.deleteById(id);
	}

}
