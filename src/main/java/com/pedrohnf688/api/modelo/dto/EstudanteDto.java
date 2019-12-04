package com.pedrohnf688.api.modelo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pedrohnf688.api.modelo.enums.EnumSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoEscolaridade;

public class EstudanteDto {

	@NotBlank(message = "O campo nome não pode ser vazio.")
	private String nome;

	@NotBlank(message = "O campo instituicao não pode ser vazio.")
	private String instituicao;

	private String descricao;

	@NotNull(message = "O campo sexo não pode ser vazio.")
	private EnumSexo sexo;

	@NotBlank(message = "O campo dataNascimento não pode ser vazio.")
	private String dataNascimento;

	@NotNull(message = "O campo escolaridade não pode ser vazio.")
	private EnumTipoEscolaridade escolaridade;

	@NotBlank(message = "O campo username não pode ser vazio.")
	private String username;

	@NotBlank(message = "O campo email não pode ser vazio.")
	private String email;

	@NotBlank(message = "O campo senha não pode ser vazio.")
	private String senha;

	public EstudanteDto() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnumTipoEscolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(EnumTipoEscolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "EstudanteDto [nome=" + nome + ", instituicao=" + instituicao + ", descricao=" + descricao + ", sexo="
				+ sexo + ", dataNascimento=" + dataNascimento + ", escolaridade=" + escolaridade + ", username="
				+ username + ", email=" + email + ", senha=" + senha + "]";
	}

}
