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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.config.Response;
import com.pedrohnf688.api.modelo.Artigo;
import com.pedrohnf688.api.servico.ArtigoServico;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/artgio")
public class ArtigoController {

	private static final Logger log = LoggerFactory.getLogger(ArtigoController.class);

	@Autowired
	private ArtigoServico as;

	@ApiOperation("Buscar todos os artigos")
	@GetMapping
	public ResponseEntity<Response<List<Artigo>>> listarTodosArtigos() {

		Response<List<Artigo>> response = new Response<List<Artigo>>();
		List<Artigo> listaArtigos = this.as.listarTodos();

		if (listaArtigos.isEmpty()) {
			response.getErros().add("A lista de artigos está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaArtigos);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Buscar artigo pelo id")
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Artigo>> buscarPorId(@PathVariable("id") Integer id) {

		Response<Artigo> response = new Response<Artigo>();
		Optional<Artigo> a = this.as.listaPorId(id);

		if (!a.isPresent()) {
			response.getErros().add("Artigo não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(a.get());
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Buscar artigo pelo titulo")
	@GetMapping(value = "/titulo")
	public ResponseEntity<Response<Artigo>> buscarPorId(@RequestParam("titulo") String titulo) {

		Response<Artigo> response = new Response<Artigo>();
		Optional<Artigo> a = this.as.listaPorTitulo(titulo);

		if (!a.isPresent()) {
			response.getErros().add("Artigo não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(a.get());
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Atualizar artigo")
	@PutMapping(value = "{id}")
	public ResponseEntity<Response<Artigo>> atualizar(@Valid @RequestBody Artigo artigo, @PathVariable("id") Integer id,
			BindingResult result) throws NoSuchAlgorithmException {

		Response<Artigo> response = new Response<Artigo>();

		Optional<Artigo> a = this.as.listaPorId(id);

		if (!a.isPresent()) {
			response.getErros().add("Artigo não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		atualizarDadosArtigo(a.get(), artigo, result);

		if (result.hasErrors()) {
			log.error("Erro validando a Artigo:{}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.as.inserir(a.get());

		response.setData(a.get());
		return ResponseEntity.ok(response);
	}

	private void atualizarDadosArtigo(Artigo a, Artigo artigo, BindingResult result) throws NoSuchAlgorithmException {

		a.setAutor(artigo.getAutor());
		a.setDataAtualizacao(artigo.getDataAtualizacao());
		a.setLinkArtigo(artigo.getLinkArtigo());
		a.setSubtitulo(artigo.getSubtitulo());
	}

	@ApiOperation("Deletar artigo pelo id")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletarPorId(@PathVariable("id") Integer id) {

		Response<String> response = new Response<String>();
		Optional<Artigo> a = this.as.listaPorId(id);

		if (!a.isPresent()) {
			response.getErros().add("Artigo não encontrada.");
			return ResponseEntity.badRequest().body(response);
		}

		this.as.deletarPorId(id);
		return ResponseEntity.ok(new Response<String>());
	}

}
