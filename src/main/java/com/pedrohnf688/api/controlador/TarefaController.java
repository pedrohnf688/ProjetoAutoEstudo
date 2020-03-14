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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.config.Response;
import com.pedrohnf688.api.modelo.Disciplina;
import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.dto.TarefaDto;
import com.pedrohnf688.api.servico.DisciplinaServico;
import com.pedrohnf688.api.servico.TarefaServico;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/tarefa")
public class TarefaController {

	private static final Logger log = LoggerFactory.getLogger(TarefaController.class);

	@Autowired
	private TarefaServico ts;

	@Autowired
	private DisciplinaServico ds;

	@ApiOperation("Buscar todas as tarefas")
	@GetMapping
	public ResponseEntity<Response<List<Tarefa>>> listarTodos() {

		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefas = this.ts.listarTodos();

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Buscar tarefas pelo id")
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Tarefa>> buscarPorId(@PathVariable("id") Integer id) {

		Response<Tarefa> response = new Response<Tarefa>();
		Optional<Tarefa> t = this.ts.listaPorId(id);

		if (!t.isPresent()) {
			response.getErros().add("Tarefa não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(t.get());
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Cadastrar tarefa na disciplina pelo id")
	@PostMapping(value = "{disciplinaId}")
	public ResponseEntity<Response<Tarefa>> cadastrar(@Valid @RequestBody Tarefa tarefa,
			@PathVariable("disciplinaId") Integer disciplinaId, BindingResult result) throws NoSuchAlgorithmException {

		Response<Tarefa> response = new Response<Tarefa>();
		Optional<Disciplina> d = this.ds.listaPorId(disciplinaId);

		if (result.hasErrors()) {
			log.error("Erro validando o cadastro da Tarefa: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		tarefa.setDisciplina(d.get());
		this.ts.inserir(tarefa);

		response.setData(tarefa);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Atualizar tarefa")
	@PutMapping(value = "{id}")
	public ResponseEntity<Response<Tarefa>> atualizar(@Valid @RequestBody TarefaDto tarefaDto,
			@PathVariable("id") Integer id, BindingResult result) throws NoSuchAlgorithmException {

		Response<Tarefa> response = new Response<Tarefa>();

		Optional<Tarefa> t = this.ts.listaPorId(id);

		if (!t.isPresent()) {
			response.getErros().add("Tarefa não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		atualizarDadosTarefa(t.get(), tarefaDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando a Tarefa:{}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.ts.inserir(t.get());

		response.setData(t.get());
		return ResponseEntity.ok(response);
	}

	private void atualizarDadosTarefa(Tarefa tarefa, TarefaDto tarefaDto, BindingResult result)
			throws NoSuchAlgorithmException {

		tarefa.setDescricaoTarefa(tarefaDto.getDescricaoTarefa());
		tarefa.setFinalizada(tarefaDto.isFinalizada());
		tarefa.setInicioTarefa(tarefaDto.getInicioTarefa());
		tarefa.setPrioridade(tarefaDto.getPrioridade());
		tarefa.setTerminoTarefa(tarefaDto.getTerminoTarefa());
		tarefa.setTituloTarefa(tarefaDto.getTituloTarefa());

	}

	@ApiOperation("Deletar tarefa pelo id")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletarPorId(@PathVariable("id") Integer id) {

		Response<String> response = new Response<String>();
		Optional<Tarefa> t = this.ts.listaPorId(id);

		if (!t.isPresent()) {
			response.getErros().add("Tarefa não encontrada.");
			return ResponseEntity.badRequest().body(response);
		}

		this.ts.deletarPorId(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@ApiOperation("Buscar todas as tarefas ativas")
	@GetMapping(value = "ativas")
	public ResponseEntity<Response<List<Tarefa>>> listarTarefasAtivas() {

		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefasAtivas = this.ts.listaTarefaAtiva();

		if (listaTarefasAtivas.isEmpty()) {
			response.getErros().add("A lista de tarefas ativas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefasAtivas);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Buscar todas as tarefas concluidas")
	@GetMapping(value ="feitas")
	public ResponseEntity<Response<List<Tarefa>>> listarTarefasFeitas() {

		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefasFeitas = this.ts.listaTarefaFeita();

		if (listaTarefasFeitas.isEmpty()) {
			response.getErros().add("A lista de tarefas concluidas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefasFeitas);
		return ResponseEntity.ok(response);
	}

}
