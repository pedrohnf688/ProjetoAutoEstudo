package com.pedrohnf688.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Conversa;
import com.pedrohnf688.api.repositorio.ConversaRepositorio;

@Service
public class ConversaServico {

	private static final Logger log = LoggerFactory.getLogger(ConversaServico.class);

	@Autowired
	private ConversaRepositorio cr;

	public Conversa inserir(Conversa c) {
		log.info("Criando conversa do usuario: {}", c.getNome());
		return this.cr.save(c);
	}

	public List<Conversa> listarTodos() {
		log.info("Listando todos os conversas");
		return this.cr.findAll();
	}

	public Optional<Conversa> listaPorId(Integer id) {
		log.info("Buscando conversa por id: {}", id);
		return this.cr.findById(id);
	}

	public void deletarPorId(Integer id) {
		log.info("Deletando conversa por id: {}", id);
		this.cr.deleteById(id);
	}

	public List<Conversa> listarPorGrupo(Integer id) {
		log.info("Listando todos as conversas do grupo: {}", id);
		return this.cr.findByListasGrupo(id);
	}

}
