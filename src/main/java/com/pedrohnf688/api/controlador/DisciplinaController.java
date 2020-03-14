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
import com.pedrohnf688.api.modelo.Estudante;
import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.dto.DisciplinaDto;
import com.pedrohnf688.api.servico.DisciplinaServico;
import com.pedrohnf688.api.servico.EstudanteServico;
import com.pedrohnf688.api.servico.TarefaServico;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/disciplina")
public class DisciplinaController {

	private static final Logger log = LoggerFactory.getLogger(DisciplinaController.class);

	@Autowired
	private DisciplinaServico ds;

	@Autowired
	private EstudanteServico es;

	@Autowired
	private TarefaServico ts;

	@ApiOperation("Buscar todas as disciplinas")
	@GetMapping
	public ResponseEntity<Response<List<Disciplina>>> listarTodasDisciplinas() {

		Response<List<Disciplina>> response = new Response<List<Disciplina>>();
		List<Disciplina> listaDisciplinas = this.ds.listarTodos();

		if (listaDisciplinas.isEmpty()) {
			response.getErros().add("A lista de disciplinas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaDisciplinas);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Buscar disciplina pelo id")
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Disciplina>> buscarPorId(@PathVariable("id") Integer id) {

		Response<Disciplina> response = new Response<Disciplina>();
		Optional<Disciplina> d = this.ds.listaPorId(id);

		if (!d.isPresent()) {
			response.getErros().add("Disciplina não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(d.get());
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Cadastrar disciplina")
	@PostMapping(value = "{estudanteId}")
	public ResponseEntity<Response<Disciplina>> cadastrar(@Valid @RequestBody Disciplina disciplina,
			@PathVariable("estudanteId") Integer estudanteId, BindingResult result) throws NoSuchAlgorithmException {

		Response<Disciplina> response = new Response<Disciplina>();
		Optional<Estudante> e = this.es.listaPorId(estudanteId);

		if (result.hasErrors()) {
			log.error("Erro validando o cadastro da Disciplina: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		disciplina.getListaEstudantes().add(e.get());
		disciplina.setQtdEstudantes(1);
		this.ds.inserir(disciplina);

		response.setData(disciplina);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Atualizar disciplina")
	@PutMapping(value = "{id}")
	public ResponseEntity<Response<Disciplina>> atualizar(@Valid @RequestBody DisciplinaDto disciplinaDto,
			@PathVariable("id") Integer id, BindingResult result) throws NoSuchAlgorithmException {

		Response<Disciplina> response = new Response<Disciplina>();

		Optional<Disciplina> d = this.ds.listaPorId(id);

		if (!d.isPresent()) {
			response.getErros().add("Disciplina não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		atualizarDadosDisciplina(d.get(), disciplinaDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando a Disciplina:{}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.ds.inserir(d.get());

		response.setData(d.get());
		return ResponseEntity.ok(response);
	}

	private void atualizarDadosDisciplina(Disciplina disciplina, DisciplinaDto disciplinaDto, BindingResult result)
			throws NoSuchAlgorithmException {

		disciplina.setAssunto(disciplinaDto.getAssunto());
		disciplina.setDisciplina(disciplinaDto.getDisciplina());
	}

	@ApiOperation("Buscar tarefas da disciplina pelo id")
	@GetMapping(value = "/tarefas/{id}")
	public ResponseEntity<Response<List<Tarefa>>> tarefasDaDisciplina(@PathVariable("id") Integer id) {

		Response<List<Tarefa>> response = new Response<List<Tarefa>>();

		List<Tarefa> tarefas = this.ts.listarPorDisciplina(id);

		if (tarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas da disciplinas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(tarefas);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Deletar disciplina pelo id")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletarPorId(@PathVariable("id") Integer id) {

		Response<String> response = new Response<String>();
		Optional<Disciplina> d = this.ds.listaPorId(id);

		if (!d.isPresent()) {
			response.getErros().add("Disicplina não encontrada.");
			return ResponseEntity.badRequest().body(response);
		}

		this.ds.deletarPorId(id);
		return ResponseEntity.ok(new Response<String>());
	}

}
