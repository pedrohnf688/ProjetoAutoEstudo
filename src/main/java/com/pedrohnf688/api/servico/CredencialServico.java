package com.pedrohnf688.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.repositorio.CredencialRepositorio;

@Service
public class CredencialServico {

	private static final Logger log = LoggerFactory.getLogger(CredencialServico.class);

	@Autowired
	private CredencialRepositorio cr;

	public Credencial inserir(Credencial c) {
		log.info("Criando credencial de username: {}", c.getUsername());
		return this.cr.save(c);
	}

	public List<Credencial> listarTodos() {
		log.info("Listando todos as credenciais");
		return this.cr.findAll();
	}

	public Optional<Credencial> listaPorId(Integer id) {
		log.info("Buscando credencial por id: {}", id);
		return this.cr.findById(id);

	}

	public void deletarPorId(Integer id) {
		log.info("Deletando credencial por id: {}", id);
		this.cr.deleteById(id);
	}

	public Optional<Credencial> buscarEmail(String email) {
		log.info("Buscando email: {}", email);
		return Optional.ofNullable(this.cr.findByEmail(email));
	}

	public Optional<Credencial> buscarUsername(String username) {
		log.info("Buscando username: {}", username);
		return Optional.ofNullable(this.cr.findByUsername(username));
	}

}
