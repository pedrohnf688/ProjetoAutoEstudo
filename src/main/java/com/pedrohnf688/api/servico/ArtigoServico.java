package com.pedrohnf688.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Artigo;
import com.pedrohnf688.api.repositorio.ArtigoRepositorio;

@Service
public class ArtigoServico {

	private static final Logger log = LoggerFactory.getLogger(ArtigoServico.class);

	@Autowired
	private ArtigoRepositorio ar;

	public Artigo inserir(Artigo a) {
		log.info("Criando artigo de titulo: {}", a.getTitulo());
		return this.ar.save(a);
	}

	public List<Artigo> listarTodos() {
		log.info("Listando todos os artigos");
		return this.ar.findAll();
	}

	public Optional<Artigo> listaPorId(Integer id) {
		log.info("Buscando artigo por id: {}", id);
		return this.ar.findById(id);
	}

	public void deletarPorId(Integer id) {
		log.info("Deletando artigo por id: {}", id);
		this.ar.deleteById(id);
	}

	public List<Artigo> listarPorDisciplina(Integer id) {
		log.info("Listando todos os artigos da disciplina: {}", id);
		return this.ar.findByListasDisciplina(id);
	}

	public Optional<Artigo> listaPorTitulo(String titulo) {
		log.info("Buscando artigo por titulo: {}", titulo);
		return Optional.ofNullable(this.ar.findByTitulo(titulo));

	}

}
