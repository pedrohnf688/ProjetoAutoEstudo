package com.pedrohnf688.api.controlador;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.config.Response;
import com.pedrohnf688.api.modelo.Conversa;
import com.pedrohnf688.api.modelo.Grupo;
import com.pedrohnf688.api.servico.ConversaServico;
import com.pedrohnf688.api.servico.GrupoServico;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/conversa")
public class ConversaController {

	private static final Logger log = LoggerFactory.getLogger(ConversaController.class);

	@Autowired
	private ConversaServico cs;

	@Autowired
	private GrupoServico gs;

	@ApiOperation("Buscar todas as conversas")
	@GetMapping
	public ResponseEntity<Response<List<Conversa>>> listarTodasConversas() {

		Response<List<Conversa>> response = new Response<List<Conversa>>();
		List<Conversa> listaConversas = this.cs.listarTodos();

		if (listaConversas.isEmpty()) {
			response.getErros().add("A lista de conversas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaConversas);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Buscar conversa pelo id")
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Conversa>> buscarPorId(@PathVariable("id") Integer id) {

		Response<Conversa> response = new Response<Conversa>();
		Optional<Conversa> c = this.cs.listaPorId(id);

		if (!c.isPresent()) {
			response.getErros().add("Conversa não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(c.get());
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Buscar conversa pelo id do grupo")
	@GetMapping(value = "/grupo/{id}")
	public ResponseEntity<Response<List<Conversa>>> buscarPorGrupoId(@PathVariable("id") Integer id) {

		Response<List<Conversa>> response = new Response<List<Conversa>>();
		List<Conversa> listaConversas = this.cs.listarPorGrupo(id);

		if (listaConversas.isEmpty()) {
			response.getErros().add("A lista de conversas do grupo está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaConversas);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Cadastrar conversa")
	@PostMapping(value = "{grupoId}")
	public ResponseEntity<Response<Conversa>> cadastrar(@Valid @RequestBody Conversa conversa,
			@PathVariable("grupoId") Integer grupoId, BindingResult result) throws NoSuchAlgorithmException {

		Response<Conversa> response = new Response<Conversa>();
		Optional<Grupo> g = this.gs.listaPorId(grupoId);

		if (result.hasErrors()) {
			log.error("Erro validando o cadastro da Conversa: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		conversa.setGrupo(g.get());
		this.cs.inserir(conversa);

		response.setData(conversa);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Deletar conversa pelo id")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletarPorId(@PathVariable("id") Integer id) {

		Response<String> response = new Response<String>();
		Optional<Conversa> c = this.cs.listaPorId(id);

		if (!c.isPresent()) {
			response.getErros().add("Conversa não encontrada.");
			return ResponseEntity.badRequest().body(response);
		}

		this.cs.deletarPorId(id);
		return ResponseEntity.ok(new Response<String>());
	}

}
