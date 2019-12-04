package com.pedrohnf688.api.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity(name = "conversa")
@Table
public class Conversa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "mensagem")
	private String mensagem;

	@Column(name = "nome", nullable = false)
	@NotBlank(message = "O campo nome não pode ser vazio.")
	private String nome;

	@Column(name = "data", nullable = false)
	@NotBlank(message = "O campo data não pode ser vazio.")
	private String data;

	@OneToOne
	private Arquivo foto;

	public Conversa() {
		super();
	}

	public Conversa(String mensagem, @NotBlank(message = "O campo nome não pode ser vazio.") String nome,
			@NotBlank(message = "O campo data não pode ser vazio.") String data, Arquivo foto) {
		super();
		this.mensagem = mensagem;
		this.nome = nome;
		this.data = data;
		this.foto = foto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Arquivo getFoto() {
		return foto;
	}

	public void setFoto(Arquivo foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Conversa [id=" + id + ", mensagem=" + mensagem + ", nome=" + nome + ", data=" + data + ", foto=" + foto
				+ "]";
	}

}
