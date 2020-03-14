package com.pedrohnf688.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Paragrafo;
import com.pedrohnf688.api.repositorio.ParagrafoResositorio;

@Service
public class ParagrafoServico {

	private static final Logger log = LoggerFactory.getLogger(ParagrafoServico.class);

	@Autowired
	private ParagrafoResositorio pr;

	public Paragrafo inserir(Paragrafo c) {
		log.info("Criando titulo do paragrafo: {}", c.getTopico());
		return this.pr.save(c);
	}

	public List<Paragrafo> listarTodos() {
		log.info("Listando todas os Paragrafos");
		return this.pr.findAll();
	}

	public Optional<Paragrafo> listaPorId(Integer id) {
		log.info("Buscando paragrafo por id: {}", id);
		return this.pr.findById(id);

	}

	public void deletarPorId(Integer id) {
		log.info("Deletando paragrafo por id: {}", id);
		this.pr.deleteById(id);
	}

	public List<Paragrafo> listarPorArtigo(Integer id) {
		log.info("Listando todos os artigos da disciplina: {}", id);
		return this.pr.findByListasArtigo(id);
	}

}
