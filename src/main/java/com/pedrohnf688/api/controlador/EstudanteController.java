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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.config.Response;
import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.modelo.Disciplina;
import com.pedrohnf688.api.modelo.Estudante;
import com.pedrohnf688.api.modelo.Grupo;
import com.pedrohnf688.api.modelo.dto.EstudanteDto;
import com.pedrohnf688.api.servico.CredencialServico;
import com.pedrohnf688.api.servico.DisciplinaServico;
import com.pedrohnf688.api.servico.EstudanteServico;

@RestController
@RequestMapping(value = "/estudante")
@CrossOrigin(origins = "*")
public class EstudanteController {

	private static final Logger log = LoggerFactory.getLogger(EstudanteController.class);

	@Autowired
	private EstudanteServico es;

	@Autowired
	private CredencialServico cs;

	@Autowired
	private DisciplinaServico ds;

	@GetMapping
	public ResponseEntity<Response<List<Estudante>>> listarTodosEstudantes() {

		Response<List<Estudante>> response = new Response<List<Estudante>>();
		List<Estudante> listaEstudantes = es.listarTodos();

		if (listaEstudantes.isEmpty()) {
			response.getErros().add("A lista de estudantes está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaEstudantes);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Estudante>> buscarPorId(@PathVariable("id") Integer id) {

		Response<Estudante> response = new Response<Estudante>();
		Optional<Estudante> e = this.es.listaPorId(id);

		if (!e.isPresent()) {
			response.getErros().add("Estudante não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(e.get());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Credencial>> cadastrar(@Valid @RequestBody EstudanteDto estudanteDto,
			BindingResult result) throws NoSuchAlgorithmException {

		Response<Credencial> response = new Response<Credencial>();
		validarDadosExistentes(estudanteDto, result);

		Estudante e = converterDtoParaEstudante(estudanteDto);
		Credencial c = converterDtoParaCredencial(estudanteDto);

		if (result.hasErrors()) {
			log.error("Erro validando o cadastro do Estudante: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.es.inserir(e);
		c.setEstudante(e);
		this.cs.inserir(c);

		response.setData(c);
		return ResponseEntity.ok(response);
	}

	private Credencial converterDtoParaCredencial(EstudanteDto estudanteDto) {
		Credencial c = new Credencial();
		c.setEmail(estudanteDto.getEmail());
		c.setUsername(estudanteDto.getUsername());
		c.setSenha(estudanteDto.getSenha());
		return c;
	}

	private Estudante converterDtoParaEstudante(EstudanteDto estudanteDto) {
		Estudante e = new Estudante();
		e.setDataNascimento(estudanteDto.getDataNascimento());
		e.setDescricao(estudanteDto.getDescricao());
		e.setEscolaridade(estudanteDto.getEscolaridade());
		e.setInstituicao(estudanteDto.getInstituicao());
		e.setNome(estudanteDto.getNome());
		e.setSexo(estudanteDto.getSexo());
		return e;
	}

	private void validarDadosExistentes(EstudanteDto estudanteDto, BindingResult result) {

		this.cs.buscarEmail(estudanteDto.getEmail())
				.ifPresent(c -> result.addError(new ObjectError("credencial", "Email já exixtente.")));

		this.cs.buscarUsername(estudanteDto.getUsername())
				.ifPresent(c -> result.addError(new ObjectError("credencial", "Username já existente.")));

	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Response<Credencial>> atualizarPorId(@PathVariable("id") Integer id,
			@Valid @RequestBody EstudanteDto estudanteDto, BindingResult result) throws NoSuchAlgorithmException {

		Response<Credencial> response = new Response<Credencial>();
		Optional<Credencial> c = this.cs.listaPorId(id);

		if (!c.isPresent()) {
			result.addError(new ObjectError("credencial", "Credencial não encontrada."));
		}

		atualizarDadosEstudante(c.get(), estudanteDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando a Estudante:{}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.cs.inserir(c.get());

		response.setData(c.get());
		return ResponseEntity.ok(response);
	}

	private void atualizarDadosEstudante(Credencial credencial, EstudanteDto estudanteDto, BindingResult result)
			throws NoSuchAlgorithmException {

		if (!credencial.getEmail().equals(estudanteDto.getEmail())) {
			this.cs.buscarEmail(estudanteDto.getEmail())
					.ifPresent(c -> result.addError(new ObjectError("credencial", "Email já exixtente.")));

			credencial.setEmail(estudanteDto.getEmail());
		}

		if (!credencial.getUsername().equals(estudanteDto.getUsername())) {
			this.cs.buscarUsername(estudanteDto.getUsername())
					.ifPresent(c -> result.addError(new ObjectError("credencial", "Username já existente.")));

			credencial.setUsername(estudanteDto.getUsername());
		}

		credencial.setSenha(estudanteDto.getSenha());
		credencial.getEstudante().setDataNascimento(estudanteDto.getDataNascimento());
		credencial.getEstudante().setDescricao(estudanteDto.getDescricao());
		credencial.getEstudante().setEscolaridade(estudanteDto.getEscolaridade());
		credencial.getEstudante().setInstituicao(estudanteDto.getInstituicao());
		credencial.getEstudante().setNome(estudanteDto.getNome());
		credencial.getEstudante().setSexo(estudanteDto.getSexo());
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletarPorId(@PathVariable("id") Integer id) {

		Response<String> response = new Response<String>();
		Optional<Credencial> c = this.cs.listaPorId(id);

		if (!c.isPresent()) {
			response.getErros().add("Credencial não encontrada.");
			return ResponseEntity.badRequest().body(response);
		}

		this.cs.deletarPorId(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "disciplinas/{id}")
	public ResponseEntity<Response<List<Disciplina>>> disciplinasDoEstudante(@PathVariable("id") Integer id) {
		Response<List<Disciplina>> response = new Response<List<Disciplina>>();

		List<Disciplina> disciplinas = this.ds.listarPorEstudante(id);

		if (disciplinas.isEmpty()) {
			response.getErros().add("A lista de disciplinas do estudante está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(disciplinas);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "grupos/{id}")
	public ResponseEntity<Response<List<Grupo>>> gruposDoEstudante() {
		Response<List<Grupo>> response = new Response<List<Grupo>>();

		return ResponseEntity.ok(response);
	}

	// findAll -- Feito
	// findbyId -- Feito
	// save --Feito
	// update -- Feito
	// deletebyId -- Feito
	// listar disciplinas
	// listar grupos
}
